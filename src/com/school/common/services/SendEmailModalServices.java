package com.school.common.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.generic.BusinessLogicHelper;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SendEmailModalServices {

	public ModelAndView sendEmailModal(HttpServletRequest request){
		ModelAndView modal = new ModelAndView("staff/sendEmailModal");
			//String action = request.getParameter("action");
			String action = BusinessLogicHelper.getQueryStringFromPost(request);
			modal.addObject("Action", action);
		return modal;
	}
}
