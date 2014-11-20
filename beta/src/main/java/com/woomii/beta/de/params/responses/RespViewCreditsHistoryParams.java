/**
* <h1>WooMii Platform</h1>
* RespViewCreditsHistoryParams: HTTP Response Parameters for ViewCreditsHistory API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params.responses;

import java.util.Collection;

import com.woomii.beta.de.utils.TransactionsHistory;

public class RespViewCreditsHistoryParams {
	private Collection<TransactionsHistory> transactions;	
	private long totalCreditsLeft;
	
	public Collection<TransactionsHistory> getTransactions() {
		return transactions;
	}
	
	public void setTransactions(Collection<TransactionsHistory> transactions) {
		this.transactions = transactions;
	}
	
	public long getTotalCreditsLeft() {
		return totalCreditsLeft;
	}
	
	public void setTotalCreditsLeft(long totalCreditsLeft) {
		this.totalCreditsLeft = totalCreditsLeft;
	}	
}
