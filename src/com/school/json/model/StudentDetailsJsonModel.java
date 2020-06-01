package com.school.json.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDetailsJsonModel 
{
	private String admissionNo;
	private String sFirstName;
	private String sName;
	private String sMiddleNname;
	private String sLastName;
	private String rollNo;
	private String status;
	private String className;
	private String sectionName;
	private String sEmail;
	private String previousSchoolName;
	private String sImage;
	private String sAddress;
	private String age;
	private String sMobile;
	private Date dob;
	private String gender;
	private String createdBy;
	private String modifiedBy;
	private Date createdOn;
	private Date modifiedOn;
	
	
	private String fName;
	private String mName;
	private String fOccupation;
	private String mOccupation;
	private String fMobileNo;
	private String mMobileNo;
	private String fEmail;
	private String mEmail;
	private String pAddress;
	
	private GenericJsonListContainerModel dataContainer;;
	

	@JsonProperty(value="admissionNo")
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
			
	/**
	 * @return the sName
	 */
	@JsonProperty(value="sName")
	public String getsName() {
		return sName;
	}
	/**
	 * @param sName the sName to set
	 */
	public void setsName(String sName) {
		this.sName = sName;
	}
	
	@JsonProperty(value="sFirstName")
	public String getsFirstName() {
		return sFirstName;
	}
	public void setsFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}
	
	@JsonProperty(value="sMiddleNname")
	public String getsMiddleNname() {
		return sMiddleNname;
	}
	public void setsMiddleNname(String sMiddleNname) {
		this.sMiddleNname = sMiddleNname;
	}
	
	@JsonProperty(value="sLastName")
	public String getsLastName() {
		return sLastName;
	}
	public void setsLastName(String sLastName) {
		this.sLastName = sLastName;
	}
	
	@JsonProperty(value="rollNo")
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	
	@JsonProperty(value="className")
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	@JsonProperty(value="sectionName")
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	@JsonProperty(value="sEmail")
	public String getsEmail() {
		return sEmail;
	}
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	
	@JsonProperty(value="previousSchoolName")
	public String getPreviousSchoolName() {
		return previousSchoolName;
	}
	public void setPreviousSchoolName(String previousSchoolName) {
		this.previousSchoolName = previousSchoolName;
	}
	
	@JsonProperty(value="sImage")
	public String getsImage() {
		return sImage;
	}
	public void setsImage(String sImage) {
		this.sImage = sImage;
	}
	
	@JsonProperty(value="sAddress")
	public String getsAddress() {
		return sAddress;
	}
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	
	@JsonProperty(value="age")
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	@JsonProperty(value="sMobile")
	public String getsMobile() {
		return sMobile;
	}
	public void setsMobile(String sMobile) {
		this.sMobile = sMobile;
	}
	
	@JsonProperty(value="dob")
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@JsonProperty(value="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@JsonProperty(value="createdBy")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@JsonProperty(value="modifiedBy")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@JsonProperty(value="createdOn")
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@JsonProperty(value="modifiedOn")
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	
	
	
	
	
	@JsonProperty(value="fName")
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	@JsonProperty(value="mName")
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	
	@JsonProperty(value="fOccupation")
	public String getfOccupation() {
		return fOccupation;
	}
	public void setfOccupation(String fOccupation) {
		this.fOccupation = fOccupation;
	}
	
	@JsonProperty(value="mOccupation")
	public String getmOccupation() {
		return mOccupation;
	}
	public void setmOccupation(String mOccupation) {
		this.mOccupation = mOccupation;
	}
	
	@JsonProperty(value="fMobileNo")
	public String getfMobileNo() {
		return fMobileNo;
	}
	public void setfMobileNo(String fMobileNo) {
		this.fMobileNo = fMobileNo;
	}
	
	@JsonProperty(value="mMobileNo")
	public String getmMobileNo() {
		return mMobileNo;
	}
	public void setmMobileNo(String mMobileNo) {
		this.mMobileNo = mMobileNo;
	}
	
	@JsonProperty(value="fEmail")
	public String getfEmail() {
		return fEmail;
	}
	public void setfEmail(String fEmail) {
		this.fEmail = fEmail;
	}
	
	@JsonProperty(value="mEmail")
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	
	@JsonProperty(value="pAddress")
	public String getpAddress() {
		return pAddress;
	}
	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}
	
	@JsonProperty(value="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonProperty(value="dataContainer")
	public GenericJsonListContainerModel getDataContainer() {
		return dataContainer;
	}
	public void setDataContainer(GenericJsonListContainerModel dataContainer) {
		this.dataContainer = dataContainer;
	}
	
	
	
	
}
