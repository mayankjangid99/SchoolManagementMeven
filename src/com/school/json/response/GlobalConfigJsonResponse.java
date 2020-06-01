package com.school.json.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.school.json.model.GlobalConfigJsonModel;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GlobalConfigJsonResponse 
{
	private List<GlobalConfigJsonModel> globalConfigList;

	
	@JsonProperty(value="globalConfigList")
	public List<GlobalConfigJsonModel> getGlobalConfigList() {
		return globalConfigList;
	}

	public void setGlobalConfigList(List<GlobalConfigJsonModel> globalConfigList) {
		this.globalConfigList = globalConfigList;
	}

	
	
	
}
