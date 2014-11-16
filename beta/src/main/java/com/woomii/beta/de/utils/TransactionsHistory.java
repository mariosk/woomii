/**
* <h1>WooMii Platform</h1>
* TransactionsHistory: Object to be returned in ViewCreditsHistory API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.utils;

import java.util.Date;

public class TransactionsHistory {
	private Date created;
	private int creditsEarned;
	private int creditsRedeemed;
	private int daysToExpire;
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}

	public int getCreditsEarned() {
		return creditsEarned;
	}

	public void setCreditsEarned(int creditsEarned) {
		this.creditsEarned = creditsEarned;
	}

	public int getCreditsRedeemed() {
		return creditsRedeemed;
	}

	public void setCreditsRedeemed(int creditsRedeemed) {
		this.creditsRedeemed = creditsRedeemed;
	}

	public int getDaysToExpire() {
		return daysToExpire;
	}

	public void setDaysToExpire(int daysToExpire) {
		this.daysToExpire = daysToExpire;
	}
}
