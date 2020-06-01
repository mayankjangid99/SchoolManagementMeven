package com.school.common.services.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.school.common.generic.CommonConstaint;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.UploadFilesUtils;
import com.school.common.generic.UserProfileUtils;
import com.school.common.model.ParentDetailsModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.UserDetailsModel;
import com.school.common.model.UserModel;
import com.school.common.model.UserRoleModel;
import com.school.common.model.UserSettingModel;
import com.school.json.model.UserJsonModel;

@Service
public class UserBusiness 
{

	private static Logger LOG = LoggerFactory.getLogger(UserBusiness.class);
	
	public List<UserRoleModel> addUser(List<UserRoleModel> list) {
		List<UserRoleModel> userRolelist = new ArrayList<UserRoleModel>();
		UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil();
		if(!"ROLE_SUPADMIN".equalsIgnoreCase(userProfileUtils.getUserRoleId()))
		{
			Iterator<UserRoleModel> iterator = list.iterator();
			while (iterator.hasNext()) 
			{
				UserRoleModel userRoleModel = (UserRoleModel) iterator.next();
				if(!"".equals(userRoleModel.getUserRoleId()) && "ROLE_SUPADMIN".equalsIgnoreCase(userRoleModel.getUserRoleId()))
				{
					LOG.info("INGNORE: Role(ROLE_SUPADMIN) from add user form because user is not super admin");
				}
				else
				{
					userRolelist.add(userRoleModel);
				}
			}
			return userRolelist;
		}
		else
		{
			return list;
		}
	}
	
	
	public UserModel saveUser(CommonsMultipartFile pimage, String pactive, UserRoleModel userRole, UserModel user, UserDetailsModel userDetails, UserSettingModel userSetting, ParentDetailsModel parentDetails){
		if("ROLE_USER".equals(userRole.getUserRoleId())){
			userDetails.setClassCode(null);
			userDetails.setSectionCode(null);
			userDetails.setParentDetails(parentDetails);
		}

		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(12);
		String encPwd = cryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encPwd);
		userDetails.setPassword(encPwd);
		
		user.setUserRole(userRole);
		userSetting.setTheme("skin-blue");
		userSetting.setUsername(user.getUsername());
		userSetting.setUserDetails(userDetails);
		userDetails.setUserSetting(userSetting);
		userDetails.setCreatedBy(SessionManagerDataHelper.getUsername()); 
		user.setUserDetails(userDetails);
		String schoolCode = SessionManagerDataHelper.getSchoolCode();
		
