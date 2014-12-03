/**
* <h1>WooMii Platform</h1>
* PaymentInitController: Controller for the PaymentInit API call
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
import com.woomii.beta.de.params.requests.ReqPaymentInitParams;
import com.woomii.beta.de.params.responses.RespPaymentInitParams;
import com.woomii.beta.de.utils.CreditsValues;
import com.woomii.beta.de.utils.WooMiiException;
import com.woomii.beta.de.utils.WooMiiUtils;
import com.woomii.beta.frontend.apps.Apps;

/**
 * Handles requests for the AddUser REST API call.
 */
@RequestMapping("paymentinit")
@Controller
public class PaymentInitController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentInitController.class);
    
	@RequestMapping(value = "/sandbox/", headers = "Accept=application/json", method = RequestMethod.PUT)
	public ResponseEntity<String> PaymentInitSandbox(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent) {
		return PaymentInit(jsonRequestBody, userAgent, true);
	}
	
	@RequestMapping(value = "/", headers = "Accept=application/json", method = RequestMethod.PUT)
    public ResponseEntity<String> PaymentInit(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent, boolean sandbox) {
		HttpHeaders headers = new HttpHeaders();
        RespErrorParams errorResponse = new RespErrorParams();
                
        try {
            ReqPaymentInitParams params = (ReqPaymentInitParams) WooMiiUtils.fromJson(jsonRequestBody, ReqPaymentInitParams.class);
        	ResponseEntity<String> response = ControllersHelpers.CheckCommonParams(headers, errorResponse, sandbox, userAgent, params.getUuId(), params.getAppId());
        	if (response != null)
        		return response;
    		        	    
        	Apps app = DatabaseHelpers.findAppByAppId(params.getAppId(), sandbox);
        	/*
			 * 1. Find the rate of the Currency and Credits, from the APPS table by using the APP_ID.
        	 */
    		float rate = DatabaseHelpers.findRateByAppId(app);
    		if (rate <= 0) {
    			errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_RATE_NOT_VALID.ordinal());
				return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);
    		}
    		/*
    		 * 2. Search in TRANSACTIONS table with APP_ID and UID_A==UUID.
    		 */
    		CreditsValues credits = DatabaseHelpers.findTotalCreditsEarned(params.getUuId(), app.getId(), sandbox);
    		    	
    		/*
    		 * 3. CREDITS_NEEDED = APP_COST/RATE
    		 */
    		long creditsNeeded = (long) (params.getAppCost()/rate);
    		logger.debug("creditsNeeded = " + creditsNeeded);
    		    		
    		/*
    		 * 6. If the CREDITS_NEEDED <= (CREDITS_EARNED – CREDITS_REDEEMED) then REDEEM_ELIGIBLE = TRUE, else REDEEM_ELIGIBLE = FALSE.
    		 */    		 
    		RespPaymentInitParams respParams = new RespPaymentInitParams();    		
    		if (creditsNeeded <= credits.getCreditsLeft()) {
    			respParams.setCreditsEarned(credits.getCreditsEarned());
    			respParams.setCreditsRedeemed(credits.getCreditsRedeemed());
    			respParams.setRedeemEligible(true);    			    			
    			respParams.setCreditsNeeded(creditsNeeded);
    			return new ResponseEntity<String>(WooMiiUtils.toJsonString(respParams), headers, HttpStatus.OK);
    		}    		
    		else {
    			String[] fields = new String[1];
    			fields[0] = "redeemEligible";
    			respParams.setRedeemEligible(false);
    			return new ResponseEntity<String>(WooMiiUtils.toJsonString(respParams, fields), headers, HttpStatus.OK);
    		}    		    																						       
    	}
    	catch (WooMiiException ex) {
    		return WooMiiUtils.handleWooMiiException(ex, headers, errorResponse);
    	}        	                
    	catch (Exception ex) {
    		return WooMiiUtils.handleGenericException(ex, headers, errorResponse);
    	}        	        
    }		
}
