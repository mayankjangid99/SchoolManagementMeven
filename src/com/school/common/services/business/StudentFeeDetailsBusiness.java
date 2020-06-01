package com.school.common.services.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.school.common.facade.FacadeDaoManager;
import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.CommonConstaint;
import com.school.common.generic.DownloadFile;
import com.school.common.generic.LookUpFile;
import com.school.common.generic.SchoolProfileUtils;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.generic.UniqueIdHelper;
import com.school.common.jasper.GenerateJasperReport;
import com.school.common.model.AdmissionDetailsModel;
import com.school.common.model.ClassFeeCategoryModel;
import com.school.common.model.FeeCategoryModel;
import com.school.common.model.StudentAdditionalFeeDetailsModel;
import com.school.common.model.StudentDetailsModel;
import com.school.common.model.StudentFeeDetailsModel;
import com.school.common.reports.model.ReceiptFeeReportModel;
import com.school.common.reports.model.ReceiptFeeSubReportModel;
import com.school.common.reports.model.StudentDetailsReportModel;
import com.school.common.reports.model.StudentFeeDetailsSubReportModel;
import com.school.json.model.GenericJsonListContainerModel;
import com.school.json.model.StudentDetailsJsonModel;
import com.school.json.model.StudentFeeDetailsJsonModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class StudentFeeDetailsBusiness 
{

	@Autowired
	private UniqueIdHelper uniqueIdHelper;
	
	@Autowired
	private FacadeDaoManager facadeDaoManager;
	
	private static Logger LOG = LoggerFactory.getLogger(StudentFeeDetailsBusiness.class);
	
	public List<StudentDetailsJsonModel> fetchClassFeeDetails(Integer month, String resultType, List<StudentDetailsModel> studentDetailsList, List<ClassFeeCategoryModel> categoryModelsList, 
			List<StudentFeeDetailsModel> studentFeeDetailsList, List<StudentAdditionalFeeDetailsModel> additionalFeeDetailsList)
	{
		studentFeeDetailsList = getCalAllSubmittedFeeDetails(studentFeeDetailsList);
		List<StudentDetailsJsonModel> responseDataList = new ArrayList<StudentDetailsJsonModel>();
		StudentDetailsJsonModel studentDetailsJsonModel = null;
		StudentFeeDetailsJsonModel studentFeeDetailsJsonModel = null;
		List<StudentFeeDetailsJsonModel> feeDetailsJsonModelsList = null;
		for(StudentDetailsModel studentDetailsModel : studentDetailsList)
		{
			boolean addFlag = true;
			String innerAddFlagN = null;
			String innerAddFlagY = null;
			boolean isSubmitAnyFee = false;
			studentDetailsJsonModel = new StudentDetailsJsonModel();
			feeDetailsJsonModelsList = new ArrayList<StudentFeeDetailsJsonModel>();
			studentDetailsJsonModel.setsImage(studentDetailsModel.getImage());
			studentDetailsJsonModel.setAdmissionNo(studentDetailsModel.getAdmissionNo());
			AdmissionDetailsModel admissionDetailsModel = BusinessLogicHelper.getAdmisionDetailsFromStudentDetails(studentDetailsModel);
			studentDetailsJsonModel.setRollNo(admissionDetailsModel.getRollNo().toString());
			studentDetailsJsonModel.setsFirstName(studentDetailsModel.getFirstName());
			studentDetailsJsonModel.setsLastName(studentDetailsModel.getLastName());
			studentDetailsJsonModel.setfName(studentDetailsModel.getParentDetails().getFatherName());
			studentDetailsJsonModel.setmName(studentDetailsModel.getParentDetails().getMotherName());
			studentDetailsJsonModel.setClassName(admissionDetailsModel.getClassInformation().getClassName());
			studentDetailsJsonModel.setSectionName(admissionDetailsModel.getSectionInformation().getSectionName());
			studentDetailsJsonModel.setGender(studentDetailsModel.getGender());
			
			List<ClassFeeCategoryModel> feeCategoryList = new ArrayList<ClassFeeCategoryModel>();
			feeCategoryList.addAll(categoryModelsList);
			
			// Add Additional Fee for Student
			if(additionalFeeDetailsList != null && additionalFeeDetailsList.size() > 0) {
				for(StudentAdditionalFeeDetailsModel additionalFee : additionalFeeDetailsList) {
					if(studentDetailsModel.getAdmissionNo().equalsIgnoreCase(additionalFee.getAdmissionNo())
							&& admissionDetailsModel.getRollNo() == additionalFee.getRollNo()) {
						
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
				}
			}
			// END: Add Additional Fee for Student
			
			for(ClassFeeCategoryModel classFeeCategoryModel : feeCategoryList)
			{
				studentFeeDetailsJsonModel = new StudentFeeDetailsJsonModel();
				studentFeeDetailsJsonModel.setFeeCategoryCode(classFeeCategoryModel.getFeeCategory().getFeeCategoryCode());
				studentFeeDetailsJsonModel.setFeeCategoryName(classFeeCategoryModel.getFeeCategory().getFeeCategoryName());
				studentFeeDetailsJsonModel.setTotalAmount(classFeeCategoryModel.getAmount().setScale(2).toString());
				if("Y".equals(classFeeCategoryModel.getType())) {
					studentFeeDetailsJsonModel.setMonthName("Yearly");
				} else {
					studentFeeDetailsJsonModel.setMonthName(BusinessLogicHelper.getMonthName(month));
					studentFeeDetailsJsonModel.setMonth(""+month);
				}
				
				for(StudentFeeDetailsModel studentFeeDetailsModel : studentFeeDetailsList)
				{
					if(studentFeeDetailsModel.getAdmissionNo().equals(studentDetailsModel.getAdmissionNo()) && studentFeeDetailsModel.getRollNo() == admissionDetailsModel.getRollNo()){
						isSubmitAnyFee = true;
						if(studentFeeDetailsModel.getFeeCategoryCode().equals(classFeeCategoryModel.getFeeCategory().getFeeCategoryCode()))
						{
							//if found any due amount then not add in list
							if(classFeeCategoryModel.getAmount().compareTo(studentFeeDetailsModel.getAmountPaid()) != 0 && "Y".equals(resultType) && innerAddFlagY == null)
								innerAddFlagY = "Y";
							//if found any no due amount then add for list
							else if(studentFeeDetailsModel.getAmountDue().compareTo(BigDecimal.ZERO) != 0 && "N".equals(resultType) && innerAddFlagN == null)
								innerAddFlagN = "Y";
							
							studentFeeDetailsJsonModel.setAmountDue(studentFeeDetailsModel.getAmountDue().setScale(2).toString());
							studentFeeDetailsJsonModel.setAmountPaid(studentFeeDetailsModel.getAmountPaid().setScale(2).toString());
							//studentFeeDetailsJsonModel.setMonth(studentFeeDetailsModel.getMonth().toString());
							//studentFeeDetailsJsonModel.setMonthName(studentFeeDetailsModel.getMonthName());
							studentFeeDetailsJsonModel.setReceiptNo(studentFeeDetailsModel.getReceiptNo());
							break;
						}
					}
				}
				feeDetailsJsonModelsList.add(studentFeeDetailsJsonModel);
			}

			if(!isSubmitAnyFee && resultType != null && "Y".equals(resultType))
				addFlag = false;
			else if(isSubmitAnyFee && innerAddFlagY != null && resultType != null && "Y".equals(resultType))
				addFlag = false;
			else if(isSubmitAnyFee && innerAddFlagN == null && resultType != null && "N".equals(resultType))
				addFlag = false;
			
			GenericJsonListContainerModel genericJsonListContainerModel = new GenericJsonListContainerModel();
			genericJsonListContainerModel.setJsonListContainer(feeDetailsJsonModelsList);
			studentDetailsJsonModel.setDataContainer(genericJsonListContainerModel);
			if(addFlag)
				responseDataList.add(studentDetailsJsonModel);
		}
		return responseDataList;
	}
	
		
	public String getGenerateFeeReceiptNumber(StudentFeeDetailsModel studentFeeDetails)
	{
		String sysGenReceipt = uniqueIdHelper.getNextTemplateName("RECEIPT-#", "FEE-RECEIPT");
		String increamentalDigit = null;
		LOG.info("NextTemplateName = " + sysGenReceipt);
		if(sysGenReceipt.equals("RECEIPT-#"))
			LOG.error("Data is not found in Table 'UNIQUE_ID' where template_id = 'FEE-RECEIPT' and tepmplate_name = 'RECEIPT-#'");
		else
			increamentalDigit = sysGenReceipt.split("-")[1];
		DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
		String yearDigit = df.format(Calendar.getInstance().getTime());
		String monthDigit = null;
		if(studentFeeDetails.getMonth() != null){
			monthDigit = String.format("%02d", studentFeeDetails.getMonth());
		}else {
			monthDigit = "00";
		}
		String schoolCode = SessionManagerDataHelper.getSchoolCode();
		return schoolCode + yearDigit + monthDigit + increamentalDigit;
	}
	
	
	public String generateFeeReceipt(String type, StudentDetailsModel studentDetails, List<ClassFeeCategoryModel> categoryModelsList, List<StudentFeeDetailsModel> studentFeeDetailsList,
			List<StudentAdditionalFeeDetailsModel> additionalFeeDetailsList)
	{
		ReceiptFeeReportModel receiptFeeReport = new ReceiptFeeReportModel(); 
		receiptFeeReport.setsFirstName(studentDetails.getFullname());
		receiptFeeReport.setAdmissionNo(studentDetails.getAdmissionNo());
		AdmissionDetailsModel admissionDetailsModel = BusinessLogicHelper.getAdmisionDetailsFromStudentDetails(studentDetails);
		receiptFeeReport.setRollNo(admissionDetailsModel.getRollNo().toString());
		receiptFeeReport.setFatherName(studentDetails.getParentDetails().getFatherName());
		receiptFeeReport.setMotherName(studentDetails.getParentDetails().getMotherName());
		receiptFeeReport.setClassName(admissionDetailsModel.getClassInformation().getClassName());
		receiptFeeReport.setSectionName(admissionDetailsModel.getSectionInformation().getSectionName());
		if("M".equalsIgnoreCase(studentDetails.getGender()))
			receiptFeeReport.setGender("MALE");
		else
			receiptFeeReport.setGender("FEMALE");
		//receiptFeeReport.setDob(studentDetails.getDateOfBirth().toString());
		receiptFeeReport.setsAddress(studentDetails.getAddress());
		receiptFeeReport.setfMobile(studentDetails.getParentDetails().getFatherMobile());
		receiptFeeReport.setSchoolName(SessionManagerDataHelper.getSchoolProfileUtil().getName());
		if(SessionManagerDataHelper.getSchoolProfileUtil().getLogo() != null && !SessionManagerDataHelper.getSchoolProfileUtil().getLogo().isEmpty())
			receiptFeeReport.setSchoolLogo(CommonConstaint.SCHOOL_IMAGE_PATH + File.separator + SessionManagerDataHelper.getSchoolProfileUtil().getLogo());
		else
			receiptFeeReport.setSchoolLogo(null);
		SchoolProfileUtils schoolProfileUtils = SessionManagerDataHelper.getSchoolProfileUtil();
		String address = schoolProfileUtils.getAddress();
		if(schoolProfileUtils.getDistrict() != null)
			address = address+ ", " + schoolProfileUtils.getDistrict();
		if(schoolProfileUtils.getCity() != null)
			address = address + ", " + schoolProfileUtils.getCity();
		if(schoolProfileUtils.getPostcode() != null)
			address = address + "\nPin- " + schoolProfileUtils.getPostcode();
		if(schoolProfileUtils.getPhone() != null)
			address = address + "\nPhone: " + schoolProfileUtils.getPhone();
		if(schoolProfileUtils.getFax() != null)
			address = address + ", Fax: " + schoolProfileUtils.getFax();
		
		receiptFeeReport.setSchoolAddress(address);
		
		List<ReceiptFeeSubReportModel> receiptFeeSubReportList = new ArrayList<ReceiptFeeSubReportModel>();
		ReceiptFeeSubReportModel receiptFeeSubReport = null;
		
		List<ClassFeeCategoryModel> feeCategoryList = new ArrayList<ClassFeeCategoryModel>();
		feeCategoryList.addAll(categoryModelsList);
		
		// Add Additional Fee for Student
		if(additionalFeeDetailsList != null && additionalFeeDetailsList.size() > 0) {
			for(StudentAdditionalFeeDetailsModel additionalFee : additionalFeeDetailsList) {
				if(studentDetails.getAdmissionNo().equalsIgnoreCase(additionalFee.getAdmissionNo())
						&& admissionDetailsModel.getRollNo() == additionalFee.getRollNo()) {
					
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
			}
		}
		// END: Add Additional Fee for Student
		
		for(ClassFeeCategoryModel classFeeCategoryModel : feeCategoryList)
		{
			boolean foundFlagOuter = false;
			if(studentFeeDetailsList.isEmpty()){
				receiptFeeSubReport = new ReceiptFeeSubReportModel();
				receiptFeeSubReport.setFeeCategoryCode(classFeeCategoryModel.getFeeCategory().getFeeCategoryCode());
				receiptFeeSubReport.setFeeCategoryName(classFeeCategoryModel.getFeeCategory().getFeeCategoryName());
				receiptFeeSubReport.setTotalAmount(classFeeCategoryModel.getAmount().toString());
			}
			for(StudentFeeDetailsModel studentFeeDetailsModel : studentFeeDetailsList)
			{
				boolean foundFlag = false;
				if(studentFeeDetailsModel.getFeeCategoryCode().equals(classFeeCategoryModel.getFeeCategory().getFeeCategoryCode()))
				{			
					foundFlagOuter = true;
					foundFlag = true;
					receiptFeeSubReport = new ReceiptFeeSubReportModel();
					receiptFeeSubReport.setFeeCategoryCode(classFeeCategoryModel.getFeeCategory().getFeeCategoryCode());
					receiptFeeSubReport.setFeeCategoryName(classFeeCategoryModel.getFeeCategory().getFeeCategoryName());
					receiptFeeSubReport.setTotalAmount(studentFeeDetailsModel.getTotalAmount().toString());
					
					receiptFeeSubReport.setAmountDue(studentFeeDetailsModel.getAmountDue().toString());
					receiptFeeSubReport.setAmountPaid(studentFeeDetailsModel.getAmountPaid().toString());
					receiptFeeSubReport.setMonth(studentFeeDetailsModel.getMonth());
					receiptFeeSubReport.setMonthName(studentFeeDetailsModel.getMonthName());
					receiptFeeReport.setReceiptNo(studentFeeDetailsModel.getReceiptNo());
					String pattern = "dd-MMM-yyyy hh.mm.ss aa";
					SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
					String date = dateFormat.format(studentFeeDetailsModel.getCreatedOn());
					receiptFeeSubReport.setDate(date);
					if(studentFeeDetailsModel.getPreviousStudentFeeDetailsId() != null)
						receiptFeeSubReport.setHasLastTransaction("YES");
					else
						receiptFeeSubReport.setHasLastTransaction("NO");
					//break;
				}
				/*else if(studentFeeDetailsModel.getType().equals(classFeeCategoryModel.getType())){
					foundFlag = true;
					receiptFeeSubReportList.add(receiptFeeSubReport);
				}*/
				if(foundFlag)
					receiptFeeSubReportList.add(receiptFeeSubReport);
			}
			if(!foundFlagOuter){
				receiptFeeSubReport = new ReceiptFeeSubReportModel();
				receiptFeeSubReport.setFeeCategoryCode(classFeeCategoryModel.getFeeCategory().getFeeCategoryCode());
				receiptFeeSubReport.setFeeCategoryName(classFeeCategoryModel.getFeeCategory().getFeeCategoryName());
				receiptFeeSubReport.setTotalAmount(classFeeCategoryModel.getAmount().toString());
				receiptFeeSubReportList.add(receiptFeeSubReport);
			}
		}
		receiptFeeReport.setReceiptFeeSubReportList(receiptFeeSubReportList);
		return prepareFeeReceipt(type, receiptFeeReport);
	}
	
	@SuppressWarnings("deprecation")
	public String prepareFeeReceipt(String type, ReceiptFeeReportModel receiptFeeReport)
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String realpath = request.getRealPath("/");
		List<ReceiptFeeReportModel> jasperDataList = new ArrayList<ReceiptFeeReportModel>();
		jasperDataList.add(receiptFeeReport);
		InputStream inputStream = null;
		String fileName = "FEE_RECEIPT.pdf";
		String reportPath = CommonConstaint.REPORT_GENERATED_PATH + File.separator + SessionManagerDataHelper.getSchoolCode() + File.separator + SessionManagerDataHelper.getUsername();
		BusinessLogicHelper.createDirectory(reportPath);
		reportPath = realpath + reportPath+ File.separator + fileName;
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(jasperDataList);
		 
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String uploadPath = realpath + "upload\\";
		String imagePath = realpath + "images\\";
		String realpathReport = realpath + "reports\\";
		try {
			inputStream = new FileInputStream (realpathReport + File.separator + LookUpFile.FeeReceiptReport + ".jrxml");
			parameters.put("SUBREPORT_DIR", realpathReport);
			parameters.put("IMAGE_PATH", imagePath);
			parameters.put("UPLOAD_PATH", uploadPath);
			parameters.put("REAL_PATH", realpath);
			 
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
			GenerateJasperReport.generateJasperReport(jasperPrint, "pdf", reportPath);
		} catch (FileNotFoundException | JRException e) {
			e.printStackTrace();
			LOG.error("ERROR: prepareFeeReceipt() in StudentFeeDetailsBusiness.class" + e);
		}
		if(type != null && "D".equals(type))
			DownloadFile.getFile(reportPath);
		return reportPath;
	}
	
	
	

	
	public String generateClassFeeDetailsReport(String type, String resultType, List<StudentDetailsModel> studentDetailsList, List<ClassFeeCategoryModel> categoryModelsList, List<StudentFeeDetailsModel> studentFeeDetailsList, 
			StudentFeeDetailsModel studentFeeDetails, List<StudentAdditionalFeeDetailsModel> additionalFeeDetailsList)
	{
		studentFeeDetailsList = getCalAllSubmittedFeeDetails(studentFeeDetailsList);
		List<StudentDetailsReportModel> studentDetailsReportModelList = new ArrayList<StudentDetailsReportModel>();
		StudentDetailsReportModel studentDetailsReportModel = null;
		StudentFeeDetailsSubReportModel studentFeeDetailsSubReportModel = null;
		List<StudentFeeDetailsSubReportModel> feeDetailsSubReportModelsList = null;
		for(StudentDetailsModel studentDetailsModel : studentDetailsList)
		{
			feeDetailsSubReportModelsList = new ArrayList<StudentFeeDetailsSubReportModel>();
			boolean addFlag = true;
			String innerAddFlagN = null;
			String innerAddFlagY = null;
			boolean isSubmitAnyFee = false;
			studentDetailsReportModel = new StudentDetailsReportModel();
			studentDetailsReportModel.setsImage(studentDetailsModel.getImage());
			studentDetailsReportModel.setAdmissionNo(studentDetailsModel.getAdmissionNo());
			AdmissionDetailsModel admissionDetailsModel = BusinessLogicHelper.getAdmisionDetailsFromStudentDetails(studentDetailsModel);
			studentDetailsReportModel.setRollNo(admissionDetailsModel.getRollNo().toString());
			studentDetailsReportModel.setFullName(studentDetailsModel.getFirstName());
			//studentDetailsReportModel.setsLastName(studentDetailsModel.getLastName());
			studentDetailsReportModel.setFatherName(studentDetailsModel.getParentDetails().getFatherName());
			studentDetailsReportModel.setMotherName(studentDetailsModel.getParentDetails().getMotherName());
			studentDetailsReportModel.setClassName(admissionDetailsModel.getClassInformation().getClassName());
			studentDetailsReportModel.setSectionName(admissionDetailsModel.getSectionInformation().getSectionName());
			studentDetailsReportModel.setGender(studentDetailsModel.getGender());
			if(categoryModelsList != null && categoryModelsList.size() > 0) {
				ClassFeeCategoryModel classFeeCategoryModel = categoryModelsList.get(0);
				studentDetailsReportModel.setClassName(classFeeCategoryModel.getClassInformation().getClassName());
				studentDetailsReportModel.setSectionName(classFeeCategoryModel.getSectionInformation().getSectionName());
				HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				String month = request.getParameter("month");
				if(month != null && !"".equals(month)){
					request.setAttribute("MonthName", BusinessLogicHelper.getMonthName(Integer.parseInt(month)));
				} else {
					request.setAttribute("MonthName", "Yearly");
				}
			}

			studentDetailsReportModel.setSchoolName(SessionManagerDataHelper.getSchoolProfileUtil().getName());
			studentDetailsReportModel.setSchoolLogo(CommonConstaint.SCHOOL_IMAGE_PATH + File.separator + SessionManagerDataHelper.getSchoolProfileUtil().getLogo());
			SchoolProfileUtils schoolProfileUtils = SessionManagerDataHelper.getSchoolProfileUtil();
			String address = schoolProfileUtils.getAddress();
			if(schoolProfileUtils.getDistrict() != null)
				address = address+ ", " + schoolProfileUtils.getDistrict();
			if(schoolProfileUtils.getCity() != null)
				address = address + ", " + schoolProfileUtils.getCity();
			if(schoolProfileUtils.getPostcode() != null)
				address = address + "\nPin- " + schoolProfileUtils.getPostcode();
			if(schoolProfileUtils.getPhone() != null)
				address = address + "\nPhone: " + schoolProfileUtils.getPhone();
			if(schoolProfileUtils.getFax() != null)
				address = address + ", Fax: " + schoolProfileUtils.getFax();
			
			studentDetailsReportModel.setSchoolAddress(address);
			if(resultType == null || "".equals(resultType)){
				studentDetailsReportModel.setFeeStatus("ALL");
			} else if("Y".equals(resultType)){
				studentDetailsReportModel.setFeeStatus("SUBMITTED");				
			} else if("N".equals(resultType)){
				studentDetailsReportModel.setFeeStatus("NOT SUBMITTED");				
			}
			
			List<ClassFeeCategoryModel> feeCategoryList = new ArrayList<ClassFeeCategoryModel>();
			feeCategoryList.addAll(categoryModelsList);
			
			// Add Additional Fee for Student
			if(additionalFeeDetailsList != null && additionalFeeDetailsList.size() > 0) {
				for(StudentAdditionalFeeDetailsModel additionalFee : additionalFeeDetailsList) {
					if(studentDetailsModel.getAdmissionNo().equalsIgnoreCase(additionalFee.getAdmissionNo())
							&& admissionDetailsModel.getRollNo() == additionalFee.getRollNo()) {
						
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
				}
			}
			// END: Add Additional Fee for Student
			
			for(ClassFeeCategoryModel classFeeCategoryModel : feeCategoryList)
			{
				studentFeeDetailsSubReportModel = new StudentFeeDetailsSubReportModel();
				studentFeeDetailsSubReportModel.setFeeCategoryCode(classFeeCategoryModel.getFeeCategory().getFeeCategoryCode());
				studentFeeDetailsSubReportModel.setFeeCategoryName(classFeeCategoryModel.getFeeCategory().getFeeCategoryName());
				studentFeeDetailsSubReportModel.setTotalAmount(classFeeCategoryModel.getAmount().toString());
				
				for(StudentFeeDetailsModel studentFeeDetailsModel : studentFeeDetailsList)
				{
					if(studentFeeDetailsModel.getAdmissionNo().equals(studentDetailsModel.getAdmissionNo()) && studentFeeDetailsModel.getRollNo() == admissionDetailsModel.getRollNo()){
						isSubmitAnyFee = true;
						if(studentFeeDetailsModel.getFeeCategoryCode().equals(classFeeCategoryModel.getFeeCategory().getFeeCategoryCode()))
						{
							//if found any due amount then not add in list
							if(classFeeCategoryModel.getAmount().compareTo(studentFeeDetailsModel.getAmountPaid()) != 0 && "Y".equals(resultType) && innerAddFlagY == null)
								innerAddFlagY = "Y";
							//if found any no due amount then add for list
							else if(studentFeeDetailsModel.getAmountDue().compareTo(BigDecimal.ZERO) != 0 && "N".equals(resultType) && innerAddFlagN == null)
								innerAddFlagN = "Y";
							
							studentFeeDetailsSubReportModel.setAmountDue(studentFeeDetailsModel.getAmountDue().toString());
							studentFeeDetailsSubReportModel.setAmountPaid(studentFeeDetailsModel.getAmountPaid().toString());
							studentFeeDetailsSubReportModel.setMonth(studentFeeDetailsModel.getMonth());
							studentFeeDetailsSubReportModel.setMonthName(studentFeeDetailsModel.getMonthName());

							String pattern = "dd-MMM-yyyy hh.mm.ss aa";
							SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
							String date = dateFormat.format(studentFeeDetailsModel.getCreatedOn());
							studentFeeDetailsSubReportModel.setDate(date);
							/*if(studentFeeDetailsModel.getPreviousStudentFeeDetailsId() != null)
								studentFeeDetailsSubReportModel.setHasLastTransaction("YES");
							else
								studentFeeDetailsSubReportModel.setHasLastTransaction("NO");*/
							break;
						}
					}
				}
				feeDetailsSubReportModelsList.add(studentFeeDetailsSubReportModel);
			}

			if(!isSubmitAnyFee && resultType != null && "Y".equals(resultType))
				addFlag = false;
			else if(isSubmitAnyFee && innerAddFlagY != null && resultType != null && "Y".equals(resultType))
				addFlag = false;
			else if(isSubmitAnyFee && innerAddFlagN == null && resultType != null && "N".equals(resultType))
				addFlag = false;
			
			studentDetailsReportModel.setSubReportDataList(feeDetailsSubReportModelsList);
			if(addFlag)
				studentDetailsReportModelList.add(studentDetailsReportModel);
		}
		return prepareClassFeeDetailsReport(type, studentDetailsReportModelList);
	}
	
	
	@SuppressWarnings("deprecation")
	public String prepareClassFeeDetailsReport(String type, List<StudentDetailsReportModel> studentDetailsReportModelList)
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String realpath = request.getRealPath("/");
		InputStream inputStream = null;
		String fileName = "CLASS_FEE_DETAILS_RECEIPT.pdf";
		
		String reportPath = CommonConstaint.REPORT_GENERATED_PATH + File.separator + SessionManagerDataHelper.getSchoolCode() + File.separator + SessionManagerDataHelper.getUsername();
		BusinessLogicHelper.createDirectory(reportPath);
		reportPath = realpath + reportPath+ File.separator + fileName;
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(studentDetailsReportModelList);
		 
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String uploadPath = realpath + "upload\\";
		String imagePath = realpath + "images\\";
		String realpathReport = realpath + "reports\\";
		try {
			inputStream = new FileInputStream (realpathReport + File.separator + LookUpFile.ClassFeeDetailsReport + ".jrxml");
			parameters.put("SUBREPORT_DIR", realpathReport);
			parameters.put("IMAGE_PATH", imagePath);
			parameters.put("UPLOAD_PATH", uploadPath);
			parameters.put("REAL_PATH", realpath);
			parameters.put("MONTH_NAME", request.getAttribute("MonthName"));
			 
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
			GenerateJasperReport.generateJasperReport(jasperPrint, "pdf", reportPath);
		} catch (FileNotFoundException | JRException e) {
			e.printStackTrace();
			LOG.error("ERROR: prepareClassFeeDetailsReport() in StudentFeeDetailsBusiness.class" + e);
		}

		if(type != null && "D".equals(type))
			DownloadFile.getFile(reportPath);
		return realpath;
	}
	
	
	
	
	
	/*Calculated Due, Paid submitted fee Details*/
	public List<StudentFeeDetailsModel> getCalAllSubmittedFeeDetails(List<StudentFeeDetailsModel> studentFeeDetailsList){
		Map<String, StudentFeeDetailsModel> mapData = new HashMap<String, StudentFeeDetailsModel>();
		List<StudentFeeDetailsModel> studentFeeDetailsListFinal = new ArrayList<StudentFeeDetailsModel>();
		for(StudentFeeDetailsModel detailsModel : studentFeeDetailsList){
			String key = detailsModel.getAdmissionNo() + "_" + detailsModel.getType() + "_" + detailsModel.getFeeCategoryCode();
			StudentFeeDetailsModel sumStudentFee = mapData.get(key);
			if(sumStudentFee != null){
				sumStudentFee.setAmountPaid(sumStudentFee.getAmountPaid().add(detailsModel.getAmountPaid()));
				if(sumStudentFee.getAmountDue().compareTo(detailsModel.getAmountDue()) > 0)
					sumStudentFee.setAmountDue(detailsModel.getAmountDue());
				mapData.put(key, sumStudentFee);
			} else{
				mapData.put(key, detailsModel);
			}
			studentFeeDetailsListFinal = new ArrayList<StudentFeeDetailsModel>(mapData.values());
		}
		return studentFeeDetailsListFinal;
		
	}
	
	
	public BigDecimal getTotalAmountPaid(List<StudentFeeDetailsModel> studentFeeDetailsList){
		BigDecimal paid = new BigDecimal("0.00");
		for(StudentFeeDetailsModel detailsModel : studentFeeDetailsList){
			paid = paid.add(detailsModel.getAmountPaid());
		}
		return paid;
	}
	
	public BigDecimal getLastAmountDue(List<StudentFeeDetailsModel> studentFeeDetailsList){
		BigDecimal due = null;
		for(StudentFeeDetailsModel detailsModel : studentFeeDetailsList){
			if(due == null){
				due = new BigDecimal(detailsModel.getAmountDue().toString());
			}
			else if(due.compareTo(detailsModel.getAmountDue()) > 0){
				due = detailsModel.getAmountDue();
			}
		}
		return due;
	}
}
