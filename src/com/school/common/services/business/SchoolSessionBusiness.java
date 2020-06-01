package com.school.common.services.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.school.common.generic.SchoolProfileUtils;
import com.school.common.generic.SessionManager;
import com.school.common.model.SchoolSessionModel;
import com.school.json.model.SchoolSessionJsonModel;

@Service
public class SchoolSessionBusiness 
{
	
//	Switch School Session
	public void switchSchoolSession(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String currentSession = request.getParameter("switchSession");
		String currentSessionName = request.getParameter("switchSessionName");
		
		SessionManager sessionManager = (SessionManager)session.getAttribute("SessionManager");
		SchoolProfileUtils schoolProfileUtils = sessionManager.getSchoolProfileUtils();
		schoolProfileUtils.setCurrentSession(currentSession);
		schoolProfileUtils.setCurrentSessionName(currentSessionName);
		sessionManager.setSchoolProfileUtils(schoolProfileUtils);
		session.setAttribute("SessionManager", sessionManager);
	}
	
	
	public List<SchoolSessionJsonModel> fetchAllocatedSchoolSession(List<SchoolSessionModel> list)
	{
		List<SchoolSessionJsonModel> schoolSessionJsonList = new ArrayList<SchoolSessionJsonModel>();
		SchoolSessionJsonModel schoolSessionJsonModel = null;
			Iterator<SchoolSessionModel> iterator = list.iterator();
			while (iterator.hasNext()) 
			{
				SchoolSessionModel schoolSessionModel = (SchoolSessionModel) iterator.next();
					
				schoolSessionJsonModel = new SchoolSessionJsonModel();
				schoolSessionJsonModel.setSchoolSessionId(schoolSessionModel.getSchoolSessionId());
				schoolSessionJsonModel.setSchoolCode(schoolSessionModel.getSchoolProfile().getSchoolCode());
				schoolSessionJsonModel.setSchoolName(schoolSessionModel.getSchoolProfile().getName());
				
				schoolSessionJsonModel.setSessionCode(schoolSessionModel.getSessionDetails().getSessionCode());
				schoolSessionJsonModel.setSessionName(schoolSessionModel.getSessionDetails().getSessionName());
				schoolSessionJsonModel.setSequence(schoolSessionModel.getSequence());
				schoolSessionJsonModel.setStatus(schoolSessionModel.getStatus());
				schoolSessionJsonList.add(schoolSessionJsonModel);
			}
			
		return schoolSessionJsonList;
	}
	
	
}
