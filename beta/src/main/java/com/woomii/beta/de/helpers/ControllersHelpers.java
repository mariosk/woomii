/**
* <h1>WooMii Platform</h1>
* ControllersHelpers: Helper functions for the Web Controllers.
* Web Controllers handle the RequestMappings.
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.woomii.beta.de.params.RespErrorParams;
import com.woomii.beta.de.utils.WooMiiUtils;

public class ControllersHelpers {

	private static final Logger logger = LoggerFactory.getLogger(ControllersHelpers.class);
	
	public static ResponseEntity<String> CheckCommonParams(HttpHeaders headers, 
														   RespErrorParams errorResponse, 
														   String userAgent, 
														   String uuId, String appId) throws Exception {

		headers.add("Content-Type", "application/json");

        /* 1. If [UA is not either Android or iOS] then return an ERROR_STR that this URL works ONLY in a mobile device (android or iOS). */
        if (!userAgent.contains(WooMiiUtils.ANDROID_UA) && !userAgent.contains(WooMiiUtils.IPHONE_UA)) {
    		errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_USER_AGENT_NOT_SUPPORTED.ordinal());
        	return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);
        }        

		if (!WooMiiUtils.isStringValid(uuId) || !WooMiiUtils.isStringValid(appId))			
			return new ResponseEntity<String>(headers, HttpStatus.UNPROCESSABLE_ENTITY);
		
		logger.debug("Welcome! The client requested with params: UUID:{}, APPID:{}", uuId, appId);
		
		return null;
	}
}
