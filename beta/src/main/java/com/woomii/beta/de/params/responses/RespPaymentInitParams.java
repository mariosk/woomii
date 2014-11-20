/**
* <h1>WooMii Platform</h1>
* RespPaymentInitParams: HTTP Response Parameters for PaymentInit API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params.responses;

public class RespPaymentInitParams {
	private boolean redeemEligible;
	private long creditsNeeded;
	private long creditsEarned;
	private long creditsRedeemed;
	
	public boolean isRedeemEligible() {
		return redeemEligible;
	}
	
	public void setRedeemEligible(boolean redeemEligible) {
		this.redeemEligible = redeemEligible;
	}

	public long getCreditsNeeded() {
		return creditsNeeded;
	}

	public void setCreditsNeeded(long creditsNeeded) {
		this.creditsNeeded = creditsNeeded;
	}

	public long getCreditsEarned() {
		return creditsEarned;
	}

	public void setCreditsEarned(long creditsEarned) {
		this.creditsEarned = creditsEarned;
	}

	public long getCreditsRedeemed() {
		return creditsRedeemed;
	}

	public void setCreditsRedeemed(long creditsRedeemed) {
		this.creditsRedeemed = creditsRedeemed;
	}
}