		if(schoolCode != null)
			user.getUserDetails().setSchoolProfile(new SchoolProfileModel(schoolCode));
		String uImage = UploadFilesUtils.uploadFileOnServer(schoolCode, pimage, CommonConstaint.USER_IMAGE_PATH, user.getUsername());
		if(uImage != null)
			user.getUserDetails().setImage(uImage);
		if("A".equalsIgnoreCase(pactive))
			user.setActive(true);
		else
			user.setActive(false);
		return user;
	}
	
	
	public void updateUser(CommonsMultipartFile pimage, String pactive, UserRoleModel userRole, UserModel user, UserDetailsModel userDetails, UserSettingModel userSetting, ParentDetailsModel parentDetails){
		if("ROLE_USER".equals(userRole.getUserRoleId())){
			user.getUserDetails().setClassCode(null);
			user.getUserDetails().setSectionCode(null);
			userDetails.setParentDetails(parentDetails);
		}
		/*if(user.getPassword().length() <= 15)
		{
			BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(12);
			String encPwd = cryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(cryptPasswordEncoder.encode(encPwd));
			userDetails.setPassword(encPwd);
		}*/

		user.setUserRole(userRole);
		userSetting.setTheme("skin-blue");
		userSetting.setUsername(user.getUsername());
		userSetting.setUserDetails(userDetails);
		userDetails.setUserSetting(userSetting);
		userDetails.setModifiedBy(SessionManagerDataHelper.getUsername()); 
		userDetails.setModifiedOn(new Date());
		user.setUserDetails(userDetails);
		
		String schoolCode = SessionManagerDataHelper.getSchoolProfileUtil().getSchoolCode();

		//Its for super admin
		if(schoolCode != null && user.getUserDetails().getSchoolProfile() == null)
			user.getUserDetails().setSchoolProfile(new SchoolProfileModel(schoolCode));
		
		if(!"".equalsIgnoreCase(pimage.getOriginalFilename()))
		{
			String uImage = UploadFilesUtils.uploadFileOnServer(schoolCode, pimage, CommonConstaint.USER_IMAGE_PATH, user.getUsername());
			user.getUserDetails().setImage(uImage);
		}
		if("A".equalsIgnoreCase(pactive))
			user.setActive(true);
		else
			user.setActive(false);
		user.getUserDetails().setModifiedOn(new Date());
		user.getUserDetails().setModifiedBy(SessionManagerDataHelper.getUsername());
	}
	
	
	public List<UserJsonModel> fetchUser(List<UserModel> users)
	{
		List<UserJsonModel> userJsonList = new ArrayList<UserJsonModel>();
		UserJsonModel userJsonModel = null;
		Iterator<UserModel> iterator = users.iterator();
		while (iterator.hasNext()) 
		{
			UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil();
			if(!"ROLE_SUPADMIN".equalsIgnoreCase(userProfileUtils.getUserRoleId()))
			{
				UserModel userModel = (UserModel) iterator.next();
				userJsonModel = new UserJsonModel();
				
				if(userModel.isActive())
					userJsonModel.setActive("Y");
				else
					userJsonModel.setActive("N");
				
				userJsonModel.setUserDetailsId(userModel.getUserDetails().getUserDetailsId().toString());
				userJsonModel.setEmail(userModel.getUserDetails().getEmail());
				userJsonModel.setFirstname(userModel.getUserDetails().getFirstname());
				userJsonModel.setFullname(userModel.getUserDetails().getFullname());
				userJsonModel.setImage(userModel.getUserDetails().getImage());
				userJsonModel.setLastname(userModel.getUserDetails().getLastname());
				userJsonModel.setMiddlename(userModel.getUserDetails().getMiddlename());
				userJsonModel.setUsername(userModel.getUserDetails().getUsername());
				userJsonModel.setUserRoleId(userModel.getUserRole().getUserRoleId());
				userJsonModel.setUserRoleName(userModel.getUserRole().getUserRoleName());
				userJsonModel.setUserId(userModel.getUserId());
				
				if(!"".equals(userModel.getUserRole().getUserRoleId()) && "ROLE_SUPADMIN".equalsIgnoreCase(userModel.getUserRole().getUserRoleId()))
				{
					LOG.info("INGNORE: User ignore(ROLE_SUPADMIN) becouse user is not super admin");
				}
				else
				{
					userJsonList.add(userJsonModel);
				}
			}
			else
			{
				UserModel userModel = (UserModel) iterator.next();
				userJsonModel = new UserJsonModel();
				
				if(userModel.isActive())
					userJsonModel.setActive("Y");
				else
					userJsonModel.setActive("N");
				
				userJsonModel.setEmail(userModel.getUserDetails().getEmail());
				userJsonModel.setFirstname(userModel.getUserDetails().getFirstname());
				userJsonModel.setFullname(userModel.getUserDetails().getFullname());
				userJsonModel.setImage(userModel.getUserDetails().getImage());
				userJsonModel.setLastname(userModel.getUserDetails().getLastname());
				userJsonModel.setMiddlename(userModel.getUserDetails().getMiddlename());
				userJsonModel.setUsername(userModel.getUsername());
				userJsonModel.setUserRoleId(userModel.getUserRole().getUserRoleId());
				userJsonModel.setUserRoleName(userModel.getUserRole().getUserRoleName());
				userJsonModel.setUserId(userModel.getUserId());
				userJsonList.add(userJsonModel);
			}
		}
		return userJsonList;
	}
	
	
}
