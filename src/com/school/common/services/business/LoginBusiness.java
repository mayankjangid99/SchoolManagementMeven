package com.school.common.services.business;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.generic.Base64;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.SchoolProfileUtils;
import com.school.common.generic.SessionManager;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StudentProfileUtils;
import com.school.common.generic.UserProfileUtils;
import com.school.common.model.AdmissionDetailsModel;
import com.school.common.model.AdmissionNumberModel;
import com.school.common.model.ApplicationSettingsModel;
import com.school.common.model.GlobalConfigModel;
import com.school.common.model.MicroServiceConfigModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SchoolSessionModel;
import com.school.common.model.StudentDetailsModel;
import com.school.common.model.UserCheckInModel;
import com.school.common.model.UserModel;

@Service
public class LoginBusiness 
{
	
	private static Logger LOG = LoggerFactory.getLogger(LoginBusiness.class);
	
	private boolean userAuth = false;
	
	public boolean isUserAuth() {
		return userAuth;
	}


	public void setUserAuth(boolean userAuth) {
		this.userAuth = userAuth;
	}


	//	Login User
	@SuppressWarnings("unchecked")
	public ModelAndView loginUser(HttpServletRequest request, UserModel user, List<SchoolSessionModel> sessionList, List<GlobalConfigModel> globalConfigList, 
			List<SchoolProfileModel> schoolProfileList, List<ApplicationSettingsModel> applicationSettingsList, List<AdmissionNumberModel> admissionNumberList, List<MicroServiceConfigModel> microServiceConfigs)
	{
		ModelAndView model = new ModelAndView();
		HttpSession session=request.getSession();	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String authRole= null;
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) auth.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority() != null && !authority.getAuthority().equals("")) {
				authRole = authority.getAuthority();
				break;
			}
		}
		
		
		userAuth = false;		  
		if(auth.isAuthenticated() && user != null) {
			/*if(user.isLogin())
			{
				model.setViewName("redirect:alreadyLogin");
				//model.addObject("error", "User already login, please logout first");
				return model;
			}*/
			userAuth = true;
			String userRole = user.getUserRole().getUserRoleId();
			String userType = null;
			if(userRole.equalsIgnoreCase("ROLE_SUPADMIN") && userRole.equals(authRole))
				userType = "supadmin";
			else if(userRole.equalsIgnoreCase("ROLE_ADMIN") && userRole.equals(authRole))
				userType = "admin";
			else if(userRole.equalsIgnoreCase("ROLE_SUBADMIN") && userRole.equals(authRole))
				userType = "subadmin";
			else if(userRole.equalsIgnoreCase("ROLE_ACC") && userRole.equals(authRole))
				userType = "account";
			else if(userRole.equalsIgnoreCase("ROLE_STAFF") && userRole.equals(authRole))
				userType = "staff";
			else if(userRole.equalsIgnoreCase("ROLE_USER") && userRole.equals(authRole))
				userType = "user";
			
			SchoolProfileModel schoolProfileModel = user.getUserDetails().getSchoolProfile();
			SchoolProfileUtils schoolProfileUtils = new SchoolProfileUtils();
			UserProfileUtils userProfileUtils = new UserProfileUtils();
			
			if(schoolProfileModel != null) {	
	//			Set School Session Details
				Iterator<SchoolSessionModel> iterator = schoolProfileModel.getSchoolSessions().iterator();
				while (iterator.hasNext()) {
					SchoolSessionModel schoolSession = (SchoolSessionModel) iterator.next();
					if(schoolSession.getStatus().equals("C")) {
						schoolProfileUtils.setCurrentSession(schoolSession.getSessionDetails().getSessionCode());
						schoolProfileUtils.setCurrentSessionName(schoolSession.getSessionDetails().getSessionName());
						break;
					}
				}
				schoolProfileUtils.setSchoolSessions(sessionList);

				schoolProfileUtils.setLogo(schoolProfileModel.getLogo());
				schoolProfileUtils.setName(schoolProfileModel.getName());
				schoolProfileUtils.setSchoolCode(schoolProfileModel.getSchoolCode());
				schoolProfileUtils.setStartYear(schoolProfileModel.getStartYear());
				schoolProfileUtils.setRegistrationDate(schoolProfileModel.getRegistrationDate());
				schoolProfileUtils.setEmail(schoolProfileModel.getEmail());
				schoolProfileUtils.setWebsite(schoolProfileModel.getWebsite());
				schoolProfileUtils.setAddress(schoolProfileModel.getAddress());
				schoolProfileUtils.setDistrict(schoolProfileModel.getDistrict());
				schoolProfileUtils.setCity(schoolProfileModel.getCity());
				schoolProfileUtils.setState(schoolProfileModel.getState());
				schoolProfileUtils.setPhone(schoolProfileModel.getPhone());
				schoolProfileUtils.setFax(schoolProfileModel.getFax());
				schoolProfileUtils.setPostcode(schoolProfileModel.getPostcode());
				
				userProfileUtils.setSchoolCode(schoolProfileModel.getSchoolCode());
			}
//			Add User Profile in Session
			userProfileUtils.setUserDetailsId(user.getUserDetails().getUserDetailsId().toString());
			userProfileUtils.setEmail(user.getUserDetails().getEmail());
			userProfileUtils.setImage(user.getUserDetails().getImage());
			userProfileUtils.setUserRoleId(authRole);
			userProfileUtils.setUserRoleName(user.getUserRole().getUserRoleName());
			userProfileUtils.setUsername(user.getUsername());
			userProfileUtils.setEncPassword(user.getPassword());
			userProfileUtils.setToken(user.getUserDetails().getToken());
			userProfileUtils.setFullname(user.getUserDetails().getFullname());
			userProfileUtils.setUserId(user.getUserId());
			userProfileUtils.setEncUserId(Base64.encodeToString(user.getUserId().toString().getBytes(), true));
			userProfileUtils.setTheme(user.getUserDetails().getUserSetting().getTheme());
			userProfileUtils.setClassCode(user.getUserDetails().getClassCode());
			userProfileUtils.setSectionCode(user.getUserDetails().getSectionCode());
			userProfileUtils.setPassword(user.getUserDetails().getPwd());
			

			if(userRole.equalsIgnoreCase("ROLE_USER") && userRole.equals(authRole)) {
				StudentProfileUtils studentProfileUtils = new StudentProfileUtils();
				AdmissionDetailsModel admisDetails = BusinessLogicHelper.getAdmisionDetailsFromStudentDetails(user.getUserDetails().getParentDetails().getStudentDetails(), schoolProfileUtils.getCurrentSession());
				StudentDetailsModel studDetails = user.getUserDetails().getParentDetails().getStudentDetails();
				userProfileUtils.setClassCode(admisDetails.getClassInformation().getClassCode());
				userProfileUtils.setSectionCode(admisDetails.getSectionInformation().getSectionCode());
				
				studentProfileUtils.setClassCode(admisDetails.getClassInformation().getClassCode());
				studentProfileUtils.setClassName(admisDetails.getClassInformation().getClassName());
				studentProfileUtils.setSectionCode(admisDetails.getSectionInformation().getSectionCode());
				studentProfileUtils.setSectionName(admisDetails.getSectionInformation().getSectionName());
				
				studentProfileUtils.setFirstName(studDetails.getFirstName());
				studentProfileUtils.setMiddleName(studDetails.getMiddleName());
				studentProfileUtils.setLastName(studDetails.getLastName());
				studentProfileUtils.setImage(studDetails.getImage());
				studentProfileUtils.setAdmissionNo(studDetails.getAdmissionNo());
				studentProfileUtils.setRollNo(admisDetails.getRollNo().toString());
				
				userProfileUtils.setStudentProfileUtils(studentProfileUtils);
			}
			
			SessionManager sessionManager = new SessionManager();
			
			sessionManager.setSchoolProfileUtils(schoolProfileUtils);
			sessionManager.setUserProfileUtils(userProfileUtils);
			sessionManager.setGlobalConfigList(globalConfigList);
			sessionManager.setSchoolProfiles(schoolProfileList);
			sessionManager.setApplicationSettings(applicationSettingsList);
			sessionManager.setAdmissionNumbers(admissionNumberList);
			sessionManager.setMicroServiceConfigs(microServiceConfigs);
			
			session.setAttribute("SessionManager", sessionManager);
			
//			create cookie if remember
			userCookie(user);
			
			if(userType != null) {
				model.setViewName("redirect:/" + userType + "/welcome");
				return model;
			} else {
				model.setViewName("login");
				model.addObject("error", "Authentication failed, Please contact Administrator.");
				return model;
			}
		} else {
			model.setViewName("login");
			model.addObject("error", "Authentication failed, Please contact Administrator.");
			return model;
		}
	}
	
	
	public String getUserIP() {

		InetAddress ip = null;
		try {
				
			ip = InetAddress.getLocalHost();
			LOG.info("User IP address : " + ip.getHostAddress());
			/*
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
				
			byte[] mac = network.getHardwareAddress();
				
			System.out.print("Current MAC address : ");
				
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
			System.out.println(sb.toString());*/
				
		} catch (UnknownHostException e) {
			LOG.error("ERROR: loginUser() ", e);
			//e.printStackTrace();		
		}
		return ip.getHostAddress();
	}
	
	
	
