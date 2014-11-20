/**
* <h1>WooMii Platform</h1>
* ReqCommonParams: HTTP Request Parameters (Common for all JSON requests)
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params;

public class ReqCommonParams {	
	private String uuId;
	private String appId;
	private String lang;
	private boolean sandBoxMode;
	
	public String getUuId() {
		return uuId;
	}
	
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	
	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public boolean getSandBoxMode() {
		return sandBoxMode;
	}

	public void setSandBoxMode(boolean sandBoxMode) {
		this.sandBoxMode = sandBoxMode;
	}	
}
