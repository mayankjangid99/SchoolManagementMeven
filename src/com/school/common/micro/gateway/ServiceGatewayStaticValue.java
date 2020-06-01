package com.school.common.micro.gateway;

public enum ServiceGatewayStaticValue {
	SALARY_MICRO_SERVICE("salary"),
	SALARY_CONFIG_MICRO_SERVICE("salaryConfig"),
	USER_MICRO_SERVICE("user");
	
	private final String type;
	
	ServiceGatewayStaticValue(String type){
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	} 
}
