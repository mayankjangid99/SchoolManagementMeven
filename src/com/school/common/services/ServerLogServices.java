package com.school.common.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.generic.Base64;
import com.school.common.services.business.ServerLogBusiness;
import com.school.json.model.ServerLogJsonModel;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ServerLogServices 
{
	@Autowired
	private ServerLogBusiness serverLogBusiness;
	
	
	public ModelAndView getServerLog()
	{
		ModelAndView model = new ModelAndView("supadmin/resultServerLog");
		Map<String, String> logInfoMap = serverLogBusiness.serverLogs();
		model.addObject("ServerLogInfo", logInfoMap);
		return model;
	}
	
	
	public void downloadLog(HttpServletRequest request)
	{
		String filePath = request.getParameter("file");
		if(filePath != null)
			filePath = new String(Base64.decode(filePath));
		serverLogBusiness.downloadLog(filePath);
		
	}
	
	
	public List<ServerLogJsonModel> fetchServerHistoryLogs() {
		return serverLogBusiness.getTomcatHistoryLogs();
	}
}
