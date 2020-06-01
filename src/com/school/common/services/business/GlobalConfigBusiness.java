package com.school.common.services.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.school.common.model.GlobalConfigModel;
import com.school.json.model.GlobalConfigJsonModel;
import com.school.json.response.GlobalConfigJsonResponse;

@Service
public class GlobalConfigBusiness 
{
	
//	Fetch All Global Config
	public ArrayList<GlobalConfigJsonModel> fetchGlobalConfig(List<GlobalConfigModel> list)
	{
		GlobalConfigJsonResponse globalConfigJsonResponse = new GlobalConfigJsonResponse();
		GlobalConfigJsonModel globalConfigJson = null;
		ArrayList<GlobalConfigJsonModel> globalConfigList = new ArrayList<GlobalConfigJsonModel>();
		
		for(GlobalConfigModel globalConfig : list) {
			globalConfigJson = new GlobalConfigJsonModel();
			globalConfigJson.setConfigCode(globalConfig.getConfigCode());
			globalConfigJson.setConfigValue(globalConfig.getConfigValue());
			globalConfigJson.setConfigDescription(globalConfig.getConfigDescription());
			globalConfigList.add(globalConfigJson);
		}
		globalConfigJsonResponse.setGlobalConfigList(globalConfigList);
		return globalConfigList;
		//return globalConfigJsonResponse;
	}
}
