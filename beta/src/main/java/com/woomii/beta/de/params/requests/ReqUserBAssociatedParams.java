/**
* <h1>WooMii Platform</h1>
* ReqUserBAssociatedParams: HTTP Request Parameters for UserBAssociated API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params.requests;

import com.woomii.beta.de.params.ReqCommonParams;

public class ReqUserBAssociatedParams extends ReqCommonParams {
	private Long cmpId;
	private String pin;

	public Long getCmpId() {
		return cmpId;
	}

	public void setCmpId(Long cmpId) {
		this.cmpId = cmpId;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}	
}
