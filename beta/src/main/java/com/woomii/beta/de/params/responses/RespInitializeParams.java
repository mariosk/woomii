/**
* <h1>WooMii Platform</h1>
* RespInitializeParams: HTTP Response Parameters for Initialize API Call.
* 
*
* @author  Marios Karagiannopoulos {mariosk@gmail.com}
* @version 0.1
* @since   2014-11-15 
*/

package com.woomii.beta.de.params.responses;

public class RespInitializeParams {
	private String name;
	private Long id;
	private String motto;
	private String terms;
	private String color;
	private String welcomeMsg;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMotto() {
		return motto;
	}
	
	public void setMotto(String motto) {
		this.motto = motto;
	}
	
	public String getTerms() {
		return terms;
	}
	
	public void setTerms(String terms) {
		this.terms = terms;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getWelcomeMsg() {
		return welcomeMsg;
	}
	
	public void setWelcomeMsg(String welcomeMsg) {
		this.welcomeMsg = welcomeMsg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
}
