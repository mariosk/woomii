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
import com.woomii.beta.de.params.requests.ReqImpressionParams;
import com.woomii.beta.de.params.responses.RespImpressionParams;
import com.woomii.beta.de.utils.WooMiiException;
import com.woomii.beta.de.utils.WooMiiUtils;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.campaigns.Campaigns;

/**
 * Handles requests for the AddUser REST API call.
 */
@RequestMapping("impression")
@Controller
public class ImpressionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImpressionController.class);
    
	@RequestMapping(value = "/", headers = "Accept=application/json", method = RequestMethod.PUT)
    public ResponseEntity<String> Impression(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent) {
		HttpHeaders headers = new HttpHeaders();
        RespErrorParams errorResponse = new RespErrorParams();
                
        try {        	
            ReqImpressionParams params = (ReqImpressionParams) WooMiiUtils.fromJson(jsonRequestBody, ReqImpressionParams.class);
        	ResponseEntity<String> response = ControllersHelpers.CheckCommonParams(headers, errorResponse, userAgent, params.getUuId(), params.getAppId());
        	if (response != null)
        		return response;
    		        	                 	
        	Apps app = DatabaseHelpers.findAppByAppId(params.getAppId());
        	/*
        	 * 1. This API call inserts a record in IMPRESSIONS table generating also the timestamp that this impression took place. 
        	 */
    		Campaigns cmp = DatabaseHelpers.findCampaignByAppId(app.getId());
			if (cmp == null) {
				errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_CAMPAIGN_NOT_FOUND.ordinal());
				return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);
			}
			logger.debug(cmp.toString());
	        DatabaseHelpers.insertImpression(params.getUuId(), app, params.getAffId(), cmp, params.getClicked());

	        /*
	         * 2. If (CLICKED==TRUE) then server should construct the URL that will be send to User-B from User-A. 
	         * An example is: http://www.woomiiserver.com/clicknwin?appid=123456&pin=A1B2C3
	         * clicknwin = CAMPAIGN NAME retrieved by CAMPAIGN_ID.
	         * appid = APP_ID
	         * pin = compressed UID_A 
	         */
	        RespImpressionParams respParams = new RespImpressionParams();
	        if (params.getClicked()) {		        
		        String pin = DatabaseHelpers.findPINByUser(params.getUuId());
	        	String cmpName = cmp.getName();
	        	respParams.setUrl(WooMiiUtils.WooMii_SERVER_URI + "/" + cmpName + "?appid=" + params.getAppId() + "&pin=" + pin);		        
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
