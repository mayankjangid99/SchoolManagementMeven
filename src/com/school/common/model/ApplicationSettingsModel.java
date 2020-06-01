package com.school.common.model;

// Generated Apr 2, 2016 3:30:20 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * GlobalConfig generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "application_settings", uniqueConstraints = @UniqueConstraint(columnNames = {"setting_code", "school_code"}))
public class ApplicationSettingsModel implements java.io.Serializable {

	private Long applicationSettingId;
	private String settingCode;
	private String settingDescription;
	private String settingValue;
	private SchoolProfileModel schoolProfile;

	public ApplicationSettingsModel() {
	}

	public ApplicationSettingsModel(Long applicationSettingId) {
		this.applicationSettingId = applicationSettingId;
	}
	public ApplicationSettingsModel(String settingCode, String settingValue) {
		this.settingCode = settingCode;
		this.settingValue = settingValue;
	}

	public ApplicationSettingsModel(String settingCode, String settingDescription,
			String settingValue, SchoolProfileModel schoolProfile) {
		this.settingCode = settingCode;
		this.settingDescription = settingDescription;
		this.settingValue = settingValue;
		this.schoolProfile = schoolProfile;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getApplicationSettingId() {
		return applicationSettingId;
	}

	public void setApplicationSettingId(Long applicationSettingId) {
		this.applicationSettingId = applicationSettingId;
	}

	@Column(name = "setting_code", nullable = false, length = 50)
	public String getSettingCode() {
		return this.settingCode;
	}

	public void setSettingCode(String settingCode) {
		this.settingCode = settingCode;
	}

	@Column(name = "setting_description")
	public String getSettingDescription() {
		return this.settingDescription;
	}

	public void setSettingDescription(String settingDescription) {
		this.settingDescription = settingDescription;
	}

	@Column(name = "setting_value", nullable = false)
	public String getSettingValue() {
		return this.settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_code", nullable = false)
	public SchoolProfileModel getSchoolProfile() {
		return schoolProfile;
	}

	public void setSchoolProfile(SchoolProfileModel schoolProfile) {
		this.schoolProfile = schoolProfile;
	}
	
	

}