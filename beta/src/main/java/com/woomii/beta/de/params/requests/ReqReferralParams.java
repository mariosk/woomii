/**
* <h1>WooMii Platform</h1>
* ReqReferralParams: HTTP Request Parameters for Referral API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params.requests;

import com.woomii.beta.de.params.ReqCommonParams;

public class ReqReferralParams extends ReqCommonParams {
	private String affId;
	private Long cmpId;
	private String uidB;
	private String uaB;
	private short suggestedFriends;
	
	public short getSuggestedFriends() {
		return suggestedFriends;
	}

	public void setSuggestedFriends(short suggestedFriends) {
		this.suggestedFriends = suggestedFriends;
	}

	public String getUaB() {
		return uaB;
	}

	public void setUaB(String uaB) {
		this.uaB = uaB;
	}

	public String getUidB() {
		return uidB;
	}

	public void setUidB(String uidB) {
		this.uidB = uidB;
	}

	public String getAffId() {
		return affId;
	}

	public void setAffId(String affId) {
		this.affId = affId;
	}

	public Long getCmpId() {
		return cmpId;
	}

	public void setCmpId(Long cmpId) {
		this.cmpId = cmpId;
	}
}
