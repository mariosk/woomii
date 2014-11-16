/**
* <h1>WooMii Platform</h1>
* ReqImpressionParams: HTTP Request Parameters for Impression API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params.requests;

import com.woomii.beta.de.params.ReqCommonParams;

public class ReqImpressionParams extends ReqCommonParams {
	private String affId;
	private Long cmpId;
	private boolean clicked;	
	
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

	public boolean getClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}	
}
