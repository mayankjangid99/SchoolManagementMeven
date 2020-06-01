package com.school.common.services;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.facade.FacadeDaoManager;
import com.school.common.facade.FacadeServicesManager;
import com.school.common.facade.HiberSessionFactory;
import com.school.common.generic.Base64;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.DataHelper;
import com.school.common.generic.EmailLookUpFile;
import com.school.common.generic.EmailUtil;
import com.school.common.generic.FreemarkerTemplate;
import com.school.common.generic.SessionManager;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.generic.UserProfileUtils;
import com.school.common.micro.gateway.MicroServiceParamsHelper;
import com.school.common.micro.gateway.MicroServicesParams;
import com.school.common.micro.gateway.ServiceGatewayStaticValue;
import com.school.common.model.AdmissionNumberModel;
import com.school.common.model.ApplicationSettingsModel;
import com.school.common.model.GlobalConfigModel;
import com.school.common.model.MicroServiceConfigModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SchoolSessionModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.model.UserCheckInModel;
import com.school.common.model.UserDetailsModel;
import com.school.common.model.UserModel;
import com.school.common.model.UserRoleModel;
import com.school.common.services.business.LoginBusiness;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginServices 
{	
	@Autowired
	private LoginBusiness loginBusiness;
		
	@Autowired
	private FacadeDaoManager facadeDaoManager;
	
	@Autowired
	private FacadeServicesManager facadeServicesManager;
	
	@Autowired
	private HiberSessionFactory hiberSessionFactory;
		
	private static Logger LOG = LoggerFactory.getLogger(LoginServices.class);
	
	
	public ModelAndView alreadyLogin(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("login");
		String msg = "User already login, please logout first";
		model.addObject("error", msg);
		return model;
	}
	
	public ModelAndView loginDefault(HttpServletRequest request, String cookieVal) {
		ModelAndView model = new ModelAndView("login");
		//log.info("cookieVal= " + Base64.decodeBase64(cookieVal));
		//cookieVal = Base64.decode(cookieVal);
		if(cookieVal == null)
			cookieVal = "";
		Map<String, String> map = BusinessLogicHelper.parseAndReadConfigProperties(cookieVal.replaceAll("~", ";"));
		if(map != null)
			model.addObject("uimg", map.get("uimg"));
		HttpSession session = request.getSession();
		SessionManager sessionManager = (SessionManager) session.getAttribute("SessionManager");
		if(sessionManager == null) {
			sessionManager = new SessionManager();
			List<GlobalConfigModel> globalConfigList = facadeDaoManager.getGlobalConfigDao().getAllGlobalConfigBySchoolCode(SessionManagerDataHelper.getSchoolCode());
			sessionManager.setGlobalConfigList(globalConfigList);
			List<MicroServiceConfigModel> microServiceConfigs = facadeDaoManager.getMicroServiceConfigDao().getAllMicroServiceConfigs();
			sessionManager.setMicroServiceConfigs(microServiceConfigs);
			session.setAttribute("SessionManager", sessionManager);
		}
		return model;
	}
	
	
//	Login User
	public ModelAndView loginUser(HttpServletRequest request) {			
		if(!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			List<SchoolSessionModel> sessionList = null;
	//		Again Authantication User 
			UserModel user = null;
			List<GlobalConfigModel> globalConfigList = new ArrayList<GlobalConfigModel>();
			List<SchoolProfileModel> schoolProfileList = new ArrayList<SchoolProfileModel>();
			List<ApplicationSettingsModel> applicationSettingsList = new ArrayList<ApplicationSettingsModel>();
			List<AdmissionNumberModel> admissionNumberList = new ArrayList<AdmissionNumberModel>();
			List<MicroServiceConfigModel> microServiceConfigs = new ArrayList<MicroServiceConfigModel>();
			try {
				user = facadeDaoManager.getUserDao().loginUser(userDetails.getUsername());
				//globalConfigList = facadeServicesManager.getGlobalConfigServices().getAllGlobalConfigBySchoolCode();
				schoolProfileList = facadeDaoManager.getSchoolProfileDao().getAllSchoolProfile();
				microServiceConfigs = facadeDaoManager.getMicroServiceConfigDao().getAllMicroServiceConfigs();
				SchoolProfileModel schoolProfileModel = user.getUserDetails().getSchoolProfile();
				if(schoolProfileModel != null) {
					sessionList = facadeDaoManager.getSchoolSessionDao().getSchoolSessionBySchoolCode(schoolProfileModel);
					globalConfigList = new ArrayList<GlobalConfigModel>(schoolProfileModel.getGlobalConfigs());
					applicationSettingsList = facadeDaoManager.getApplicationSettingsDao().getApplicationSettingsForSchool(schoolProfileModel.getSchoolCode());
					admissionNumberList = facadeDaoManager.getStudentDetailsDao().getAllAdmissionNumberBySchoolCode(schoolProfileModel.getSchoolCode());
				}
			} catch (Exception e) {
				LOG.error("ERROR: in getting in loginUser() in LoginServices.java " + e);
				//e.printStackTrace();
			}
			
	//		Call Login Business Class Method to validate business
			ModelAndView result = loginBusiness.loginUser(request, user, sessionList, globalConfigList, schoolProfileList, applicationSettingsList, admissionNumberList, microServiceConfigs);

			
			String microServiceEnabled = SessionManagerDataHelper.getMicroServiceConfigParameterValue(StaticValue.MICRO_SERVICE_ENABLED_CONFIG);
			if("Y".equalsIgnoreCase(microServiceEnabled)) {
				MicroServicesParams params = new MicroServicesParams();
				MicroServiceParamsHelper.getInstance().setMicroServiceParams(ServiceGatewayStaticValue.USER_MICRO_SERVICE.getType(), params);
				String token = (params.getHeaders() != null) ? params.getHeaders().getSessionToken() : null;
				user.getUserDetails().setToken(token);
				updateUserDetailsToken(user.getUserDetails());
			}
			boolean userAuth = loginBusiness.isUserAuth();
	//		update ip and login if user auth
			if(userAuth) {
				UserCheckInModel userCheckIn = new UserCheckInModel();
				String ip = loginBusiness.getUserIP();
				user.setIp(ip);
				user.setLogin(true);
				/*facadeDaoManager.getUserDao().updateUserIP(user);
				facadeDaoManager.getUserDao().updateUserLogin(user);*/
				facadeDaoManager.getUserDao().updateUserIPAndLogin(user);
				
				SessionDetailsModel sessionDetails = new SessionDetailsModel();
				sessionDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
				userCheckIn.setSessionDetails(sessionDetails);
				userCheckIn.setUser(user);
				userCheckIn.setDate(new Date());
				UserCheckInModel userCheckInModel = facadeDaoManager.getUserCheckInDao().checkCheckInUser(userCheckIn);
				loginBusiness.UserAuthorized(userCheckInModel);
				
				/*SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");			
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
				SessionManagerDataHelper.setUserProfileUtil(userProfileUtils);*/
			}
			return result;
		} else {
			ModelAndView model = new ModelAndView("redirect:loginDefault");
			return model;
		}
	}
		
	
	public void updateUserDetailsToken(UserDetailsModel userDetails) {
		facadeDaoManager.getUserDao().updateUserDetailsToken(userDetails);
	}
	
	
	
	
//	@Before aspect use on this method
	public void logout(HttpSession session) {
		hiberSessionFactory.getSessionFactory().getCurrentSession().clear();
		session.removeAttribute("SessionManager");
		session.invalidate();
		
	}

	
	public void updateUserLogin(UserModel user) {
		facadeDaoManager.getUserDao().updateUserLogin(user);
	}
	
	
	public void updateAllUserLogin() {
		facadeDaoManager.getUserDao().updateAllUserLogin();
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject forgotPassword(HttpServletRequest request) {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		JSONObject jsonObject = new JSONObject();
		
		if(username != null && !"".equals(username) && email != null && !"".equals(email)) {
			UserModel userModel = new UserModel();
			userModel.setUsername(username);
			UserDetailsModel userDetails = new UserDetailsModel();
			userDetails.setEmail(email);
			userModel.setUserDetails(userDetails);
			UserModel user = facadeDaoManager.getUserDao().getUserByUsernameAndEmail(userModel);
			if(user != null && user.getUserDetails().getEmail() != null && !"".equals(user.getUserDetails().getEmail())) {
				HttpSession session = request.getSession();
				StringBuffer queryBuffer = new StringBuffer("");
				queryBuffer.append("username=" + username);
				queryBuffer.append(";email=" + email);
				queryBuffer.append(";sessionId=" + session.getId());
				String queryString = Base64.encodeToString(queryBuffer.toString().getBytes(), true);

				EmailUtil emailUtil = new EmailUtil();
				Template template = FreemarkerTemplate.getTemplate(EmailLookUpFile.BasicEmailForSchool + ".ftl");
				Map<String, Object> input = FreemarkerTemplate.getInputMap();	
				
				String domain = DataHelper.getDomainWithContext();
				StringBuffer emailContent = new StringBuffer("Hi " + user.getUserDetails().getFullname() + ", <br/>");
				emailContent.append("This email sent by system to change password, please click ");
				emailContent.append("<a href='" + domain + "/changeForgotPassword?info=" + queryString + "'>here</a>");
				emailContent.append(" to change the password");
				input.put("content", emailContent);
	            try {
					Writer consoleWriter = new StringWriter();
					template.process(input, consoleWriter);
		            String mailMessageBody = consoleWriter.toString();
		            
					emailUtil.sendEmail(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), user.getUserDetails().getEmail(), "Forgot Password", mailMessageBody);
					
					jsonObject.put("message", "Email successfully sent on registered email address.");
					jsonObject.put("status", "success");
				} catch (TemplateException e) {
					//e.printStackTrace();
					LOG.error("ERROR: In LoginServices forgotPassword() " + e);
				} catch (IOException e) {
					//e.printStackTrace();
					LOG.error("ERROR: In LoginServices forgotPassword() " + e);
				}
				return jsonObject;
			} else {
				jsonObject.put("message", "Invalid username or email, please provide correct username or email.");
				jsonObject.put("status", "invalid");
				return jsonObject;
			}
		} else if((username == null || "".equals(username)) && (email == null || "".equals(email))) {
			jsonObject.put("message", "Please provide a username.<br>Please provide registered email.");
			jsonObject.put("status", "blank");
			return jsonObject;
		} else if(username == null || "".equals(username)) {
			jsonObject.put("message", "Please provide a username.");
			jsonObject.put("status", "blank");
			return jsonObject;
		} else {
			jsonObject.put("message", "Please provide registered email.");
			jsonObject.put("status", "blank");
			return jsonObject;
		}
	
	}
	
	
	public ModelAndView changeForgotPassword(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("outside/changePassword");
		String queryString = request.getParameter("info");
		HttpSession session = request.getSession();
		
		if(queryString != null)
			session.setAttribute("queryString", queryString);
		
		if((queryString == null || queryString.isEmpty()) && session.getAttribute("queryString") != null)
			queryString = (String) session.getAttribute("queryString");
		
		UserModel userModel = null;
		UserDetailsModel userDetails = null;
		UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil();
		if((queryString != null && !queryString.isEmpty() && userProfileUtils == null) || session.getAttribute("queryString") != null) {
			System.out.println("queryString=" + Base64.decode(queryString));
			queryString = Base64.decode(queryString);
			Map<String, String> queryStringMap = BusinessLogicHelper.parseAndReadConfigProperties(queryString);
			userModel = new UserModel();
			userDetails = new UserDetailsModel();
			userDetails.setEmail(queryStringMap.get("email"));
			userModel.setUserDetails(userDetails);
			userModel.setUsername(queryStringMap.get("username"));
			
		} else if(userProfileUtils != null) {
			userModel = new UserModel();
			userDetails = new UserDetailsModel();
			userDetails.setEmail(userProfileUtils.getEmail());
			userModel.setUserDetails(userDetails);
			userModel.setUsername(userProfileUtils.getUsername());
		}
		UserModel user = facadeDaoManager.getUserDao().getUserByUsernameAndEmail(userModel);
		model.addObject("User", user);
		return model;
	}
	
	public ModelAndView changePassword(HttpServletRequest request) {
		ModelAndView model = changeForgotPassword(request);
		model.setViewName("user/changePassword");
		return model;
	}
	
	public ModelAndView updatePassword(HttpServletRequest request, UserModel user) {
		ModelAndView model = new ModelAndView("redirect:updatedPassword");
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(12);
		UserModel userModel = facadeDaoManager.getUserDao().getUserByUsername(user);
		boolean pwdMatcherFlag = cryptPasswordEncoder.matches(user.getPassword(), userModel.getPassword());
		if(pwdMatcherFlag) {
			model = changeForgotPassword(request);
			UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil();
			if(userProfileUtils != null)
				model.setViewName("user/changePassword");
			else
				model.setViewName("outside/changePassword");
			model.addObject("ErrorMessage", "Please enter different password from last password");
			return model;
		}
		String password = user.getPassword();
		user.getUserDetails().setPwd(password);
		user.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
		user.getUserDetails().setPassword(cryptPasswordEncoder.encode(user.getPassword()));
		
		if(!"ROLE_SUPADMIN".equalsIgnoreCase(SessionManagerDataHelper.getUserProfileUtil().getUserRoleId())) {
			String microServiceEnabled = SessionManagerDataHelper.getMicroServiceConfigParameterValue(StaticValue.MICRO_SERVICE_ENABLED_CONFIG);
			if("Y".equalsIgnoreCase(microServiceEnabled)) {
				UserModel userModel2 = new UserModel();
				BeanUtils.copyProperties(user, userModel2);
				userModel2.setPassword(password);
				UserRoleModel roleModel = new UserRoleModel(SessionManagerDataHelper.getUserProfileUtil().getUserRoleId());
				userModel2.setUserRole(roleModel);
				boolean flag = facadeServicesManager.getUserServices().updateUserMicroService(userModel2, model);
				if(!flag)
					return model;
			}
		}
		boolean flag1 = facadeDaoManager.getUserDao().updatePassword(user);
		boolean flag2 = facadeDaoManager.getUserDao().updatePasswordInUserDetails(user);
		if(flag1 && flag2)
			LOG.info("Password Successfully Changed...");
		else
			LOG.info("Password Successfully not Changed due to some internal error...");
		return model;
	}
	
	
	public ModelAndView updatedPassword(HttpServletRequest request) {
		ModelAndView model = changeForgotPassword(request);
		HttpSession session = request.getSession();
		session.removeAttribute("queryString");
		model.addObject("SuccessMessage", "Password Successfully Changed...!!!");
		return model;
	}
	
	
	public ModelAndView updatedPasswordByAnyUser(HttpServletRequest request) {
		ModelAndView model = changePassword(request);
		HttpSession session = request.getSession();
		session.removeAttribute("queryString");
		model.addObject("SuccessMessage", "Password Successfully Changed...!!!");
		return model;
	}
	
}
