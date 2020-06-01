package com.school.json.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.school.json.model.StudentDetailsJsonModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDetailsJsonResponse {

	private List<StudentDetailsJsonModel> studentDetailsJsonModels;

	
	@JsonProperty(value = "studentDetailsJsonModels")
	public List<StudentDetailsJsonModel> getStudentDetailsJsonModels() {
		return studentDetailsJsonModels;
	}

	public void setStudentDetailsJsonModels(
			List<StudentDetailsJsonModel> studentDetailsJsonModels) {
		this.studentDetailsJsonModels = studentDetailsJsonModels;
	}
	
	
}
