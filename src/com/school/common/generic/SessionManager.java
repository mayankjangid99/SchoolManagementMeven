package com.school.common.generic;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.school.common.model.AdmissionNumberModel;
import com.school.common.model.ApplicationSettingsModel;
import com.school.common.model.GlobalConfigModel;
import com.school.common.model.MicroServiceConfigModel;
import com.school.common.model.SchoolProfileModel;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionManager 
{
	private SchoolProfileUtils schoolProfileUtils;
	private UserProfileUtils userProfileUtils;
	private List<GlobalConfigModel> globalConfigList;
	private List<SchoolProfileModel> schoolProfiles;
	private List<ApplicationSettingsModel> applicationSettings;
	private List<AdmissionNumberModel> admissionNumbers;
	private List<MicroServiceConfigModel> microServiceConfigs;
	
	
	public SchoolProfileUtils getSchoolProfileUtils() {
		return schoolProfileUtils;
	}
	public void setSchoolProfileUtils(SchoolProfileUtils schoolProfileUtils) {
		this.schoolProfileUtils = schoolProfileUtils;
	}
	
	public UserProfileUtils getUserProfileUtils() {
		return userProfileUtils;
	}
	public void setUserProfileUtils(UserProfileUtils userProfileUtils) {
		this.userProfileUtils = userProfileUtils;
	}
	
	public List<GlobalConfigModel> getGlobalConfigList() {
		return globalConfigList;
	}
	public void setGlobalConfigList(List<GlobalConfigModel> globalConfigList) {
		this.globalConfigList = globalConfigList;
	}
	public List<SchoolProfileModel> getSchoolProfiles() {
		return schoolProfiles;
	}
	public void setSchoolProfiles(List<SchoolProfileModel> schoolProfiles) {
		this.schoolProfiles = schoolProfiles;
	}
	public List<ApplicationSettingsModel> getApplicationSettings() {
		return applicationSettings;
	}
	public void setApplicationSettings(List<ApplicationSettingsModel> applicationSettings) {
		this.applicationSettings = applicationSettings;
	}
	public List<AdmissionNumberModel> getAdmissionNumbers() {
		return admissionNumbers;
	}
	public void setAdmissionNumbers(List<AdmissionNumberModel> admissionNumbers) {
		this.admissionNumbers = admissionNumbers;
	}
	/**
	 * @return the microServiceConfigs
	 */
	public List<MicroServiceConfigModel> getMicroServiceConfigs() {
		return microServiceConfigs;
	}
	/**
	 * @param microServiceConfigs the microServiceConfigs to set
	 */
	public void setMicroServiceConfigs(List<MicroServiceConfigModel> microServiceConfigs) {
		this.microServiceConfigs = microServiceConfigs;
	}
	
	
	
}
