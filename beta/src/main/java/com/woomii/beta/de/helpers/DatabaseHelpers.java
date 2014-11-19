/**
* <h1>WooMii Platform</h1>
* DatabaseHelpers: Helper functions for the Database Connections through Entity Managers.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.helpers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.woomii.beta.backend.endusers.EndUsers;
import com.woomii.beta.backend.impressions.Impressions;
import com.woomii.beta.backend.referrals.Referrals;
import com.woomii.beta.backend.transactions.Transactions;
import com.woomii.beta.de.controllers.IsAppInstalledController;
import com.woomii.beta.de.utils.WooMiiException;
import com.woomii.beta.de.utils.WooMiiUtils;
import com.woomii.beta.frontend.apps.Apps;
import com.woomii.beta.frontend.campaigns.Campaigns;
import com.woomii.beta.frontend.languages.Languages;
import com.woomii.beta.frontend.translations.Translations;
import com.woomii.beta.types.TransactionType;

/*
String hibernateQuery = "SELECT o FROM DataStreams o WHERE o.Channel = :Channel AND o.DeviceDT >= :fromTs AND o.DeviceDT <= :toTs GROUP BY Id ORDER BY o.DeviceDT";
return entityManager().createQuery(hibernateQuery, DataStreams.class).setParameter("Channel", channel).setParameter("fromTs", fromTs).setParameter("toTs", toTs).getResultList();
 */

