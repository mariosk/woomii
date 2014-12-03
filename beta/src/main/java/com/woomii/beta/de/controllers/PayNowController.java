/**
* <h1>WooMii Platform</h1>
* PayNowController: Controller for the PayNow API call
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
import com.woomii.beta.de.params.RespCommonParams;
import com.woomii.beta.de.params.RespErrorParams;
import com.woomii.beta.de.params.requests.ReqPayNowParams;
import com.woomii.beta.de.utils.CreditsValues;
import com.woomii.beta.de.utils.WooMiiException;
import com.woomii.beta.de.utils.WooMiiUtils;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.types.TransactionType;

/**
 * Handles requests for the AddUser REST API call.
 */
@RequestMapping("paynow")
@Controller
public class PayNowController {
	
	private static final Logger logger = LoggerFactory.getLogger(PayNowController.class);
    
	@RequestMapping(value = "/sandbox/", headers = "Accept=application/json", method = RequestMethod.PUT)
	public ResponseEntity<String> PayNowSandbox(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent) {
		return PayNow(jsonRequestBody, userAgent, true);
	}
		
	@RequestMapping(value = "/", headers = "Accept=application/json", method = RequestMethod.PUT)
    public ResponseEntity<String> PayNow(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent, boolean sandbox) {
		HttpHeaders headers = new HttpHeaders();
        RespErrorParams errorResponse = new RespErrorParams();
                
        try {
            ReqPayNowParams params = (ReqPayNowParams) WooMiiUtils.fromJson(jsonRequestBody, ReqPayNowParams.class);
        	ResponseEntity<String> response = ControllersHelpers.CheckCommonParams(headers, errorResponse, sandbox, userAgent, params.getUuId(), params.getAppId());
        	if (response != null)
        		return response;

        	Apps app = DatabaseHelpers.findAppByAppId(params.getAppId(), sandbox);
        	
        	/* 
        	 * Make sure there are enough credits to pay this order
        	 */
        	CreditsValues credits = DatabaseHelpers.findTotalCreditsEarned(params.getUuId(), app.getId(), sandbox);
        	if (credits.getCreditsLeft() < params.getCreditsNeeded()) {
        		logger.debug("NOT ENOUGH CREDITS TO REDEEM! PAYMENT OF " + params.getCreditsNeeded() + " CANNOT BE DONE FOR UUID = " + params.getUuId() + " APPID = " + params.getAppId() + "CREDITS: " + credits.toString());
        		throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_CREDITS_NOT_ENOUGH_TO_REDEEM);
        	}
        	/*
        	 * 1. Insert a new record in TRANSACTIONS table with 
        	 * APP_ID, UID_A=UUID, CAMPAIGN_ID=NULL, TYPE=REDEEM, CREDITS_EARNED=0, CREDITS_REDEEMED=CREDITS_NEEDED. 
        	 * This API call will have 3 delegates (onPaySuccess, onPayFailure, onPayCancel) and the developer is 
        	 * responsible to implement actions/show messages inside each delegate.
        	 */        	
    		DatabaseHelpers.insertTransaction(params.getUuId(), 
    										  null, 
    										  null, 
    										  app, 
    										  0, 
    										  params.getCreditsNeeded(), 
    										  TransactionType.REDEMPTION,
    										  sandbox);
    		logger.debug("PAYMENT OF " + params.getCreditsNeeded() + " COMPLETED FOR UUID = " + params.getUuId() + " APPID = " + params.getAppId());
    		
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
