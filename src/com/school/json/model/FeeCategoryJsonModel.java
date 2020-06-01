package com.school.json.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FeeCategoryJsonModel 
{

	private String feeCategoryCode;
	private String feeCategoryName;
	private String classCode;
	private String className;
	private String sectionCode;
	private String sectionName;
	private String schoolCode;
	private String schoolName;
	private String type;
	private String feeType;
	private String status;
	

	@JsonProperty(value="feeCategoryCode")
	public String getFeeCategoryCode() {
		return feeCategoryCode;
	}
	public void setFeeCategoryCode(String classCode) {
		this.feeCategoryCode = classCode;
	}
	
	@JsonProperty(value="feeCategoryName")
	public String getFeeCategoryName() {
		return feeCategoryName;
	}
	public void setFeeCategoryName(String className) {
		this.feeCategoryName = className;
	}
	
	@JsonProperty(value="classCode")
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	@JsonProperty(value="className")
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
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
	
	@JsonProperty(value="type")
	public String getType() {
		return type;
	}
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
	@JsonProperty(value="sectionCode")
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	@JsonProperty(value="sectionName")
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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

	
	
}
