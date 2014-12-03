/**
* <h1>WooMii Platform</h1>
* ReferralController: Controller for the Referral API call
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
import com.woomii.beta.de.params.requests.ReqReferralParams;
import com.woomii.beta.de.params.responses.RespReferralParams;
import com.woomii.beta.de.utils.WooMiiException;
import com.woomii.beta.de.utils.WooMiiUtils;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.campaigns.Campaigns;
import com.woomii.beta.frontend.translations.Translations;

/**
 * Handles requests for the AddUser REST API call.
 */
@RequestMapping("referral")
@Controller
public class ReferralController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReferralController.class);
    
	@RequestMapping(value = "/sandbox/", headers = "Accept=application/json", method = RequestMethod.PUT)
	public ResponseEntity<String> ReferralSandbox(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent) {
		return Referral(jsonRequestBody, userAgent, true);
	}
	
	@RequestMapping(value = "/", headers = "Accept=application/json", method = RequestMethod.PUT)
    public ResponseEntity<String> Referral(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent, boolean sandbox) {
		HttpHeaders headers = new HttpHeaders();
        RespErrorParams errorResponse = new RespErrorParams();
                
        try {
            ReqReferralParams params = (ReqReferralParams) WooMiiUtils.fromJson(jsonRequestBody, ReqReferralParams.class);
        	ResponseEntity<String> response = ControllersHelpers.CheckCommonParams(headers, errorResponse, sandbox, userAgent, params.getUuId(), params.getAppId());
        	if (response != null)
        		return response;
    		    
        	Apps app = DatabaseHelpers.findAppByAppId(params.getAppId(), sandbox);
        	/*
        	 * 1. This API call inserts a record in REFERRALS table generating also the timestamp that this referral took place.
        	 * 2. When User-A clicks on SEND, Referral API call inserts a record in REFERRALS table with empty UID_B.
        	 */
    		Campaigns cmp = DatabaseHelpers.findCampaignByAppId(app.getId(), sandbox);
			if (cmp == null) {
				errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_CAMPAIGN_NOT_FOUND.ordinal());
				return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);
			}
			logger.debug(cmp.toString());
	        DatabaseHelpers.insertReferral(params.getUuId(), 
	        							   app, 
	        							   params.getAffId(), 
	        							   cmp, 
	        							   params.getUidB(), 
	        							   params.getUaB(), 
	        							   params.getSuggestedFriends(),
	        							   sandbox);

	        RespReferralParams respParams = new RespReferralParams();
	        Translations translation = DatabaseHelpers.findTranslationsByLangIdAndCampaignId(cmp.getId(), params.getLang(), sandbox);
	        if (translation != null) {
	        	respParams.setReferralDoneMsg(translation.getReferral_done_msg());
	        }
	        	        	        	        	        	        	        	        			     
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
