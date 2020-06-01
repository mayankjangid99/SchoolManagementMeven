package com.school.common.services.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.school.common.generic.SchoolProfileUtils;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.UserProfileUtils;
import com.school.common.model.ClassFeeCategoryId;
import com.school.common.model.ClassFeeCategoryModel;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.ClassSectionId;
import com.school.common.model.ClassSectionModel;
import com.school.common.model.ClassSubjectId;
import com.school.common.model.ClassSubjectModel;
import com.school.common.model.FeeCategoryModel;
import com.school.common.model.PaymentCategoryModel;
import com.school.common.model.ReportCardConfigModel;
import com.school.common.model.SchoolPaymentCategoryModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SchoolSessionModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.model.SubjectDetailsModel;
import com.school.json.model.SchoolProfileJsonModel;
import com.school.json.response.SchoolProfileJsonResponse;

@Service
public class SchoolProfileBusiness 
{
	
	public List<SchoolProfileJsonModel> fetchSchoolProfile(List<SchoolProfileModel> schoolProfileList)
	{
		List<SchoolProfileJsonModel> schoolProfileJsonList = new ArrayList<SchoolProfileJsonModel>();
		SchoolProfileJsonModel schoolProfileJson = null;
		if(schoolProfileList.size() != 0)
		{
			for(SchoolProfileModel schoolProfile : schoolProfileList)
			{
				schoolProfileJson = new SchoolProfileJsonModel();
				schoolProfileJson.setLogo(schoolProfile.getLogo());
				schoolProfileJson.setName(schoolProfile.getName());
				schoolProfileJson.setSchoolCode(schoolProfile.getSchoolCode());
				schoolProfileJson.setStartYear(schoolProfile.getStartYear());
				schoolProfileJson.setRegistrationDate(schoolProfile.getRegistrationDate());
				schoolProfileJson.setEmail(schoolProfile.getEmail());
				schoolProfileJson.setWebsite(schoolProfile.getWebsite());
				schoolProfileJson.setAddress(schoolProfile.getAddress());
				schoolProfileJson.setDistrict(schoolProfile.getDistrict());
				schoolProfileJson.setCity(schoolProfile.getCity());
				schoolProfileJson.setState(schoolProfile.getState());
				schoolProfileJson.setPhone(schoolProfile.getPhone());
				schoolProfileJson.setFax(schoolProfile.getFax());
				schoolProfileJson.setPostcode(schoolProfile.getPostcode());
				schoolProfileJsonList.add(schoolProfileJson);
			}
			return schoolProfileJsonList;
		}
		else
			return null;
	}
	
	
	
	
	public SchoolProfileJsonResponse getActiveSchoolWithAllSchool(List<SchoolProfileModel> schoolProfileList)
	{
		SchoolProfileJsonResponse schoolProfileJsonResponse = new SchoolProfileJsonResponse();
		List<SchoolProfileJsonModel> profileJsonModelsList = new ArrayList<SchoolProfileJsonModel>();
		SchoolProfileJsonModel schoolProfileJsonModel = null;
		String schoolCode = SessionManagerDataHelper.getUserActiveSchoolCode();
		for(SchoolProfileModel schoolProfile : schoolProfileList)
		{
			schoolProfileJsonModel = new SchoolProfileJsonModel();
			if(schoolCode != null && schoolCode.equalsIgnoreCase(schoolProfile.getSchoolCode()))
			{
				schoolProfileJsonResponse.setActiveSchoolCode(schoolProfile.getSchoolCode());
				schoolProfileJsonResponse.setActiveSchoolName(schoolProfile.getName());
			}
			else
			{
				schoolProfileJsonModel.setSchoolCode(schoolProfile.getSchoolCode());
				schoolProfileJsonModel.setName(schoolProfile.getName());
				profileJsonModelsList.add(schoolProfileJsonModel);
			}			
		}
		schoolProfileJsonResponse.setSchoolProfileJsons(profileJsonModelsList);
		return schoolProfileJsonResponse;
	}
	
	
	
