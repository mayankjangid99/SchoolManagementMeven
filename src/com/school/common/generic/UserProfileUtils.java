package com.school.common.generic;

public class UserProfileUtils 
{
	private Long userId;
	private String encUserId;
	private String userDetailsId;
	private String username;
	private String password;
	private String encPassword;
	private String fullname;
	private String email;
	private String userRoleId;
	private String userRoleName;
	private String image;
	private String firstName;
	private String middleName;
	private String lastName;
	private String token;
	private String checkInStatus;
	private String checkIn;
	private String checkOut;
	private String classCode;
	private String sectionCode;
	private String schoolCode;
	private String theme;
	private StudentProfileUtils studentProfileUtils;
	

	public String getUserDetailsId() {
		return userDetailsId;
	}
	public void setUserDetailsId(String userDetailsId) {
		this.userDetailsId = userDetailsId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the encPassword
	 */
	public String getEncPassword() {
		return encPassword;
	}
	/**
	 * @param encPassword the encPassword to set
	 */
	public void setEncPassword(String encPassword) {
		this.encPassword = encPassword;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEncUserId() {
		return encUserId;
	}
	public void setEncUserId(String encUserId) {
		this.encUserId = encUserId;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
	public String getCheckInStatus() {
		return checkInStatus;
	}
	public void setCheckInStatus(String checkInStatus) {
		this.checkInStatus = checkInStatus;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public StudentProfileUtils getStudentProfileUtils() {
		return studentProfileUtils;
	}
	public void setStudentProfileUtils(StudentProfileUtils studentProfileUtils) {
		this.studentProfileUtils = studentProfileUtils;
	}
		
	
}
