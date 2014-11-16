/**
* <h1>WooMii Platform</h1>
* WooMiiException: Custom exception for WooMii exceptions.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.utils;

public class WooMiiException extends Exception {
	private static final long serialVersionUID = 1L;
	private WooMiiUtils.ERROR_CODES errorCode;
	
	public WooMiiException(WooMiiUtils.ERROR_CODES errorCode)
    {
        super(errorCode.name());
        this.errorCode = errorCode;
    }
	
    public String getMessage()
    {
        return super.getMessage();
    }

	public WooMiiUtils.ERROR_CODES getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(WooMiiUtils.ERROR_CODES errorCode) {
		this.errorCode = errorCode;
	}       
    
}
