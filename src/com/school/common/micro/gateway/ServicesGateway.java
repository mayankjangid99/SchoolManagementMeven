package com.school.common.micro.gateway;

import com.school.common.micro.services.SalaryMicroServices;
import com.school.common.micro.services.UserMicroServices;

public interface ServicesGateway {
	
	public String getToken(MicroServicesParams params);
	
	public static SalaryMicroServices newSalaryMicroServices() {
		return new SalaryMicroServices();
	}
	
	public static UserMicroServices newUserMicroServices() {
		return new UserMicroServices();
	}
}
