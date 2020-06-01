package com.school.common.micro.gateway;

import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.micro.model.AuthMicroModel;
import com.school.common.micro.services.SalaryMicroServices;

public class MicroServiceParamsHelper {	
	private static MicroServiceParamsHelper instance = null;
	private final String URL = SessionManagerDataHelper.getMicroServiceConfigParameterValue(StaticValue.MICRO_SERVICE_URL_CONFIG);
	private MicroServiceParamsHelper() {
		
	}
	
	public static MicroServiceParamsHelper getInstance() {
		if(instance == null) {
			synchronized (MicroServiceParamsHelper.class) {
				if(instance == null)
					instance = new MicroServiceParamsHelper();
			}
		}
		return instance;
	}
	
	private void setAuthMicro(MicroServicesParams params) {
		AuthMicroModel bodyParams = new AuthMicroModel();
/*		bodyParams.setUsername("admin");
		bodyParams.setPassword("12345");*/
		if("ROLE_SUPADMIN".equals(SessionManagerDataHelper.getUserProfileUtil().getUserRoleId())) {
			bodyParams.setUsername("SUPADMIN");
			bodyParams.setPassword("SUPADMIN");
		} else {
			bodyParams.setUsername(SessionManagerDataHelper.getUsername());
			bodyParams.setPassword(SessionManagerDataHelper.getPassword());
			//bodyParams.setPassword(SessionManagerDataHelper.getEncPassword());
		}
		params.setAuthUrl(URL + "/auth/");
		params.setMicroEntity(bodyParams);
		SalaryMicroServices ms = ServicesGateway.newSalaryMicroServices();
		
		if(params.getHeaders() == null)
			params.setHeaders(new HttpHeaders());

		params.getHeaders().setSessionToken(SessionManagerDataHelper.getToken());
		
		String microServiceEnabled = SessionManagerDataHelper.getMicroServiceConfigParameterValue(StaticValue.MICRO_SERVICE_ENABLED_CONFIG);
		if("Y".equalsIgnoreCase(microServiceEnabled) && (SessionManagerDataHelper.getToken() == null || SessionManagerDataHelper.getToken().isEmpty())) {
			if(params.getHeaders().getSessionToken() == null || params.getHeaders().getSessionToken().isEmpty())
				ms.getToken(params);
			
			String token  = params.getHeaders().getSessionToken();
			SessionManagerDataHelper.setToken(token);
		}
	}
	
	public void setMicroServiceParams(String serviceType, MicroServicesParams params) {
		setAuthMicro(params);
		if(ServiceGatewayStaticValue.SALARY_MICRO_SERVICE.getType().equals(serviceType)) {
			params.setUrl(URL + "/salary/salary");
		} else if(ServiceGatewayStaticValue.SALARY_CONFIG_MICRO_SERVICE.getType().equals(serviceType)) {
			params.setUrl(URL + "/salary/salaryConfig");
		} else if(ServiceGatewayStaticValue.USER_MICRO_SERVICE.getType().equals(serviceType)) {
			params.setUrl(URL + "/users/user");
		}
	}
	
}
