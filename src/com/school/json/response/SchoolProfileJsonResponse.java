package com.school.json.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.school.json.model.SchoolProfileJsonModel;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SchoolProfileJsonResponse 
{
	private List<SchoolProfileJsonModel> schoolProfileJsons;
	private String activeSchoolCode;
	private String activeSchoolName;
	
	@JsonProperty(value = "schoolProfileJsons")
	public List<SchoolProfileJsonModel> getSchoolProfileJsons() {
		return schoolProfileJsons;
	}
	public void setSchoolProfileJsons(List<SchoolProfileJsonModel> schoolProfileJsons) {
		this.schoolProfileJsons = schoolProfileJsons;
	}
	
	@JsonProperty(value = "activeSchoolCode")
	public String getActiveSchoolCode() {
		return activeSchoolCode;
	}
	public void setActiveSchoolCode(String activeSchoolCode) {
		this.activeSchoolCode = activeSchoolCode;
	}
	
	@JsonProperty(value = "activeSchoolName")
	public String getActiveSchoolName() {
		return activeSchoolName;
	}
	public void setActiveSchoolName(String activeSchoolName) {
		this.activeSchoolName = activeSchoolName;
	}
	
	
	
	
	
}
