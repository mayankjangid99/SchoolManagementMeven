package com.school.json.model;

/**
 * GlobalConfig generated by hbm2java
 */

public class ReportCardConfigJsonModel{

	private String configCode;
	private String configDescription;
	private String configValue;
	private String schoolCode;
	private String sessionCode;
	private String classCode;
	private String sectionCode;

	public ReportCardConfigJsonModel() {
	}

	public ReportCardConfigJsonModel(String configCode, String configValue) {
		this.configCode = configCode;
		this.configValue = configValue;
	}

	public ReportCardConfigJsonModel(String configCode, String configDescription,
			String configValue) {
		this.configCode = configCode;
		this.configDescription = configDescription;
		this.configValue = configValue;
	}

	public String getConfigCode() {
		return this.configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getConfigDescription() {
		return this.configDescription;
	}

	public void setConfigDescription(String configDescription) {
		this.configDescription = configDescription;
	}

	public String getConfigValue() {
		return this.configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	/**
	 * @return the schoolCode
	 */
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
	 * @return the sessionCode
	 */
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
	 * @return the classCode
	 */
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
	 * @return the sectionCode
	 */
	public String getSectionCode() {
		return sectionCode;
	}

	/**
	 * @param sectionCode the sectionCode to set
	 */
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	
	

}