public class DatabaseHelpers {
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseHelpers.class);

	/* AppId and CampaignId should be Long in all of the following helpers.
	 * This is a trick to prevent asking the database many times for Apps and Campaigns objects.
	 * It should be responsibility of the caller to discover these ids ONCE!
	 * 		Apps app = DatabaseHelpers.findAppByAppId(appId);
        if (app == null) {        	        	
        	throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_APPID_NOT_FOUND);
        }

	 */
	
	public static Apps findAppByAppId(String appId) throws Exception {
		try {
			Apps app = EndUsers.entityManager().createQuery("SELECT o FROM Apps o WHERE APP_ID = '" + appId + "'", Apps.class).getSingleResult();
			return app;
		} catch (Exception ex) {
			throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_APPID_NOT_FOUND);
		}		
	}
	
	public static boolean findTransactionByUID_AAndUID_BAndCampaignAndAPPId(String uidA, String uidB, Long cmpId, Long appId) throws Exception {		
        List<Transactions> transactions = Transactions.entityManager().createQuery("SELECT o FROM Transactions o WHERE UUID_A = '" + uidA + "' AND UUID_B = '" + uidB + "' AND APP_ID = '" + appId + "' AND CAMPAIGN_ID = '" + cmpId + "'", Transactions.class).getResultList();
        if (transactions == null || transactions.size() == 0) {
        	return false;
        }                
        return true;
	}
	
	public static List<Transactions> findTransactionsByUUIDAndAPPId(String uuId, Long appId) throws Exception {
        List<Transactions> transactions = Transactions.entityManager().createQuery("SELECT o FROM Transactions o WHERE UUID_A = '" + uuId + "' AND APP_ID = '" + appId + "'", Transactions.class).getResultList();
        if (transactions == null) {
        	throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_TRANSACTIONS_NOT_FOUND);
        }                
        return transactions;
	}
	
	public static EndUsers findEndUserByUUIDAndAPPId(String uuId, Long appId) throws Exception {    	
        EndUsers user = DatabaseHelpers.findUserByUuidAndAppId(uuId, appId);        
        if (user == null) {
        	throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_UUID_NOT_FOUND);
        }                
        return user;
    } 

	public static EndUsers findUserByUuidAndAppId(String uuId, Long appId) throws Exception {
		try {
			EndUsers users = EndUsers.entityManager().createQuery("SELECT o FROM EndUsers o WHERE UUID = '" + uuId + "' AND APP_ID = '" + appId + "'", EndUsers.class).getSingleResult();
			return users;
		} catch (Exception ex) {
			throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_UUID_NOT_FOUND);
		}		
	}

	public static String findPINByUser(String uuId) throws Exception {
		try {			
			EndUsers user = EndUsers.entityManager().createQuery("SELECT o FROM EndUsers o WHERE UUID = '" + uuId + "'", EndUsers.class).getSingleResult();
			return user.getPin();
		} catch (Exception ex) {
			throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_UUID_NOT_FOUND);
		} 
	}
	
	public static EndUsers findUserByPIN(String pin) throws Exception {
		try {
			EndUsers user = EndUsers.entityManager().createQuery("SELECT o FROM EndUsers o WHERE PIN = '" + pin + "'", EndUsers.class).getSingleResult();
			return user;
		} catch (Exception ex) {
			throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_UUID_NOT_FOUND);
		} 
	}

	public static float findRateByAppId(Apps app) throws Exception {
		try {			
			return app.getRate();
		} catch (Exception ex) {
			throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_APPID_NOT_FOUND);
		}		
	}
	
	public static Long[] findTotalCreditsEarned(String uuId, Long appId) throws Exception {
		try {
			Long[] credits = new Long[2];			
			Long totalCreditsEarned = Transactions.entityManager().createQuery("SELECT SUM(o.credits_earned) FROM Transactions o WHERE APP_ID = '" + appId + "' AND UUID_A = '" + uuId + "'", Long.class).getSingleResult();
			if (totalCreditsEarned == null)
				throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_TRANSACTION_NOT_FOUND);
			credits[0] = totalCreditsEarned;
			Long totalCreditsRedeemed = Transactions.entityManager().createQuery("SELECT SUM(o.credits_redeemed) FROM Transactions o WHERE APP_ID = '" + appId + "' AND UUID_A = '" + uuId + "'", Long.class).getSingleResult();
			if (totalCreditsRedeemed == null)
				throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_TRANSACTION_NOT_FOUND);
			credits[1] = totalCreditsRedeemed;
			return credits;
		} catch (Exception ex) {
			throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_TRANSACTION_NOT_FOUND);
		}		
	}
	
	public static String findUserAByUserB(String uidB, Long cmpId, Long appId) throws Exception {
		try {			
			Referrals ref = Referrals.entityManager().createQuery("SELECT o FROM Referrals o WHERE UUID_B = '" + uidB + "' AND UUID_A != '" + uidB + "' AND CAMPAIGN_ID = '" + cmpId + "' AND APP_ID = '" + appId + "'", Referrals.class).getSingleResult();
			return ref.getUuid_a();
		} catch (Exception ex) {
			throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_UUID_NOT_FOUND_IN_REFERRALS);
		} 
	}

	public static List<Referrals> findReferralsByUID_A(String uidA, Long cmpId, Long appId) throws Exception {    	
		List<Referrals> listReferrals = Referrals.entityManager().createQuery("SELECT o FROM Referrals o WHERE APP_ID = '" + appId + "' AND CAMPAIGN_ID = '" + cmpId + "' AND UUID_A = '" + uidA + "' AND UUID_B = ''", Referrals.class).getResultList();
		return listReferrals;			
	}
	
	public static boolean findReferralsByUID_AOrUID_B(String uuId, Long appId) throws Exception {
		List<Referrals> listReferrals;
		try {
			listReferrals = Referrals.entityManager().createQuery("SELECT o FROM Referrals o WHERE APP_ID = '" + appId + "' AND (UUID_A = '" + uuId + "' OR UUID_B = '" + uuId + "')", Referrals.class).getResultList();
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			listReferrals = null;
		}
		if (listReferrals == null || listReferrals.size() == 0)
			return false;
		else
			return true;
	}

	public static Referrals findReferralByUUIDAndAppIdAndCmpId(String uuId, Long app_id, Long cmpId) throws Exception {
		Referrals ref = Referrals.entityManager().createQuery("SELECT o FROM Referrals o WHERE APP_ID = '" + app_id + "' AND UUID_A = '" + uuId + "' AND CAMPAIGN_ID = '" + cmpId + "'", Referrals.class).getSingleResult();
		return ref;			
	}

	public static Impressions findImpressionByUUIDAndAppId(String uuId, Long appId) throws Exception {
		Impressions imp = Impressions.entityManager().createQuery("SELECT o FROM Impressions o WHERE APP_ID = '" + appId + "' AND UUID_A = '" + uuId + "'", Impressions.class).getSingleResult();
		return imp;			
	}
	
	public static Campaigns findCampaignByAppId(Long appId) throws Exception {
		// find only active campaign
		try {
			Campaigns campaign = Campaigns.entityManager().createQuery("SELECT o FROM Campaigns o WHERE APP_ID = '" + appId + "' AND STATUS = 'true'", Campaigns.class).getSingleResult();
			return campaign;
		}
		catch (Exception ex) {
			throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_CAMPAIGN_NOT_FOUND);
		}			
	}
	
	public static Languages findLanguageIdByLangName(String lang) throws Exception {			
		try {
			Languages language = Languages.entityManager().createQuery("SELECT o FROM Languages o WHERE CODE = '" + lang + "'", Languages.class).getSingleResult();		
			return language;
		}
		catch (Exception ex) {
			throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_LANG_NOT_FOUND);
		}
	}

	public static Translations findTranslationsByLangIdAndCampaignId(Long cmpId, String lang) throws Exception {
		Languages language = findLanguageIdByLangName(lang);
		try {						
			Translations translation = Translations.entityManager().createQuery("SELECT o FROM Translations o WHERE CAMPAIGN_ID = '" + cmpId + "' AND LANGUAGE_ID = '" + language.getId() + "'", Translations.class).getSingleResult();		
			return translation;		
		}
		catch (Exception ex) {
			throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_TRANSLATION_NOT_FOUND);
		}
	}    

	private static Referrals associationOfUserAAndUserB(String uidA, String uidB, Campaigns cmp, String uaB, Apps app) throws Exception {
		Referrals referral = new Referrals();
		referral.setApp(app);
		referral.setUuid_a(uidA);
		referral.setUuid_b(uidB);
		referral.setCampaign(cmp);
		referral.setUa_b(uaB);
		referral.setCreated(new Date());
		return referral;
	}
	
	public static void makeAssociationOfUserAAndUserB(String uidA, String uidB, Campaigns cmp, String uaB, Apps app) throws Exception {
		Referrals referral = associationOfUserAAndUserB(uidA, uidB, cmp, uaB, app);
		referral.merge();
	}

	public static void insertTransaction(String uidA, String uidB, Campaigns cmp, Apps app, int creditsEarned, int creditsRedeemed, TransactionType type) throws Exception {
		if (type == TransactionType.INSTALLATION) {
			//SUCH RECORDS SHOULD BE INSERTED ONLY ONCE from the same UID_A and UID_B in order to prevent attacks!!! 
			//This means that prior inserting such a record a check for INSTALLATION, APPID, CAMPAIGNID, UID_B==UUID && UID_A==UID_A should be done!
			if (findTransactionByUID_AAndUID_BAndCampaignAndAPPId(uidA, uidB, app.getId(), cmp.getId())) {
				throw new WooMiiException(WooMiiUtils.ERROR_CODES.ERROR_TRANSACTION_WITH_SAME_USERA_AND_USERB_FOUND);
			}
		}
		Transactions transaction = new Transactions();
		transaction.setCampaign(cmp);
		transaction.setApp(app);
		transaction.setUuid_a(uidA);
		transaction.setUuid_b(uidB);
		transaction.setType(type);
		transaction.setCredits_redeemed(creditsRedeemed);
		transaction.setCredits_earned(creditsEarned);			
		transaction.merge();
	}
	
	public static void insertUser(String uuId, Apps app) throws Exception {
		EndUsers user = new EndUsers();
		user.setApp(app);
		user.setUuid(uuId);
		user.setPin(WooMiiUtils.getRandomPIN());
		user.setApp_installed(true);
		user.merge();
	}

	public static void insertImpression(String uuId, Apps app, String affId, Campaigns cmp, boolean clicked) throws Exception {
		Impressions imp = null;
		try {
			imp = findImpressionByUUIDAndAppId(uuId, app.getId());
		}
		catch (Exception ex) {
			if (imp == null) {
				imp = new Impressions();	
			}
		}						
		imp.setApp(app);
		imp.setUuid_a(uuId);
		imp.setCampaign(cmp);
		imp.setClicked(clicked);
		imp.setCreated(new Date());
		imp.merge();
	}
	
	public static void insertReferral(String uuId, Apps app, String affId, Campaigns cmp, String uidB, String uaB, short suggestedFriends) throws Exception {
		Referrals ref = null;
		try {
			if (app != null) {
				ref = findReferralByUUIDAndAppIdAndCmpId(uuId, app.getId(), cmp.getId());
			}
		}
		catch (Exception ex) {
			if (ref == null) {
				ref = new Referrals();
			}
		}						
		ref.setAff_id(affId);
		ref.setApp(app);
		ref.setCampaign(cmp);
		ref.setCreated(new Date());
		ref.setSuggested_friends(suggestedFriends);
		ref.setUa_b(uaB);
		ref.setUuid_a(uuId);
		ref.setUuid_b(uidB);					
		ref.merge();			
	}
	
}
