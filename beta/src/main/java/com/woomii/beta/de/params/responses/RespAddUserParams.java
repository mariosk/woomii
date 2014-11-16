/**
* <h1>WooMii Platform</h1>
* RespAddUserParams: HTTP Response Parameters for AddUser API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params.responses;

public class RespAddUserParams {
	private boolean showPINPopup;	
	private String enterPinMsg;
	
	public String getEnterPinMsg() {
		return enterPinMsg;
	}
	
	public void setEnterPinMsg(String enterPinMsg) {
		this.enterPinMsg = enterPinMsg;
	}

	public boolean isShowPINPopup() {
		return showPINPopup;
	}

	public void setShowPINPopup(boolean showPINPopup) {
		this.showPINPopup = showPINPopup;
	}
}
