/**
* <h1>WooMii Platform</h1>
* ReqDonateUserAParams: HTTP Request Parameters for DonateUserA API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params.requests;

import com.woomii.beta.de.params.ReqCommonParams;

public class ReqDonateUserAParams extends ReqCommonParams {
	private Long cmpId;

	public Long getCmpId() {
		return cmpId;
	}

	public void setCmpId(Long cmpId) {
		this.cmpId = cmpId;
	}	
}
