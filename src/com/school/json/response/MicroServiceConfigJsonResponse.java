package com.school.json.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.school.json.model.MicroServiceConfigJsonModel;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MicroServiceConfigJsonResponse 
{
	private List<MicroServiceConfigJsonModel> microServiceConfigList;

	
	@JsonProperty(value="microServiceConfigList")
	public List<MicroServiceConfigJsonModel> getMicroServiceConfigList() {
		return microServiceConfigList;
	}

	public void setMicroServiceConfigList(List<MicroServiceConfigJsonModel> microServiceConfigList) {
		this.microServiceConfigList = microServiceConfigList;
	}

	
	
	
}
