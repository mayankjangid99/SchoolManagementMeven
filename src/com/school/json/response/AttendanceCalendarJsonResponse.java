package com.school.json.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.school.json.model.AttendanceCalendarJsonModel;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AttendanceCalendarJsonResponse {

	private String year;
	private String month;
	private String date;
	private String hasError;
	private String errorMsg;
	private List<AttendanceCalendarJsonModel> attendanceCalendarJsonModels;

	@JsonProperty(value="year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@JsonProperty(value="month")
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	@JsonProperty(value="date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
		
	@JsonProperty(value="result")
	public List<AttendanceCalendarJsonModel> getAttendanceCalendarJsonModels() {
		return attendanceCalendarJsonModels;
	}

	public void setAttendanceCalendarJsonModels(
			List<AttendanceCalendarJsonModel> attendanceCalendarJsonModels) {
		this.attendanceCalendarJsonModels = attendanceCalendarJsonModels;
	}
	public String getHasError() {
		return hasError;
	}
	public void setHasError(String hasError) {
		this.hasError = hasError;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
