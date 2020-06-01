package com.school.common.micro.gateway;

import com.school.common.micro.model.MicroEntityModel;

public class MicroServicesParams implements Cloneable {

	private String url;
	private String authUrl;
	private HttpHeaders headers;
	private MicroEntityModel microEntity;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	

	/**
	 * @return the authUrl
	 */
	public String getAuthUrl() {
		return authUrl;
	}

	/**
	 * @param authUrl the authUrl to set
	 */
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	/**
	 * @return the headers
	 */
	public HttpHeaders getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}

	/**
	 * @return the microEntity
	 */
	public MicroEntityModel getMicroEntity() {
		return microEntity;
	}

	/**
	 * @param microEntity the microEntity to set
	 */
	public void setMicroEntity(MicroEntityModel microEntity) {
		this.microEntity = microEntity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}
