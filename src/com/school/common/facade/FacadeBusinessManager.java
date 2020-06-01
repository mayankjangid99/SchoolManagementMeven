package com.school.common.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.services.business.ClassSectionBusiness;

@Service(value = "facadeBusinessManager")
public class FacadeBusinessManager 
{
	@Autowired
	private ClassSectionBusiness classSectionBusiness;

	public ClassSectionBusiness getClassSectionBusiness() {
		return classSectionBusiness;
	}

	public void setClassSectionBusiness(ClassSectionBusiness classSectionBusiness) {
		this.classSectionBusiness = classSectionBusiness;
	}
	
	
}
