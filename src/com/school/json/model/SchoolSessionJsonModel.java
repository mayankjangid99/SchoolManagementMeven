package com.school.json.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolSessionJsonModel 
{
	private Integer schoolSessionId;
	private String schoolCode;
	private String schoolName;
	private String sessionCode;
	private String sessionName;
	private int sequence;
	private String status;
	
	
	@JsonProperty(value="schoolSessionId")
	public Integer getSchoolSessionId() {
		return schoolSessionId;
	}

	public void setSchoolSessionId(Integer schoolSessionId) {
		this.schoolSessionId = schoolSessionId;
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
	
	@JsonProperty(value="sequence")
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	@JsonProperty(value="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
