/**
* <h1>WooMii Platform</h1>
* ViewCreditsHistoryController: Controller for the ViewCreditsHistory API call
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.joda.time.DateTime;
import org.joda.time.Days;
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

import com.woomii.beta.backend.transactions.Transactions;
import com.woomii.beta.de.helpers.ControllersHelpers;
import com.woomii.beta.de.helpers.DatabaseHelpers;
import com.woomii.beta.de.params.ReqCommonParams;
import com.woomii.beta.de.params.RespErrorParams;
import com.woomii.beta.de.params.responses.RespViewCreditsHistoryParams;
import com.woomii.beta.de.utils.CreditsValues;
import com.woomii.beta.de.utils.WooMiiException;
import com.woomii.beta.de.utils.WooMiiUtils;
import com.woomii.beta.de.utils.TransactionsHistory;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.campaigns.Campaigns;

/**
 * Handles requests for the AddUser REST API call.
 */
@RequestMapping("viewcreditshistory")
@Controller
public class ViewCreditsHistoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(ViewCreditsHistoryController.class);
    
	@RequestMapping(value = "/sandbox/", headers = "Accept=application/json", method = RequestMethod.PUT)
	public ResponseEntity<String> ViewCreditsHistorySandbox(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent) {
		return ViewCreditsHistory(jsonRequestBody, userAgent, true);
	}
	
	@RequestMapping(value = "/", headers = "Accept=application/json", method = RequestMethod.PUT)
    public ResponseEntity<String> ViewCreditsHistory(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent, boolean sandbox) {
		HttpHeaders headers = new HttpHeaders();
        RespErrorParams errorResponse = new RespErrorParams();
                
        try {
            ReqCommonParams params = (ReqCommonParams) WooMiiUtils.fromJson(jsonRequestBody, ReqCommonParams.class);
        	ResponseEntity<String> response = ControllersHelpers.CheckCommonParams(headers, errorResponse, sandbox, userAgent, params.getUuId(), params.getAppId());
        	if (response != null)
        		return response;

        	Apps app = DatabaseHelpers.findAppByAppId(params.getAppId(), sandbox);
        	/*
        	 * 1. Search in TRANSACTIONS table and find the 
        	 * LAST 10 Transactions(APP_ID, UID_A=UUID). The fields that would be returned are:
        	 * ARRAY_OF_RESULTS	: {DATETIME|CREDITS_EARNED|CREDITS_REDEEMED|DAYS_TO_EXPIRE}
        	 * TOTAL_CREDITS	: Total credits left so far (EARNED-REDEEMED)
        	 */
        	
    		Collection<Transactions> transactions = DatabaseHelpers.findTransactionsByUUIDAndAPPId(params.getUuId(), app.getId(), sandbox);
    		if (transactions != null) {
    			RespViewCreditsHistoryParams respParams = new RespViewCreditsHistoryParams();
    			// traverse all transactions to fill properly the TransactionsHistory list with values for the JSON string.
    			Collection<TransactionsHistory> itemsForJson = new ArrayList<TransactionsHistory>();
	        	Iterator<Transactions> i = transactions.iterator();	        	
	        	while(i.hasNext()) {
	        		Transactions trans = i.next();
	        		logger.debug(trans.toString());
	        		TransactionsHistory item = new TransactionsHistory();	        		
	        		item.setCreated(trans.getCreated());
	        		item.setCreditsEarned(trans.getCredits_earned());
	        		item.setCreditsRedeemed(trans.getCredits_redeemed());	        		
	        		Campaigns cmp = trans.getCampaign();
	        		if (cmp != null) {
	        			if (cmp.getCredits_expiration_date() != null) {
			        		DateTime startDate = new DateTime();
			        		DateTime endDate = new DateTime(cmp.getCredits_expiration_date());
                            Days d = Days.daysBetween(startDate, endDate);                            
                            if (d.getDays() <= 0) {
                                // these credits have been expired! they will be removed from the worked thread.
                                // let's do not send them back to the framework.
                                continue;
                            }
			        		item.setDaysToExpire(d.getDays());
	        			}
	        			else {
	        				// do not expire
	        				item.setDaysToExpire(Integer.MAX_VALUE);
	        			}
	        		}
        			else {
        				// do not expire
        				item.setDaysToExpire(Integer.MAX_VALUE);
        			}	        		
	        		itemsForJson.add(item);	        		
	        	}	        		        
				respParams.setTransactions(itemsForJson);
				
				// find the totalCreditsLeft to set this value as well.
				CreditsValues credits = DatabaseHelpers.findTotalCreditsEarned(params.getUuId(), app.getId(), sandbox);				
				if (credits.getCreditsLeft() < 0) {
					errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_CREDITS_EARNED_LESS_THAN_REDEEMED.ordinal());
			        return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);
				}
				respParams.setTotalCreditsLeft(credits.getCreditsLeft());
				String[] fields = new String[1];
				fields[0] = "TransactionsHistory";
				return new ResponseEntity<String>(WooMiiUtils.toJsonString(respParams, true), headers, HttpStatus.OK);				
    		}    						
			
    		errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_TRANSACTIONS_NOT_FOUND.ordinal());
	        return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);
    	}
    	catch (WooMiiException ex) {
    		return WooMiiUtils.handleWooMiiException(ex, headers, errorResponse);
    	}        	                
    	catch (Exception ex) {
    		return WooMiiUtils.handleGenericException(ex, headers, errorResponse);
    	}        	        
    }		
}
