package com.school.json.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerLogJsonModel 
{	
	private String logName;
	private String logPath;
	private String logSize;
	private String logLastModifiedTime;
	
	
	@JsonProperty(value="logName")
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	
	@JsonProperty(value="logPath")
	public String getLogPath() {
		return logPath;
	}
	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}
	
	@JsonProperty(value="logSize")
	public String getLogSize() {
		return logSize;
	}
	public void setLogSize(String logSize) {
		this.logSize = logSize;
	}
	
	@JsonProperty(value="logLastModifiedTime")
	public String getLogLastModifiedTime() {
		return logLastModifiedTime;
	}
	public void setLogLastModifiedTime(String logLastModifiedTime) {
		this.logLastModifiedTime = logLastModifiedTime;
	}
	
	
	
}
