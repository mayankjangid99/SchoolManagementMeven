package com.school.common.reports.model;

import java.util.List;


public class StudentReportCardReportModel 
{
	private String admissionNo;
	private String sFirstName;
	private String rollNo;
	private String className;
	private String sectionName;
	private String fEmail;
	private String sImage;
	private String sAddress;
	private String fMobile;
	private String gender;
	private String fatherName;
	private String motherName;
	private String receiptNo;
	private String schoolName;
	private String schoolLogo;
	private String schoolAddress;
	private List<ReceiptFeeSubReportModel> receiptFeeSubReportList;
	private String col0;
	private String header0;
	
	
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getsFirstName() {
		return sFirstName;
	}
	public void setsFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getsImage() {
		return sImage;
	}
	public void setsImage(String sImage) {
		this.sImage = sImage;
	}
	public String getsAddress() {
		return sAddress;
	}
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public List<ReceiptFeeSubReportModel> getReceiptFeeSubReportList() {
		return receiptFeeSubReportList;
	}
	public void setReceiptFeeSubReportList(
			List<ReceiptFeeSubReportModel> receiptFeeSubReportList) {
		this.receiptFeeSubReportList = receiptFeeSubReportList;
	}
	public String getfMobile() {
		return fMobile;
	}
	public void setfMobile(String fMobile) {
		this.fMobile = fMobile;
	}
	public String getfEmail() {
		return fEmail;
	}
	public void setfEmail(String fEmail) {
		this.fEmail = fEmail;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolLogo() {
		return schoolLogo;
	}
	public void setSchoolLogo(String schoolLogo) {
		this.schoolLogo = schoolLogo;
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	/**
	 * @return the col0
	 */
	public String getCol0() {
		return col0;
	}
	/**
	 * @param col0 the col0 to set
	 */
	public void setCol0(String col0) {
		this.col0 = col0;
	}
	/**
	 * @return the header0
	 */
	public String getHeader0() {
		return header0;
	}
	/**
	 * @param header0 the header0 to set
	 */
	public void setHeader0(String header0) {
		this.header0 = header0;
	}
	
	
	
}
