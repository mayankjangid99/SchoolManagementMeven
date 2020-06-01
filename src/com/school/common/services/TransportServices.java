package com.school.common.services;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.TransportDao;
import com.school.common.generic.Base64;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.DateUtil;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.TransportModel;
import com.school.common.services.business.TransportBusiness;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TransportServices 
{
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private TransportDao transportDao;
	
	@Autowired
	private TransportBusiness transportBusiness;

	private static Logger LOG = LoggerFactory.getLogger(TransportServices.class);
	
	
	public ModelAndView searchTransport() {
		BusinessLogicHelper.removeHQLQueryDataFromSession(session);
		ModelAndView model = new ModelAndView("user/searchTransport");
		return model;
	}
	
	public ModelAndView resultTransport(TransportModel transport) {
		ModelAndView model = new ModelAndView("user/resultTransport");
		transportBusiness.prepareFetchTransport(transport);
		return model;
	}
	
	public List<TransportModel> fetchTransport() {
		List<TransportModel> TransportModelList = transportDao.fetchTransport((String)session.getAttribute("hqlQuery"), (Object[])session.getAttribute("params"));
		for(TransportModel tran : TransportModelList) {
			tran.setSchoolProfile(null);
		}
		return TransportModelList;
	}
	
	
//	change Transport Status from Grid	
	public String changeTransportStatus() {
		String status = request.getParameter("status");
		String transportId = request.getParameter("transportId");
		
		if(status.equalsIgnoreCase("Y"))
			status = "N";
		else 
			status = "Y";
		
		transportDao.changeTransportStatus(status, new Long(transportId), SessionManagerDataHelper.getSchoolCode());
		return "changed";
	}
	
	
	public ModelAndView addTransport() {
		ModelAndView model = new ModelAndView("subadmin/addTransport");
		return model;
	}
	
	
	public ModelAndView saveTransport(TransportModel transport) {
		ModelAndView model = new ModelAndView("redirect:savedTransport");
		String operation = request.getParameter("operation");
		if("cancel".equals(operation)) {
			navigate(model);
			return model;
		}
		try {
			TransportModel transportModel = transportDao.checkTransportRoute(transport.getRoute(), SessionManagerDataHelper.getSchoolCode());
			if(transportModel != null && transportModel.getRoute().equalsIgnoreCase(transport.getRoute())) {
				model.setViewName("subadmin/addTransport");
				model.addObject("ErrorMessage", "Route already exist, please choose another one ...!!!");
			}
			transport.setSchoolProfile(new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode()));
			transport.setCreatedBy(SessionManagerDataHelper.getUsername());
			transport.setCreatedOn(new Date());
			transportDao.persist(transport);
			if("saveReturn".equals(operation)) {
				navigate(model);
				return model;
			} else {
				model.setViewName("subadmin/editTransport");
				model.addObject("Transport", transport);
				model.addObject("SuccessMessage", "Transport Details Successfully Saved ...!!!");
				model.addObject("createdOnStr", DateUtil.formatForUIDateTimeAMPM(transport.getCreatedOn()));				
			}
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: saveTransport() in TransportServices " , e);
		}
		return model;
	}
	
	public ModelAndView savedTransport() {
		ModelAndView model = new ModelAndView("subadmin/addTransport");
		model.addObject("SuccessMessage", "Transport Details Successfully Saved ...!!!");
		return model;
	}
	
	public ModelAndView editTransport(TransportModel transport) {
		ModelAndView model = new ModelAndView("subadmin/editTransport");
		try {
			transport = (TransportModel)transportDao.get(TransportModel.class, transport.getTransportId());
			model.addObject("Transport", transport);
			model.addObject("createdOnStr", DateUtil.formatForUIDateTimeAMPM(transport.getCreatedOn()));
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: editTransport() in TransportServices " , e);
		}
		return model;
	}
	
	public ModelAndView deleteTransport(TransportModel transport) {
		ModelAndView model = resultTransport(transport);
		try {
			transportDao.delete(transport);
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: deleteTransport() in TransportServices " , e);
		}
		return model;
	}
	
	public ModelAndView updateTransport(TransportModel transport) {
		ModelAndView model = new ModelAndView("subadmin/editTransport");
		try {
			String operation = request.getParameter("operation");
			if("cancel".equals(operation)) {
				navigate(model);
				return model;
			}
			TransportModel transport2 = (TransportModel)transportDao.get(TransportModel.class, transport.getTransportId());
			if(transport2 != null && !transport2.getRoute().equalsIgnoreCase(transport.getRoute())) {
				TransportModel transportModel = transportDao.checkTransportRoute(transport.getRoute(), SessionManagerDataHelper.getSchoolCode());
				if(transportModel != null && transportModel.getRoute().equalsIgnoreCase(transport.getRoute())) {
					model.addObject("Transport", transport);
					model.addObject("createdOnStr", DateUtil.formatForUIDateTimeAMPM(transport.getCreatedOn()));
					model.setViewName("subadmin/editTransport");
					model.addObject("ErrorMessage", "Route already exist, please choose another one ...!!!");
					return model;
				}
			}
			String createdOnStr = request.getParameter("createdOnStr");
			transport.setSchoolProfile(new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode()));
			transport.setModifiedBy(SessionManagerDataHelper.getUsername());
			transport.setModifiedOn(new Date());
			transport.setCreatedOn(DateUtil.parseFromUIDateTimeAMPM(createdOnStr));
			transportDao.update(transport);
			if("saveReturn".equals(operation)) {
				navigate(model);
				return model;
			} else {
				model.setViewName("subadmin/editTransport");
				model.addObject("Transport", transport);
				model.addObject("SuccessMessage", "Transport Details Successfully Updated ...!!!");
				model.addObject("createdOnStr", DateUtil.formatForUIDateTimeAMPM(transport.getCreatedOn()));				
			}
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: updateTransport() in TransportServices ", e);
		}
		return model;
	}
	
	public ModelAndView updatedTransport() {
		ModelAndView model = resultTransport(null);
		model.addObject("SuccessMessage", "Transport Details Successfully Updated ...!!!");
		return model;
	}
	
	
	public List<TransportModel> getAllActiveTransports(){
		return transportDao.getAllActiveTransports(SessionManagerDataHelper.getSchoolCode());
	}
	
	
	private void navigate(ModelAndView model) {
		if(session.getAttribute("searched") != null) {
			model.setViewName(StaticValue.REDIRECT_PAGE_URL);
			model.addObject("rd", Base64.encodeToString("resultTransport".getBytes(), true));
		} else {
			model.setViewName("redirect:searchTransport");
		}
	}
	
	
}
