package com.school.common.generic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.school.common.model.AdmissionNumberModel;
import com.school.common.model.ApplicationSettingsModel;
import com.school.common.model.GlobalConfigModel;
import com.school.common.model.MicroServiceConfigModel;

public class SessionManagerDataHelper 
{	
	
//	----------------------------------------------------	School	Profile ------------------------------------------------------
	public static SchoolProfileUtils getSchoolProfileUtil()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null)
			return sessionManager.getSchoolProfileUtils();
		else
			return null;
	}
	
	
	public static boolean setSchoolProfileUtils(SchoolProfileUtils schoolProfileUtils)
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null)
		{
			sessionManager.setSchoolProfileUtils(schoolProfileUtils);
			session.setAttribute("SessionManager", sessionManager);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String getSchoolCurrentSession()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getSchoolProfileUtils() != null)
			return sessionManager.getSchoolProfileUtils().getCurrentSession();
		else
			return null;
	}
	
	
	public static String getSchoolCurrentSession(HttpSession session)
	{
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getSchoolProfileUtils() != null)
			return sessionManager.getSchoolProfileUtils().getCurrentSession();
		else
			return null;
	}
	
	
	public static String getSchoolCode()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getSchoolProfileUtils() != null)
			return sessionManager.getSchoolProfileUtils().getSchoolCode();
		else
			return null;
	}
	
	
	
	
	
	
//	----------------------------------------------------	User Profile	------------------------------------------------------
	
	public static UserProfileUtils getUserProfileUtil()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null)
			return sessionManager.getUserProfileUtils();
		else
			return null;
	}
	
	
	public static String getUsername()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getUserProfileUtils() != null)
			return sessionManager.getUserProfileUtils().getUsername();
		else
			return null;
	}

	
	public static String getEncPassword() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getUserProfileUtils() != null)
			return sessionManager.getUserProfileUtils().getEncPassword();
		else
			return null;
	}
	
	
	public static void setPassword(String password) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getUserProfileUtils() != null)
			sessionManager.getUserProfileUtils().setPassword(password);
	}
	
	public static String getPassword() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getUserProfileUtils() != null)
			return sessionManager.getUserProfileUtils().getPassword();
		else
			return null;
	}
	

	
	
	public static String getToken() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getUserProfileUtils() != null)
			return sessionManager.getUserProfileUtils().getToken();
		else
			return null;
	}

	
	public static void setToken(String token) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getUserProfileUtils() != null)
			sessionManager.getUserProfileUtils().setToken(token);
	}
	
	
	public static UserProfileUtils getUserProfileUtil(HttpSession session) {
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null)
			return sessionManager.getUserProfileUtils();
		else
			return null;
	}
	
	
	public static boolean setUserProfileUtils(UserProfileUtils userProfileUtils)
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null)
		{
			sessionManager.setUserProfileUtils(userProfileUtils);
			session.setAttribute("SessionManager", sessionManager);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public static boolean setUserProfileUtils(HttpSession session, UserProfileUtils userProfileUtils)
	{
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null)
		{
			sessionManager.setUserProfileUtils(userProfileUtils);
			session.setAttribute("SessionManager", sessionManager);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
//	get Class Code
	public static String getUserClassCode()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getUserProfileUtils() != null)
			return sessionManager.getUserProfileUtils().getClassCode();
		else
			return null;
	}
	
//	get Section Code
	public static String getUserSectionCode()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getUserProfileUtils() != null)
			return sessionManager.getUserProfileUtils().getSectionCode();
		return null;
	}
	
	
//	get Active School Code
	public static String getUserActiveSchoolCode()
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getUserProfileUtils() != null)
			return sessionManager.getUserProfileUtils().getSchoolCode();
		return null;
	}
	
	
	
	
	
	
	
	
//	----------------------------------------------------	Global Configuration	------------------------------------------------------
	
	public static String getGlobalConfigParameterValue(String confCode)
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager) session.getAttribute("SessionManager");
		List<GlobalConfigModel> globalConfigList = sessionManager.getGlobalConfigList();
		for(GlobalConfigModel config : globalConfigList) {
			if(confCode.equalsIgnoreCase(config.getConfigCode()))
				return config.getConfigValue();				
		}
		return null;
	}
	
	
	public static boolean setSchoolProfileUtils(List<GlobalConfigModel> globalConfigModels)
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null)
		{
			sessionManager.setGlobalConfigList(globalConfigModels);
			session.setAttribute("SessionManager", sessionManager);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	

//	----------------------------------------------------	Application Settings	------------------------------------------------------
	
	public static List<ApplicationSettingsModel> getApplicationSettings(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null && sessionManager.getUserProfileUtils() != null)
			return sessionManager.getApplicationSettings();
		return null;
	}
	
	
	public static String getApplicationSettingsParameterValue(String settingCode)
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager) session.getAttribute("SessionManager");
		List<ApplicationSettingsModel> applicationSettingsList = sessionManager.getApplicationSettings();
		for(ApplicationSettingsModel setting : applicationSettingsList)
		{
			if(settingCode.equalsIgnoreCase(setting.getSettingCode()))
				return setting.getSettingValue();				
		}
		return null;
	}
	
	public static ApplicationSettingsModel getApplicationSettings(String settingCode)
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager) session.getAttribute("SessionManager");
		List<ApplicationSettingsModel> applicationSettingsList = sessionManager.getApplicationSettings();
		for(ApplicationSettingsModel setting : applicationSettingsList)
		{
			if(settingCode.equalsIgnoreCase(setting.getSettingCode()))
				return setting;				
		}
		return null;
	}
	
	


//	----------------------------------------------------	Admission Number	------------------------------------------------------
	
	public static AdmissionNumberModel getAdmissionNumber(String admissionNumber)
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager) session.getAttribute("SessionManager");
		List<AdmissionNumberModel> admissionNumberList = sessionManager.getAdmissionNumbers();
		for(AdmissionNumberModel admissionModel : admissionNumberList)
		{
			if(admissionNumber.equalsIgnoreCase(admissionModel.getAdmissionNo()))
				return admissionModel;				
		}
		return null;
	}
	
	
	public static boolean setAdmissionNumber(List<AdmissionNumberModel> admissionNumberList)
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		if(sessionManager != null)
		{
			sessionManager.setAdmissionNumbers(admissionNumberList);
			session.setAttribute("SessionManager", sessionManager);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String getMicroServiceConfigParameterValue(String confCode) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager) session.getAttribute("SessionManager");
		if(sessionManager != null) {
			List<MicroServiceConfigModel> microServiceConfigList = sessionManager.getMicroServiceConfigs();
			if(microServiceConfigList != null && !microServiceConfigList.isEmpty()) {
				for(MicroServiceConfigModel config : microServiceConfigList) {
					if(confCode.equalsIgnoreCase(config.getConfigCode()))
						return config.getConfigValue();				
				}
			}
		}
		return null;
	}
	
}
