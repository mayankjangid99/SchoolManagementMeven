package com.school.json.model;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AttendanceBarChartJsonModel 
{
	
	private ArrayList<String> months;
	private ArrayList<Integer> presentList;
	private ArrayList<Integer> absentList;
	private Integer totalPresent;
	private Integer totalAbsent;
	
	@JsonProperty(value="months")
	public ArrayList<String> getMonths() {
		return months;
	}
	public void setMonths(ArrayList<String> months) {
		this.months = months;
	}
	
	@JsonProperty(value="presents")
	public ArrayList<Integer> getPresentList() {
		return presentList;
	}
	public void setPresentList(ArrayList<Integer> presentList) {
		this.presentList = presentList;
	}
	
	@JsonProperty(value="absents")
	public ArrayList<Integer> getAbsentList() {
		return absentList;
	}
	public void setAbsentList(ArrayList<Integer> absentList) {
		this.absentList = absentList;
	}
	
	@JsonProperty(value="totalPresent")
	public Integer getTotalPresent() {
		return totalPresent;
	}
	public void setTotalPresent(Integer totalPresent) {
		this.totalPresent = totalPresent;
	}
	
	@JsonProperty(value="totalAbsent")
	public Integer getTotalAbsent() {
		return totalAbsent;
	}
	public void setTotalAbsent(Integer totalAbsent) {
		this.totalAbsent = totalAbsent;
	}
	
	
	
}
