package com.school.common.services.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.school.common.model.SchoolPaymentCategoryModel;
import com.school.json.model.SchoolPaymentCategoryJsonModel;

@Service
public class PaymentCategoryBusiness {

	public List<SchoolPaymentCategoryJsonModel> fetchAllocatedPaymentCategory(List<SchoolPaymentCategoryModel> list){
		List<SchoolPaymentCategoryJsonModel> jsonResponseList = new ArrayList<SchoolPaymentCategoryJsonModel>();
		SchoolPaymentCategoryJsonModel categoryJsonModel = null;
		for(SchoolPaymentCategoryModel categoryModel : list){
			categoryJsonModel = new SchoolPaymentCategoryJsonModel();
			categoryJsonModel.setSchoolPaymentCategoryId(categoryModel.getSchoolPaymentCategoryId());
			categoryJsonModel.setPaymentCategoryCode(categoryModel.getPaymentCategory().getPaymentCategoryCode());
			categoryJsonModel.setPaymentCategoryName(categoryModel.getPaymentCategory().getPaymentCategoryName());
			categoryJsonModel.setSchoolCode(categoryModel.getSchoolProfile().getSchoolCode());
			categoryJsonModel.setSchoolName(categoryModel.getSchoolProfile().getName());
			categoryJsonModel.setSessionCode(categoryModel.getSessionDetails().getSessionCode());
			categoryJsonModel.setSessionName(categoryModel.getSessionDetails().getSessionName());
			jsonResponseList.add(categoryJsonModel);
		}
		return jsonResponseList;
	}
}