	public void changeActiveSchoolProfile(SchoolProfileModel schoolProfileModel, List<SchoolSessionModel> sessionList)
	{
		SchoolProfileUtils schoolProfileUtils = new SchoolProfileUtils();
		UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil();
		if(schoolProfileModel != null)
		{	
//			Set School Session Details
			Iterator<SchoolSessionModel> iterator = schoolProfileModel.getSchoolSessions().iterator();
			while (iterator.hasNext()) 
			{
				SchoolSessionModel schoolSession = (SchoolSessionModel) iterator.next();
				if(schoolSession.getStatus().equals("C"))
				{
					schoolProfileUtils.setCurrentSession(schoolSession.getSessionDetails().getSessionCode());
					schoolProfileUtils.setCurrentSessionName(schoolSession.getSessionDetails().getSessionName());
					break;
				}
			}
			schoolProfileUtils.setSchoolSessions(sessionList);

			schoolProfileUtils.setLogo(schoolProfileModel.getLogo());
			schoolProfileUtils.setName(schoolProfileModel.getName());
			schoolProfileUtils.setSchoolCode(schoolProfileModel.getSchoolCode());
			schoolProfileUtils.setStartYear(schoolProfileModel.getStartYear());
			schoolProfileUtils.setRegistrationDate(schoolProfileModel.getRegistrationDate());
			schoolProfileUtils.setEmail(schoolProfileModel.getEmail());
			schoolProfileUtils.setWebsite(schoolProfileModel.getWebsite());
			schoolProfileUtils.setAddress(schoolProfileModel.getAddress());
			schoolProfileUtils.setDistrict(schoolProfileModel.getDistrict());
			schoolProfileUtils.setCity(schoolProfileModel.getCity());
			schoolProfileUtils.setState(schoolProfileModel.getState());
			schoolProfileUtils.setPhone(schoolProfileModel.getPhone());
			schoolProfileUtils.setFax(schoolProfileModel.getFax());
			schoolProfileUtils.setPostcode(schoolProfileModel.getPostcode());
			
			userProfileUtils.setSchoolCode(schoolProfileModel.getSchoolCode());
			
			SessionManagerDataHelper.setSchoolProfileUtils(schoolProfileUtils);
			SessionManagerDataHelper.setUserProfileUtils(userProfileUtils);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> updatePromoteSchoolData(Map<String, Object> mapData, String schoolCode, String sessionCode) {
		Map<String, Object> mapPromoted = new HashMap<String, Object>();
		
		List<ClassSectionModel> classSectionList = (List<ClassSectionModel>) mapData.get("classSectionList");
		List<ClassSubjectModel> classSubjectList =  (List<ClassSubjectModel>) mapData.get("classSubjectList");
		List<SchoolPaymentCategoryModel> schoolPaymentList =  (List<SchoolPaymentCategoryModel>) mapData.get("schoolPaymentList");
		List<ClassFeeCategoryModel> classFeeCategoryList =  (List<ClassFeeCategoryModel>) mapData.get("classFeeCategoryList");
		List<ReportCardConfigModel> reportCardConfigList =  (List<ReportCardConfigModel>) mapData.get("reportCardConfigList");
		
		SchoolProfileModel schoolProfile = new SchoolProfileModel(schoolCode);
		SessionDetailsModel sessionDetails = new SessionDetailsModel(sessionCode);
		
		
		if(classSectionList != null && !classSectionList.isEmpty()) {
			List<ClassSectionModel> classSectionPromoted = new ArrayList<ClassSectionModel>();
			for(ClassSectionModel classSection : classSectionList) {
				ClassSectionModel classSectionTemp = new ClassSectionModel();
				classSectionTemp.setSessionDetails(sessionDetails);
				classSectionTemp.setSchoolProfile(schoolProfile);
				if(classSection.getClassInformation() != null) {
					ClassInformationModel classInformation = new ClassInformationModel(classSection.getClassInformation().getClassCode());
					classSectionTemp.setClassInformation(classInformation);
				}
				if(classSection.getSectionInformation() != null) {
					SectionInformationModel sectionInformation = new SectionInformationModel(classSection.getSectionInformation().getSectionCode());
					classSectionTemp.setSectionInformation(sectionInformation);
				}
				
				ClassSectionId classSectionId = new ClassSectionId();
				classSectionId.setClassCode(classSection.getClassInformation().getClassCode());
				classSectionId.setSectionCode(classSection.getSectionInformation().getSectionCode());
				classSectionId.setSchoolCode(schoolCode);
				classSectionId.setSessionCode(sessionCode);
				classSectionTemp.setId(classSectionId);
				
				classSectionPromoted.add(classSectionTemp);
			}
			mapPromoted.put("classSectionList", classSectionPromoted);
		}
		
		if(classSubjectList != null && !classSubjectList.isEmpty()) {
			List<ClassSubjectModel> classSubjectPromoted = new ArrayList<ClassSubjectModel>();
			for(ClassSubjectModel classSubject : classSubjectList) {
				ClassSubjectModel classSubjectTemp = new ClassSubjectModel();
				classSubjectTemp.setSessionDetails(sessionDetails);
				classSubjectTemp.setSchoolProfile(schoolProfile);
				classSubjectTemp.setType(classSubject.getType());
				if(classSubject.getClassInformation() != null) {
					ClassInformationModel classInformation = new ClassInformationModel(classSubject.getClassInformation().getClassCode());
					classSubjectTemp.setClassInformation(classInformation);
				}
				if(classSubject.getSectionInformation() != null) {
					SectionInformationModel sectionInformation = new SectionInformationModel(classSubject.getSectionInformation().getSectionCode());
					classSubjectTemp.setSectionInformation(sectionInformation);
				}
				if(classSubject.getSubjectDetails() != null) {
					SubjectDetailsModel subjectDetails = new SubjectDetailsModel(classSubject.getSubjectDetails().getSubjectCode());
					classSubjectTemp.setSubjectDetails(subjectDetails);
				}
				ClassSubjectId classSubjectId = new ClassSubjectId();
				classSubjectId.setSchoolCode(schoolCode);
				classSubjectId.setClassCode(classSubject.getClassInformation().getClassCode());
				classSubjectId.setSectionCode(classSubject.getSectionInformation().getSectionCode());
				classSubjectId.setSubjectCode(classSubject.getSubjectDetails().getSubjectCode());
				classSubjectId.setSessionCode(sessionCode);
				classSubjectTemp.setId(classSubjectId);
				
				classSubjectPromoted.add(classSubjectTemp);
			}
			mapPromoted.put("classSubjectList", classSubjectPromoted);
		}
		
		if(schoolPaymentList != null && !schoolPaymentList.isEmpty()) {
			List<SchoolPaymentCategoryModel> schoolPaymentPromoted = new ArrayList<SchoolPaymentCategoryModel>();
			for(SchoolPaymentCategoryModel schoolPayment : schoolPaymentList) {
				SchoolPaymentCategoryModel schoolPaymentTemp = new SchoolPaymentCategoryModel();
				schoolPaymentTemp.setSessionDetails(sessionDetails);
				schoolPaymentTemp.setSchoolProfile(schoolProfile);

				if(schoolPayment.getPaymentCategory() != null) {
					PaymentCategoryModel paymentCategory = new PaymentCategoryModel(schoolPayment.getPaymentCategory().getPaymentCategoryCode());
					schoolPaymentTemp.setPaymentCategory(paymentCategory);
				}
				schoolPaymentTemp.setSchoolPaymentCategoryId(null);
				schoolPaymentPromoted.add(schoolPaymentTemp);
			}
			mapPromoted.put("schoolPaymentList", schoolPaymentPromoted);
		}
		
		if(classFeeCategoryList != null && !classFeeCategoryList.isEmpty()) {
			List<ClassFeeCategoryModel> classFeeCategoryPromoted = new ArrayList<ClassFeeCategoryModel>();
			for(ClassFeeCategoryModel classFeeCategory : classFeeCategoryList) {
				ClassFeeCategoryModel classFeeCategoryTemp = new ClassFeeCategoryModel();
				BeanUtils.copyProperties(classFeeCategory, classFeeCategoryTemp);
				classFeeCategoryTemp.setSessionDetails(sessionDetails);
				classFeeCategoryTemp.setSchoolProfile(schoolProfile);
				if(classFeeCategory.getClassInformation() != null) {
					ClassInformationModel classInformation = new ClassInformationModel(classFeeCategory.getClassInformation().getClassCode());
					classFeeCategoryTemp.setClassInformation(classInformation);
				}
				if(classFeeCategory.getSectionInformation() != null) {
					SectionInformationModel sectionInformation = new SectionInformationModel(classFeeCategory.getSectionInformation().getSectionCode());
					classFeeCategoryTemp.setSectionInformation(sectionInformation);
				}
				if(classFeeCategory.getFeeCategory() != null) {
					FeeCategoryModel feeCategory = new FeeCategoryModel(classFeeCategory.getFeeCategory().getFeeCategoryCode());
					classFeeCategoryTemp.setFeeCategory(feeCategory);
				}

				ClassFeeCategoryId classFeeCategoryId = new ClassFeeCategoryId();
				
				classFeeCategoryId.setClassCode(classFeeCategory.getClassInformation().getClassCode());
				classFeeCategoryId.setFeeCategoryCode(classFeeCategory.getFeeCategory().getFeeCategoryCode());
				classFeeCategoryId.setSchoolCode(schoolCode);
				classFeeCategoryId.setSessionCode(sessionCode);
				classFeeCategoryId.setSectionCode(classFeeCategory.getSectionInformation().getSectionCode());
				classFeeCategoryTemp.setId(classFeeCategoryId);
				
				classFeeCategoryPromoted.add(classFeeCategoryTemp);
			}
			mapPromoted.put("classFeeCategoryList", classFeeCategoryPromoted);
		}
		
		if(reportCardConfigList != null && !reportCardConfigList.isEmpty()) {
			List<ReportCardConfigModel> reportCardConfigPromoted = new ArrayList<ReportCardConfigModel>();
			for(ReportCardConfigModel reportCardConfig : reportCardConfigList) {
				ReportCardConfigModel reportCardConfigTemp = new ReportCardConfigModel();
				BeanUtils.copyProperties(reportCardConfig, reportCardConfigTemp);
				reportCardConfigTemp.setSessionDetails(sessionDetails);
				reportCardConfigTemp.setSchoolProfile(schoolProfile);
				if(reportCardConfig.getClassInformation() != null) {
					ClassInformationModel classInformation = new ClassInformationModel(reportCardConfig.getClassInformation().getClassCode());
					reportCardConfigTemp.setClassInformation(classInformation);
				}
				if(reportCardConfig.getSectionInformation() != null) {
					SectionInformationModel sectionInformation = new SectionInformationModel(reportCardConfig.getSectionInformation().getSectionCode());
					reportCardConfigTemp.setSectionInformation(sectionInformation);
				}
				reportCardConfigPromoted.add(reportCardConfigTemp);
			}
			mapPromoted.put("reportCardConfigList", reportCardConfigPromoted);
		}
		return mapPromoted;
	}
}
