package com.school.common.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.PaymentCategoryDao;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.model.PaymentCategoryModel;
import com.school.common.model.SchoolPaymentCategoryModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.SessionDetailsModel;
import com.school.common.services.business.PaymentCategoryBusiness;
import com.school.json.model.SchoolPaymentCategoryJsonModel;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PaymentCategoryServices 
{
	@Autowired
	private PaymentCategoryDao paymentCategoryDao;
	
	@Autowired
	private PaymentCategoryBusiness paymentCategoryBusiness;
	
	public ModelAndView searchPaymentCategory()
	{
		ModelAndView model = new ModelAndView("supadmin/searchPaymentCategory");
		setAllDropDown(model);
		return model;
	}
	
	public ModelAndView resultPaymentCategory(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("supadmin/resultPaymentCategory");
		model.addObject("QueryString", BusinessLogicHelper.getQueryStringFromPost(request));
		return model;
	}
	
	public List<SchoolPaymentCategoryJsonModel> fetchAllocatedPaymentCategory(PaymentCategoryModel paymentCategory){
		List<SchoolPaymentCategoryModel> list = paymentCategoryDao.getAllocatedSchoolPaymentCategory(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), paymentCategory);
		List<SchoolPaymentCategoryJsonModel> responseList = paymentCategoryBusiness.fetchAllocatedPaymentCategory(list);
		return responseList;
	}
	
	public ModelAndView addPaymentCategory()
	{
		ModelAndView model = new ModelAndView("supadmin/addPaymentCategory");
		setAllDropDown(model);
		setNotAllocatedDropDown(model);
		setAllocatedDropDown(model);
		return model;
	}
	
	
	public ModelAndView savePaymentCategory(PaymentCategoryModel paymentCategory)
	{
		ModelAndView model = new ModelAndView("redirect:savedPaymentCategory");
		PaymentCategoryModel paymentCategoryModel = paymentCategoryDao.getPaymentCategoryByCode(paymentCategory);
		if(paymentCategoryModel != null)
		{
			setAllDropDown(model);
			setNotAllocatedDropDown(model);
			setAllocatedDropDown(model);
			model.setViewName("supadmin/addPaymentCategory");
			model.addObject("ErrorMessage", "Payment Category information already exist, please enter other Payment Category information...!!!");
			return model;
		}
		else
		{
			paymentCategoryDao.savePaymentCategory(paymentCategory);
			return model;
		}
	}
	
	public ModelAndView savedPaymentCategory()
	{
		ModelAndView model = new ModelAndView("supadmin/addPaymentCategory");
		model.addObject("SuccessMessage", "Payment Category Information Successfully Saved...!!!");
		setAllDropDown(model);
		setNotAllocatedDropDown(model);
		setAllocatedDropDown(model);
		return model;
	}
	
	public ModelAndView editPaymentCategory(PaymentCategoryModel paymentCategory)
	{
		ModelAndView model = new ModelAndView("supadmin/editPaymentCategory");
		PaymentCategoryModel paymentCategoryModel = paymentCategoryDao.getPaymentCategoryByCode(paymentCategory);
		model.addObject("PaymentCategory", paymentCategoryModel);
		return model;
	}
	
	public ModelAndView updatePaymentCategory(PaymentCategoryModel paymentCategory)
	{
		ModelAndView model = new ModelAndView("redirect:updatedPaymentCategory");
		paymentCategoryDao.updatePaymentCategory(paymentCategory);
		return model;
	}
	
	
	public ModelAndView updatedPaymentCategory()
	{
		ModelAndView model = new ModelAndView("supadmin/editPaymentCategory");
		model.addObject("SuccessMessage", "Payment Category Information Successfully updated...!!!");
		return model;
	}
	
	
	public ModelAndView deletePaymentCategory(PaymentCategoryModel paymentCategory)
	{
		ModelAndView model = new ModelAndView("redirect:deletedPaymentCategory");
		paymentCategoryDao.deletePaymentCategory(paymentCategory);
		return model;
	}
	
	
	public ModelAndView deletedPaymentCategory()
	{
		ModelAndView model = new ModelAndView("supadmin/addPaymentCategory");
		model.addObject("SuccessMessage", "Payment Category Information Successfully deleted...!!!");
		setAllDropDown(model);
		setNotAllocatedDropDown(model);
		setAllocatedDropDown(model);
		return model;
	}
	
	public ModelAndView saveAllocatePaymentCategory(PaymentCategoryModel paymentCategory){
		ModelAndView model = new ModelAndView("redirect:savedAllocatePaymentCategory");
		SessionDetailsModel sessionDetails = new SessionDetailsModel();
		sessionDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		SchoolProfileModel schoolProfile = new SchoolProfileModel();
		schoolProfile.setSchoolCode(SessionManagerDataHelper.getSchoolCode());
		SchoolPaymentCategoryModel schoolPaymentCategory = new SchoolPaymentCategoryModel();
		schoolPaymentCategory.setSchoolProfile(schoolProfile);
		schoolPaymentCategory.setSessionDetails(sessionDetails);
		schoolPaymentCategory.setPaymentCategory(paymentCategory);
		paymentCategoryDao.saveAllocatedPaymentCategoryToSchool(schoolPaymentCategory);
		return model;
	}
	
	public ModelAndView savedAllocatePaymentCategory()
	{
		ModelAndView model = new ModelAndView("supadmin/addPaymentCategory");
		model.addObject("SuccessMessage", "Payment Category Information Successfully Allocated...!!!");
		setAllDropDown(model);
		setNotAllocatedDropDown(model);
		setAllocatedDropDown(model);
		return model;
	}
	
	
	public boolean deleteAllocatedPaymentCategory(SchoolPaymentCategoryModel schoolPaymentCategory){
		return paymentCategoryDao.deleteAllocatedPaymentCategory(schoolPaymentCategory);
	}
	
	private void setNotAllocatedDropDown(ModelAndView model){
		List<PaymentCategoryModel> list = paymentCategoryDao.getNotAllocatedPaymentCategory(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession());
		model.addObject("NotAllocated", list);
	}
	
	private void setAllocatedDropDown(ModelAndView model){
		List<PaymentCategoryModel> list = paymentCategoryDao.getAllocatedPaymentCategory(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession());
		model.addObject("Allocated", list);
	}
	
	private void setAllDropDown(ModelAndView model){
		List<PaymentCategoryModel> list = paymentCategoryDao.getAllPaymentCategory();
		model.addObject("All", list);
	}
	

	public void updateCashPaymentCategory() {
		PaymentCategoryModel cashCategoryTemp = new PaymentCategoryModel();
		cashCategoryTemp.setPaymentCategoryCode("CASH");
		cashCategoryTemp.setPaymentCategoryName("Cash");
		PaymentCategoryModel cashCategory = (PaymentCategoryModel) paymentCategoryDao.getPaymentCategoryByCode(cashCategoryTemp);
		if(cashCategory == null)
			paymentCategoryDao.savePaymentCategory(cashCategoryTemp);
	}
	
}
