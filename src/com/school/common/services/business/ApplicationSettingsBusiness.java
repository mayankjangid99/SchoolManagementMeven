package com.school.common.services.business;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.model.ApplicationSettingsModel;
import com.school.common.model.SchoolProfileModel;

@Service
public class ApplicationSettingsBusiness {
	
	@Autowired
	private MessageSource messageSource;
	
	public ApplicationSettingsModel addOrChangeEmailSettings(HttpServletRequest request){
		ApplicationSettingsModel settingsModel = new ApplicationSettingsModel();
		
		String applicationSettingIdStr = request.getParameter("applicationSettingId");
		if(applicationSettingIdStr != null && !"".equals(applicationSettingIdStr)) {
			Long applicationSettingId = Long.parseLong(request.getParameter("applicationSettingId"));
			settingsModel.setApplicationSettingId(applicationSettingId);
		}
		
		String emailStudentDetails = request.getParameter("emailStudentDetails");
		if(emailStudentDetails != null){
			settingsModel.setSchoolProfile(new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode()));
			settingsModel.setSettingCode(StaticValue.APPLICATION_SETTINGS_EMAIL_STUDENT_DETAILS);
			settingsModel.setSettingDescription(messageSource.getMessage("ui.text.thisParameterUseSendEmailMarkStudentDetails", null, null));
			if("Y".equals(emailStudentDetails))
				settingsModel.setSettingValue("Y");
			else
				settingsModel.setSettingValue("N");
		}
		
		String emailStudentAttendance = request.getParameter("emailStudentAttendance");
		if(emailStudentAttendance != null){
			settingsModel.setSchoolProfile(new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode()));
			settingsModel.setSettingCode(StaticValue.APPLICATION_SETTINGS_EMAIL_STUDENT_ATTENDANCE);
			settingsModel.setSettingDescription(messageSource.getMessage("ui.text.thisParameterUseSendEmailMarkStudentAttendance", null, null));
			if("Y".equals(emailStudentAttendance))
				settingsModel.setSettingValue("Y");
			else
				settingsModel.setSettingValue("N");
		}
		
		String emailStudentFeeReceipt = request.getParameter("emailStudentFeeReceipt");
		if(emailStudentFeeReceipt != null){
			settingsModel.setSchoolProfile(new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode()));
			settingsModel.setSettingCode(StaticValue.APPLICATION_SETTINGS_EMAIL_STUDENT_FEE_RECEIPT);
			settingsModel.setSettingDescription(messageSource.getMessage("ui.text.thisParameterUseSendEmailStudentFeeReceipt", null, null));
			if("Y".equals(emailStudentFeeReceipt))
				settingsModel.setSettingValue("Y");
			else
				settingsModel.setSettingValue("N");
		}
		
		String emailUserDetails = request.getParameter("emailUserDetails");
		if(emailUserDetails != null){
			settingsModel.setSchoolProfile(new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode()));
			settingsModel.setSettingCode(StaticValue.APPLICATION_SETTINGS_EMAIL_USER_DETAILS);
			settingsModel.setSettingDescription(messageSource.getMessage("ui.text.thisParameterUseSendEmailUserDetails", null, null));
			if("Y".equals(emailUserDetails))
				settingsModel.setSettingValue("Y");
			else
				settingsModel.setSettingValue("N");
		}
		return settingsModel;
	}
}
