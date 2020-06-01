package com.school.common.services;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.school.common.generic.AppHttpSessionListener;
import com.school.common.services.business.DashboardBusiness;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DashboardServices 
{
	
	@Autowired
	private DashboardBusiness dashboardServices;
	
	
	
	@SuppressWarnings("unchecked")
	public JSONObject getActiveSessions()
	{
		long active = AppHttpSessionListener.getActiveSessions();
		long totalActivated = AppHttpSessionListener.getTotalSessions();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("active", active);
		jsonObject.put("totalActivated", totalActivated);
		return jsonObject;
	}
}
