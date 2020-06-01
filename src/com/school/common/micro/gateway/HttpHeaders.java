package com.school.common.micro.gateway;

import java.util.Map;

public class HttpHeaders {
	private String sessionToken;

	
	private Map<String, String> header;
	
	public HttpHeaders() {
	}
	
	
	public HttpHeaders(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	
	/**
	 * @return the sessionToken
	 */
	public String getSessionToken() {
		return sessionToken;
	}
	/**
	 * @param sessionToken the sessionToken to set
	 */
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}


	/**
	 * @return the header
	 */
	public Map<String, String> getHeader() {
		return header;
	}


	/**
	 * @param header the header to set
	 */
	public void setHeader(Map<String, String> header) {
		this.header = header;
	}
	
	
}
