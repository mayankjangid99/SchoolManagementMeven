package com.school.common.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.school.common.generic.dao.DaoManagerBean;
import com.school.common.model.ReportCardConfigModel;

@Service
public class ReportCardConfigDao extends DaoManagerBean {
	
	private static Logger LOG = LoggerFactory.getLogger(ReportCardConfigDao.class);
	

	public ReportCardConfigModel getReportCardConfig(String schoolCode, String sessionCode, String configCode, String classCode, String sectionCode) {
		try {
			List<ReportCardConfigModel> list = null;
			
			if(schoolCode != null && !schoolCode.isEmpty() && sessionCode != null && !sessionCode.isEmpty() && configCode != null && !configCode.isEmpty() && classCode != null && !classCode.isEmpty() && sectionCode != null && !sectionCode.isEmpty()) {
				list = findDynamicQuery("FROM ReportCardConfigModel c WHERE c.schoolProfile.schoolCode=:p1 AND c.sessionDetails.sessionCode=:p2 "
						+ "AND c.configCode=:p3 AND c.classInformation.classCode=:p4 AND c.sectionInformation.sectionCode=:p5", new Object[] {schoolCode, sessionCode, configCode, classCode, sectionCode});
				if(list != null && !list.isEmpty())
					return list.get(0);
			}
			
			if(schoolCode != null && !schoolCode.isEmpty() && sessionCode != null && !sessionCode.isEmpty() && configCode != null && !configCode.isEmpty() && classCode != null && !classCode.isEmpty()) {
				list = findDynamicQuery("FROM ReportCardConfigModel c WHERE c.schoolProfile.schoolCode=:p1 AND c.sessionDetails.sessionCode=:p2 "
						+ "AND c.configCode=:p3 AND c.classInformation.classCode=:p4", new Object[] {schoolCode, sessionCode, configCode, classCode});
				if(list != null && !list.isEmpty())
					return list.get(0);
			}
			
			if(schoolCode != null && !schoolCode.isEmpty() && sessionCode != null && !sessionCode.isEmpty() && configCode != null && !configCode.isEmpty()) {
				list = findDynamicQuery("FROM ReportCardConfigModel c WHERE c.schoolProfile.schoolCode=:p1 AND c.sessionDetails.sessionCode=:p2 "
						+ "AND c.configCode=:p3", new Object[] {schoolCode, sessionCode, configCode});
				if(list != null && !list.isEmpty())
					return list.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERROR: getReportCardConfig() in ReportCardConfigDao ", e);
		}
		return null;
	}
	
	public List<ReportCardConfigModel> getAllReportCardConfig(String schoolCode, String sessionCode, String configCode, String classCode, String sectionCode) {
		try {
			Object[] param = new Object[5];
			param[0] = schoolCode;
			param[1] = sessionCode;
			int idx = 2;
			String query = "FROM ReportCardConfigModel c WHERE c.schoolProfile.schoolCode=:p1  AND c.sessionDetails.sessionCode=:p2";
			if(configCode != null && !configCode.isEmpty()) {
				param[idx++] = configCode;
				query += " AND c.configCode=:p" + idx;
			}
			if(classCode != null && !classCode.isEmpty()) {
				param[idx++] = classCode;
				query += " AND c.classInformation.classCode=:p" + idx;
			}
			if(sectionCode != null && !sectionCode.isEmpty()) {
				param[idx++] = sectionCode;
				query += " AND c.sectionInformation.sectionCode=:p" + idx;
			}
			return findDynamicQuery(query, param);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERROR: getAllReportCardConfig() in ReportCardConfigDao ", e);
		}
		return null;
	}
	
}
