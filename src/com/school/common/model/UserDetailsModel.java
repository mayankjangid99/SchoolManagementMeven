package com.school.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "user_details")
public class UserDetailsModel implements EntityModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3160571101118444804L;
	private Long userDetailsId;
	private String createdBy;
	private Date createdOn = new Date();
	private String email;
	private String mobile;
	private String firstname;
	private String fullname;
	private String image;
	private String lastname;
	private String middlename;
	private String modifiedBy;
	private Date modifiedOn;
	private String password;
	private String pwd;
	private String username;
	private String classCode;
	private String sectionCode;
	private String token;
	private UserModel user;
	private SchoolProfileModel schoolProfile;
	private UserSettingModel userSetting;
	private ParentDetailsModel parentDetails;
	
	
	public UserDetailsModel(){}

	public UserDetailsModel(Long userDetailsId, String createdBy, Date createdOn,
			String email, String firstname, String fullname, String image,
			String lastname, String middlename, String modifiedBy,
			Date modifiedOn, String password, String username, UserModel user, SchoolProfileModel schoolProfile,
			UserSettingModel userSetting, ParentDetailsModel parentDetails) {
		this.userDetailsId = userDetailsId;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.email = email;
		this.firstname = firstname;
		this.fullname = fullname;
		this.image = image;
		this.lastname = lastname;
		this.middlename = middlename;
		this.modifiedBy = modifiedBy;
		this.modifiedOn = modifiedOn;
		this.password = password;
		this.username = username;
		this.user = user;
		this.schoolProfile = schoolProfile;
		this.userSetting = userSetting;
		this.parentDetails = parentDetails;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_details_id")
	public Long getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}



	@Column(name = "created_by", length = 50)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", length = 0)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "email", nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	@Column(name = "mobile", nullable = false, length = 10)
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "firstname", nullable = false, length = 50)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Column(name = "lastname", nullable = false, length = 50)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "middlename", length = 50)
	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	@Column(name = "modified_by", length = 50)
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_on", length = 0)
	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	

	@Column(name = "username", nullable = false, unique = true, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	@Column(name = "password", nullable = false, length = 150)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the pwd
	 */
	@Column(name = "pwd", nullable = false, length = 150)
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userDetails")
	public UserModel getUser() {
		return user;
	}


	public void setUser(UserModel user) {
		this.user = user;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_code", nullable = true)
	public SchoolProfileModel getSchoolProfile() {
		return schoolProfile;
	}

	public void setSchoolProfile(SchoolProfileModel schoolProfile) {
		this.schoolProfile = schoolProfile;
	}
	
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userDetails")
	public UserSettingModel getUserSetting() {
		return userSetting;
	}

	public void setUserSetting(UserSettingModel userSetting) {
		this.userSetting = userSetting;
	}

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_details_id", nullable = true)
	public ParentDetailsModel getParentDetails() {
		return parentDetails;
	}

	public void setParentDetails(ParentDetailsModel parentDetails) {
		this.parentDetails = parentDetails;
	}
	
	
	@Column(name = "class_code", length = 5)
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	@Column(name = "section_code", length = 5)
	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	
	/**
	 * @return the token
	 */
	@Column(name = "token", length = 500)
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	@Transient
	public String getFullname() {
		if(this.fullname != null && !"".equals(this.fullname))
		{
			return this.fullname;
		}
		else if(!"".equals(this.middlename) && this.middlename != null)
		{
			this.fullname = this.firstname + " " + this.middlename + " " + this.lastname;
		}
		else
		{
			this.fullname = this.firstname + " " + this.lastname;
		}
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
}
