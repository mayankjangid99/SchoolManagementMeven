package com.school.common.services.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.school.common.model.ReportCardConfigModel;
import com.school.json.model.ReportCardConfigJsonModel;

@Service
public class ReportCardConfigBusiness {

	
	public List<ReportCardConfigJsonModel> fetchAllReportCardConfig(List<ReportCardConfigModel> list) {
		List<ReportCardConfigJsonModel> reportCardConfigList = new ArrayList<ReportCardConfigJsonModel>();
		if(list != null && !list.isEmpty()) {
			list.forEach(e -> {
				ReportCardConfigJsonModel jsonModel = new ReportCardConfigJsonModel();
				BeanUtils.copyProperties(e, jsonModel);
				jsonModel.setSchoolCode(e.getSchoolProfile().getSchoolCode());
				jsonModel.setSessionCode(e.getSessionDetails().getSessionCode());
				if(e.getClassInformation() != null)
					jsonModel.setClassCode(e.getClassInformation().getClassCode());
				if(e.getSectionInformation() != null)
					jsonModel.setSectionCode(e.getSectionInformation().getSectionCode());
				reportCardConfigList.add(jsonModel);
			});
		}
		return reportCardConfigList;
	}
}
