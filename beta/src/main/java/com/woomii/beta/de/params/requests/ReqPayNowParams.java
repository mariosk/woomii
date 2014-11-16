/**
* <h1>WooMii Platform</h1>
* ReqPayNowParams: HTTP Request Parameters for PayNow API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params.requests;

import com.woomii.beta.de.params.ReqCommonParams;

public class ReqPayNowParams extends ReqCommonParams {	
	private int creditsNeeded;

	public int getCreditsNeeded() {
		return creditsNeeded;
	}

	public void setCreditsNeeded(int creditsNeeded) {
		this.creditsNeeded = creditsNeeded;
	}	
}
