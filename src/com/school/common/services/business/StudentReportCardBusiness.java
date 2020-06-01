package com.school.common.services.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.school.common.generic.BusinessLogicHelper;
import com.school.common.generic.CommonConstaint;
import com.school.common.generic.DownloadFile;
import com.school.common.generic.LookUpFile;
import com.school.common.generic.SchoolProfileUtils;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.jasper.GenerateJasperReport;
import com.school.common.model.AdmissionDetailsModel;
import com.school.common.model.ReportCardConfigModel;
import com.school.common.model.StudentDetailsModel;
import com.school.common.model.StudentReportCardModel;
import com.school.common.reports.model.StudentReportCardReportModel;
import com.school.common.util.StudentReportCardDynamicReportBuilder;
import com.school.json.model.ClassSubjectJsonModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class StudentReportCardBusiness {
	
	private static Logger LOG = LoggerFactory.getLogger(StudentReportCardBusiness.class);
	

	public Map<String, Map<String, Object>> preparedReportCardFields(List<ClassSubjectJsonModel> subjects, ReportCardConfigModel term, ReportCardConfigModel unitTest, HttpServletRequest request) {
		Map<String, Map<String, Object>> finalData = new LinkedHashMap<String, Map<String, Object>>();
		Map<String, Object> objectMap = null;
		Map<String, String> termList = null;
		Map<String, String> unitTestList = null;
		if(subjects != null && !subjects.isEmpty()) {
			for(ClassSubjectJsonModel subjectModel : subjects) {
				objectMap = new LinkedHashMap<String, Object>();
				objectMap.put(subjectModel.getSubjectCode(), subjectModel);
				
				if(term != null) {
					String configVal = term.getConfigValue();
					configVal = configVal.split("=")[0];
					int termLength = Integer.parseInt(configVal.trim());
					termList = new LinkedHashMap<String, String>();
					for(int i = 1; i <= termLength; i++ ) {
						String fieldId = subjectModel.getSubjectCode() + StaticValue.REPORT_CARD_TERM + i;
						termList.put(fieldId, getReportCardFieldValue(request, fieldId));
						if(unitTest != null) {
							String configValUnit = unitTest.getConfigValue();
							configValUnit = configValUnit.split("=")[0];
							int unitTestLength = Integer.parseInt(configValUnit.trim());
							unitTestList = new LinkedHashMap<String, String>();
							for(int j = 1; j <= unitTestLength; j++ ) {
								String fieldIdUnit = subjectModel.getSubjectCode() + StaticValue.REPORT_CARD_TERM + i + StaticValue.REPORT_CARD_UNIT_TEST + j;
								unitTestList.put(fieldIdUnit, getReportCardFieldValue(request, fieldIdUnit));
							}
							objectMap.put(subjectModel.getSubjectCode() + StaticValue.REPORT_CARD_TERM + i, unitTestList);
						}
					}
					objectMap.put(subjectModel.getSubjectCode() + StaticValue.REPORT_CARD_TERM, termList);
				}
				finalData.put(subjectModel.getSubjectCode(), objectMap);
				
			}
		}
		return finalData;
	}
	
	private String getReportCardFieldValue(HttpServletRequest request, String fieldId) {
		String value = request.getParameter(fieldId);
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public List<StudentReportCardModel> preparedReportCardObjects(Map<String, Map<String, Object>> fieldsData, String classCode, String sectionCode, String admissionNo, String rollNo) {
		List<StudentReportCardModel> objectList = null;
		if(fieldsData != null && !fieldsData.isEmpty()) {
			objectList = new ArrayList<StudentReportCardModel>();
			StudentReportCardModel reportCard = null;
			for(Map.Entry<String, Map<String, Object>> entry : fieldsData.entrySet()) {
				String subjectCode = entry.getKey();
				Map<String, Object> objectMap = entry.getValue();
				ClassSubjectJsonModel subject = (ClassSubjectJsonModel) objectMap.get(subjectCode);
				
				Map<String, String> termList = (Map<String, String>) objectMap.get(subjectCode + StaticValue.REPORT_CARD_TERM);
				if(termList != null && !termList.isEmpty()) {
					int i = 1;
					for(Map.Entry<String, String> term : termList.entrySet()) {
						Map<String, String> unitTestList = (Map<String, String>) objectMap.get(subjectCode + StaticValue.REPORT_CARD_TERM + i);
						if(unitTestList != null && !unitTestList.isEmpty()) {
							for(Map.Entry<String, String> unit : unitTestList.entrySet()) {
								if(unit.getValue() != null && !unit.getValue().isEmpty()) {
									reportCard = new StudentReportCardModel();
									reportCard.setSchoolCode(SessionManagerDataHelper.getSchoolCode());
									reportCard.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
									reportCard.setCreatedBy(SessionManagerDataHelper.getUsername());
									reportCard.setCreatedOn(new Date());
									reportCard.setClassCode(classCode);
									reportCard.setSectionCode(sectionCode);
									reportCard.setAdmissionNo(admissionNo);
									if(rollNo != null && !rollNo.isEmpty())
										reportCard.setRollNo(Integer.parseInt(rollNo));
									reportCard.setSubjectCode(subjectCode);
									reportCard.setTermId(term.getKey());
									reportCard.setUnitTestId(unit.getKey());
									if(unit.getValue() != null && !unit.getValue().isEmpty())
										reportCard.setMarks(Long.parseLong(unit.getValue()));

									objectList.add(reportCard);
								}
							}
							
						}
						if(term.getValue() != null && !term.getValue().isEmpty()) {
							reportCard = new StudentReportCardModel();
							reportCard.setSchoolCode(SessionManagerDataHelper.getSchoolCode());
							reportCard.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession());
							reportCard.setCreatedBy(SessionManagerDataHelper.getUsername());
							reportCard.setCreatedOn(new Date());
							reportCard.setClassCode(classCode);
							reportCard.setSectionCode(sectionCode);
							reportCard.setAdmissionNo(admissionNo);
							if(rollNo != null && !rollNo.isEmpty())
								reportCard.setRollNo(Integer.parseInt(rollNo));
							reportCard.setSubjectCode(subjectCode);
							reportCard.setTermId(term.getKey());
							if(term.getValue() != null && !term.getValue().isEmpty())
								reportCard.setMarks(Long.parseLong(term.getValue()));
							
							objectList.add(reportCard);
						}
						i++;
					}
				}
			}
		}
		return objectList;
	}
	

	@SuppressWarnings("unchecked")
	public void preparedReportCardFieldsWithReportCardData(Map<String, Map<String, Object>> fieldsData, List<StudentReportCardModel> reportCardData) {
		if(fieldsData != null && !fieldsData.isEmpty() && reportCardData != null && !reportCardData.isEmpty()) {
			for(Map.Entry<String, Map<String, Object>> entry : fieldsData.entrySet()) {
				String subjectCode = entry.getKey();
				Map<String, Object> objectMap = entry.getValue();
				
				String termKey = subjectCode + StaticValue.REPORT_CARD_TERM;
				Map<String, String> termList = (Map<String, String>) objectMap.get(termKey);
				if(termList != null && !termList.isEmpty()) {
					int i = 1;
					for(Map.Entry<String, String> term : termList.entrySet()) {
						String unitTestKey = subjectCode + StaticValue.REPORT_CARD_TERM + i;
						Map<String, String> unitTestList = (Map<String, String>) objectMap.get(unitTestKey);
						if(unitTestList != null && !unitTestList.isEmpty()) {
							for(Map.Entry<String, String> unit : unitTestList.entrySet()) {
								if(unit.getKey() != null && !unit.getKey().isEmpty()) {
									reportCardData.stream().filter(e -> 
									(e.getSubjectCode().equals(subjectCode) && e.getTermId().equals(unitTestKey) && e.getUnitTestId() != null && !e.getUnitTestId().isEmpty() && unit.getKey().equals(e.getUnitTestId())))
									.collect(Collectors.toList()).forEach(e -> unitTestList.put(e.getUnitTestId(), e.getMarks() != null ? String.valueOf(e.getMarks()) : ""));
								}
							}
							
						}
						if(term.getKey() != null && !term.getKey().isEmpty()) {
							reportCardData.stream().filter(e -> 
							(e.getSubjectCode().equals(subjectCode) && e.getTermId().equals(unitTestKey) && (e.getUnitTestId() == null || e.getUnitTestId().isEmpty())))
							.collect(Collectors.toList()).forEach(e -> termList.put(e.getTermId(), e.getMarks() != null ? String.valueOf(e.getMarks()) : ""));
						}
						i++;
					}
				}
			}
		}
	}
	
	
	public Map<String, Object> preparedReportCardHeaderAndColumn(Map<String, Map<String, Object>> fieldsData, ReportCardConfigModel termConfig, ReportCardConfigModel unitTest) {
		Map<String, Object> reportCardData = new HashMap<String, Object>();
		List<String> headers = new ArrayList<String>();
		List<List<String>> rowsData = new ArrayList<List<String>>();
		List<String> row = null;
		headers.add("Subjects");
		String overallText = "Overall";
		String termHeaderWithMarks = termConfig.getConfigValue().split("=")[1];
		String termHeader[] = termHeaderWithMarks.split(",");
		String unitHeaderWithMarks = unitTest.getConfigValue().split("=")[1];
		String unitHeader[] = unitHeaderWithMarks.split(",");
		if(fieldsData != null && !fieldsData.isEmpty()) {
			boolean isCompleteFldDataIt = false;
			for(Map.Entry<String, Map<String, Object>> entry : fieldsData.entrySet()) {
				int overallTotal = 0;
				String subjectCode = entry.getKey();
				row = new ArrayList<String>();
				Map<String, Object> objectMap = entry.getValue();
				ClassSubjectJsonModel subject = (ClassSubjectJsonModel) objectMap.get(subjectCode);
				row.add(subject.getSubjectName());
				
				String termKey = subjectCode + StaticValue.REPORT_CARD_TERM;
				Map<String, String> termList = (Map<String, String>) objectMap.get(termKey);
				if(termList != null && !termList.isEmpty()) {
					int i = 1;
					for(Map.Entry<String, String> term : termList.entrySet()) {
						int total = 0;
						String unitTestKey = subjectCode + StaticValue.REPORT_CARD_TERM + i;
						Map<String, String> unitTestList = (Map<String, String>) objectMap.get(unitTestKey);
						if(unitTestList != null && !unitTestList.isEmpty()) {
							int j = 1;
							for(Map.Entry<String, String> unit : unitTestList.entrySet()) {
								if(unit.getValue() != null && !unit.getValue().isEmpty()) {
									row.add(unit.getValue());
									total += Integer.parseInt(unit.getValue());
								} else {
									row.add("0");
								}
								if(!isCompleteFldDataIt) {
									String[] unitWithMark = unitHeader[j-1].split("~");
									headers.add(unitWithMark[0].trim() + i + " (" + unitWithMark[1].trim() + ")");
								}
								j++;
							}
							
						}
						if(term.getValue() != null && !term.getValue().isEmpty()) {
							row.add(term.getValue());
							total += Integer.parseInt(term.getValue());
						} else {
							row.add("0");
						}
						
						row.add(String.valueOf(total));
						overallTotal += total;
						if(!isCompleteFldDataIt) {
							String[] termWithMark = termHeader[i-1].split("~");
							headers.add(termWithMark[0].trim() + i + " (" + termWithMark[1].trim() + ")");
							headers.add("Total" + i);
							if(i == 1)
								overallText += " Term" + i;
							else
								overallText += " + Term" + i;
						}
						i++;
					}
				}
				isCompleteFldDataIt = true;
				row.add(String.format("%.2f", (float)overallTotal/(float)termList.size()));
				rowsData.add(row);
			}
		}
		headers.add("Overall");
		reportCardData.put("headers", headers);
		reportCardData.put("rowsData", rowsData);
		return reportCardData;
	}
	
	
	
	public String generateStudentReportCard(String type, StudentDetailsModel studentDetails, Map<String, Object> reportCardData)
	{
		StudentReportCardReportModel receiptFeeReport = new StudentReportCardReportModel(); 
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
		
		return prepareStudentReportCard(type, receiptFeeReport, reportCardData);
	}
	
	@SuppressWarnings("deprecation")
	public String prepareStudentReportCard(String type, StudentReportCardReportModel receiptFeeReport, Map<String, Object> reportCardData)
	{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String realpath = request.getRealPath("/");
		List<StudentReportCardReportModel> jasperDataList = new ArrayList<StudentReportCardReportModel>();
		jasperDataList.add(receiptFeeReport);
		InputStream inputStream = null;
		String fileName = "REPORT_CARD.pdf";
		String reportPath = CommonConstaint.REPORT_GENERATED_PATH + File.separator + SessionManagerDataHelper.getSchoolCode() + File.separator + SessionManagerDataHelper.getUsername();
		BusinessLogicHelper.createDirectory(reportPath);
		reportPath = realpath + reportPath + File.separator + fileName;
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(jasperDataList);
		 
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String uploadPath = realpath + "upload\\";
		String imagePath = realpath + "images\\";
		String realpathReport = realpath + "reports\\";
		try {
			inputStream = new FileInputStream (realpathReport + File.separator + LookUpFile.StudentReportCardReport + ".jrxml");
			parameters.put("SUBREPORT_DIR", realpathReport);
			parameters.put("IMAGE_PATH", imagePath);
			parameters.put("UPLOAD_PATH", uploadPath);
			parameters.put("REAL_PATH", realpath);
			 
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			
			StudentReportCardDynamicReportBuilder reportBuilder = new StudentReportCardDynamicReportBuilder(jasperDesign, reportCardData);
			int dynamicColumnsWidth = reportBuilder.addDynamicHeaders();
			reportBuilder.addDynamicColumns();
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
			int pageWidth = 595;
			
			if(dynamicColumnsWidth < pageWidth)
				dynamicColumnsWidth = pageWidth;
			jasperPrint.setPageWidth(dynamicColumnsWidth);
			GenerateJasperReport.generateJasperReport(jasperPrint, "pdf", reportPath);
		} catch (FileNotFoundException | JRException e) {
			e.printStackTrace();
			LOG.error("ERROR: prepareStudentReportCard() in StudentReportCardBusiness" + e);
		}
		if(type != null && "D".equals(type))
			DownloadFile.getFile(reportPath);
		return reportPath;
	}
}
