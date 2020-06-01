package com.school.common.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.dao.ApplicationSettingsDao;
import com.school.common.dao.ClassSectionDao;
import com.school.common.dao.FeeCategoryDao;
import com.school.common.dao.GlobalConfigDao;
import com.school.common.dao.MicroServiceConfigDao;
import com.school.common.dao.PaymentCategoryDao;
import com.school.common.dao.SchoolProfileDao;
import com.school.common.dao.SchoolSessionDao;
import com.school.common.dao.StudentAdditionalFeeDetailsDao;
import com.school.common.dao.StudentDetailsDao;
import com.school.common.dao.UserAttendanceDao;
import com.school.common.dao.UserCheckInDao;
import com.school.common.dao.UserDao;

@Service(value = "facadeDaoManager")
public class FacadeDaoManager 
{
	
	@Autowired
	private ClassSectionDao classSectionDao;
	
	@Autowired
	private SchoolSessionDao schoolSessionDao;
	
	@Autowired
	private StudentDetailsDao studentDetailsDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserCheckInDao userCheckInDao;
	
	@Autowired
	private UserAttendanceDao userAttendanceDetailsDao;
	
	@Autowired
	private GlobalConfigDao globalConfigDao;

	@Autowired
	private SchoolProfileDao schoolProfileDao;
	
	@Autowired
	private FeeCategoryDao feeCategoryDao;
	
	@Autowired
	private ApplicationSettingsDao applicationSettingsDao;
	
	@Autowired
	private StudentAdditionalFeeDetailsDao studentAdditionalFeeDetailsDao;
	
	@Autowired
	private PaymentCategoryDao paymentCategoryDao;
	
	@Autowired
	private MicroServiceConfigDao microServiceConfigDao;
	
	
	public ClassSectionDao getClassSectionDao() {
		return classSectionDao;
	}

	public void setClassSectionDao(ClassSectionDao classSectionDao) {
		this.classSectionDao = classSectionDao;
	}

	public SchoolSessionDao getSchoolSessionDao() {
		return schoolSessionDao;
	}

	public void setSchoolSessionDao(SchoolSessionDao schoolSessionDao) {
		this.schoolSessionDao = schoolSessionDao;
	}

	public StudentDetailsDao getStudentDetailsDao() {
		return studentDetailsDao;
	}

	public void setStudentDetailsDao(StudentDetailsDao studentDetailsDao) {
		this.studentDetailsDao = studentDetailsDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserCheckInDao getUserCheckInDao() {
		return userCheckInDao;
	}

	public void setUserCheckInDao(UserCheckInDao userCheckInDao) {
		this.userCheckInDao = userCheckInDao;
	}

	public UserAttendanceDao getUserAttendanceDetailsDao() {
		return userAttendanceDetailsDao;
	}

	public void setUserAttendanceDetailsDao(
			UserAttendanceDao userAttendanceDetailsDao) {
		this.userAttendanceDetailsDao = userAttendanceDetailsDao;
	}

	public GlobalConfigDao getGlobalConfigDao() {
		return globalConfigDao;
	}

	public void setGlobalConfigDao(GlobalConfigDao globalConfigDao) {
		this.globalConfigDao = globalConfigDao;
	}

	public SchoolProfileDao getSchoolProfileDao() {
		return schoolProfileDao;
	}

	public void setSchoolProfileDao(SchoolProfileDao schoolProfileDao) {
		this.schoolProfileDao = schoolProfileDao;
	}

	public FeeCategoryDao getFeeCategoryDao() {
		return feeCategoryDao;
	}

	public void setFeeCategoryDao(FeeCategoryDao feeCategoryDao) {
		this.feeCategoryDao = feeCategoryDao;
	}

	public ApplicationSettingsDao getApplicationSettingsDao() {
		return applicationSettingsDao;
	}

	public void setApplicationSettingsDao(ApplicationSettingsDao applicationSettingsDao) {
		this.applicationSettingsDao = applicationSettingsDao;
	}

	/**
	 * @return the studentAdditionalFeeDetailsDao
	 */
	public StudentAdditionalFeeDetailsDao getStudentAdditionalFeeDetailsDao() {
		return studentAdditionalFeeDetailsDao;
	}

	/**
	 * @param studentAdditionalFeeDetailsDao the studentAdditionalFeeDetailsDao to set
	 */
	public void setStudentAdditionalFeeDetailsDao(StudentAdditionalFeeDetailsDao studentAdditionalFeeDetailsDao) {
		this.studentAdditionalFeeDetailsDao = studentAdditionalFeeDetailsDao;
	}

	public PaymentCategoryDao getPaymentCategoryDao() {
		return paymentCategoryDao;
	}

	public void setPaymentCategoryDao(PaymentCategoryDao paymentCategoryDao) {
		this.paymentCategoryDao = paymentCategoryDao;
	}

	/**
	 * @return the microServiceConfigDao
	 */
	public MicroServiceConfigDao getMicroServiceConfigDao() {
		return microServiceConfigDao;
	}

	/**
	 * @param microServiceConfigDao the microServiceConfigDao to set
	 */
	public void setMicroServiceConfigDao(MicroServiceConfigDao microServiceConfigDao) {
		this.microServiceConfigDao = microServiceConfigDao;
	}

		
	
}
