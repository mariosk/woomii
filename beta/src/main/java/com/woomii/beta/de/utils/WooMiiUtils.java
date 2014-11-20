/**
* <h1>WooMii Platform</h1>
* WooMiiUtils: Custom utilities for WooMii.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;

import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import org.apache.commons.lang3.RandomStringUtils;

import com.woomii.beta.de.params.RespErrorParams;

public class WooMiiUtils {
    
	public static final String WooMii_SERVER_URI = "http://zulu748.startdedicated.net:8080";
	/*
     * Android User-Agent example: Mozilla/5.0 (Linux; U; Android 2.3.3; zh-tw; HTC_Pyramid Build/GRI40) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1
     */
	public static final String ANDROID_UA = "Android";
    /*
     * iOS User-Agent example: Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) CriOS/30.0.1599.12 Mobile/11A465 Safari/8536.25 (3B92C18B-D9DE-4CB7-A02A-22FD2AF17C8F)
     * 						   Mozilla/5.0 (iPhone; CPU iPhone OS 6_1_4 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10B350 Safari/8536.25
     */
	public static final String IPHONE_UA = "iPhone";	
	
	public static enum STATUS_CODES {
		OK
	}
		
	public static enum ERROR_CODES {
		ERROR_UUID_NOT_FOUND_IN_REFERRALS,
		ERROR_UUID_NOT_FOUND,
		ERROR_APPID_NOT_FOUND,
		ERROR_TRANSACTION_WITH_SAME_USERA_AND_USERB_FOUND,
		ERROR_TRANSACTION_NOT_FOUND,
		ERROR_TRANSACTIONS_NOT_FOUND,
		ERROR_CAMPAIGN_NOT_FOUND,
		ERROR_RATE_NOT_VALID,
		ERROR_LANG_NOT_FOUND,
		ERROR_TRANSLATION_NOT_FOUND,
		ERROR_USER_NOT_FOUND,
		ERROR_USER_AGENT_NOT_SUPPORTED,
		ERROR_CREDITS_EARNED_LESS_THAN_REDEEMED,
		ERROR_CREDITS_NOT_ENOUGH_TO_REDEEM,
		ERROR_OTHER
	}
	
	public static String getRandomPIN() {
		return RandomStringUtils.randomAlphanumeric(6);
	}
	
	public static String replaceBooleanWithBit(String key, boolean value) {
		return key.replace(value ? "true" : "false", value ? "1" : "0");
	}
	
	public static boolean isStringValid(String value) {
		return (value != null && value.trim().length() != 0);
	}
		
	public static <T> T fromJson(String json, Class<T> classOfT) {
		return new JSONDeserializer<T>().use(null, classOfT).deserialize(json);
	}

	public static String toJsonString(Object obj, boolean deepSerialize) {
		if (deepSerialize)
			return new JSONSerializer().exclude("*.class").deepSerialize(obj);
		else
			return toJsonString(obj); 
	}

	public static String toJsonString(Object obj) {
		return new JSONSerializer().exclude("*.class").serialize(obj);
	}
	
	public static String toJsonString(Object obj, String[] fields) {
		return new JSONSerializer().include(fields).exclude("*").serialize(obj);
	}
	
    public static String jsonResult(String jsonString, String callback) {        
        if (callback != null && !callback.equals("null")) {
            String jsonPString = callback + "( { items:" + jsonString + " } )";
            return jsonPString;        	
        }
        return jsonString;
    }

    public static void binderInitilization(WebDataBinder binder) {
    	binder.registerCustomEditor(String.class, null);    	
    }
    
    public static ResponseEntity<String> handleGenericException(Exception ex, HttpHeaders headers, RespErrorParams errorResponse) {
    	errorResponse.seterrC(WooMiiUtils.ERROR_CODES.ERROR_OTHER.ordinal());
		if (ex.getCause() instanceof PersistenceException) {
			PersistenceException exJpa = (PersistenceException)ex.getCause();
			if (exJpa.getCause() instanceof ConstraintViolationException) {
				ConstraintViolationException exJpaCause = (ConstraintViolationException)exJpa.getCause();
				if (exJpaCause.getCause() instanceof PSQLException) {
					PSQLException psqlCause = (PSQLException)exJpaCause.getCause();
					errorResponse.seterrS(psqlCause.getMessage());	
				}    				
			}
		}
		else {
			errorResponse.seterrS(ex.getMessage());
		}
		return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);    	
    }
    
    public static ResponseEntity<String> handleWooMiiException(WooMiiException ex, HttpHeaders headers, RespErrorParams errorResponse) {
    	errorResponse.seterrC(ex.getErrorCode().ordinal());    		
    	return new ResponseEntity<String>(WooMiiUtils.toJsonString(errorResponse), headers, HttpStatus.BAD_REQUEST);
    }

}
