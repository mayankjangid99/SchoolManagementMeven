package com.school.json.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SchoolPaymentCategoryJsonModel 
{
	private Integer schoolPaymentCategoryId;
	private String sessionCode;
	private String sessionName;
	private String schoolCode;
	private String schoolName;
	private String paymentCategoryCode;
	private String paymentCategoryName;
	
	
	@JsonProperty(value="schoolPaymentCategoryId")
	public Integer getSchoolPaymentCategoryId() {
		return schoolPaymentCategoryId;
	}
	public void setSchoolPaymentCategoryId(Integer schoolPaymentCategoryId) {
		this.schoolPaymentCategoryId = schoolPaymentCategoryId;
	}
	
	@JsonProperty(value="sessionCode")
	public String getSessionCode() {
		return sessionCode;
	}
	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}
	
	@JsonProperty(value="sessionName")
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	
	@JsonProperty(value="schoolCode")
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	
	@JsonProperty(value="schoolName")
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	@JsonProperty(value="paymentCategoryCode")
	public String getPaymentCategoryCode() {
		return paymentCategoryCode;
	}
	public void setPaymentCategoryCode(String paymentCategoryCode) {
		this.paymentCategoryCode = paymentCategoryCode;
	}
	
	@JsonProperty(value="paymentCategoryName")
	public String getPaymentCategoryName() {
		return paymentCategoryName;
	}
	public void setPaymentCategoryName(String paymentCategoryName) {
		this.paymentCategoryName = paymentCategoryName;
	}
	
	
}
