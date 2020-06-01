package com.school.json.model;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassFeeCategoryJsonModel {

	private String classCode;
	private String className;
	private String sectionCode;
	private String sectionName;
	private String schoolCode;
	private String schoolName;
	private String sessionCode;
	private String sessionName;
	private String feeCategoryCode;
	private String feeCategoryName;
	private String type;
	private String feeType;
	private String status;
	private BigDecimal amount;
	
	
	public ClassFeeCategoryJsonModel() {
	}


	/**
	 * @return the classCode
	 */
	@JsonProperty(value="classCode")
	public String getClassCode() {
		return classCode;
	}


	/**
	 * @param classCode the classCode to set
	 */
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}


	/**
	 * @return the className
	 */
	@JsonProperty(value="className")
	public String getClassName() {
		return className;
	}


	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}


	/**
	 * @return the sectionCode
	 */
	@JsonProperty(value="sectionCode")
	public String getSectionCode() {
		return sectionCode;
	}


	/**
	 * @param sectionCode the sectionCode to set
	 */
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}


	/**
	 * @return the sectionName
	 */
	@JsonProperty(value="sectionName")
	public String getSectionName() {
		return sectionName;
	}


	/**
	 * @param sectionName the sectionName to set
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}


	/**
	 * @return the schoolCode
	 */
	@JsonProperty(value="schoolCode")
	public String getSchoolCode() {
		return schoolCode;
	}


	/**
	 * @param schoolCode the schoolCode to set
	 */
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}


	/**
	 * @return the schoolName
	 */
	@JsonProperty(value="schoolName")
	public String getSchoolName() {
		return schoolName;
	}


	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	/**
	 * @return the sessionCode
	 */
	@JsonProperty(value="sessionCode")
	public String getSessionCode() {
		return sessionCode;
	}


	/**
	 * @param sessionCode the sessionCode to set
	 */
	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}


	/**
	 * @return the sessionName
	 */
	@JsonProperty(value="sessionName")
	public String getSessionName() {
		return sessionName;
	}


	/**
	 * @param sessionName the sessionName to set
	 */
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}


	/**
	 * @return the feeCategoryCode
	 */
	@JsonProperty(value="feeCategoryCode")
	public String getFeeCategoryCode() {
		return feeCategoryCode;
	}


	/**
	 * @param feeCategoryCode the feeCategoryCode to set
	 */
	public void setFeeCategoryCode(String feeCategoryCode) {
		this.feeCategoryCode = feeCategoryCode;
	}


	/**
	 * @return the feeCategoryName
	 */
	@JsonProperty(value="feeCategoryName")
	public String getFeeCategoryName() {
		return feeCategoryName;
	}


	/**
	 * @param feeCategoryName the feeCategoryName to set
	 */
	public void setFeeCategoryName(String feeCategoryName) {
		this.feeCategoryName = feeCategoryName;
	}


	/**
	 * @return the type
	 */
	@JsonProperty(value="type")
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the feeType
	 */
	@JsonProperty(value="feeType")
	public String getFeeType() {
		return feeType;
	}


	/**
	 * @param feeType the feeType to set
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}


	/**
	 * @return the status
	 */
	@JsonProperty(value="status")
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the amount
	 */
	@JsonProperty(value="amount")
	public BigDecimal getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	
		
}
