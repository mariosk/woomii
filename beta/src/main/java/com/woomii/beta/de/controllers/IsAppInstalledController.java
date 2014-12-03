/**
* <h1>WooMii Platform</h1>
* IsAppInstalledController: Controller for the IsAppInstalled API call
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
import com.woomii.beta.de.helpers.*;
import com.woomii.beta.de.params.ReqCommonParams;
import com.woomii.beta.de.params.RespErrorParams;
import com.woomii.beta.de.utils.WooMiiException;
import com.woomii.beta.de.utils.WooMiiUtils;
import com.woomii.beta.frontend.apps.Apps;

/**
 * Handles requests for the IsAppInstalled REST API call.
 */
@RequestMapping("isappinstalled")
@Controller
public class IsAppInstalledController {
	
	private static final Logger logger = LoggerFactory.getLogger(IsAppInstalledController.class);
    
	@RequestMapping(value = "/sandbox/", headers = "Accept=application/json", method = RequestMethod.PUT)
	public ResponseEntity<String> IsAppInstalledSandbox(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent) {
		return IsAppInstalled(jsonRequestBody, userAgent, true);
	}

	@RequestMapping(value = "/", headers = "Accept=application/json", method = RequestMethod.PUT)
    public ResponseEntity<String> IsAppInstalled(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent, boolean sandbox) {
		HttpHeaders headers = new HttpHeaders();
		RespErrorParams errorResponse = new RespErrorParams();
		
        try {        	        
        	ReqCommonParams params = WooMiiUtils.fromJson(jsonRequestBody, ReqCommonParams.class);
        	ResponseEntity<String> response = ControllersHelpers.CheckCommonParams(headers, errorResponse, sandbox, userAgent, params.getUuId(), params.getAppId());
        	if (response != null)
        		return response;
        	
        	Apps app = DatabaseHelpers.findAppByAppId(params.getAppId(), sandbox);
        	String fields[] = new String[1];
        	fields[0] = "app_installed";        	
        	                 			
	        EndUsers user = DatabaseHelpers.findEndUserByUUIDAndAPPId(params.getUuId(), app.getId(), sandbox);
	        if (user == null) {        	        	
	        	return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);
	        }
	        else {
	        	logger.debug(user.toString());
	        	/*
	        	 * 1. If [USERS.APP_INSTALLED == TRUE] then return INSTALLED = TRUE and exit.
	        	 */
	        	if (user.getApp_installed() == null || user.getApp_installed() == false) {                   			        
	        		/*
	        		 * 2. Else If there are no records in REFERRALS table where [(UUID != REFERRAL.UID_A) && (UUID != REFERRAL.UID_B)] then return INSTALLED = TRUE and exit. 
	        		 */
	        		user.setApp_installed(DatabaseHelpers.findReferralsByUID_AOrUID_B(params.getUuId(), app.getId(), sandbox));	        		
	        	}
	        }	        	        	        			        	
	        return new ResponseEntity<String>(WooMiiUtils.replaceBooleanWithBit(WooMiiUtils.toJsonString(user, fields), user.getApp_installed()), headers, HttpStatus.OK);
    	}
    	catch (WooMiiException ex) {
    		return WooMiiUtils.handleWooMiiException(ex, headers, errorResponse);
    	}        	                
    	catch (Exception ex) {
    		return WooMiiUtils.handleGenericException(ex, headers, errorResponse);
    	}       	        
    }		
}
