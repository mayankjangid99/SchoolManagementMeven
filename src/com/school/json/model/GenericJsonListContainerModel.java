package com.school.json.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GenericJsonListContainerModel 
{
	private List<?> jsonListContainer;

	@JsonProperty(value="jsonListContainer")
	public List<?> getJsonListContainer() {
		return jsonListContainer;
	}

	public void setJsonListContainer(List<?> jsonListContainer) {
		this.jsonListContainer = jsonListContainer;
	}
	
}
