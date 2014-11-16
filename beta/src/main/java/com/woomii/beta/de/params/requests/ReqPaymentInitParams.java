/**
* <h1>WooMii Platform</h1>
* ReqPaymentInitParams: HTTP Request Parameters for PaymentInit API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params.requests;

import com.woomii.beta.de.params.ReqCommonParams;

public class ReqPaymentInitParams extends ReqCommonParams {	
	private double appCost;

	public double getAppCost() {
		return appCost;
	}

	public void setAppCost(double appCost) {
		this.appCost = appCost;
	}	
}
