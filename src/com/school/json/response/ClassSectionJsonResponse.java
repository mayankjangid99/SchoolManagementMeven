package com.school.json.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.school.json.model.ClassSectionJsonModel;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ClassSectionJsonResponse 
{
	private List<ClassSectionJsonModel> classSectionModels;

	@JsonProperty(value="classSectionList")
	public List<ClassSectionJsonModel> getClassSectionModels() {
		return classSectionModels;
	}

	public void setClassSectionModels(List<ClassSectionJsonModel> classSectionModels) {
		this.classSectionModels = classSectionModels;
	}
	
	
}
