package com.school.json.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GlobalConfigJsonModel 
{

	private String configCode;
	private String configDescription;
	private String configValue;
	
	@JsonProperty(value="configCode")
	public String getConfigCode() {
		return configCode;
	}
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	
	@JsonProperty(value="configDescription")
	public String getConfigDescription() {
		return configDescription;
	}
	public void setConfigDescription(String configDescription) {
		this.configDescription = configDescription;
	}
	
	@JsonProperty(value="configValue")
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
	
}
