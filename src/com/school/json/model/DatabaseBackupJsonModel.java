package com.school.json.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatabaseBackupJsonModel 
{	
	private String backupName;
	private String backupPath;
	private String backupSize;
	private String backupLastModifiedTime;
	

	@JsonProperty(value="backupName")
	public String getBackupName() {
		return backupName;
	}
	public void setBackupName(String backupName) {
		this.backupName = backupName;
	}
	

	@JsonProperty(value="backupPath")
	public String getBackupPath() {
		return backupPath;
	}
	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}
	

	@JsonProperty(value="backupSize")
	public String getBackupSize() {
		return backupSize;
	}
	public void setBackupSize(String backupSize) {
		this.backupSize = backupSize;
	}
	

	@JsonProperty(value="backupLastModifiedTime")
	public String getBackupLastModifiedTime() {
		return backupLastModifiedTime;
	}
	public void setBackupLastModifiedTime(String backupLastModifiedTime) {
		this.backupLastModifiedTime = backupLastModifiedTime;
	}
	
	

	
	
	
}
