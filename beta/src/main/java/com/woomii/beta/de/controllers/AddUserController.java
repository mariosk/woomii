/**
* <h1>WooMii Platform</h1>
* AddUserController: Controller for the AddUser API call
* <p>
* 1. This API call inserts the user in the USERS table and makes APP_INSTALLED = TRUE. 
* 	 If the user exists already no new change exist in the database.
* </p>
* <p>
* 2. A search in the CAMPAIGNS table should be done in order to find out whether there 
* 	 is an active campaign for the APP_ID. If there is not an active campaign then SHOW_PIN_POPUP = FALSE and exit.
* </p>
* <p>
* 3. Else if an active campaign exists then: SHOW_PIN_POPUP = TRUE.
* </p> 
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
import com.woomii.beta.de.params.ReqCommonParams;
import com.woomii.beta.de.params.RespErrorParams;
import com.woomii.beta.de.params.responses.RespAddUserParams;
import com.woomii.beta.de.utils.WooMiiException;
import com.woomii.beta.de.utils.WooMiiUtils;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.campaigns.Campaigns;
import com.woomii.beta.frontend.translations.Translations;

/**
 * Handles requests for the AddUser REST API call.
 */
@RequestMapping("adduser")
@Controller
public class AddUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(AddUserController.class);
    
	@RequestMapping(value = "/sandbox/", headers = "Accept=application/json", method = RequestMethod.PUT)
	public ResponseEntity<String> AddUserSandbox(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent) {		
		return AddUser(jsonRequestBody, userAgent, true);
	}
	
	@RequestMapping(value = "/", headers = "Accept=application/json", method = RequestMethod.PUT)
    public ResponseEntity<String> AddUser(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent, boolean sandbox) {
		HttpHeaders headers = new HttpHeaders();
        RespErrorParams errorResponse = new RespErrorParams();
                
        try {
            ReqCommonParams params = (ReqCommonParams) WooMiiUtils.fromJson(jsonRequestBody, ReqCommonParams.class);
        	ResponseEntity<String> response = ControllersHelpers.CheckCommonParams(headers, errorResponse, sandbox, userAgent, params.getUuId(), params.getAppId());
        	if (response != null)
        		return response;
    		        	   
        	Apps app = DatabaseHelpers.findAppByAppId(params.getAppId(), sandbox);
        	/*
        	 * 1. This API call inserts the user in the USERS table and makes APP_INSTALLED = TRUE. 
        	 * If the user exists already no new change exist in the database.
        	 */
	        DatabaseHelpers.insertUser(params.getUuId(), app, sandbox);
	        
	        /*
	         * 2. A search in the CAMPAIGNS table should be done in order to find out whether 
	         * there is an active campaign for the APP_ID. 
	         * If there is not an active campaign then SHOW_PIN_POPUP = FALSE and exit.
	         * 
	         */
	        RespAddUserParams respParams = new RespAddUserParams();
	        Campaigns cmp = DatabaseHelpers.findCampaignByAppId(app.getId(), sandbox);
			if (cmp == null) {
				respParams.setShowPINPopup(false);	
	        }
			else {
				logger.debug(cmp.toString());
				respParams.setShowPINPopup(true);
				Translations translation = DatabaseHelpers.findTranslationsByLangIdAndCampaignId(cmp.getId(), params.getLang(), sandbox);
		        if (translation != null) {
		        	respParams.setEnterPinMsg(translation.getEnter_pin_msg());
		        }		        
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
