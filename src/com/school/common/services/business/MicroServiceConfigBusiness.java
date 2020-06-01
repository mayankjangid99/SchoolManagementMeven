package com.school.common.services.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.school.common.model.MicroServiceConfigModel;
import com.school.json.model.MicroServiceConfigJsonModel;
import com.school.json.response.MicroServiceConfigJsonResponse;

@Service
public class MicroServiceConfigBusiness {
	
//	Fetch All Micro Service Config
	public ArrayList<MicroServiceConfigJsonModel> fetchMicroServiceConfig(List<MicroServiceConfigModel> list)
	{
		MicroServiceConfigJsonResponse microServiceConfigJsonResponse = new MicroServiceConfigJsonResponse();
		MicroServiceConfigJsonModel microServiceConfigJson = null;
		ArrayList<MicroServiceConfigJsonModel> microServiceConfigList = new ArrayList<MicroServiceConfigJsonModel>();
		
		for(MicroServiceConfigModel microServiceConfig : list) {
			microServiceConfigJson = new MicroServiceConfigJsonModel();
			microServiceConfigJson.setConfigCode(microServiceConfig.getConfigCode());
			microServiceConfigJson.setConfigValue(microServiceConfig.getConfigValue());
			microServiceConfigJson.setConfigDescription(microServiceConfig.getConfigDescription());
			microServiceConfigList.add(microServiceConfigJson);
		}
		microServiceConfigJsonResponse.setMicroServiceConfigList(microServiceConfigList);
		return microServiceConfigList;
		//return microServiceConfigJsonResponse;
	}
}
