/**
* <h1>WooMii Platform</h1>
* ReqInitializeParams: HTTP Request Parameters for Initialize API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params.requests;

import com.woomii.beta.de.params.ReqCommonParams;

public class ReqInitializeParams extends ReqCommonParams {
	private String affId;
	private String location;
	private Double lat, lon;
	private String pin;
	
	public String getAffId() {
		return affId;
	}
	
	public void setAffId(String affId) {
		this.affId = affId;
	}
	
	public Double getLat() {
		return lat;
	}
	
	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	public Double getLon() {
		return lon;
	}
	
	public void setLon(Double lon) {
		this.lon = lon;
	}
	
	public String getPin() {
		return pin;
	}
	
	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}	
}
