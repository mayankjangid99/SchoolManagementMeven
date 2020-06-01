package com.school.common.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.services.ClassSectionServices;
import com.school.common.services.FeeCategoryServices;
import com.school.common.services.GlobalConfigServices;
import com.school.common.services.LoginServices;
import com.school.common.services.PaymentCategoryServices;
import com.school.common.services.ReportCardConfigServices;
import com.school.common.services.SubjectServices;
import com.school.common.services.TransportServices;
import com.school.common.services.UserCheckInServices;
import com.school.common.services.UserServices;

@Service(value = "facadeServicesManager")
public class FacadeServicesManager 
{
	@Autowired
	private ClassSectionServices classSectionServices;
	
	@Autowired
	private GlobalConfigServices globalConfigServices;
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private LoginServices loginServices;
	
	@Autowired
	private UserCheckInServices userCheckInServices;
	
	@Autowired
	private TransportServices transportServices;
	
	@Autowired
	private FeeCategoryServices feeCategoryServices;
	
	@Autowired
	private SubjectServices subjectServices;
	
	@Autowired
	private PaymentCategoryServices paymentCategoryServices;
	
	@Autowired
	private ReportCardConfigServices reportCardConfigServices;
	

	public ClassSectionServices getClassSectionServices() {
		return classSectionServices;
	}

	public void setClassSectionServices(ClassSectionServices classSectionServices) {
		this.classSectionServices = classSectionServices;
	}

	public GlobalConfigServices getGlobalConfigServices() {
		return globalConfigServices;
	}

	public void setGlobalConfigServices(GlobalConfigServices globalConfigServices) {
		this.globalConfigServices = globalConfigServices;
	}

	public UserServices getUserServices() {
		return userServices;
	}

	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

	public LoginServices getLoginServices() {
		return loginServices;
	}

	public void setLoginServices(LoginServices loginServices) {
		this.loginServices = loginServices;
	}

	public UserCheckInServices getUserCheckInServices() {
		return userCheckInServices;
	}

	public void setUserCheckInServices(UserCheckInServices userCheckInServices) {
		this.userCheckInServices = userCheckInServices;
	}

	/**
	 * @return the transportServices
	 */
	public TransportServices getTransportServices() {
		return transportServices;
	}

	/**
	 * @param transportServices the transportServices to set
	 */
	public void setTransportServices(TransportServices transportServices) {
		this.transportServices = transportServices;
	}

	/**
	 * @return the feeCategoryServices
	 */
	public FeeCategoryServices getFeeCategoryServices() {
		return feeCategoryServices;
	}

	/**
	 * @param feeCategoryServices the feeCategoryServices to set
	 */
	public void setFeeCategoryServices(FeeCategoryServices feeCategoryServices) {
		this.feeCategoryServices = feeCategoryServices;
	}

	/**
	 * @return the subjectServices
	 */
	public SubjectServices getSubjectServices() {
		return subjectServices;
	}

	/**
	 * @param subjectServices the subjectServices to set
	 */
	public void setSubjectServices(SubjectServices subjectServices) {
		this.subjectServices = subjectServices;
	}

	public PaymentCategoryServices getPaymentCategoryServices() {
		return paymentCategoryServices;
	}

	public void setPaymentCategoryServices(PaymentCategoryServices paymentCategoryServices) {
		this.paymentCategoryServices = paymentCategoryServices;
	}

	/**
	 * @return the reportCardConfigServices
	 */
	public ReportCardConfigServices getReportCardConfigServices() {
		return reportCardConfigServices;
	}

	/**
	 * @param reportCardConfigServices the reportCardConfigServices to set
	 */
	public void setReportCardConfigServices(ReportCardConfigServices reportCardConfigServices) {
		this.reportCardConfigServices = reportCardConfigServices;
	}
	
	
	
	
}
