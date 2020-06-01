package com.school.common.generic;


public class StudentProfileUtils {

	private Long StudentDetailsId;
	private String admissionNo;
	private String rollNo;
	private String age;
	private String category;
	private String dateOfBirth;
	private String gender;
	private String previousSchoolName;
	private String address;
	private String email;
	private String firstName;
	private String image;
	private String lastName;
	private String middleName;
	private String studentMobile;
	private String fullname;
	private String className;
	private String classCode;
	private String sectionName;
	private String sectionCode;


	public Long getStudentDetailsId() {
		return StudentDetailsId;
	}




	public void setStudentDetailsId(Long studentDetailsId) {
		StudentDetailsId = studentDetailsId;
	}




	public String getAdmissionNo() {
		return admissionNo;
	}




	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}




	public String getRollNo() {
		return rollNo;
	}




	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}




	public String getAge() {
		return age;
	}




	public void setAge(String age) {
		this.age = age;
	}




	public String getCategory() {
		return category;
	}




	public void setCategory(String category) {
		this.category = category;
	}



	public String getDateOfBirth() {
		return dateOfBirth;
	}




	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getPreviousSchoolName() {
		return previousSchoolName;
	}




	public void setPreviousSchoolName(String previousSchoolName) {
		this.previousSchoolName = previousSchoolName;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getMiddleName() {
		return middleName;
	}




	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}




	public String getStudentMobile() {
		return studentMobile;
	}




	public void setStudentMobile(String studentMobile) {
		this.studentMobile = studentMobile;
	}




	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	



	public String getClassName() {
		return className;
	}




	public void setClassName(String className) {
		this.className = className;
	}




	public String getClassCode() {
		return classCode;
	}




	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}




	public String getSectionName() {
		return sectionName;
	}




	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}




	public String getSectionCode() {
		return sectionCode;
	}




	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}




	public String getFullname() {
		if(!"".equals(this.middleName) && this.middleName != null)
		{
			this.fullname = this.firstName + " " + this.middleName + " " + this.lastName;
		}
		else
		{
			this.fullname = this.firstName + " " + this.lastName;
		}
		return this.fullname;
	}

	
	
}

