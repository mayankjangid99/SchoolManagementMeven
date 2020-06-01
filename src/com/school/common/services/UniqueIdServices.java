package com.school.common.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UniqueIdServices {

	
	public ModelAndView searchUniqueId() {
		return new ModelAndView("admin/searchUniqueId");
	}
}
