package com.school.common.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.facade.FacadeServicesManager;
import com.school.common.generic.BusinessLogicHelper;
import com.school.json.model.ClassFeeCategoryJsonModel;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CommonServices {
		
	@Autowired
	private FacadeServicesManager facadeServicesManager;
	
	
	public ResponseEntity<List<ClassFeeCategoryJsonModel>> getAdditionaFeeCategories() {
		return facadeServicesManager.getFeeCategoryServices().getAdditionaFeeCategories();
	}
	
	public ModelAndView redirectPage() {
		return new ModelAndView("user/redirectPage");
	}
	
	public ModelAndView selectClassSectionModal(HttpServletRequest request){
		ModelAndView modal = new ModelAndView("user/selectClassSectionModal");
			//String action = request.getParameter("action");
			String action = BusinessLogicHelper.getQueryStringFromPost(request);
			modal.addObject("Action", action);
		return modal;
	}
}