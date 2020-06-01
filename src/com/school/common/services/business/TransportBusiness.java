package com.school.common.services.business;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.model.TransportModel;

@Service
public class TransportBusiness 
{	
	@Autowired
	private HttpSession session;
	
	private static Logger LOG = LoggerFactory.getLogger(TransportBusiness.class);
	
	public void prepareFetchTransport(TransportModel transport) {
		Object[] params = new Object[15];
		StringBuffer hqlQuery = new StringBuffer("");
		int idx = 0;
		
		if(session.getAttribute("hqlQuery") != null) {
			params = (Object[])session.getAttribute("params");
		}
		hqlQuery.append(" WHERE");
		hqlQuery.append(" schoolProfile.schoolCode=:p" + (idx + 1));
		params[idx] = SessionManagerDataHelper.getSchoolCode();
		idx++;
		
		if(session.getAttribute("hqlQuery") == null) {
			
			if(transport.getRoute() != null && !"".equals(transport.getRoute())) {
				hqlQuery.append(" AND route LIKE '%" + transport.getRoute() + "%'");
			}
			if(transport.getPickUpStop() != null && !"".equals(transport.getPickUpStop())) {
				hqlQuery.append(" AND pickUpStop LIKE '%" + transport.getPickUpStop() + "%'");
			}
			if(transport.getPickUpTime() != null && !"".equals(transport.getPickUpTime())) {
				hqlQuery.append(" AND pickUpTime LIKE '%" + transport.getPickUpTime() + "%'");
			}
			if(transport.getVehicle() != null && !"".equals(transport.getVehicle())) {
				hqlQuery.append(" AND vehicle LIKE '%" + transport.getVehicle() + "%'");
			}
			if(transport.getDropStop() != null && !"".equals(transport.getDropStop())) {
				hqlQuery.append(" AND dropStop LIKE '%" + transport.getDropStop() + "%'");
			}
			if(transport.getDropTime() != null && !"".equals(transport.getDropTime())) {
				hqlQuery.append(" AND dropTime LIKE '%" + transport.getDropTime() + "%'");
			}
			if(transport.getTransportFee() != null && !"".equals(transport.getTransportFee())) {
				hqlQuery.append(" AND transportFee=:p" + (idx + 1));
				params[idx] = transport.getTransportFee();
				idx++;
			}
			if(transport.getType() != null && !"".equals(transport.getType())) {
				hqlQuery.append(" AND type=:p" + (idx + 1));
				params[idx] = transport.getType();
				idx++;
			}
			if(transport.getStatus() != null && !"".equals(transport.getStatus())) {
				hqlQuery.append(" AND status=:p" + (idx + 1));
				params[idx] = transport.getStatus();
				idx++;
			}
		} else {
			hqlQuery.setLength(0);;
			hqlQuery.append((String)session.getAttribute("hqlQuery"));
		}
		
		session.setAttribute("searched", "searched");
		session.setAttribute("params", params);
		session.setAttribute("hqlQuery", hqlQuery.toString());
	}
}
