package com.school.common.services;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.StudentFeeDetailsDao;
import com.school.common.facade.FacadeDaoManager;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.EmailLookUpFile;
import com.school.common.generic.EmailUtil;
import com.school.common.generic.FreemarkerTemplate;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.generic.UserProfileUtils;
import com.school.common.model.ClassFeeCategoryModel;
import com.school.common.model.FeeCategoryModel;
import com.school.common.model.SchoolPaymentCategoryModel;
import com.school.common.model.StudentAdditionalFeeDetailsModel;
import com.school.common.model.StudentDetailsModel;
import com.school.common.model.StudentFeeDetailsModel;
import com.school.common.services.business.StudentFeeDetailsBusiness;
import com.school.json.model.StudentDetailsJsonModel;

import freemarker.template.Template;
import freemarker.template.TemplateException;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StudentFeeDetailsServices 
{
	private static Logger LOG = LoggerFactory.getLogger(FreemarkerTemplate.class);
	
	@Autowired
	private StudentFeeDetailsDao studentFeeDetailsDao; 
	
	@Autowired
	private FacadeDaoManager facadeDaoManager;
	
	@Autowired
	private StudentFeeDetailsBusiness studentFeeDetailsBusiness;
	
	@Autowired
	private MessageSource messageSource;
	
	public ModelAndView searchStudentFeeDetails()
	{
		ModelAndView model = new ModelAndView("staff/searchStudentFeeDetails");
		return model;
	}
	
	
	public ModelAndView addStudentFeeDetails(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("subadmin/addStudentFeeDetails");
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		String admissionNo = request.getParameter("admissionNo");
		String rollNoStr = request.getParameter("rollNo");
		String receiptNo = request.getParameter("receiptNo");
		
		String schoolCode = SessionManagerDataHelper.getSchoolCode();
		String sessionCode = SessionManagerDataHelper.getSchoolCurrentSession();
		
		String status = request.getParameter("status");
		if(status == null || "".equals(status))
			status = "Y";
		
		List<StudentFeeDetailsModel> dataListByReceipt = null;
		String type = null;
		Integer month = null;
		if(receiptNo != null && !receiptNo.isEmpty()) {
			dataListByReceipt = studentFeeDetailsDao.getAllSubmittedStudentFeeDetailsByReceiptNo(receiptNo, schoolCode, sessionCode, classCode, sectionCode);
			if(dataListByReceipt != null && dataListByReceipt.size() > 0) {
				StudentFeeDetailsModel detailsModel = dataListByReceipt.get(0);
				admissionNo = detailsModel.getAdmissionNo();
				rollNoStr = detailsModel.getRollNo().toString();
				type = detailsModel.getType();
				month = detailsModel.getMonth();
			}
		}
		
		
		UserProfileUtils profileUtils = SessionManagerDataHelper.getUserProfileUtil();
		if("ROLE_STAFF".equals(profileUtils.getUserRoleId())){
			classCode = profileUtils.getClassCode();
			sectionCode = profileUtils.getSectionCode();
		}

		if("ROLE_USER".equals(profileUtils.getUserRoleId())){
			classCode = profileUtils.getStudentProfileUtils().getClassCode();
			sectionCode = profileUtils.getStudentProfileUtils().getSectionCode();
			admissionNo = profileUtils.getStudentProfileUtils().getAdmissionNo();
			rollNoStr = profileUtils.getStudentProfileUtils().getRollNo();
		}

		int rollNo = 0;
		if(rollNoStr != null && !"".equals(rollNoStr))
			rollNo = Integer.parseInt(rollNoStr);
		
		StudentDetailsModel studentDetailsModel = facadeDaoManager.getStudentDetailsDao().getStudentDetailsByAddmissioNoOrRollNo(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classCode, sectionCode, admissionNo, rollNo, status);
		if(studentDetailsModel == null){
			model.setViewName("subadmin/searchStudentFeeDetails");
			model.addObject("ErrorMessage", messageSource.getMessage("ui.text.valid.msg.recordsNotFound", null, null));
			return model;
		}
		
		List<ClassFeeCategoryModel> feeCategoryList = facadeDaoManager.getFeeCategoryDao().getFeeCategoryListByClassCodeSchoolCodeAndType(SessionManagerDataHelper.getUserActiveSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classCode, sectionCode, null, "N");
		
		// Add Additional Fee for Student
		StudentAdditionalFeeDetailsModel studentAdditionalFeeDetails = new StudentAdditionalFeeDetailsModel(schoolCode, sessionCode, classCode, sectionCode, admissionNo, rollNo);
		List<StudentAdditionalFeeDetailsModel> additionalFeeDetailsList = facadeDaoManager.getStudentAdditionalFeeDetailsDao().getStudentAdditionalFeeDetails(studentAdditionalFeeDetails);
		for(StudentAdditionalFeeDetailsModel additionalFee : additionalFeeDetailsList) {
			ClassFeeCategoryModel categoryModel = new ClassFeeCategoryModel();
			BeanUtils.copyProperties(additionalFee, categoryModel);
			categoryModel.setFeeType("A");
			if(additionalFee.getFeeCategoryCode().equals(StaticValue.FEE_CATEGORY_TRANSPORT)) {
				categoryModel.setFeeCategory(new FeeCategoryModel(StaticValue.FEE_CATEGORY_TRANSPORT, StaticValue.FEE_CATEGORY_TRANSPORT_NAME + " (Add.)"));
			} else {
				List<FeeCategoryModel> feeCategoryModelList = facadeDaoManager.getFeeCategoryDao().getFeeCategoryListByCategoryCode(new FeeCategoryModel(additionalFee.getFeeCategoryCode()));
				if(feeCategoryModelList != null && feeCategoryModelList.size() > 0) {
					FeeCategoryModel feeModel = feeCategoryModelList.get(0);
					feeModel.setFeeCategoryName(feeModel.getFeeCategoryName() + " (Add.)");
					categoryModel.setFeeCategory(feeModel);
				}
			}
			feeCategoryList.add(categoryModel);
		}
		// END: Add Additional Fee for Student
		
		
		List<ClassFeeCategoryModel> classFeeCategoryYearly = new ArrayList<ClassFeeCategoryModel>();
		List<ClassFeeCategoryModel> classFeeCategoryHalfYearly = new ArrayList<ClassFeeCategoryModel>();
		List<ClassFeeCategoryModel> classFeeCategoryMonthly = new ArrayList<ClassFeeCategoryModel>();
		List<List<ClassFeeCategoryModel>> classFeeCategoryMonthlyForYear = new ArrayList<List<ClassFeeCategoryModel>>();
		List<List<ClassFeeCategoryModel>> classFeeCategoryHalfForYear = new ArrayList<List<ClassFeeCategoryModel>>();
		for(ClassFeeCategoryModel categoryModel : feeCategoryList)
		{
			if(categoryModel.getType().equals("Y")){
				if(receiptNo != null && !receiptNo.isEmpty() && type != null && month == 0 && "Y".equals(type))
					classFeeCategoryYearly.add(categoryModel);
				else if(receiptNo == null || receiptNo.isEmpty())
					classFeeCategoryYearly.add(categoryModel);
			}
			if(categoryModel.getType().equals("H")){
				classFeeCategoryHalfYearly.add(categoryModel);
			}
			if(categoryModel.getType().equals("M")){
				classFeeCategoryMonthly.add(categoryModel);
			}
		}
		
		for(int i = 0; i < 12; i ++) {
			if(receiptNo != null && !receiptNo.isEmpty() && month != null && month > 0 && "M".equals(type)) {
				if(i == month)
					classFeeCategoryMonthlyForYear.add(classFeeCategoryMonthly);
			} else if(receiptNo == null || receiptNo.isEmpty()) {
				classFeeCategoryMonthlyForYear.add(classFeeCategoryMonthly);
			}
		}
		for(int i = 0; i < 2; i ++)
			classFeeCategoryHalfForYear.add(classFeeCategoryHalfYearly);
		List<StudentFeeDetailsModel> studentFeeDetailsList = studentFeeDetailsDao.getAllSubmittedStudentFeeDetails(SessionManagerDataHelper.getUserActiveSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), classCode, sectionCode, admissionNo, rollNo);
		if(receiptNo != null && !receiptNo.isEmpty()) {
			studentFeeDetailsList.clear();
			studentFeeDetailsList.addAll(dataListByReceipt);
		}
		studentFeeDetailsList = studentFeeDetailsBusiness.getCalAllSubmittedFeeDetails(studentFeeDetailsList);
		/*Map<String, StudentFeeDetailsModel> cal = new HashMap<String, StudentFeeDetailsModel>();
		for(StudentFeeDetailsModel detailsModel : studentFeeDetailsList){
			String key = detailsModel.getType() + "_" + detailsModel.getFeeCategoryCode();
			StudentFeeDetailsModel sumStudentFee = cal.get(key);
			if(sumStudentFee != null){
				sumStudentFee.setAmountPaid(sumStudentFee.getAmountPaid().add(detailsModel.getAmountPaid()));
				if(sumStudentFee.getAmountDue().compareTo(detailsModel.getAmountDue()) > 0)
					sumStudentFee.setAmountDue(detailsModel.getAmountDue());
				cal.put(key, sumStudentFee);
			} else{
				cal.put(key, detailsModel);
			}
		}

		List<StudentFeeDetailsModel> studentFeeDetailsListFinal = new ArrayList<StudentFeeDetailsModel>(cal.values());*/
		
		List<SchoolPaymentCategoryModel> schoolPaymentCategoryList = facadeDaoManager.getPaymentCategoryDao().getAllocatedSchoolPaymentCategory(SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), null);
		model.addObject("StudentDetails", studentDetailsModel);
		model.addObject("FeeCategoriesYearly", classFeeCategoryYearly);
		model.addObject("FeeCategoriesHalfYearly", classFeeCategoryHalfForYear);
		model.addObject("FeeCategoriesMonthly", classFeeCategoryMonthlyForYear);
		model.addObject("StudentFeeDetailses", studentFeeDetailsList);
		model.addObject("SchoolPaymentCategories", schoolPaymentCategoryList);
		return model;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject saveStudentFeeDetails(HttpServletRequest request, StudentFeeDetailsModel studentFeeDetails)
	{
		studentFeeDetails.setSchoolCode(SessionManagerDataHelper.getSchoolCode());
		studentFeeDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		String totalAmountStr = request.getParameter("totalAmount");
		BigDecimal totalAmount = new BigDecimal("0.00");
		if(studentFeeDetails.getMonth() == null && !"M".equalsIgnoreCase(studentFeeDetails.getType()))
			studentFeeDetails.setMonth(0);
		
		if(totalAmountStr != null && !"".equals(totalAmountStr))
			totalAmount = new BigDecimal(totalAmountStr);

		JSONObject jsonData = new JSONObject();
		if(studentFeeDetails.getAmountPaid() == null || studentFeeDetails.getAmountPaid().equals(""))
		{
			jsonData.put("msg", "Please enter Paid Amount...!!!");
			jsonData.put("hasError", true);
			return jsonData;
		}
		List<StudentFeeDetailsModel> detailsModels = studentFeeDetailsDao.getAllSubmittedStudentFeeDetailsByTypeAndFeeCodeAndRollAndAdmis(studentFeeDetails);
		BigDecimal duePre = studentFeeDetailsBusiness.getLastAmountDue(detailsModels);
		if(detailsModels.isEmpty()){
			if(studentFeeDetails.getAmountPaid() != null && studentFeeDetails.getAmountPaid().compareTo(studentFeeDetails.getTotalAmount()) > 0){
				jsonData.put("msg", "Please enter valid Paid Amount, you can't enter more than due amount...!!!");
				jsonData.put("hasError", true);
				return jsonData;
			}
		}else if(studentFeeDetails.getAmountPaid() != null && duePre != null && studentFeeDetails.getAmountPaid().compareTo(duePre) > 0){
			jsonData.put("msg", "Please enter valid Paid Amount, you can't enter more than due amount...!!!");
			jsonData.put("hasError", true);
			return jsonData;
		}

		List<StudentFeeDetailsModel> detailsModelsForReceipt = studentFeeDetailsDao.getAllSubmittedStudentFeeDetailsByTypeAndRollAndAdmis(studentFeeDetails);
		if(detailsModelsForReceipt.size() > 0){
			for(StudentFeeDetailsModel detailsModel : detailsModelsForReceipt){
				if(detailsModel.getReceiptNo() != null && !"".equals(detailsModel.getReceiptNo())){
					studentFeeDetails.setReceiptNo(detailsModel.getReceiptNo());
					break;
				}
			}
		}else {
			studentFeeDetails.setReceiptNo(studentFeeDetailsBusiness.getGenerateFeeReceiptNumber(studentFeeDetails));
		}
		studentFeeDetails.setCreatedOn(new Date());
		studentFeeDetails.setCreatedBy(SessionManagerDataHelper.getUsername());
		boolean flag = false;
		
		/*--------------------------------------------------- start -----------------------------------------------------*/
		StudentFeeDetailsModel previousStudentFeeDetails = studentFeeDetailsDao.getPreviousSubmittedStudentsFeeDetailsWithFieldByTypeAndFeeCode(studentFeeDetails);
		if(previousStudentFeeDetails == null) {
			BigDecimal amountDue = totalAmount.subtract(studentFeeDetails.getAmountPaid());
			studentFeeDetails.setAmountDue(amountDue);
			flag = studentFeeDetailsDao.saveStudentFeeDetails(studentFeeDetails);
		} else {
			studentFeeDetails.setPreviousStudentFeeDetailsId(previousStudentFeeDetails.getStudentFeeDetailsId());
			studentFeeDetails.setTotalAmount(previousStudentFeeDetails.getAmountDue());
			studentFeeDetails.setAmountDue(previousStudentFeeDetails.getAmountDue().subtract(studentFeeDetails.getAmountPaid()));
			flag = studentFeeDetailsDao.saveStudentFeeDetails(studentFeeDetails);
		}
		List<StudentFeeDetailsModel> studentFeeDetailsModelList = studentFeeDetailsDao.getAllSubmittedStudentFeeDetailsByTypeAndFeeCodeAndRollAndAdmis(studentFeeDetails);
		studentFeeDetailsModelList = studentFeeDetailsBusiness.getCalAllSubmittedFeeDetails(studentFeeDetailsModelList);
		BigDecimal paid = studentFeeDetailsBusiness.getTotalAmountPaid(studentFeeDetailsModelList);
		BigDecimal due = studentFeeDetailsBusiness.getLastAmountDue(studentFeeDetailsModelList);
		/*--------------------------------------------------- end -------------------------------------------------------*/
		
		if(!flag){
			jsonData.put("msg", "server encountered an internal error. please try again after some time...!!!");
			jsonData.put("hasError", true);
			return jsonData;
		}
		
		jsonData.put("paid", paid.setScale(2).toString());
		jsonData.put("due", due.setScale(2).toString());
		jsonData.put("msg", "Fee Successfully Submitted...!!!");
		jsonData.put("hasError", false);
		jsonData.put("receiptNo", studentFeeDetails.getReceiptNo());
		return jsonData;
	}
	
	
	public ModelAndView resultClassFeeDetails(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("staff/resultClassFeeDetails");
		String queryString = BusinessLogicHelper.getQueryStringFromPost(request);
		model.addObject("QueryString", queryString);
		
		String classCode = request.getParameter("classCode");
		String sectionCode = request.getParameter("sectionCode");
		UserProfileUtils profileUtils = SessionManagerDataHelper.getUserProfileUtil();
		if("ROLE_STAFF".equals(profileUtils.getUserRoleId())){
			classCode = profileUtils.getClassCode();
			sectionCode = profileUtils.getSectionCode();
		}
		model.addObject("ClassCode", classCode);
		model.addObject("SectionCode", sectionCode);
		return model;
	}
	
	
	public List<StudentDetailsJsonModel> fetchClassFeeDetails(StudentFeeDetailsModel studentFeeDetails, HttpServletRequest request)
	{
		String resultType = request.getParameter("resultType");
		/*if(request.getParameter("month") != null && !"".equals(request.getParameter("month")))
		{
			//String dateArray[] = request.getParameter("months").split("-");
			//studentFeeDetails.setMonth(Integer.parseInt(dateArray[1]));
			studentFeeDetails.setMonth(Integer.parseInt(request.getParameter("month")));
		}*/
		UserProfileUtils profileUtils = SessionManagerDataHelper.getUserProfileUtil();
		if("ROLE_STAFF".equals(profileUtils.getUserRoleId())){
			if(studentFeeDetails.getClassCode() == null || "".equals(studentFeeDetails.getClassCode()) 
					|| studentFeeDetails.getSectionCode() == null || "".equals(studentFeeDetails.getSectionCode())){
				studentFeeDetails.setClassCode(profileUtils.getClassCode());
				studentFeeDetails.setSectionCode(profileUtils.getSectionCode());
			}
		}
		studentFeeDetails.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		studentFeeDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		List<StudentDetailsModel> studentDetailsList = facadeDaoManager.getStudentDetailsDao().getAllStudentsDetailsByClassSectionSessionAndStatus(studentFeeDetails.getSchoolCode(), studentFeeDetails.getSessionCode(), studentFeeDetails.getClassCode(), studentFeeDetails.getSectionCode(), "Y");
		List<ClassFeeCategoryModel> categoryModelsList = facadeDaoManager.getFeeCategoryDao().getFeeCategoryListByClassCodeSchoolCodeAndType(studentFeeDetails.getSchoolCode(), studentFeeDetails.getSessionCode(), studentFeeDetails.getClassCode(), studentFeeDetails.getSectionCode(), studentFeeDetails.getType(), "N");
		List<StudentFeeDetailsModel> studentFeeDetailsList = studentFeeDetailsDao.getSubmittedAllStudentsFeeDetailsByType(studentFeeDetails);
		
		// Add Additional Fee for Student
		List<StudentAdditionalFeeDetailsModel> additionalFeeDetailsList = null;
		StudentAdditionalFeeDetailsModel studentAdditionalFeeDetails = new StudentAdditionalFeeDetailsModel(studentFeeDetails.getSchoolCode(), studentFeeDetails.getSessionCode(), studentFeeDetails.getClassCode(), studentFeeDetails.getSectionCode(), studentFeeDetails.getType());
		if(studentFeeDetails.getType() != null && !studentFeeDetails.getType().isEmpty())
			additionalFeeDetailsList = facadeDaoManager.getStudentAdditionalFeeDetailsDao().getClassSectionAdditionalFeeDetailsByType(studentAdditionalFeeDetails);
		else
			additionalFeeDetailsList = facadeDaoManager.getStudentAdditionalFeeDetailsDao().getClassSectionAdditionalFeeDetails(studentAdditionalFeeDetails);
		// END: Add Additional Fee for Student
		
		List<StudentDetailsJsonModel> responseDataList = studentFeeDetailsBusiness.fetchClassFeeDetails(studentFeeDetails.getMonth(), resultType, studentDetailsList, categoryModelsList, studentFeeDetailsList, additionalFeeDetailsList);
		
		
		return responseDataList;
	}
	
	
	public String generateFeeReceipt(String type, StudentFeeDetailsModel studentFeeDetails)
	{
		studentFeeDetails.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		studentFeeDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		StudentDetailsModel studentDetails = facadeDaoManager.getStudentDetailsDao().getStudentDetailsByAddmissioNoOrRollNo(studentFeeDetails.getSchoolCode(), studentFeeDetails.getSessionCode(), studentFeeDetails.getClassCode(), studentFeeDetails.getSectionCode(), studentFeeDetails.getAdmissionNo(), studentFeeDetails.getRollNo(), "Y");
		List<ClassFeeCategoryModel> categoryModelsList = facadeDaoManager.getFeeCategoryDao().getFeeCategoryListByClassCodeSchoolCodeAndType(studentFeeDetails.getSchoolCode(), studentFeeDetails.getSessionCode(), studentFeeDetails.getClassCode(), studentFeeDetails.getSectionCode(), studentFeeDetails.getType(), "N");
		List<StudentFeeDetailsModel> studentFeeDetailsList = new ArrayList<StudentFeeDetailsModel>();
		if(studentFeeDetails.getReceiptNo() != null && !"".equals(studentFeeDetails.getReceiptNo()))
			studentFeeDetailsList = studentFeeDetailsDao.getAllSubmittedStudentFeeDetailsByReceiptNo(studentFeeDetails.getReceiptNo(), studentFeeDetails.getSchoolCode(), studentFeeDetails.getSessionCode(), studentFeeDetails.getClassCode(), studentFeeDetails.getSectionCode());
		else if(studentFeeDetails.getAdmissionNo() != null && !"".equals(studentFeeDetails.getAdmissionNo()) && studentFeeDetails.getRollNo() != null && !"".equals(studentFeeDetails.getRollNo()))
			studentFeeDetailsList = studentFeeDetailsDao.getAllSubmittedStudentFeeDetailsByTypeAndRollAndAdmis(studentFeeDetails);
		
		// Add Additional Fee for Student
		List<StudentAdditionalFeeDetailsModel> additionalFeeDetailsList = null;
		StudentAdditionalFeeDetailsModel studentAdditionalFeeDetails = new StudentAdditionalFeeDetailsModel(studentFeeDetails.getSchoolCode(), studentFeeDetails.getSessionCode(), studentFeeDetails.getClassCode(), studentFeeDetails.getSectionCode(), studentFeeDetails.getType());
		if(studentFeeDetails.getType() != null && !studentFeeDetails.getType().isEmpty())
			additionalFeeDetailsList = facadeDaoManager.getStudentAdditionalFeeDetailsDao().getClassSectionAdditionalFeeDetailsByType(studentAdditionalFeeDetails);
		else
			additionalFeeDetailsList = facadeDaoManager.getStudentAdditionalFeeDetailsDao().getClassSectionAdditionalFeeDetails(studentAdditionalFeeDetails);
		// END: Add Additional Fee for Student
				
		return studentFeeDetailsBusiness.generateFeeReceipt(type, studentDetails, categoryModelsList, studentFeeDetailsList, additionalFeeDetailsList);
	}
	
	
	public ModelAndView getStudentFeeTransactionHistoryModal(StudentFeeDetailsModel studentFeeDetails){
		ModelAndView model = new ModelAndView("user/getStudentFeeTransactionHistoryModal");
		List<StudentFeeDetailsModel> studentFeeDetailsList = studentFeeDetailsDao.getAllSubmittedStudentFeeDetailsByReceiptNo(studentFeeDetails.getReceiptNo(), SessionManagerDataHelper.getSchoolCode(), SessionManagerDataHelper.getSchoolCurrentSession(), studentFeeDetails.getClassCode(), studentFeeDetails.getSectionCode());
		model.addObject("FeeTranHistories", studentFeeDetailsList);
		return model;
	}
	
	
	public void generateClassFeeDetailsReport(String type, StudentFeeDetailsModel studentFeeDetails, HttpServletRequest request)
	{
		String resultType = request.getParameter("resultType");
		studentFeeDetails.setSchoolCode(SessionManagerDataHelper.getUserActiveSchoolCode());
		studentFeeDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
		List<StudentDetailsModel> studentDetailsList = facadeDaoManager.getStudentDetailsDao().getAllStudentsDetailsByClassSectionSessionAndStatus(studentFeeDetails.getSchoolCode(), studentFeeDetails.getSessionCode(), studentFeeDetails.getClassCode(), studentFeeDetails.getSectionCode(), "Y");
		List<ClassFeeCategoryModel> categoryModelsList = facadeDaoManager.getFeeCategoryDao().getFeeCategoryListByClassCodeSchoolCodeAndType(studentFeeDetails.getSchoolCode(), studentFeeDetails.getSessionCode(), studentFeeDetails.getClassCode(), studentFeeDetails.getSectionCode(), studentFeeDetails.getType(), "N");
		List<StudentFeeDetailsModel> studentFeeDetailsList = studentFeeDetailsDao.getSubmittedAllStudentsFeeDetailsByType(studentFeeDetails);
		
		// Add Additional Fee for Student
		List<StudentAdditionalFeeDetailsModel> additionalFeeDetailsList = null;
		StudentAdditionalFeeDetailsModel studentAdditionalFeeDetails = new StudentAdditionalFeeDetailsModel(studentFeeDetails.getSchoolCode(), studentFeeDetails.getSessionCode(), studentFeeDetails.getClassCode(), studentFeeDetails.getSectionCode(), studentFeeDetails.getType());
		if(studentFeeDetails.getType() != null && !studentFeeDetails.getType().isEmpty())
			additionalFeeDetailsList = facadeDaoManager.getStudentAdditionalFeeDetailsDao().getClassSectionAdditionalFeeDetailsByType(studentAdditionalFeeDetails);
		else
			additionalFeeDetailsList = facadeDaoManager.getStudentAdditionalFeeDetailsDao().getClassSectionAdditionalFeeDetails(studentAdditionalFeeDetails);
		// END: Add Additional Fee for Student
				
		studentFeeDetailsBusiness.generateClassFeeDetailsReport(type, resultType, studentDetailsList, categoryModelsList, studentFeeDetailsList, studentFeeDetails, additionalFeeDetailsList);
		//return response;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject sendFeeReceiptOnEmail(StudentFeeDetailsModel feeDetails, HttpServletRequest request){
		String reportPath = generateFeeReceipt(null, feeDetails);
		String recipient = request.getParameter("recipient");
		String ccRecipient = request.getParameter("ccRecipient");
		String bccRecipient = request.getParameter("bccRecipient");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		JSONObject jsonObject = new JSONObject();
        if(!"Y".equals(SessionManagerDataHelper.getApplicationSettingsParameterValue(StaticValue.APPLICATION_SETTINGS_EMAIL_STUDENT_FEE_RECEIPT))){
        	jsonObject.put("hasError", true);
        	jsonObject.put("Message", "You are denide to send student fee receipt on email, please contact system administrator");
        	return jsonObject;
        }
		
		try {
			EmailUtil emailUtil = new EmailUtil();
			Template template = FreemarkerTemplate.getTemplate(EmailLookUpFile.BasicEmailForSchool + ".ftl");
			Map<String, Object> input = FreemarkerTemplate.getInputMap();			
			input.put("content", message);

			Writer consoleWriter = new StringWriter();
            template.process(input, consoleWriter);
            String mailMessageBody = consoleWriter.toString();
            
            emailUtil.sendEmailWithAttach(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), recipient, subject, mailMessageBody, reportPath);
			//emailUtil.sendEmailWithCCWithAttach(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), recipient, ccRecipient, subject, message, reportPath);
		} catch (TemplateException e) {
			//e.printStackTrace();
			LOG.error("ERROR: StudentFeeDetailsServices in sendFeeReceiptOnEmail()");
		} catch (IOException e) {
			//e.printStackTrace();
			LOG.error("ERROR: StudentFeeDetailsServices in sendFeeReceiptOnEmail()");
		}
		jsonObject.put("hasError", false);
    	jsonObject.put("Message", "Email successfully send...!!!");
    	return jsonObject;
	}
	
	public ModelAndView resultStudentFeeDetails(HttpServletRequest request)
	{
		ModelAndView model = addStudentFeeDetails(request);
		model.setViewName("user/resultStudentFeeDetails");
		return model;
	}
}
