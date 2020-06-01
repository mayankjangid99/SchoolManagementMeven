package com.school.common.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.micro.gateway.HttpHeaders;
import com.school.common.micro.gateway.MicroServiceParamsHelper;
import com.school.common.micro.gateway.MicroServicesParams;
import com.school.common.micro.gateway.ServiceGatewayStaticValue;
import com.school.common.micro.gateway.ServicesGateway;
import com.school.common.micro.model.ApiResponse;
import com.school.common.micro.model.AuthMicroModel;
import com.school.common.micro.model.MicroEntityModel;
import com.school.common.micro.model.SalaryConfigModel;
import com.school.common.micro.model.UserSalaryModel;
import com.school.common.micro.services.SalaryMicroServices;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SalaryServices {
	
	@Autowired
	private MessageSource messageSource;

	private static Logger LOG = LoggerFactory.getLogger(SalaryServices.class);
	
	
	private ApiResponse getApiResponse(MicroEntityModel microEntityModel, String method, String queryString) {
		SalaryMicroServices ms = ServicesGateway.newSalaryMicroServices();

		MicroServicesParams params = new MicroServicesParams();
		MicroServiceParamsHelper.getInstance().setMicroServiceParams(ServiceGatewayStaticValue.SALARY_CONFIG_MICRO_SERVICE.getType(), params);
		params.setMicroEntity(microEntityModel);
		if(queryString != null && !queryString.isEmpty())
			params.setUrl(params.getUrl() + "?" + queryString);
		ApiResponse response  = null;
		if(StaticValue.POST.equals(method))
			response  = ms.addSalaryConfig(params);
		else if(StaticValue.GET.equals(method))
			response  = ms.getSalaryConfig(params);
		else if(StaticValue.PUT.equals(method))
			response  = ms.updateSalaryConfig(params);
		return response;
	}
	
	private ModelAndView getResponseModelAndView(ApiResponse response, ModelAndView model, String viewName, MicroEntityModel microEntityModel, String microEntityName) {
		
		if(response != null && !response.getSuccess()) {
			if(viewName != null && !viewName.isEmpty())
				model.setViewName(viewName);
			if(microEntityName != null && !microEntityName.isEmpty())
				model.addObject(microEntityName, microEntityModel);
			model.addObject("ErrorMessage", response.getMessage());
		} else if(response == null) {
			if(viewName != null && !viewName.isEmpty())
				model.setViewName(viewName);
			if(microEntityName != null && !microEntityName.isEmpty())
				model.addObject(microEntityName, microEntityModel);
			
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.error.msg.server", null, null));
		}
		return model;
	}
	private boolean isResponseSuccess(ApiResponse response) {
		boolean flag = true;
		if(response != null && !response.getSuccess()) {
			flag = false;
		} else if(response == null) {
			flag = false;
		}
		return flag;
	}
	
	private ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		return mapper;
	}
	
	public void demo() {
		HttpHeaders headers = new HttpHeaders();

		MicroServicesParams params = new MicroServicesParams();
		MicroServiceParamsHelper.getInstance().setMicroServiceParams(ServiceGatewayStaticValue.SALARY_MICRO_SERVICE.getType(), params);
		params.setUrl(params.getUrl() + "salary?schoolCode=ASS&sessionCode=19-20&username=admin");
		SalaryMicroServices ms = ServicesGateway.newSalaryMicroServices();
		//ms.getToken(params);
		ms.getSalaryDetails(params);
		
		UserSalaryModel salaryModel = new UserSalaryModel();

		MicroServiceParamsHelper.getInstance().setMicroServiceParams(ServiceGatewayStaticValue.SALARY_MICRO_SERVICE.getType(), params);
		params.setUrl(params.getUrl());
		salaryModel.setAmountPaid(new BigDecimal("40000"));
		salaryModel.setMonth(1);
		salaryModel.setSchoolCode("ASS");
		salaryModel.setSessionCode("19-20");
		salaryModel.setUsername("admin");
		params.setMicroEntity(salaryModel);
		ms.addSalaryDetails(params);
		
		AuthMicroModel bodyParams = (AuthMicroModel) params.getMicroEntity();
		bodyParams.setUsername("KTAUNK");
		bodyParams.setPassword("ocr123");
		bodyParams.setUser_name("KTAUNK");
		params.setMicroEntity(bodyParams);
		
		Map<String, String> head = new HashMap<String, String>();
		head.put("Application-Id", "0c8eef04-7762-449a-a5c1-90078ecb1856");
		headers.setHeader(head);
		params.setHeaders(headers);
		params.setUrl("https://18.195.197.121:8080//api/access/onguard/openaccess/authentication?version=1.0");
		params.setAuthUrl("https://18.195.197.121:8080//api/access/onguard/openaccess/authentication?version=1.0");
		ms.getToken(params);
	}
	
	public ModelAndView searchSalaryConfig() {
		ModelAndView model = new ModelAndView("admin/searchSalaryConfig");
		return model;
	}
	
	public ModelAndView addSalaryConfig() {
		ModelAndView model = new ModelAndView("admin/addSalaryConfig"); 
		return model;
	}
	
