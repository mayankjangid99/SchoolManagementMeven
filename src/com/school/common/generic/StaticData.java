package com.school.common.generic;

import java.util.LinkedHashMap;
import java.util.Map;

public class StaticData {

	public static Map<String, String> getGlobalConfigParametersData() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(StaticValue.GLOBAL_CONFIG_MAIL_SERVER, "Mail Server Config");
		map.put(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS, "Mail Server Address");
		map.put(StaticValue.GLOBAL_CONFIG_MAIL_SENDER, "Mail Sender");
		return map;
	}
	
	public static Map<String, String> getUserRolesData() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(StaticValue.USER_ROLE_ADMIN, "Admin");
		map.put(StaticValue.USER_ROLE_EXECUTIVE, "Executive");
		map.put(StaticValue.USER_ROLE_STAFF, "Staff");
		map.put(StaticValue.USER_ROLE_SUBADMIN, "Sub Admin");
		map.put(StaticValue.USER_ROLE_SUPADMIN, "Super Admin");
		map.put(StaticValue.USER_ROLE_USER, "User");
		map.put(StaticValue.USER_ROLE_ACC, "Account");
		return map;
	}
	
	
	public static Map<String, String> getReportCardTermData() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(StaticValue.REPORT_CARD_UNIT_TEST, "Unit Test / Sessional");
		map.put(StaticValue.REPORT_CARD_TERM, "Term");
		return map;
	}
	


	public static Map<String, String> getMicroServiceConfigParametersData() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(StaticValue.MICRO_SERVICE_URL_CONFIG, "Micro Service URL");
		map.put(StaticValue.MICRO_SERVICE_ENABLED_CONFIG, "Micro Service Enabled");
		return map;
	}
}
