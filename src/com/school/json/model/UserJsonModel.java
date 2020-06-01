package com.school.json.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserJsonModel 
{
	private Long userId;
	private String userRoleId;
	private String userDetailsId;
	private String userRoleName;
	private String active;
	private String code;
	private String createdBy;
	private String createdOn;
	private String email;
	private String firstname;
	private String fullname;
	private String image;
	private String ip;
	private String lastname;
	private String middlename;
	private String modifiedBy;
	private String modifiedOn;
	private String password;
	private String salt;
	private String username;
	

	@JsonProperty(value="userId")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@JsonProperty(value="userRoleId")
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	@JsonProperty(value="userDetailsId")
	public String getUserDetailsId() {
		return userDetailsId;
	}
	public void setUserDetailsId(String userDetailsId) {
		this.userDetailsId = userDetailsId;
	}
	
	@JsonProperty(value="userRoleName")
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	@JsonProperty(value="active")
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}

	@JsonProperty(value="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty(value="createdBy")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@JsonProperty(value="createdOn")
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	@JsonProperty(value="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty(value="firstname")
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@JsonProperty(value="fullname")
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@JsonProperty(value="image")
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	@JsonProperty(value="ip")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	@JsonProperty(value="lastname")
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@JsonProperty(value="middlename")
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	@JsonProperty(value="modifiedBy")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@JsonProperty(value="modifiedOn")
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@JsonProperty(value="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonProperty(value="salt")
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

	@JsonProperty(value="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
