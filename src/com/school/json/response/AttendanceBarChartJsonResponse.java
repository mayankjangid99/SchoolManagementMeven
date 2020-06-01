package com.school.json.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.school.json.model.AttendanceBarChartJsonModel;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AttendanceBarChartJsonResponse {

	private AttendanceBarChartJsonModel attendanceBarChartJsonModel;

	
	@JsonProperty(value="result")
	public AttendanceBarChartJsonModel getAttendanceBarChartJsonModel() {
		return attendanceBarChartJsonModel;
	}

	public void setAttendanceBarChartJsonModel(
			AttendanceBarChartJsonModel attendanceBarChartJsonModel) {
		this.attendanceBarChartJsonModel = attendanceBarChartJsonModel;
	}
	
	
}
