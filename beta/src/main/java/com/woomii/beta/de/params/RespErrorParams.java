/**
* <h1>WooMii Platform</h1>
* RespErrorParams: HTTP Error Response Parameters (Common for all JSON requests)
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params;

import com.woomii.beta.de.utils.WooMiiUtils;

public class RespErrorParams {

	private int errC;
	private String errS;
	
	public int geterrC() {
		return errC;
	}

	public void seterrC(int errC) {
		WooMiiUtils.ERROR_CODES value = WooMiiUtils.ERROR_CODES.values()[errC];
		if (value != WooMiiUtils.ERROR_CODES.ERROR_OTHER) {
			this.seterrS(value.name());
		}
		this.errC = errC;
	}

	public String geterrS() {
		return errS;
	}

	public void seterrS(String errS) {
		this.errS = errS;
	}
}
