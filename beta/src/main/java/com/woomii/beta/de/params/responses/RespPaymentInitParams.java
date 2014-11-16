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
	private Long creditsNeeded;
	private Long creditsEarned;
	private Long creditsRedeemed;
	
	public boolean isRedeemEligible() {
		return redeemEligible;
	}
	
	public void setRedeemEligible(boolean redeemEligible) {
		this.redeemEligible = redeemEligible;
	}

	public Long getCreditsNeeded() {
		return creditsNeeded;
	}

	public void setCreditsNeeded(Long creditsNeeded) {
		this.creditsNeeded = creditsNeeded;
	}

	public Long getCreditsEarned() {
		return creditsEarned;
	}

	public void setCreditsEarned(Long creditsEarned) {
		this.creditsEarned = creditsEarned;
	}

	public Long getCreditsRedeemed() {
		return creditsRedeemed;
	}

	public void setCreditsRedeemed(Long creditsRedeemed) {
		this.creditsRedeemed = creditsRedeemed;
	}
}
