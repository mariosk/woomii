/**
* <h1>WooMii Platform</h1>
* DonateUserAController: Controller for the DonateUserA API call
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.woomii.beta.de.helpers.ControllersHelpers;
import com.woomii.beta.de.helpers.DatabaseHelpers;
import com.woomii.beta.de.params.RespErrorParams;
import com.woomii.beta.de.params.requests.ReqDonateUserAParams;
import com.woomii.beta.de.params.responses.RespDonateUserAParams;
import com.woomii.beta.de.utils.WooMiiException;
import com.woomii.beta.de.utils.WooMiiUtils;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.campaigns.Campaigns;
import com.woomii.beta.frontend.translations.Translations;
import com.woomii.beta.types.TransactionType;

/**
 * Handles requests for the AddUser REST API call.
 */
@RequestMapping("donateusera")
@Controller
public class DonateUserAController {
	
	private static final Logger logger = LoggerFactory.getLogger(DonateUserAController.class);
    
	@RequestMapping(value = "/sandbox/", headers = "Accept=application/json", method = RequestMethod.PUT)
	public ResponseEntity<String> AddUserSandbox(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent) {
		return DonateUserA(jsonRequestBody, userAgent, true);
	}
	
	@RequestMapping(value = "/", headers = "Accept=application/json", method = RequestMethod.PUT)
    public ResponseEntity<String> DonateUserA(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent, boolean sandbox) {
		HttpHeaders headers = new HttpHeaders();
        RespErrorParams errorResponse = new RespErrorParams();
                
        try {
            ReqDonateUserAParams params = (ReqDonateUserAParams) WooMiiUtils.fromJson(jsonRequestBody, ReqDonateUserAParams.class);
        	ResponseEntity<String> response = ControllersHelpers.CheckCommonParams(headers, errorResponse, sandbox, userAgent, params.getUuId(), params.getAppId());
        	if (response != null)
        		return response;

        	Apps app = DatabaseHelpers.findAppByAppId(params.getAppId(), sandbox);
        	/*
        	 	1. Find out who is User-A that User-B (UUID) should donate. This can be done from table REFERRALS. 
            	A search on the table for UID_B==UUID and UID_A!=UUID. This record contains UID_A that is to be donated.
            	One record should be found here. If more than one is found it seems we have been attacked.
        	 */
    		String uidA = DatabaseHelpers.findUserAByUserB(params.getUuId(), params.getCmpId(), app.getId(), sandbox);
    		if (uidA == null) {
    			errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_USER_NOT_FOUND.ordinal());
				return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);    			
    		}    		
    		/*
    		 * 2. Search in CAMPAIGNS table for the value of the CREDITS_EARN_AT_TRANSACTION.
    		 */    		
    		Campaigns cmp = DatabaseHelpers.findCampaignByAppId(app.getId(), sandbox);
			if (cmp == null) {
				errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_CAMPAIGN_NOT_FOUND.ordinal());
				return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);        	
			}
			logger.debug(cmp.toString());
			
			/*
			 * 3. Insert a new record in TRANSACTIONS table: (CAMPAIGN_ID, APP_ID, UID_A, UUID=UID_B, DONATION, CREDITS_EARNED=CREDITS_EARN_AT_TRANSACTION)
			 */
			DatabaseHelpers.insertTransaction(uidA, params.getUuId(), cmp, app, cmp.getCredits_earn_at_transaction(), 0, TransactionType.DONATION, sandbox);
						
			/*
			 * 4. Constructs a DONATION_MSG according to the language that informs User-B about how many credits User-A just earned.
			 */
			RespDonateUserAParams respParams = new RespDonateUserAParams();
			Translations translation = DatabaseHelpers.findTranslationsByLangIdAndCampaignId(params.getCmpId(), params.getLang(), sandbox);
			if (translation != null) {
				respParams.setDonationMsg(translation.getDonation_msg());				
			}
		        
            /*
             * 5. Sends a PUSH NOTIFICATION to UID_A: “You just earned $CREDITS_EARN_AT_TRANSACTION credits from a friend who made an order”.
             */
	        return new ResponseEntity<String>(WooMiiUtils.toJsonString(respParams), headers, HttpStatus.OK);
    	}
    	catch (WooMiiException ex) {
    		return WooMiiUtils.handleWooMiiException(ex, headers, errorResponse);
    	}        	                
    	catch (Exception ex) {
    		return WooMiiUtils.handleGenericException(ex, headers, errorResponse);
    	}        	        
    }		
}