//	After User Auth manage Data
	public void UserAuthorized(UserCheckInModel userCheckInModel)
	{
		if(userAuth)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");			
			UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil();;
			if(userCheckInModel != null)
			{
				if(userCheckInModel.getCheckIn() != null)
					userProfileUtils.setCheckIn(dateFormat.format(userCheckInModel.getCheckIn()));
				if(userCheckInModel.getCheckOut() != null)
					userProfileUtils.setCheckOut(dateFormat.format(userCheckInModel.getCheckOut()));
				
				if("I".equalsIgnoreCase(userCheckInModel.getStatus()))
				{
					userProfileUtils.setCheckInStatus("checkOut");
				}
				else
				{
					userProfileUtils.setCheckInStatus("checkIn");
				}
			}
			else
			{
				userProfileUtils.setCheckInStatus("checkIn");
			}
			SessionManagerDataHelper.setUserProfileUtils(userProfileUtils);
		}
	}
	
	private void userCookie(UserModel user)
	{
		try {
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil();
			StringBuffer cookieVal = new StringBuffer("uid=" + userProfileUtils.getUsername());
			cookieVal.append("~sid=" + request.getSession().getId());
			cookieVal.append("~uimg=" + user.getUserDetails().getImage());
			//Cookie cookie = new Cookie("SMUser", Base64.encodeToString(cookieVal.toString().getBytes(), true));
			Cookie cookie = new Cookie("SMUser", cookieVal.toString());
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
		} catch (Exception e) {
			LOG.error("ERROR: userCookie() ", e);
		}
	}
	
	
}
