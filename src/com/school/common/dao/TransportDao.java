package com.school.common.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.school.common.generic.dao.DaoManagerBean;
import com.school.common.model.TransportModel;

@Repository
public class TransportDao extends DaoManagerBean {

	private static Logger LOG = LoggerFactory.getLogger(TransportDao.class);
	
	
	public List<TransportModel> fetchTransport(String hqlQuery, Object[] params) {
		try {
			return findDynamicQuery("FROM TransportModel" + hqlQuery, params);
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: fetchTransport() in TransportDao " , e);
		}
		return null;
	}
	
	
//	change Transport Status from Grid
	public void changeTransportStatus(String status, Long transportId, String schoolCode) {
		try {
			executeUpdateHQLQuery("UPDATE TransportModel SET status=:p1 WHERE transportId=:p2 AND schoolProfile.schoolCode=:p3", new Object[] {status, transportId, schoolCode});
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: changeTransportStatus() in TransportDao " , e);
		}
	}
	
	public TransportModel checkTransportRoute(String route, String schoolCode) {
		try {
			return (TransportModel)uniqueResultHQLQuery("FROM TransportModel WHERE route=:p1 AND schoolProfile.schoolCode=:p2", new Object[] {route, schoolCode});
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: checkTransportRoute() in TransportDao " , e);
		}
		return null;
	}
	
	

	
	
	public List<TransportModel> getAllActiveTransports(String schoolCode){
		try {
			return findDynamicQuery("FROM TransportModel WHERE status=:p1 AND schoolProfile.schoolCode=:p2", new Object[] {"Y", schoolCode});
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: getAllActiveTransports() in TransportDao " , e);
		}
		return null;
	}
}
