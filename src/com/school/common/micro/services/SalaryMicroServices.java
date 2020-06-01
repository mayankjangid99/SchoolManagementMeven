package com.school.common.micro.services;

import org.springframework.http.ResponseEntity;

import com.school.common.micro.gateway.MicroServicesGateway;
import com.school.common.micro.gateway.MicroServicesGatewayImp;
import com.school.common.micro.gateway.MicroServicesParams;
import com.school.common.micro.model.ApiResponse;

public final class SalaryMicroServices {

	private MicroServicesGateway microServicesGateway = new MicroServicesGatewayImp();
	
	public ApiResponse getSalaryConfig(MicroServicesParams params) {
		ResponseEntity<ApiResponse> responseEntiry = microServicesGateway.getForObject(params);
		ApiResponse response  = null;
		if(responseEntiry != null)
			response = responseEntiry.getBody();
		return response;
	}
	
	public ApiResponse addSalaryConfig(MicroServicesParams params) {
		ResponseEntity<ApiResponse> responseEntiry = microServicesGateway.postForEntity(params);
		ApiResponse response  = null;
		if(responseEntiry != null)
			response = responseEntiry.getBody();
		return response;
	}
	
	public ApiResponse updateSalaryConfig(MicroServicesParams params) {
		ResponseEntity<ApiResponse> responseEntiry = microServicesGateway.putForEntity(params);
		ApiResponse response  = null;
		if(responseEntiry != null)
			response = responseEntiry.getBody();
		return response;
	}
	
	public ApiResponse getSalaryDetails(MicroServicesParams params) {
		ResponseEntity<ApiResponse> responseEntiry = microServicesGateway.getForEntity(params);
		ApiResponse response  = null;
		if(responseEntiry != null)
			response = responseEntiry.getBody();
		return response;
	}
	
	public ApiResponse addSalaryDetails(MicroServicesParams params) {
		ResponseEntity<ApiResponse> responseEntiry = microServicesGateway.postForEntity(params);
		ApiResponse response  = null;
		if(responseEntiry != null)
			response = responseEntiry.getBody();
		return response;
	}
	
	
	
	public String getToken(MicroServicesParams params) {
		return microServicesGateway.getSessionToken(params);
	}
}