//	Save SalaryConfig Information
	public ModelAndView saveSalaryConfig(SalaryConfigModel salaryConfig) {
		ModelAndView model = new ModelAndView("redirect:savedSalaryConfig");
		salaryConfig.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		salaryConfig.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		
		ApiResponse response = getApiResponse(salaryConfig, StaticValue.POST, null);
		if(!isResponseSuccess(response)) {
			model = getResponseModelAndView(response, model, "admin/addSalaryConfig", salaryConfig, "salaryConfig");
		}
		return model;
	}
	
	public ModelAndView savedSalaryConfig() {
		ModelAndView model = new ModelAndView("admin/addSalaryConfig");
		model.addObject("SuccessMessage", "Salary Configuration Successfully Saved...!!!");
		return model;
	}
	
	public ModelAndView resultSalaryConfig(SalaryConfigModel salaryConfig, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("admin/resultSalaryConfig");
		String queryString = "schoolCode="+SessionManagerDataHelper.getUserActiveSchoolCode() + "&sessionCode=" + SessionManagerDataHelper.getSchoolCurrentSession() + "&configCode="+salaryConfig.getConfigCode()+"&configName="+salaryConfig.getConfigName();
		ApiResponse response = getApiResponse(salaryConfig, StaticValue.GET, queryString);
		if(!isResponseSuccess(response)) {
			model = getResponseModelAndView(response, model, "admin/searchSalaryConfig", salaryConfig, "salaryConfig");
		} else {
			try {
				List<SalaryConfigModel> result = getObjectMapper().readValue(response.getData().toString(), new TypeReference<List<SalaryConfigModel>>() {});
				HttpSession session = request.getSession();
				session.setAttribute("result", result);
			} catch (Exception e) {
				LOG.error("ERROR: resultSalaryConfig() in SalaryConfigServices ", e);
				e.printStackTrace();
			}
		}
		return model;
	}
	
		
//	Fetch All Salary Config
	@SuppressWarnings("unchecked")
	public ArrayList<SalaryConfigModel> fetchSalaryConfig(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<SalaryConfigModel> salaryConfigList  = (ArrayList<SalaryConfigModel>) session.getAttribute("result");
		return salaryConfigList;
	}
	
	
//	Get One Salary Config
	public ModelAndView editSalaryConfig(SalaryConfigModel salaryConfig) {
		ModelAndView model = new ModelAndView("admin/editSalaryConfig");
		String queryString = "schoolCode="+SessionManagerDataHelper.getUserActiveSchoolCode() + "&sessionCode=" + SessionManagerDataHelper.getSchoolCurrentSession() + "&configCode="+salaryConfig.getConfigCode()+"&configName=";
		ApiResponse response = getApiResponse(salaryConfig, StaticValue.GET, queryString);
		if(!isResponseSuccess(response)) {
			model = getResponseModelAndView(response, model, "admin/resultSalaryConfig", salaryConfig, "salaryConfig");
		} else {
			try {
				List<SalaryConfigModel> result = getObjectMapper().readValue(response.getData().toString(), new TypeReference<List<SalaryConfigModel>>() {});
				//List<SalaryConfigModel> result = Arrays.asList(mapper.readValue(response.getData().toString(), SalaryConfigModel[].class));
				if(result != null && !result.isEmpty())
					salaryConfig = result.get(0);
				model.addObject("salaryConfig", salaryConfig);
			} catch (Exception e) {
				LOG.error("ERROR: editSalaryConfig() in SalaryConfigServices ", e);
				e.printStackTrace();
			}
		}
		return model;
	}
	
	
//	Update Salary Config
	public ModelAndView updateSalaryConfig(SalaryConfigModel salaryConfig) {
		ModelAndView model = new ModelAndView("redirect:updatedSalaryConfig");
		salaryConfig.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		salaryConfig.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		
		ApiResponse response = getApiResponse(salaryConfig, StaticValue.PUT, null);
		if(!isResponseSuccess(response)) {
			model = getResponseModelAndView(response, model, "admin/resultSalaryConfig", salaryConfig, "salaryConfig");
		}
		return model;
	}
	
	
	public ModelAndView updatedSalaryConfig() {
		ModelAndView model = new ModelAndView("admin/searchSalaryConfig");
		model.addObject("SuccessMessage", "Salary Configuration Successfully Updated...!!!");
		return model;
	}

	
//	View Salary Config
	public ModelAndView viewSalaryConfig(SalaryConfigModel salaryConfigModel) {
		ModelAndView model = editSalaryConfig(salaryConfigModel);
		model.addObject("Operation", "View");
		return model;
	}
	
	public ModelAndView addSalaryStructure() {
		ModelAndView model = new ModelAndView("account/addSalaryStructure");
		return model;
	}
	
	public ModelAndView searchSalaryStructure() {
		ModelAndView model = new ModelAndView("account/searchSalaryStructure");
		return model;
	}
}
