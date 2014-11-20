/**
* <h1>WooMii Platform</h1>
* InitializeController: Controller for the Initialize API call
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
import com.woomii.beta.de.params.requests.ReqInitializeParams;
import com.woomii.beta.de.params.responses.RespInitializeParams;
import com.woomii.beta.de.utils.WooMiiException;
import com.woomii.beta.de.utils.WooMiiUtils;

import java.util.List;

import com.woomii.beta.backend.endusers.EndUsers;
import com.woomii.beta.backend.referrals.Referrals;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.campaigns.Campaigns;
import com.woomii.beta.frontend.translations.Translations;
import com.woomii.beta.types.TransactionType;

/**
 * Handles requests for the Initialize REST API call.
 */
@RequestMapping("initialize")
@Controller
public class InitializeController {

	private static final Logger logger = LoggerFactory.getLogger(InitializeController.class);
	
	private static RespInitializeParams getInitializeResponse(ReqInitializeParams params, String userAgentB, RespErrorParams errorResponse, Apps app) throws Exception {				
		/*
		2. If [PIN == NULL] the server retrieves {CAMPAIGN_NAME, CAMPAIGN_ID, MOTTO, TERMS, COLOR} from CAMPAIGNS table using the APP_ID. 
		Also, the server should check against the location of the user, whether this campaign is enabled for this position or not!
		** Let’s consider the KML location framework whether it is convenient for the implementation. [Not Applicable for the Beta version]
		*/
		RespInitializeParams respParams = new RespInitializeParams();
		if (params.getPin() == null) {
			Campaigns cmp = DatabaseHelpers.findCampaignByAppId(app.getId());
			if (cmp == null) {
	        	errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_CAMPAIGN_NOT_FOUND.ordinal());
	        	return null;
	        }	        
			logger.debug(cmp.toString());
	        // fill the returned object from campaign
			respParams.setId(cmp.getId());
			respParams.setName(cmp.getName());
			respParams.setColor(cmp.getRgbcolor());
	        // for motto, terms and welcome message we need to use the 2 char code of the language.
	        // then we must search the translation by campaign id AND language id.
	        Translations translation = DatabaseHelpers.findTranslationsByLangIdAndCampaignId(cmp.getId(), params.getLang());
	        if (translation != null) {
	        	respParams.setMotto(translation.getMotto());
	        	respParams.setTerms(translation.getTerms());
	        	respParams.setWelcomeMsg(translation.getWelcome_msg());	        	
	        }
	        return respParams;
		}
		else {
			/* 3. Else If PIN is NOT NULL the server:
				a. Retrieves UID_A from PIN.
			*/
			EndUsers userA = DatabaseHelpers.findUserByPIN(params.getPin());
			if (userA == null) {
				errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_USER_NOT_FOUND.ordinal());
	        	return null;
			}
			String uidA = userA.getUuid();			
			/*
			 * b. Looks for an active CAMPAIGN in CAMPAIGNS table by using the APP_ID. 
			 * The CAMPAIGN_ID returned is used below to retrieve the record from REFERRALS.
			 */
			Campaigns cmp = DatabaseHelpers.findCampaignByAppId(app.getId());
			if (cmp == null) {
				errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_CAMPAIGN_NOT_FOUND.ordinal());
	        	return null;
			}
			Long cmpId = cmp.getId();
			/*
			 * c. Searches in the REFERRALS table for UID_A and UID_B (=EMPTY).
			 */
			List<Referrals> referrals = DatabaseHelpers.findReferralsByUID_A(uidA, cmpId, app.getId());
			if (referrals != null && referrals.size() > 0) {
				/*
				 * d. If it finds such a record, 
				 * it uses the {UID_A, CAMPAIGN_ID} to create a new record 
				 * in REFERRALS: Referral(UID_A, APP_ID, CAMPAIGN_ID, UID_B, UA_B). 
				 * This is actually the association between User-A and User-B.
				 */			
				DatabaseHelpers.makeAssociationOfUserAAndUserB(uidA, params.getUuId(), cmp, userAgentB, app);
			}
			/*
			 * e. IFF (CREDITS_EARN_AT_INSTALLATION_USER_B > 0) server:
			 *  
			 *  i. insert a new record in TRANSACTIONS table:
			 *	(CAMPAIGN_ID, APP_ID, UUID=UID_B, UUID=UID_B, INSTALLATION, CREDITS_EARNED=CREDITS_EARN_AT_INSTALLATION_USER_B)
			 *
			 *  The above record means that User-B collected for him/herself $CREDITS_EARN_AT_INSTALLATION_USER_B credits.
			 *
			 *	ii.	SUCH RECORDS SHOULD BE INSERTED ONLY ONCE!!! This means that prior inserting such a record a check for 
			 *	INSTALLATION && UID_B==UUID && UID_A==UUID should be done!
			 *
			 *	iii. Constructs a WELCOME_MSG according to the language that informs User-B about how many credits he/she just earned.
			 *
			 */
			
			if (cmp.getCredits_earn_at_installation_userb() > 0) {
				DatabaseHelpers.insertTransaction(params.getUuId(), params.getUuId(), cmp, app, cmp.getCredits_earn_at_installation_userb(), 0, TransactionType.INSTALLATION);
				Translations translation = DatabaseHelpers.findTranslationsByLangIdAndCampaignId(cmp.getId(), params.getLang());
		        if (translation != null) {
		        	respParams.setColor(cmp.getRgbcolor());
		        	respParams.setName(cmp.getName());
		        	respParams.setId(cmpId);
		        	respParams.setMotto(translation.getMotto());
		        	respParams.setTerms(translation.getTerms());
		        	respParams.setWelcomeMsg(translation.getWelcome_msg() + ":: YOU JUST EARNED " + cmp.getCredits_earn_at_installation_userb() + " CREDITS!");	        	
		        }
				
				return respParams;
			}							
			
			return null;
		}
    }    
	
	@RequestMapping(value = "/", headers = "Accept=application/json", method = RequestMethod.PUT)
    public ResponseEntity<String> Initialize(@RequestBody String jsonRequestBody, @RequestHeader(value="User-Agent") String userAgent) {
		HttpHeaders headers = new HttpHeaders();
        RespErrorParams errorResponse = new RespErrorParams();
        
        try {
        	ReqInitializeParams params = WooMiiUtils.fromJson(jsonRequestBody, ReqInitializeParams.class);
        	ResponseEntity<String> response = ControllersHelpers.CheckCommonParams(headers, errorResponse, userAgent, params.getUuId(), params.getAppId());
        	if (response != null)
        		return response;
        	        	        
        	Apps app = DatabaseHelpers.findAppByAppId(params.getAppId());
        	
	        RespInitializeParams resParams = getInitializeResponse(params, userAgent, errorResponse, app);
	        if (resParams == null) {        	        	
	        	return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);
	        }
	        else {	        		        	        			        	
	        	return new ResponseEntity<String>(WooMiiUtils.toJsonString(resParams), headers, HttpStatus.OK);
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
