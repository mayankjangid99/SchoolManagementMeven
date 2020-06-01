package com.school.common.reports.model;

import java.util.List;


public class StudentDetailsReportModel 
{
	private String admissionNo;
	private String fullName;
	private String rollNo;
	private String sImage;
	private String gender;	
	private String fatherName;
	private String motherName;
	private String schoolName;
	private String schoolLogo;
	private String schoolAddress;
	private String className;
	private String sectionName;
	private String feeStatus;
	private List<?> subReportDataList;
	
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getsImage() {
		return sImage;
	}
	public void setsImage(String sImage) {
		this.sImage = sImage;
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
	public List<?> getSubReportDataList() {
		return subReportDataList;
	}
	public void setSubReportDataList(List<?> subReportDataList) {
		this.subReportDataList = subReportDataList;
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
	public String getFeeStatus() {
		return feeStatus;
	}
	public void setFeeStatus(String feeStatus) {
		this.feeStatus = feeStatus;
	}
	
	
}
