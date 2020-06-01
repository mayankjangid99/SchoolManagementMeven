package com.school.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_setting")
public class UserSettingModel {

	private String username;
	private String theme;
	private UserDetailsModel userDetails;
	
	public UserSettingModel(){}
	
	public UserSettingModel(String username, String theme,
			UserDetailsModel userDetails) {
		this.username = username;
		this.theme = theme;
		this.userDetails = userDetails;
	}
	
	@Id
	@Column(name = "username", nullable = false, unique = true, length = 50)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "theme", length = 20)
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_details_id")
	public UserDetailsModel getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetailsModel userDetails) {
		this.userDetails = userDetails;
	}
	
	
	
}
