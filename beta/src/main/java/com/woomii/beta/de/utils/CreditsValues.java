package com.woomii.beta.de.utils;

import com.woomii.beta.de.params.responses.RespPaymentInitParams;

public class CreditsValues extends RespPaymentInitParams {

	private long creditsLeft;

	public long getCreditsLeft() {
		return creditsLeft;
	}

	public void setCreditsLeft(long creditsLeft) {
		this.creditsLeft = creditsLeft;
	}
	
}
