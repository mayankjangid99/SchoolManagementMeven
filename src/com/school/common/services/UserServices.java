package com.school.common.services;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.UserDao;
import com.school.common.facade.FacadeDaoManager;
import com.school.common.generic.Base64;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticData;
import com.school.common.generic.StaticValue;
import com.school.common.micro.gateway.MicroServiceParamsHelper;
import com.school.common.micro.gateway.MicroServicesParams;
import com.school.common.micro.gateway.ServiceGatewayStaticValue;
import com.school.common.micro.gateway.ServicesGateway;
import com.school.common.micro.model.ApiResponse;
import com.school.common.micro.model.UserMicroModel;
import com.school.common.micro.services.UserMicroServices;
import com.school.common.model.MicroServiceConfigModel;
import com.school.common.model.ParentDetailsModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.StudentDetailsModel;
import com.school.common.model.UserDetailsModel;
import com.school.common.model.UserModel;
import com.school.common.model.UserRoleModel;
import com.school.common.model.UserSettingModel;
import com.school.common.services.business.UserBusiness;
import com.school.json.model.UserJsonModel;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServices 
{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserBusiness userBusiness;
	
	@Autowired
	private FacadeDaoManager facadeDaoManager;
	
	@Autowired
	private MessageSource messageSource;	
	
	
	private static Logger LOG = LoggerFactory.getLogger(UserServices.class);
	
	
	public ModelAndView addUser() {
		ModelAndView model = new ModelAndView("admin/addUser");
		List<UserRoleModel> list = userDao.getAllUserRole();
		list = userBusiness.addUser(list);
		model.addObject("UserRoles", list);
		List<SchoolProfileModel> list2 = facadeDaoManager.getSchoolProfileDao().getAllSchoolProfile();
		model.addObject("SchoolProfiles", list2);
		return model;
	}
	
	
	public ModelAndView searchUser(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("admin/searchUser");
		List<UserRoleModel> list = userDao.getAllUserRole();
		list = userBusiness.addUser(list);
		model.addObject("UserRoles", list);
		if("ROLE_SUPADMIN".equals(SessionManagerDataHelper.getUserProfileUtil().getUserRoleId())) {
			List<SchoolProfileModel> list2 = facadeDaoManager.getSchoolProfileDao().getAllSchoolProfile();
			model.addObject("SchoolProfiles", list2);
		}
		return model;
	}
	
	
	public ModelAndView editUser(UserModel userModel) {
		ModelAndView model = new ModelAndView("admin/editUser");
		List<UserRoleModel> list = userDao.getAllUserRole();
		list = userBusiness.addUser(list);
		UserModel user = userDao.getUserByUserId(userModel);
		model.addObject("UserRoles", list);
		model.addObject("User", user);
		List<SchoolProfileModel> list2 = facadeDaoManager.getSchoolProfileDao().getAllSchoolProfile();
		model.addObject("SchoolProfiles", list2);
		return model;
	}
	
//	@After aspect use on this method
	public ModelAndView viewUser(HttpServletRequest request, UserModel userModel) {
		ModelAndView model = new ModelAndView("admin/editUser");
		List<UserRoleModel> list = userDao.getAllUserRole();
		list = userBusiness.addUser(list);
		String encUserId = request.getParameter("encUserId");
		if(encUserId != null && !"".equals(encUserId))
		{
			userModel.setUserId(Long.parseLong(Base64.decode(encUserId)));
		}
		UserModel user = userDao.getUserByUserId(userModel);
		model.addObject("UserRoles", list);
		model.addObject("User", user);
		model.addObject("Operation", "View");
		return model;
	}
	
	public boolean validateUsernameForNewUser(HttpServletRequest request) {
		String username = request.getParameter("username");
		UserModel user = new UserModel();
		user.setUsername(username);
		user = userDao.getUserByUsername(user);
		if(user != null)
			return false;
		else
			return true;		
	}
	
	
//	@After aspect use on this method
	public ModelAndView saveUser(HttpServletRequest request, CommonsMultipartFile pimage, String pactive, UserRoleModel userRole, UserModel user, UserDetailsModel userDetails, UserSettingModel userSetting, ParentDetailsModel parentDetails) {
		ModelAndView model = new ModelAndView("redirect:savedUser");
		if((request.getParameter("admissionNo") != null && !"".equals(request.getParameter("admissionNo")))
				|| (request.getParameter("rollNo") != null && !"".equals(request.getParameter("rollNo")))) {
			if("ROLE_USER".equals(request.getParameter("userRoleId"))){
				UserDetailsModel detailsModel = userDao.getUserDetailsByParentDetailsId(Long.parseLong(request.getParameter("parentDetailsId")));
				if(detailsModel != null) {
					List<UserRoleModel> list = userDao.getAllUserRole();
					list = userBusiness.addUser(list);
					model.addObject("UserRoles", list);
					
					model.setViewName("admin/addUser");	
					model.addObject("ErrorMessage", "Student Parent already have Login Credential, so we can't provide credential...!!!");
					return model;
				}
			}
		}
		
		request.setAttribute("UserPassword", user.getPassword());
		userDetails.setPwd(user.getPassword());
		user = userBusiness.saveUser(pimage, pactive, userRole, user, userDetails, userSetting, parentDetails);
		String currentYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		
		if(!"ROLE_SUPADMIN".equalsIgnoreCase(user.getUserRole().getUserRoleId())) {
			String microServiceEnabled = SessionManagerDataHelper.getMicroServiceConfigParameterValue(StaticValue.MICRO_SERVICE_ENABLED_CONFIG);
			if("Y".equalsIgnoreCase(microServiceEnabled)) {
				UserModel userModel = new UserModel();
				BeanUtils.copyProperties(user, userModel);
				userModel.setPassword((String)request.getAttribute("UserPassword"));
				boolean flag = saveUserMicroService(userModel, model);
				if(!flag)
					return model;
			}
		} else {
			try {
				String microServiceEnabled = request.getParameter("microServiceEnabled");
				String microServiceUrl = request.getParameter("microServiceUrl");
				if(!"Y".equalsIgnoreCase(microServiceEnabled))
					microServiceEnabled = "N";
				MicroServiceConfigModel microServiceConfig = new MicroServiceConfigModel(StaticValue.MICRO_SERVICE_ENABLED_CONFIG, microServiceEnabled, "Micro Service Enabled/Disabled");
				userDao.save(microServiceConfig);
				microServiceConfig = new MicroServiceConfigModel(StaticValue.MICRO_SERVICE_URL_CONFIG, microServiceUrl, "Micro Service URL");
				userDao.save(microServiceConfig);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		userDao.saveUser(user, currentYear);
		return model;
	}
	
	private boolean saveUserMicroService(UserModel user, ModelAndView model) {
		boolean flag = false;
		UserMicroServices ms = ServicesGateway.newUserMicroServices();
		MicroServicesParams params = new MicroServicesParams();
		MicroServiceParamsHelper.getInstance().setMicroServiceParams(ServiceGatewayStaticValue.USER_MICRO_SERVICE.getType(), params);
		
		String viewName = "admin/searchUser";
		UserMicroModel userMicroModel = new UserMicroModel();
		userMicroModel.setUsername(user.getUsername());
		userMicroModel.setPassword(user.getPassword());
		userMicroModel.setRole(user.getUserRole().getUserRoleId());
		params.setMicroEntity(userMicroModel);
		
		ApiResponse response  = ms.addUser(params);
		if(response != null && response.getSuccess()) {
			flag = true;
		} else if(response != null && !response.getSuccess()) {
			model.setViewName(viewName);
			model.addObject("ErrorMessage", response.getMessage());
		} else if(response == null) {
			model.setViewName(viewName);
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.server", null, null));
		}
		return flag;
	}
	
	protected boolean updateUserMicroService(UserModel user, ModelAndView model) {
		boolean flag = false;
		UserMicroServices ms = ServicesGateway.newUserMicroServices();
		MicroServicesParams params = new MicroServicesParams();
		MicroServiceParamsHelper.getInstance().setMicroServiceParams(ServiceGatewayStaticValue.USER_MICRO_SERVICE.getType(), params);
		
		String viewName = "admin/editUser";
		UserMicroModel userMicroModel = new UserMicroModel();
		userMicroModel.setUsername(user.getUsername());
		userMicroModel.setPassword(user.getPassword());
		userMicroModel.setRole(user.getUserRole().getUserRoleId());
		params.setMicroEntity(userMicroModel);
		
		ApiResponse response  = ms.updateUser(params);
		if(response != null && response.getSuccess()) {
			flag = true;
		} else if(response != null && !response.getSuccess()) {
			model.setViewName(viewName);
			model.addObject("ErrorMessage", response.getMessage());
		} else if(response == null) {
			model.setViewName(viewName);
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.server", null, null));
		}
		return flag;
	}
	
	public ModelAndView savedUser() {
		ModelAndView model = new ModelAndView("admin/addUser");
		List<UserRoleModel> list = userDao.getAllUserRole();
		list = userBusiness.addUser(list);
		model.addObject("UserRoles", list);
		model.addObject("SuccessMessage", "User Information Successfully saved... !!!");
		return model;
	}
	
	
//	@After aspect use on this method
	public ModelAndView updateUser(HttpServletRequest request, CommonsMultipartFile pimage, String pactive, UserRoleModel userRole, UserModel user, UserDetailsModel userDetails, UserSettingModel userSetting, ParentDetailsModel parentDetails) {
		ModelAndView model = new ModelAndView("redirect:updatedUser");
		String currentUserRoleId = request.getParameter("currentUserRoleId");
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(12);
		UserModel userModel = facadeDaoManager.getUserDao().getUserByUsername(user);

		userDetails.setPwd(user.getPassword());
		boolean pwdMatcherFlag = cryptPasswordEncoder.matches(user.getPassword(), userModel.getPassword());
		if(pwdMatcherFlag) {
			model = editUser(userModel);
			model.addObject("ErrorMessage", "Please enter different password from last password");
			return model;
		} else if(user.getPassword().length() <= 15) {
			String encPwd = cryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encPwd);
			userDetails.setPassword(encPwd);
		}
		if(currentUserRoleId.equals("ROLE_USER")) {
			model = editUser(userModel);
			model.addObject("ErrorMessage", "Sorry! you can't change status of User...!!!");
			return model;
		}
		userBusiness.updateUser(pimage, pactive, userRole, user, userDetails, userSetting, parentDetails);
		if(!"ROLE_SUPADMIN".equalsIgnoreCase(user.getUserRole().getUserRoleId())) {
			String microServiceEnabled = SessionManagerDataHelper.getMicroServiceConfigParameterValue(StaticValue.MICRO_SERVICE_ENABLED_CONFIG);
			if("Y".equalsIgnoreCase(microServiceEnabled)) {
				UserModel userModel2 = new UserModel();
				BeanUtils.copyProperties(user, userModel2);
				userModel2.setPassword(userDetails.getPwd());
				boolean flag = updateUserMicroService(userModel2, model);
				if(!flag)
					return model;
			}
		}	
		boolean queryStatus = userDao.updateUser(user);
		if(!queryStatus) {
			model.setViewName("admin/editUser");
			model.addObject("ErrorMessage", "User Information can not Successfully updated... !!!");
			return model;
		}
		return model;
	}
	
	public ModelAndView updatedUser() {
		ModelAndView model = new ModelAndView("admin/editUser");
		List<UserRoleModel> list = userDao.getAllUserRole();
		list = userBusiness.addUser(list);
		model.addObject("UserRoles", list);
		model.addObject("SuccessMessage", "User Information Successfully updated... !!!");
		return model;
	}
	
	
	public ModelAndView resultUser(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("admin/resultUser");
		if((request.getParameter("admissionNo") != null && !"".equals(request.getParameter("admissionNo")))
				|| (request.getParameter("rollNo") != null && !"".equals(request.getParameter("rollNo")))){
			if("ROLE_USER".equals(request.getParameter("userRoleId")) && (request.getParameter("parentDetailsId") == null || "".equals(request.getParameter("parentDetailsId")))) {
				List<UserRoleModel> list = userDao.getAllUserRole();
				list = userBusiness.addUser(list);
				model.addObject("UserRoles", list);
				
				model.setViewName("admin/searchUser");	
				model.addObject("ErrorMessage", "Student Parent does't have any Login Credential, please provide credential...!!!");
			} else if("ROLE_USER".equals(request.getParameter("userRoleId"))) {
				UserDetailsModel detailsModel = userDao.getUserDetailsByParentDetailsId(Long.parseLong(request.getParameter("parentDetailsId")));
				if(detailsModel == null){
					List<UserRoleModel> list = userDao.getAllUserRole();
					list = userBusiness.addUser(list);
					model.addObject("UserRoles", list);
					
					model.setViewName("admin/searchUser");	
					model.addObject("ErrorMessage", "Student Parent does't have any Login Credential, please provide credential...!!!");
				}
			}
		}
		model.addObject("queryString", BusinessLogicHelper.getQueryStringFromPost(request));
		return model;
	}
	
	public List<UserJsonModel> fetchUser(HttpServletRequest request, UserModel user, UserRoleModel userRole, 
			UserDetailsModel userDetails, SchoolProfileModel schoolProfile, ParentDetailsModel parentDetails)
	{
		String pActive = request.getParameter("pactive");
		/*if(!"ROLE_SUPADMIN".equals(SessionManagerDataHelper.getUserProfileUtil().getUserRoleId()))
		{
			String schoolCode = SessionManagerDataHelper.getSchoolProfileUtil().getSchoolCode();
			user.getUserDetails().getSchoolProfile().setSchoolCode(schoolCode);
		}*/

		userDetails.setParentDetails(parentDetails);
		userDetails.setSchoolProfile(schoolProfile);
		user.setUserRole(userRole);
		user.setUserDetails(userDetails);
		
		user.getUserDetails().getSchoolProfile().setSchoolCode(SessionManagerDataHelper.getSchoolProfileUtil().getSchoolCode());
		List<UserModel> list = userDao.fetchUser(user, pActive);
		List<UserJsonModel> userJsonList = userBusiness.fetchUser(list);
		return userJsonList;
	}
	
	
	
	public String changeUserActive(HttpServletRequest request, UserModel user) {
		String currentStatus = request.getParameter("currentStatus");
		if("Y".equalsIgnoreCase(currentStatus))
			user.setActive(false);
		else
			user.setActive(true);
		boolean queryStatus = userDao.changeUserActive(user);
		if(queryStatus)
			return "changed";
		else
			return "notChanged";
	}
	
	public String changeUserTheme(UserSettingModel userSetting) {
		userSetting.setUsername(SessionManagerDataHelper.getUsername());
		boolean flag = userDao.changeUserTheme(userSetting);
		if(flag) {
			SessionManagerDataHelper.getUserProfileUtil().setTheme(userSetting.getTheme());
			return "success";
		} else {
			return "failed";
		}
	}
	
	public String getParentUserDetailsId(HttpServletRequest request) {
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		String admissionNo = request.getParameter("admissionNo");
		int rollNo = Integer.parseInt(request.getParameter("rollNo"));
		StudentDetailsModel detailsModel = facadeDaoManager.getStudentDetailsDao().getStudentDetailsByAddmissioNoOrRollNo(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classCode, sectionCode, admissionNo, rollNo, "Y");
		return detailsModel.getParentDetails().getParentDetailsId().toString();
	}
	
	
	public void updateAllUserRoles() {
		Map<String, String> userRoles = StaticData.getUserRolesData();
		UserRoleModel userRole = null;
		for(Map.Entry<String, String> entry : userRoles.entrySet()) {
			userRole = new UserRoleModel(entry.getKey());
			userRole.setUserRoleName(entry.getValue());
			try {
				UserRoleModel role = (UserRoleModel) userDao.get(UserRoleModel.class, userRole.getUserRoleId());
				if(role == null)
					userDao.save(userRole);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("ERROR: updateAllUserRoles() in UserServices ", e);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray getUsersDetailsInAutocomplete(HttpServletRequest request) {
		JSONArray jsonResponse = new JSONArray();
		String nameLike = request.getParameter("term");
		try {
			List<UserDetailsModel> userList = userDao.getUserDetailsBySchoolCodeWithLike(SessionManagerDataHelper.getUserActiveSchoolCode(), nameLike, null);
			JSONObject jsonObject = null;
			for(UserDetailsModel userDetails : userList){
				jsonObject = new JSONObject();
				jsonObject.put("image", userDetails.getImage());
				jsonObject.put("name", userDetails.getFullname());
				jsonObject.put("email", userDetails.getEmail());
				jsonObject.put("mobile", userDetails.getMobile());
				jsonObject.put("username", userDetails.getUsername());
				jsonResponse.add(jsonObject);
			}
		} catch (Exception e) {
			LOG.error("ERROR: getUsersDetailsInAutocomplete() in UserServices ", e);
			e.printStackTrace();
		}
		return jsonResponse;
	}
	
}
