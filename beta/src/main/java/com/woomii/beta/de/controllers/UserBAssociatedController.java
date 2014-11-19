/**
* <h1>WooMii Platform</h1>
* UserBAssociatedController: Controller for the UserBAssociated API call
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

import com.woomii.beta.backend.endusers.EndUsers;
import com.woomii.beta.de.helpers.ControllersHelpers;
import com.woomii.beta.de.helpers.DatabaseHelpers;
import com.woomii.beta.de.params.RespCommonParams;
import com.woomii.beta.de.params.RespErrorParams;
import com.woomii.beta.de.params.requests.ReqUserBAssociatedParams;
import com.woomii.beta.de.utils.WooMiiException;
import com.woomii.beta.de.utils.WooMiiUtils;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.campaigns.Campaigns;
import com.woomii.beta.types.TransactionType;

/**
 * Handles requests for the AddUser REST API call.
 */
@RequestMapping("userbassociated")
@Controller
public class UserBAssociatedController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserBAssociatedController.class);
    
	@RequestMapping(value = "/", headers = "Accept=application/json", method = RequestMethod.PUT)
    public ResponseEntity<String> UserBAssociated(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent) {
		HttpHeaders headers = new HttpHeaders();
        RespErrorParams errorResponse = new RespErrorParams();
                
        try {        	
            ReqUserBAssociatedParams params = (ReqUserBAssociatedParams) WooMiiUtils.fromJson(jsonRequestBody, ReqUserBAssociatedParams.class);
        	ResponseEntity<String> response = ControllersHelpers.CheckCommonParams(headers, errorResponse, userAgent, params.getUuId(), params.getAppId());
        	if (response != null)        	
        		return response;
    		        	         
        	Apps app = DatabaseHelpers.findAppByAppId(params.getAppId());
        	/*
        	 * 1. Retrieves UID_A from PIN.
        	 */
    		EndUsers userA = DatabaseHelpers.findUserByPIN(params.getPin());
    		if (userA == null) {
    			errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_USER_NOT_FOUND.ordinal());
				return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);
    		}
    		logger.debug(userA.toString());    	
    		String uidA = userA.getUuid();
    		
    		/*
        	 * 2. IFF (CREDITS_EARN_AT_INSTALLATION_USER_A > 0), server will:
			 *	i. insert a new record in TRANSACTIONS table:
			 *	(CAMPAIGN_ID, APP_ID, UID_A, UUID (=UID_B), INSTALLATION, 
			 *	CREDITS_EARNED=CREDITS_EARN_AT_INSTALLATION_USER_A)
			 *	*** The above record means that User-B collected for User-A $CREDITS_EARN_AT_INSTALLATION_USER_A credits.
			 *				
			 * ii. SUCH RECORDS SHOULD BE INSERTED ONLY ONCE!!! This means that prior inserting such a record a check for INSTALLATION && UID_B==UUID && UID_A==UID_A should be done!
			 * iii. sends a PUSH NOTIFICATION to UID_A $PUSH_MSG_AFTER_INSTALLATION (e.g.“You just earned $CREDITS_EARN_AT_INSTALLATION_USER_A credits from a friend who installed the APP”).
        	 */
    		Campaigns cmp = DatabaseHelpers.findCampaignByAppId(app.getId());
			if (cmp == null) {
				errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_CAMPAIGN_NOT_FOUND.ordinal());
				return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);
			}
			
			if (cmp.getCredits_earn_at_installation_usera() > 0) {
				DatabaseHelpers.insertTransaction(uidA, params.getUuId(), cmp, app, cmp.getCredits_earn_at_installation_usera(), 0, TransactionType.INSTALLATION);
			}				        	        
			
			RespCommonParams respParams = new RespCommonParams();
			respParams.setStatus(WooMiiUtils.STATUS_CODES.OK.name());
			
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
