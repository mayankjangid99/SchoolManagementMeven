package com.school.common.services.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.school.common.generic.EmailUtil;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.StaticValue;
import com.school.common.model.AdmissionNumberModel;
import com.school.common.model.ClassInformationModel;
import com.school.common.model.SectionInformationModel;
import com.school.common.model.StudentAttendanceDetailsModel;
import com.school.common.model.StudentAttendanceEmailDetailsModel;
import com.school.common.model.StudentAttendanceSmsDetailsModel;
import com.school.common.model.StudentDetailsModel;
import com.school.common.reports.AttendanceExcelManager;
import com.school.json.model.AttendanceBarChartJsonModel;
import com.school.json.model.AttendanceCalendarJsonModel;
import com.school.json.response.AttendanceBarChartJsonResponse;
import com.school.json.response.AttendanceCalendarJsonResponse;

@Service
public class StudentAttendanceBusiness 
{

	private static Logger LOG = LoggerFactory.getLogger(StudentAttendanceBusiness.class);
	
	public void saveOrUpdateMarkAttendance(String[] admissionNos, String[] attendances, String[] smss,  String[] emails, String[] fatherEmail, String[] fatherMobile)
	{
		String recipientsEmailForAbsent = null;
		String recipientsSmsForAbsent = null;
		String recipientsEmailForPresent = null;
		String recipientsSmsForPresent = null;

		int arrayIndexSmsAbsent = 0;
		int arrayIndexEmailAbsent = 0;
		int arrayIndexSmsPresent = 0;
		int arrayIndexEmailPresent = 0;
		for(int i = 0; i < admissionNos.length; i++)
		{
			if(attendances[i].equalsIgnoreCase("A") && !"".equalsIgnoreCase(attendances[i])
					&& emails[i].equalsIgnoreCase("Y") && !"".equalsIgnoreCase(emails[i]))
			{
				if(arrayIndexEmailAbsent == 0 && (fatherEmail[i] != null || "".equalsIgnoreCase(fatherEmail[i])) )
					recipientsEmailForAbsent = fatherEmail[i];
				else if(arrayIndexEmailAbsent > 0 && (fatherEmail[i] != null || "".equalsIgnoreCase(fatherEmail[i])) )
					recipientsEmailForAbsent = recipientsEmailForAbsent + ";" + fatherEmail[i];
				arrayIndexEmailAbsent++;
			}
			if(attendances[i].equalsIgnoreCase("A") && !"".equalsIgnoreCase(attendances[i]) 
					&& smss[i].equalsIgnoreCase("Y") && !"".equalsIgnoreCase(smss[i]) )
			{
				if(arrayIndexSmsAbsent == 0 && (fatherMobile[i] != null || "".equalsIgnoreCase(fatherMobile[i])))
					recipientsSmsForAbsent = fatherMobile[i];
				else if(arrayIndexSmsAbsent > 0  && (fatherMobile[i] != null || "".equalsIgnoreCase(fatherMobile[i])) )
					recipientsSmsForAbsent = recipientsSmsForAbsent + ";" + fatherMobile[i];
				arrayIndexSmsAbsent++;
			}
			
			if(attendances[i].equalsIgnoreCase("P") && !"".equalsIgnoreCase(attendances[i])
					&& emails[i].equalsIgnoreCase("Y") && !"".equalsIgnoreCase(emails[i]))
			{
				if(arrayIndexEmailPresent == 0 && (fatherEmail[i] != null || "".equalsIgnoreCase(fatherEmail[i])) )
					recipientsEmailForPresent = fatherEmail[i];
				else if(arrayIndexEmailPresent > 0 && (fatherEmail[i] != null || "".equalsIgnoreCase(fatherEmail[i])) )
					recipientsEmailForPresent = recipientsEmailForPresent + ";" + fatherEmail[i];
				arrayIndexEmailPresent++;
			}
			if(attendances[i].equalsIgnoreCase("P") && !"".equalsIgnoreCase(attendances[i]) 
					&& smss[i].equalsIgnoreCase("Y") && !"".equalsIgnoreCase(smss[i]) )
			{
				if(arrayIndexSmsPresent == 0 && (fatherMobile[i] != null || "".equalsIgnoreCase(fatherMobile[i])))
					recipientsSmsForPresent = fatherMobile[i];
				else if(arrayIndexSmsPresent > 0  && (fatherMobile[i] != null || "".equalsIgnoreCase(fatherMobile[i])) )
					recipientsSmsForPresent = recipientsSmsForPresent + ";" + fatherMobile[i];
				arrayIndexSmsPresent++;
			}
		}

		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		String emailStudentAttendance = request.getParameter("emailStudentAttendance");
		if(emailStudentAttendance != null && !"".equals(emailStudentAttendance) && "Y".equals(emailStudentAttendance)){
			request.setAttribute("recipientsEmailForAbsent", recipientsEmailForAbsent);
			request.setAttribute("recipientsEmailForPresent", recipientsEmailForPresent);
		}
		
		String smsStudentAttendance = request.getParameter("smsStudentAttendance");
		if(smsStudentAttendance != null && !"".equals(smsStudentAttendance) && "Y".equals(smsStudentAttendance)){
			request.setAttribute("recipientsSmsForAbsent", recipientsSmsForAbsent);
			request.setAttribute("recipientsSmsForPresent", recipientsSmsForPresent);	
		}
		
	}
	
	
	
	
//	get All Student Details 
	public ArrayList<Map<String, String>> getAttendanceDetails(List<StudentAttendanceDetailsModel> attendanceList, List<StudentAttendanceSmsDetailsModel> smsList, List<StudentAttendanceEmailDetailsModel> emailList, int dd)
	{
		ArrayList<Map<String, String>> studentList = new ArrayList<Map<String, String>>();
		Map<String, String> studentMap = null;
		String admissionNo = "";
		
		for(int i = 0; i < attendanceList.size(); i++)
		{
			studentMap = new HashMap<String, String>();
				StudentAttendanceDetailsModel attendanceDetails = attendanceList.get(i);
				
				if(!"".equals(attendanceDetails.getAdmissionDetails().getAdmissionNumber().getAdmissionNo()))
				{
					admissionNo = attendanceDetails.getAdmissionDetails().getAdmissionNumber().getAdmissionNo();
					
					studentMap.put("rollNo", attendanceDetails.getAdmissionDetails().getRollNo().toString());
					studentMap.put("admissionNo", attendanceDetails.getAdmissionDetails().getAdmissionNumber().getAdmissionNo());
					studentMap.put("image", attendanceDetails.getAdmissionDetails().getStudentDetails().getImage());
					studentMap.put("sName", attendanceDetails.getAdmissionDetails().getStudentDetails().getFullname());
					studentMap.put("fName", attendanceDetails.getAdmissionDetails().getStudentDetails().getParentDetails().getFatherName());
					studentMap.put("fEmail", attendanceDetails.getAdmissionDetails().getStudentDetails().getParentDetails().getFatherEmail());
					studentMap.put("fMobile", attendanceDetails.getAdmissionDetails().getStudentDetails().getParentDetails().getFatherMobile());
					studentMap.put("mEmail", attendanceDetails.getAdmissionDetails().getStudentDetails().getParentDetails().getMotherEmail());
					studentMap.put("mMobile", attendanceDetails.getAdmissionDetails().getStudentDetails().getParentDetails().getMotherMobile());
					
					
				}
				else
				{
					continue;
				}
				
					
				if(dd == 1)
					studentMap.put("attendance", attendanceDetails.getD1());
				if(dd == 2)
					studentMap.put("attendance", attendanceDetails.getD2());
				if(dd == 3)
					studentMap.put("attendance", attendanceDetails.getD3());
				if(dd == 4)
					studentMap.put("attendance", attendanceDetails.getD4());
				if(dd == 5)
					studentMap.put("attendance", attendanceDetails.getD5());
				if(dd == 6)
					studentMap.put("attendance", attendanceDetails.getD6());
				if(dd == 7)
					studentMap.put("attendance", attendanceDetails.getD7());
				if(dd == 8)
					studentMap.put("attendance", attendanceDetails.getD8());
				if(dd == 9)
					studentMap.put("attendance", attendanceDetails.getD9());
				if(dd == 10)
					studentMap.put("attendance", attendanceDetails.getD10());
				if(dd == 11)
					studentMap.put("attendance", attendanceDetails.getD11());
				if(dd == 12)
					studentMap.put("attendance", attendanceDetails.getD12());
				if(dd == 13)
					studentMap.put("attendance", attendanceDetails.getD13());
				if(dd == 14)
					studentMap.put("attendance", attendanceDetails.getD14());
				if(dd == 15)
					studentMap.put("attendance", attendanceDetails.getD15());
				if(dd == 16)
					studentMap.put("attendance", attendanceDetails.getD16());
				if(dd == 17)
					studentMap.put("attendance", attendanceDetails.getD17());
				if(dd == 18)
					studentMap.put("attendance", attendanceDetails.getD18());
				if(dd == 19)
					studentMap.put("attendance", attendanceDetails.getD19());
				if(dd == 20)
					studentMap.put("attendance", attendanceDetails.getD20());
				if(dd == 21)
					studentMap.put("attendance", attendanceDetails.getD21());
				if(dd == 22)
					studentMap.put("attendance", attendanceDetails.getD22());
				if(dd == 23)
					studentMap.put("attendance", attendanceDetails.getD23());
				if(dd == 24)
					studentMap.put("attendance", attendanceDetails.getD24());
				if(dd == 25)
					studentMap.put("attendance", attendanceDetails.getD25());
				if(dd == 26)
					studentMap.put("attendance", attendanceDetails.getD26());
				if(dd == 27)
					studentMap.put("attendance", attendanceDetails.getD27());
				if(dd == 28)
					studentMap.put("attendance", attendanceDetails.getD28());
				if(dd == 29)
					studentMap.put("attendance", attendanceDetails.getD29());
				if(dd == 30)
					studentMap.put("attendance", attendanceDetails.getD30());
				if(dd == 31)
					studentMap.put("attendance", attendanceDetails.getD31());
			
			
			Iterator<StudentAttendanceSmsDetailsModel> iteratorSms = smsList.iterator();
			while (iteratorSms.hasNext()) {
				StudentAttendanceSmsDetailsModel attendanceSmsDetails = (StudentAttendanceSmsDetailsModel) iteratorSms.next();
				
				if(!admissionNo.equals(attendanceSmsDetails.getAdmissionDetails().getAdmissionNumber().getAdmissionNo()))
				{
					continue;
				}
				
				
				if(dd == 1)
				{
					studentMap.put("sms", attendanceSmsDetails.getD1());
					break;
				}
				if(dd == 2)
				{
					studentMap.put("sms", attendanceSmsDetails.getD2());
					break;
				}
				if(dd == 3)
				{
					studentMap.put("sms", attendanceSmsDetails.getD3());
					break;
				}
				if(dd == 4)
				{
					studentMap.put("sms", attendanceSmsDetails.getD4());
					break;
				}
				if(dd == 5)
				{
					studentMap.put("sms", attendanceSmsDetails.getD5());
					break;
				}
				if(dd == 6)
				{
					studentMap.put("sms", attendanceSmsDetails.getD6());
					break;
				}
				if(dd == 7)
				{
					studentMap.put("sms", attendanceSmsDetails.getD7());
					break;
				}
				if(dd == 8)
				{
					studentMap.put("sms", attendanceSmsDetails.getD8());
					break;
				}
				if(dd == 9)
				{
					studentMap.put("sms", attendanceSmsDetails.getD9());
					break;
				}
				if(dd == 10)
				{
					studentMap.put("sms", attendanceSmsDetails.getD10());
					break;
				}
				if(dd == 11)
				{
					studentMap.put("sms", attendanceSmsDetails.getD11());
					break;
				}
				if(dd == 12)
				{
					studentMap.put("sms", attendanceSmsDetails.getD12());
					break;
				}
				if(dd == 13)
				{
					studentMap.put("sms", attendanceSmsDetails.getD13());
					break;
				}
				if(dd == 14)
				{
					studentMap.put("sms", attendanceSmsDetails.getD14());
					break;
				}
				if(dd == 15)
				{
					studentMap.put("sms", attendanceSmsDetails.getD15());
					break;
				}
				if(dd == 16)
				{
					studentMap.put("sms", attendanceSmsDetails.getD16());
					break;
				}
				if(dd == 17)
				{
					studentMap.put("sms", attendanceSmsDetails.getD17());
					break;
				}
				if(dd == 18)
				{
					studentMap.put("sms", attendanceSmsDetails.getD18());
					break;
				}
				if(dd == 19)
				{
					studentMap.put("sms", attendanceSmsDetails.getD19());
					break;
				}
				if(dd == 20)
				{
					studentMap.put("sms", attendanceSmsDetails.getD20());
					break;
				}
				if(dd == 21)
				{
					studentMap.put("sms", attendanceSmsDetails.getD21());
					break;
				}
				if(dd == 22)
				{
					studentMap.put("sms", attendanceSmsDetails.getD22());
					break;
				}
				if(dd == 23)
				{
					studentMap.put("sms", attendanceSmsDetails.getD23());
					break;
				}
				if(dd == 24)
				{
					studentMap.put("sms", attendanceSmsDetails.getD24());
					break;
				}
				if(dd == 25)
				{
					studentMap.put("sms", attendanceSmsDetails.getD25());
					break;
				}
				if(dd == 26)
				{
					studentMap.put("sms", attendanceSmsDetails.getD26());
					break;
				}
				if(dd == 27)
				{
					studentMap.put("sms", attendanceSmsDetails.getD27());
					break;
				}
				if(dd == 28)
				{
					studentMap.put("sms", attendanceSmsDetails.getD28());
					break;
				}
				if(dd == 29)
				{
					studentMap.put("sms", attendanceSmsDetails.getD29());
					break;
				}
				if(dd == 30)
				{
					studentMap.put("sms", attendanceSmsDetails.getD30());
					break;
				}
				if(dd == 31)
				{
					studentMap.put("sms", attendanceSmsDetails.getD31());
					break;
				}
			}
			
			
			
			Iterator<StudentAttendanceEmailDetailsModel> iteratorEmail = emailList.iterator();
			while (iteratorEmail.hasNext()) {
				StudentAttendanceEmailDetailsModel attendanceEmailDetails = (StudentAttendanceEmailDetailsModel) iteratorEmail.next();
				if(!admissionNo.equals(attendanceEmailDetails.getAdmissionDetails().getAdmissionNumber().getAdmissionNo()))
				{
					continue;
				}
				
				
				if(dd == 1)
				{
					studentMap.put("email", attendanceEmailDetails.getD1());
					break;
				}
				if(dd == 2)
				{
					studentMap.put("email", attendanceEmailDetails.getD2());
					break;
				}
				if(dd == 3)
				{
					studentMap.put("email", attendanceEmailDetails.getD3());
					break;
				}
				if(dd == 4)
				{
					studentMap.put("email", attendanceEmailDetails.getD4());
					break;
				}
				if(dd == 5)
				{
					studentMap.put("email", attendanceEmailDetails.getD5());
					break;
				}
				if(dd == 6)
				{
					studentMap.put("email", attendanceEmailDetails.getD6());
					break;
				}
				if(dd == 7)
				{
					studentMap.put("email", attendanceEmailDetails.getD7());
					break;
				}
				if(dd == 8)
				{
					studentMap.put("email", attendanceEmailDetails.getD8());
					break;
				}
				if(dd == 9)
				{
					studentMap.put("email", attendanceEmailDetails.getD9());
					break;
				}
				if(dd == 10)
				{
					studentMap.put("email", attendanceEmailDetails.getD10());
					break;
				}
				if(dd == 11)
				{
					studentMap.put("email", attendanceEmailDetails.getD11());
					break;
				}
				if(dd == 12)
				{
					studentMap.put("email", attendanceEmailDetails.getD12());
					break;
				}
				if(dd == 13)
				{
					studentMap.put("email", attendanceEmailDetails.getD13());
					break;
				}
				if(dd == 14)
				{
					studentMap.put("email", attendanceEmailDetails.getD14());
					break;
				}
				if(dd == 15)
				{
					studentMap.put("email", attendanceEmailDetails.getD15());
					break;
				}
				if(dd == 16)
				{
					studentMap.put("email", attendanceEmailDetails.getD16());
					break;
				}
				if(dd == 17)
				{
					studentMap.put("email", attendanceEmailDetails.getD17());
					break;
				}
				if(dd == 18)
				{
					studentMap.put("email", attendanceEmailDetails.getD18());
					break;
				}
				if(dd == 19)
				{
					studentMap.put("email", attendanceEmailDetails.getD19());
					break;
				}
				if(dd == 20)
				{
					studentMap.put("email", attendanceEmailDetails.getD20());
					break;
				}
				if(dd == 21)
				{
					studentMap.put("email", attendanceEmailDetails.getD21());
					break;
				}
				if(dd == 22)
				{
					studentMap.put("email", attendanceEmailDetails.getD22());
					break;
				}
				if(dd == 23)
				{
					studentMap.put("email", attendanceEmailDetails.getD23());
					break;
				}
				if(dd == 24)
				{
					studentMap.put("email", attendanceEmailDetails.getD24());
					break;
				}
				if(dd == 25)
				{
					studentMap.put("email", attendanceEmailDetails.getD25());
					break;
				}
				if(dd == 26)
				{
					studentMap.put("email", attendanceEmailDetails.getD26());
					break;
				}
				if(dd == 27)
				{
					studentMap.put("email", attendanceEmailDetails.getD27());
					break;
				}
				if(dd == 28)
				{
					studentMap.put("email", attendanceEmailDetails.getD28());
					break;
				}
				if(dd == 29)
				{
					studentMap.put("email", attendanceEmailDetails.getD29());
					break;
				}
				if(dd == 30)
				{
					studentMap.put("email", attendanceEmailDetails.getD30());
					break;
				}
				if(dd == 31)
				{
					studentMap.put("email", attendanceEmailDetails.getD31());
					break;
				}
			}
			
			studentList.add(studentMap);
		}
		return studentList;
	}
	
	
	
	
	
	
	
		
//	fetch Student Attendance Details Monthly
	public AttendanceCalendarJsonResponse fetchStudentAttendanceAllDetails(HttpServletRequest request, AttendanceCalendarJsonResponse attendanceCalendarJsonResponse, List<StudentAttendanceDetailsModel> attendanceDetailsList,
			List<StudentAttendanceSmsDetailsModel> attendanceSmsDetailsList, List<StudentAttendanceEmailDetailsModel> attendanceEmailDetailsList)
	{
		List<AttendanceCalendarJsonModel> attendanceCalendarJsonModelsList = new ArrayList<AttendanceCalendarJsonModel>();
		AttendanceCalendarJsonModel attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
		String yyStr = attendanceCalendarJsonResponse.getYear();
		String mmStr = attendanceCalendarJsonResponse.getMonth();
		
		/*String date = request.getParameter("date");
		String ddStr = null;
		String mmStr = null;
		String yyStr = null;
		
		if(date != null && !date.equalsIgnoreCase(""))
		{
			ddStr = date.split("-")[0];
			mmStr = date.split("-")[1];
			yyStr = date.split("-")[2];
		}
		
		String nextMonth = request.getParameter("nextMonth");
		if(nextMonth != null && !nextMonth.equals(""))
		{
			if(nextMonth.equalsIgnoreCase("Y"))
			{
				int mm = Integer.parseInt(mmStr);
				mm++;
				if(mm < 10)
				{
					mmStr = "0"+mm;
				}
			}
			else
			{
				int mm = Integer.parseInt(mmStr);
				mm--;
				if(mm < 10)
				{
					mmStr = "0"+mm;
				}
			}
		}
		
		attendanceCalendarJsonResponse.setYear(yyStr);
		attendanceCalendarJsonResponse.setMonth(mmStr);
		attendanceCalendarJsonResponse.setDate(ddStr);*/
		
		Iterator<StudentAttendanceDetailsModel> AttendanceIterator = attendanceDetailsList.iterator();
		while (AttendanceIterator.hasNext()) 
		{
			StudentAttendanceDetailsModel attendanceDetailsModel = (StudentAttendanceDetailsModel) AttendanceIterator.next();
			
			if(attendanceDetailsModel.getD1() != null && attendanceDetailsModel.getD1().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-01");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD1() != null && attendanceDetailsModel.getD1().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-01");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD2() != null && attendanceDetailsModel.getD2().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-02");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD2() != null && attendanceDetailsModel.getD2().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-02");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD3() != null && attendanceDetailsModel.getD3().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-03");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD3() != null && attendanceDetailsModel.getD3().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-03");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD4() != null && attendanceDetailsModel.getD4().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-04");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD4() != null && attendanceDetailsModel.getD4().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-04");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD5() != null && attendanceDetailsModel.getD5().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-05");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD5() != null && attendanceDetailsModel.getD5().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-05");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD6() != null && attendanceDetailsModel.getD6().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-06");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD6() != null && attendanceDetailsModel.getD6().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-06");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD7() != null && attendanceDetailsModel.getD7().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-07");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD7() != null && attendanceDetailsModel.getD7().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-07");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD8() != null && attendanceDetailsModel.getD8().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-08");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD8() != null && attendanceDetailsModel.getD8().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-08");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD9() != null && attendanceDetailsModel.getD9().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-09");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD9() != null && attendanceDetailsModel.getD9().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-09");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD10() != null && attendanceDetailsModel.getD10().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-10");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD10() != null && attendanceDetailsModel.getD10().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-10");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD11() != null && attendanceDetailsModel.getD11().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-11");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD11() != null && attendanceDetailsModel.getD11().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-11");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD12() != null && attendanceDetailsModel.getD12().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-12");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD12() != null && attendanceDetailsModel.getD12().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-12");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD13() != null && attendanceDetailsModel.getD13().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-13");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD13() != null && attendanceDetailsModel.getD13().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-13");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD14() != null && attendanceDetailsModel.getD14().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-14");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD14() != null && attendanceDetailsModel.getD14().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-14");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD15() != null && attendanceDetailsModel.getD15().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-15");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD15() != null && attendanceDetailsModel.getD15().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-15");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD16() != null && attendanceDetailsModel.getD16().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-16");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD16() != null && attendanceDetailsModel.getD16().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-16");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD17() != null && attendanceDetailsModel.getD17().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-17");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD17() != null && attendanceDetailsModel.getD17().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-17");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD18() != null && attendanceDetailsModel.getD18().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-18");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD18() != null && attendanceDetailsModel.getD18().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-18");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD19() != null && attendanceDetailsModel.getD19().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-19");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD19() != null && attendanceDetailsModel.getD19().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-19");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD20() != null && attendanceDetailsModel.getD20().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-20");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD20() != null && attendanceDetailsModel.getD20().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-20");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD21() != null && attendanceDetailsModel.getD21().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-21");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD21() != null && attendanceDetailsModel.getD21().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-21");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD22() != null && attendanceDetailsModel.getD22().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-22");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD22() != null && attendanceDetailsModel.getD22().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-22");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD23() != null && attendanceDetailsModel.getD23().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-23");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD23() != null && attendanceDetailsModel.getD23().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-23");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD24() != null && attendanceDetailsModel.getD24().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-24");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD24() != null && attendanceDetailsModel.getD24().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-24");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD25() != null && attendanceDetailsModel.getD25().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-25");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD25() != null && attendanceDetailsModel.getD25().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-25");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD26() != null && attendanceDetailsModel.getD26().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-26");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD26() != null && attendanceDetailsModel.getD26().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-26");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD27() != null && attendanceDetailsModel.getD27().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-27");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD27() != null && attendanceDetailsModel.getD27().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-27");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD28() != null && attendanceDetailsModel.getD28().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-28");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD28() != null && attendanceDetailsModel.getD28().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-28");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD29() != null && attendanceDetailsModel.getD29().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-29");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD29() != null && attendanceDetailsModel.getD29().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-29");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD30() != null && attendanceDetailsModel.getD30().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-30");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD30() != null && attendanceDetailsModel.getD30().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-30");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetailsModel.getD31() != null && attendanceDetailsModel.getD31().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-31");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceDetailsModel.getD31() != null && attendanceDetailsModel.getD31().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-31");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
						
		}
		
		
		Iterator<StudentAttendanceSmsDetailsModel> smsIterator = attendanceSmsDetailsList.iterator();
		while (smsIterator.hasNext()) {
			StudentAttendanceSmsDetailsModel attendanceSmsDetailsModel = (StudentAttendanceSmsDetailsModel) smsIterator.next();
			
			if(attendanceSmsDetailsModel.getD1() != null && attendanceSmsDetailsModel.getD1().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-01");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD1() != null && attendanceSmsDetailsModel.getD1().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-01");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD2() != null && attendanceSmsDetailsModel.getD2().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-02");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD2() != null && attendanceSmsDetailsModel.getD2().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-02");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD3() != null && attendanceSmsDetailsModel.getD3().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-03");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD3() != null && attendanceSmsDetailsModel.getD3().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-03");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD4() != null && attendanceSmsDetailsModel.getD4().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-04");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD4() != null && attendanceSmsDetailsModel.getD4().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-04");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD5() != null && attendanceSmsDetailsModel.getD5().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-05");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD5() != null && attendanceSmsDetailsModel.getD5().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-05");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD6() != null && attendanceSmsDetailsModel.getD6().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-06");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD6() != null && attendanceSmsDetailsModel.getD6().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-06");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceSmsDetailsModel.getD7() != null && attendanceSmsDetailsModel.getD7().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-07");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD7() != null && attendanceSmsDetailsModel.getD7().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-07");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD8() != null && attendanceSmsDetailsModel.getD8().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-08");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD8() != null && attendanceSmsDetailsModel.getD8().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-08");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD9() != null && attendanceSmsDetailsModel.getD9().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-09");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD9() != null && attendanceSmsDetailsModel.getD9().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-09");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceSmsDetailsModel.getD10() != null && attendanceSmsDetailsModel.getD10().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-10");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD10() != null && attendanceSmsDetailsModel.getD10().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-10");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD11() != null && attendanceSmsDetailsModel.getD11().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-11");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD11() != null && attendanceSmsDetailsModel.getD11().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-11");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD12() != null && attendanceSmsDetailsModel.getD12().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-12");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD12() != null && attendanceSmsDetailsModel.getD12().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-12");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceSmsDetailsModel.getD13() != null && attendanceSmsDetailsModel.getD13().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-13");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD13() != null && attendanceSmsDetailsModel.getD13().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-13");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD14() != null && attendanceSmsDetailsModel.getD14().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-14");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD14() != null && attendanceSmsDetailsModel.getD14().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-14");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD15() != null && attendanceSmsDetailsModel.getD15().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-15");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD15() != null && attendanceSmsDetailsModel.getD15().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-15");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceSmsDetailsModel.getD16() != null && attendanceSmsDetailsModel.getD16().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-16");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD16() != null && attendanceSmsDetailsModel.getD16().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-16");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD17() != null && attendanceSmsDetailsModel.getD17().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-17");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD17() != null && attendanceSmsDetailsModel.getD17().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-17");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD18() != null && attendanceSmsDetailsModel.getD18().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-18");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD18() != null && attendanceSmsDetailsModel.getD18().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-18");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceSmsDetailsModel.getD19() != null && attendanceSmsDetailsModel.getD19().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-19");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD19() != null && attendanceSmsDetailsModel.getD19().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-19");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD20() != null && attendanceSmsDetailsModel.getD20().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-20");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD20() != null && attendanceSmsDetailsModel.getD20().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-20");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD21() != null && attendanceSmsDetailsModel.getD21().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-21");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD21() != null && attendanceSmsDetailsModel.getD21().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-21");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceSmsDetailsModel.getD22() != null && attendanceSmsDetailsModel.getD22().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-22");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD22() != null && attendanceSmsDetailsModel.getD22().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-22");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD23() != null && attendanceSmsDetailsModel.getD23().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-23");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD23() != null && attendanceSmsDetailsModel.getD23().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-23");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD24() != null && attendanceSmsDetailsModel.getD24().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-24");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD24() != null && attendanceSmsDetailsModel.getD24().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-24");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceSmsDetailsModel.getD25() != null && attendanceSmsDetailsModel.getD25().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-25");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD25() != null && attendanceSmsDetailsModel.getD25().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-25");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD26() != null && attendanceSmsDetailsModel.getD26().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-26");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD26() != null && attendanceSmsDetailsModel.getD26().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-26");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD27() != null && attendanceSmsDetailsModel.getD27().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-27");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD27() != null && attendanceSmsDetailsModel.getD27().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-27");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceSmsDetailsModel.getD28() != null && attendanceSmsDetailsModel.getD28().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-28");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD28() != null && attendanceSmsDetailsModel.getD28().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-28");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD29() != null && attendanceSmsDetailsModel.getD29().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-29");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD29() != null && attendanceSmsDetailsModel.getD29().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-29");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceSmsDetailsModel.getD30() != null && attendanceSmsDetailsModel.getD30().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-30");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD30() != null && attendanceSmsDetailsModel.getD30().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-30");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceSmsDetailsModel.getD31() != null && attendanceSmsDetailsModel.getD31().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Sent SMS");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-31");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceSmsDetailsModel.getD31() != null && attendanceSmsDetailsModel.getD31().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("2. Not Sent SMS");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-31");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
		}
		
		
		Iterator<StudentAttendanceEmailDetailsModel> emailIterator = attendanceEmailDetailsList.iterator();
		while (emailIterator.hasNext()) {
			StudentAttendanceEmailDetailsModel attendanceEmailDetailsModel = (StudentAttendanceEmailDetailsModel) emailIterator.next();
			
			if(attendanceEmailDetailsModel.getD1() != null && attendanceEmailDetailsModel.getD1().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-01");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD1() != null && attendanceEmailDetailsModel.getD1().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-01");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD2() != null && attendanceEmailDetailsModel.getD2().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-02");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD2() != null && attendanceEmailDetailsModel.getD2().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-02");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD3() != null && attendanceEmailDetailsModel.getD3().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-03");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD3() != null && attendanceEmailDetailsModel.getD3().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-03");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD4() != null && attendanceEmailDetailsModel.getD4().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-04");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD4() != null && attendanceEmailDetailsModel.getD4().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-04");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD5() != null && attendanceEmailDetailsModel.getD5().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-05");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD5() != null && attendanceEmailDetailsModel.getD5().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-05");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD6() != null && attendanceEmailDetailsModel.getD6().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-06");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD6() != null && attendanceEmailDetailsModel.getD6().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-06");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD7() != null && attendanceEmailDetailsModel.getD7().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-07");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD7() != null && attendanceEmailDetailsModel.getD7().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-07");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD8() != null && attendanceEmailDetailsModel.getD8().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-08");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD8() != null && attendanceEmailDetailsModel.getD8().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-08");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD9() != null && attendanceEmailDetailsModel.getD9().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-09");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD9() != null && attendanceEmailDetailsModel.getD9().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-09");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD10() != null && attendanceEmailDetailsModel.getD10().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-10");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD10() != null && attendanceEmailDetailsModel.getD10().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-10");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD11() != null && attendanceEmailDetailsModel.getD11().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-11");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD11() != null && attendanceEmailDetailsModel.getD11().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-11");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD12() != null && attendanceEmailDetailsModel.getD12().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-12");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD12() != null && attendanceEmailDetailsModel.getD12().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-12");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD13() != null && attendanceEmailDetailsModel.getD13().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-13");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD13() != null && attendanceEmailDetailsModel.getD13().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-13");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD14() != null && attendanceEmailDetailsModel.getD14().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-14");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD14() != null && attendanceEmailDetailsModel.getD14().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-14");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD15() != null && attendanceEmailDetailsModel.getD15().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-15");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD15() != null && attendanceEmailDetailsModel.getD15().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-15");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD16() != null && attendanceEmailDetailsModel.getD16().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-16");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD16() != null && attendanceEmailDetailsModel.getD16().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-16");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD17() != null && attendanceEmailDetailsModel.getD17().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-17");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD17() != null && attendanceEmailDetailsModel.getD17().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-17");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD18() != null && attendanceEmailDetailsModel.getD18().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-18");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD18() != null && attendanceEmailDetailsModel.getD18().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-18");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD19() != null && attendanceEmailDetailsModel.getD19().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-19");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD19() != null && attendanceEmailDetailsModel.getD19().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-19");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD20() != null && attendanceEmailDetailsModel.getD20().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-20");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD20() != null && attendanceEmailDetailsModel.getD20().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-20");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD21() != null && attendanceEmailDetailsModel.getD21().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-21");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD21() != null && attendanceEmailDetailsModel.getD21().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-21");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD22() != null && attendanceEmailDetailsModel.getD22().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-22");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD22() != null && attendanceEmailDetailsModel.getD22().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-22");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD23() != null && attendanceEmailDetailsModel.getD23().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-23");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD23() != null && attendanceEmailDetailsModel.getD23().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-23");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD24() != null && attendanceEmailDetailsModel.getD24().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-24");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD24() != null && attendanceEmailDetailsModel.getD24().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-24");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD25() != null && attendanceEmailDetailsModel.getD25().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-25");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD25() != null && attendanceEmailDetailsModel.getD25().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-25");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD26() != null && attendanceEmailDetailsModel.getD26().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-26");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD26() != null && attendanceEmailDetailsModel.getD26().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-26");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD27() != null && attendanceEmailDetailsModel.getD27().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-27");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD27() != null && attendanceEmailDetailsModel.getD27().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-27");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD28() != null && attendanceEmailDetailsModel.getD28().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-28");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD28() != null && attendanceEmailDetailsModel.getD28().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-28");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD29() != null && attendanceEmailDetailsModel.getD29().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-29");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD29() != null && attendanceEmailDetailsModel.getD29().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-29");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD30() != null && attendanceEmailDetailsModel.getD30().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-30");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD30() != null && attendanceEmailDetailsModel.getD30().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-30");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceEmailDetailsModel.getD31() != null && attendanceEmailDetailsModel.getD31().equalsIgnoreCase("Y"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Sent Email");
				attendanceCalendarJsonModel.setValue("Y");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-31");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			else if(attendanceEmailDetailsModel.getD31() != null && attendanceEmailDetailsModel.getD31().equalsIgnoreCase("N"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("3. Not Sent Email");
				attendanceCalendarJsonModel.setValue("N");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-31");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
		}

		attendanceCalendarJsonResponse.setAttendanceCalendarJsonModels(attendanceCalendarJsonModelsList);
		return attendanceCalendarJsonResponse;
	}
	
	
	
	
	
	
	
	
//	fetch Student Attendance Details Yearly
	public AttendanceBarChartJsonResponse fetchStudentAttendanceDetailsYearly(HttpServletRequest request, List<StudentAttendanceDetailsModel> attendanceDetailsList)
	{		
		/*String date = request.getParameter("date");
		String ddStr = null;
		String mmStr = null;
		String yyStr = null;
		int dd = 0;
		int mm = 0;
		
		if(date != null && !date.equalsIgnoreCase(""))
		{
			ddStr = date.split("-")[0];
			mmStr = date.split("-")[1];
			yyStr = date.split("-")[2];
			dd = Integer.parseInt(ddStr);
			mm = Integer.parseInt(mmStr);
		}*/

		AttendanceBarChartJsonResponse attendanceBarChartJsonResponse = new AttendanceBarChartJsonResponse();
		AttendanceBarChartJsonModel attendanceBarChartJsonModel = new AttendanceBarChartJsonModel();
		ArrayList<Integer> presentList = new ArrayList<Integer>();
		ArrayList<Integer> absentList = new ArrayList<Integer>();
		ArrayList<String> monthList = new ArrayList<String>();	
		
		Iterator<StudentAttendanceDetailsModel> AttendanceIterator = attendanceDetailsList.iterator();
		while (AttendanceIterator.hasNext()) {
			StudentAttendanceDetailsModel attendanceDetailsModel = (StudentAttendanceDetailsModel) AttendanceIterator.next();

			int totalPresent = 0;
			int totalAbsent = 0;
			
			if(attendanceDetailsModel.getMonths() == 1)
				monthList.add("Jan");
			if(attendanceDetailsModel.getMonths() == 2)
				monthList.add("Feb");
			if(attendanceDetailsModel.getMonths() == 3)
				monthList.add("Mar");
			if(attendanceDetailsModel.getMonths() == 4)
				monthList.add("Apr");
			if(attendanceDetailsModel.getMonths() == 5)
				monthList.add("May");
			if(attendanceDetailsModel.getMonths() == 6)
				monthList.add("Jun");
			if(attendanceDetailsModel.getMonths() == 7)
				monthList.add("Jul");
			if(attendanceDetailsModel.getMonths() == 8)
				monthList.add("Aug");
			if(attendanceDetailsModel.getMonths() == 9)
				monthList.add("Sep");
			if(attendanceDetailsModel.getMonths() == 10)
				monthList.add("Oct");
			if(attendanceDetailsModel.getMonths() == 11)
				monthList.add("Nov");
			if(attendanceDetailsModel.getMonths() == 12)
				monthList.add("Dec");
			
			if(attendanceDetailsModel.getD1() != null && attendanceDetailsModel.getD1().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD1() != null && attendanceDetailsModel.getD1().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD2() != null && attendanceDetailsModel.getD2().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD2() != null && attendanceDetailsModel.getD2().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD3() != null && attendanceDetailsModel.getD3().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD3() != null && attendanceDetailsModel.getD3().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD4() != null && attendanceDetailsModel.getD4().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD4() != null && attendanceDetailsModel.getD4().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD5() != null && attendanceDetailsModel.getD5().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD5() != null && attendanceDetailsModel.getD5().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD6() != null && attendanceDetailsModel.getD6().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD6() != null && attendanceDetailsModel.getD6().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			if(attendanceDetailsModel.getD7() != null && attendanceDetailsModel.getD7().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD7() != null && attendanceDetailsModel.getD7().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD8() != null && attendanceDetailsModel.getD8().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD8() != null && attendanceDetailsModel.getD8().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD9() != null && attendanceDetailsModel.getD9().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD9() != null && attendanceDetailsModel.getD9().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			if(attendanceDetailsModel.getD10() != null && attendanceDetailsModel.getD10().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD10() != null && attendanceDetailsModel.getD10().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD11() != null && attendanceDetailsModel.getD11().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD11() != null && attendanceDetailsModel.getD11().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD12() != null && attendanceDetailsModel.getD12().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD12() != null && attendanceDetailsModel.getD12().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			if(attendanceDetailsModel.getD13() != null && attendanceDetailsModel.getD13().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD13() != null && attendanceDetailsModel.getD13().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD14() != null && attendanceDetailsModel.getD14().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD14() != null && attendanceDetailsModel.getD14().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD15() != null && attendanceDetailsModel.getD15().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD15() != null && attendanceDetailsModel.getD15().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD16() != null && attendanceDetailsModel.getD16().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD16() != null && attendanceDetailsModel.getD16().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD17() != null && attendanceDetailsModel.getD17().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD17() != null && attendanceDetailsModel.getD17().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD18() != null && attendanceDetailsModel.getD18().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD18() != null && attendanceDetailsModel.getD18().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD19() != null && attendanceDetailsModel.getD19().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD19() != null && attendanceDetailsModel.getD19().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			if(attendanceDetailsModel.getD20() != null && attendanceDetailsModel.getD20().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD20() != null && attendanceDetailsModel.getD20().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD21() != null && attendanceDetailsModel.getD21().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD21() != null && attendanceDetailsModel.getD21().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD22() != null && attendanceDetailsModel.getD22().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD22() != null && attendanceDetailsModel.getD22().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			if(attendanceDetailsModel.getD23() != null && attendanceDetailsModel.getD23().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD23() != null && attendanceDetailsModel.getD23().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD24() != null && attendanceDetailsModel.getD24().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD24() != null && attendanceDetailsModel.getD24().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD25() != null && attendanceDetailsModel.getD25().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD25() != null && attendanceDetailsModel.getD25().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			if(attendanceDetailsModel.getD26() != null && attendanceDetailsModel.getD26().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD26() != null && attendanceDetailsModel.getD26().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD27() != null && attendanceDetailsModel.getD27().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD27() != null && attendanceDetailsModel.getD27().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD28() != null && attendanceDetailsModel.getD28().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD28() != null && attendanceDetailsModel.getD28().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			if(attendanceDetailsModel.getD29() != null && attendanceDetailsModel.getD29().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD29() != null && attendanceDetailsModel.getD29().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD30() != null && attendanceDetailsModel.getD30().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD30() != null && attendanceDetailsModel.getD30().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
			
			if(attendanceDetailsModel.getD31() != null && attendanceDetailsModel.getD31().equalsIgnoreCase("P"))
			{
				totalPresent++;
			}
			else if(attendanceDetailsModel.getD31() != null && attendanceDetailsModel.getD31().equalsIgnoreCase("A"))
			{
				totalAbsent++;
			}
						
			
			/*if(attendanceDetailsModel.getMonths() == mm)
			{
				attendanceBarChartJsonModel.setTotalPresent(totalPresent);
				attendanceBarChartJsonModel.setTotalAbsent(totalAbsent);
			}*/
			
			presentList.add(totalPresent);
			absentList.add(totalAbsent);
			
		}
		attendanceBarChartJsonModel.setMonths(monthList);
		attendanceBarChartJsonModel.setPresentList(presentList);
		attendanceBarChartJsonModel.setAbsentList(absentList);
		attendanceBarChartJsonResponse.setAttendanceBarChartJsonModel(attendanceBarChartJsonModel);
		return attendanceBarChartJsonResponse;
	}
	
	
	
	public void downloadBulkMarkAttendance(HttpServletRequest request, HttpServletResponse response, List<StudentAttendanceDetailsModel> attendanceList, ClassInformationModel classInformation, 
			SectionInformationModel sectionInformation, String date, String status)
	{
		LOG.info("Attendance Excel report Downloading...");
		
		if("A".equalsIgnoreCase(status))
			status = "All";
		else if("Y".equalsIgnoreCase(status))
			status = "Active";
		else if("N".equalsIgnoreCase(status))
			status = "Inactive";

		String fileType = request.getParameter("fileType");
		// 1. Create new workbook
//		HSSFWorkbook workbook = new HSSFWorkbook();
		Workbook workbook = null;
		// 2. Create new worksheet
//		HSSFSheet worksheet = workbook.createSheet("POI Worksheet");
		if(!"".equals(fileType) && "xls".equals(fileType))
		{
			workbook = new HSSFWorkbook();
		}
		else if(!"".equals(fileType) && "xlsx".equals(fileType))
		{
			workbook = new XSSFWorkbook();
		}

		Sheet worksheet = workbook.createSheet("Attendance Sheet");
		
		// 3. Define starting indices for rows and columns
		int startRowIndex = 0;
		int startColIndex = 0;
		
		// 4. Build layout 
		// Build title, date, and column headers
		AttendanceExcelManager.buildReport(worksheet, startRowIndex, startColIndex, classInformation, sectionInformation, date, status);

		// 5. Fill report
		AttendanceExcelManager.fillReport(worksheet, startRowIndex, startColIndex, attendanceList);
		
		// 6. Set the response properties
		String fileName = classInformation.getClassCode() + "-" + sectionInformation.getSectionCode() + "." + fileType;
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		// Make sure to set the correct content type
		response.setContentType("application/vnd.ms-excel");
		
		//7. Write to the output stream
		AttendanceExcelManager.write(response, worksheet);
	}
	
	
	
	private Map<String, Object> uploadBulkMarkAttendance(CommonsMultipartFile bulkFile)
	{
		try {
			String fileName = bulkFile.getOriginalFilename();
			int headerIndex = 2;
			//Create the input stream from the xlsx/xls file
			//FileInputStream fis = (FileInputStream)bulkFile.getInputStream();
			 //Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
			    workbook = new XSSFWorkbook(bulkFile.getInputStream());
			}else if(fileName.toLowerCase().endsWith("xls")){
			    workbook = new HSSFWorkbook(bulkFile.getInputStream());
			}
			Map<String, Object> excelData = AttendanceExcelManager.readExcelData(workbook, headerIndex);
			//List<String> queryList = prepareQueryForBulkAttendance(excelData);
			return excelData;
		} catch (IOException e) {
			LOG.error("ERROR: uploadBulkMarkAttendance() in AttendanceBusiness.java " + e);
			//e.printStackTrace();
		}
		return null;
	}
	
	
	public Map<String, Object> getPrepareQueryForBulkAttendance(CommonsMultipartFile bulkFile)
	{
		Map<String, Object> preparedData = new HashMap<String, Object>();
		
		Map<String, Object> excelData = uploadBulkMarkAttendance(bulkFile);
		
		String currentSession = (String) excelData.get("currentSession");
		String classInfo = (String) excelData.get("classInfo");
		String[] classInfoArray = classInfo.split("-");
		String sectionInfo = (String) excelData.get("sectionInfo");
		String[] sectionInfoArray = sectionInfo.split("-");
		String status = (String) excelData.get("status");
		String date = (String) excelData.get("date");
		int dd = 0;
		int mm = 0;
		List<String> queryList = new ArrayList<String>();
		
		if(!date.equals(""))
         {
         	String[] dateArray = date.split("-");
         	dd = Integer.parseInt(dateArray[0].trim());
         	mm = Integer.parseInt(dateArray[1].trim());
         }
		if(status.equalsIgnoreCase("Active"))
			status = "Y";
		else if(status.equalsIgnoreCase("Inactive"))
			status = "N";
		
		String outerAttQuery = "UPDATE StudentAttendanceDetailsModel SET d"+dd+"='attc' WHERE months="+mm+" AND admission_details_id IN ";
		String changeOuterAttQuery = outerAttQuery;
		String outerSmsQuery = "UPDATE StudentAttendanceSmsDetailsModel SET d"+dd+"='csms' WHERE months="+mm+" AND admission_details_id IN ";
		String changeOuterSmsQuery = outerSmsQuery;
		String outerEmailQuery = "UPDATE StudentAttendanceEmailDetailsModel SET d"+dd+"='eml' WHERE months="+mm+" AND admission_details_id IN ";
		String changeOuterEmailQuery = outerEmailQuery;

		int arrayIndexSmsAbs = 0;
		int arrayIndexEmailAbs = 0;
		StringBuffer admissionNoListSmsAbs = new StringBuffer("");
		StringBuffer admissionNoListEmailAbs = new StringBuffer("");
		Collection<String> admissionNoListSmsColAbs = new ArrayList<String>();
		Collection<String> admissionNoListEmailColAbs = new ArrayList<String>();

		int arrayIndexSmsPrsnt = 0;
		int arrayIndexEmailPrsnt = 0;
		StringBuffer admissionNoListSmsPrsnt = new StringBuffer("");
		StringBuffer admissionNoListEmailPrsnt = new StringBuffer("");
		Collection<String> admissionNoListSmsColPrsnt = new ArrayList<String>();
		Collection<String> admissionNoListEmailColPrsnt = new ArrayList<String>();
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> studentDataList = (List<Map<String, Object>>) excelData.get("studentDataList");
		for(Map<String, Object> studentData : studentDataList)
		{
			changeOuterAttQuery = outerAttQuery.replace("attc", (String)studentData.get("attendance"));
			changeOuterSmsQuery = outerSmsQuery.replace("csms", (String)studentData.get("sms"));
			changeOuterEmailQuery = outerEmailQuery.replace("eml", (String)studentData.get("email"));
			AdmissionNumberModel admissionNumberModel = SessionManagerDataHelper.getAdmissionNumber(((String)studentData.get("admissionNo")));
			String innerQuery = "FROM AdmissionDetailsModel WHERE  classInformation.classCode='"+classInfoArray[1].trim()+"' AND sectionInformation.sectionCode='"+sectionInfoArray[1].trim()+"' AND status='"+status+"' AND sessionDetails.sessionCode='"+currentSession+"' AND admissionNumber.admissionNumberId='"+admissionNumberModel.getAdmissionNumberId()+"' AND rollNo="+(int)studentData.get("rollNo");
			queryList.add(changeOuterAttQuery+"("+innerQuery+")");
			queryList.add(changeOuterSmsQuery+"("+innerQuery+")");
			queryList.add(changeOuterEmailQuery+"("+innerQuery+")");
			//System.out.println(changeOuterAttQuery+"("+innerQuery+")");
			
//			Absent student send email and SMS to parent
			if(((String)studentData.get("attendance")).equalsIgnoreCase("A") && !"".equalsIgnoreCase((String)studentData.get("attendance"))
					&& ((String)studentData.get("sms")).equalsIgnoreCase("Y") && !"".equalsIgnoreCase((String)studentData.get("sms")))
			{
				if(arrayIndexSmsAbs == 0)
					admissionNoListSmsAbs.append("'" + ((String)studentData.get("admissionNo")).trim() + "'");
				else
					admissionNoListSmsAbs.append(",'" + ((String)studentData.get("admissionNo")).trim() + "'");
				arrayIndexSmsAbs++;
				admissionNoListSmsColAbs.add( ((String)studentData.get("admissionNo")).trim() );				
			}
			if(((String)studentData.get("attendance")).equalsIgnoreCase("A") && !"".equalsIgnoreCase((String)studentData.get("attendance"))
					&& ((String)studentData.get("email")).equalsIgnoreCase("Y") && !"".equalsIgnoreCase((String)studentData.get("email")))
			{
				if(arrayIndexEmailAbs == 0)
					admissionNoListEmailAbs.append("'" + ((String)studentData.get("admissionNo")).trim() + "'");
				else
					admissionNoListEmailAbs.append(",'" + ((String)studentData.get("admissionNo")).trim() + "'");
				arrayIndexEmailAbs++;	
				admissionNoListEmailColAbs.add( ((String)studentData.get("admissionNo")).trim() );
			}
			
//			Present student send email and SMS to parent
			if(((String)studentData.get("attendance")).equalsIgnoreCase("P") && !"".equalsIgnoreCase((String)studentData.get("attendance"))
					&& ((String)studentData.get("sms")).equalsIgnoreCase("Y") && !"".equalsIgnoreCase((String)studentData.get("sms")))
			{
				if(arrayIndexSmsPrsnt == 0)
					admissionNoListSmsPrsnt.append("'" + ((String)studentData.get("admissionNo")).trim() + "'");
				else
					admissionNoListSmsPrsnt.append(",'" + ((String)studentData.get("admissionNo")).trim() + "'");
				arrayIndexSmsAbs++;
				admissionNoListSmsColPrsnt.add( ((String)studentData.get("admissionNo")).trim() );				
			}
			if(((String)studentData.get("attendance")).equalsIgnoreCase("P") && !"".equalsIgnoreCase((String)studentData.get("attendance"))
					&& ((String)studentData.get("email")).equalsIgnoreCase("Y") && !"".equalsIgnoreCase((String)studentData.get("email")))
			{
				if(arrayIndexEmailPrsnt == 0)
					admissionNoListEmailPrsnt.append("'" + ((String)studentData.get("admissionNo")).trim() + "'");
				else
					admissionNoListEmailPrsnt.append(",'" + ((String)studentData.get("admissionNo")).trim() + "'");
				arrayIndexEmailAbs++;	
				admissionNoListEmailColPrsnt.add( ((String)studentData.get("admissionNo")).trim() );
			}
		}
		preparedData.put("queryList", queryList);
		preparedData.put("admissionNoListSmsAbs", admissionNoListSmsAbs.toString());
		preparedData.put("admissionNoListEmailAbs", admissionNoListEmailAbs.toString());
		preparedData.put("admissionNoListSmsColAbs", admissionNoListSmsColAbs);
		preparedData.put("admissionNoListEmailColAbs", admissionNoListEmailColAbs);

		preparedData.put("admissionNoListSmsPrsnt", admissionNoListSmsPrsnt.toString());
		preparedData.put("admissionNoListEmailPrsnt", admissionNoListEmailPrsnt.toString());
		preparedData.put("admissionNoListSmsColPrsnt", admissionNoListSmsColPrsnt);
		preparedData.put("admissionNoListEmailColPrsnt", admissionNoListEmailColPrsnt);
		return preparedData;
	}
	
	
	public void sendEmailForBulkAttendance(List<StudentDetailsModel> studentDetailsList)
	{
		StringBuffer recipientsEmail = new StringBuffer("");
		int arrayIndex = 0;
		for(StudentDetailsModel studentDetails : studentDetailsList)
		{
			if(arrayIndex == 0 && (studentDetails.getParentDetails().getFatherEmail() != null || "".equalsIgnoreCase(studentDetails.getParentDetails().getFatherEmail())) )
				recipientsEmail.append(studentDetails.getParentDetails().getFatherEmail());
			else if(arrayIndex > 0 && (studentDetails.getParentDetails().getFatherEmail() != null || "".equalsIgnoreCase(studentDetails.getParentDetails().getFatherEmail())) )
				recipientsEmail.append("," + studentDetails.getParentDetails().getFatherEmail());
			arrayIndex++;
		}
		EmailUtil emailUtil = new EmailUtil();
		emailUtil.sendEmail("", SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), recipientsEmail.toString(), "", "", "Attendance Status", "Your children Absent Today", "");
		System.out.println("AttendanceBusiness.java -> sendEmailForBulkAttendance()"+recipientsEmail);
	}
	
	
	public void sendEmailForBulkAttendanceForPresent(List<StudentDetailsModel> studentDetailsList)
	{
		StringBuffer recipientsEmail = new StringBuffer("");
		int arrayIndex = 0;
		for(StudentDetailsModel studentDetails : studentDetailsList)
		{
			if(arrayIndex == 0 && (studentDetails.getParentDetails().getFatherEmail() != null || "".equalsIgnoreCase(studentDetails.getParentDetails().getFatherEmail())) )
				recipientsEmail.append(studentDetails.getParentDetails().getFatherEmail());
			else if(arrayIndex > 0 && (studentDetails.getParentDetails().getFatherEmail() != null || "".equalsIgnoreCase(studentDetails.getParentDetails().getFatherEmail())) )
				recipientsEmail.append("," + studentDetails.getParentDetails().getFatherEmail());
			arrayIndex++;
		}
		EmailUtil emailUtil = new EmailUtil();
		emailUtil.sendEmail("", SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), recipientsEmail.toString(), "", "", "Attendance Status", "Your children Present Today", "");
		System.out.println("AttendanceBusiness.java -> sendEmailForBulkAttendance()"+recipientsEmail);
	}
	
	/*
	public String[] getAdmissionNoListForBulkAttendance(CommonsMultipartFile bulkFile)
	{
		Map<String, Object> excelData = uploadBulkMarkAttendance(bulkFile);
		String[] smssAndEmails = new String[2];
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> studentDataList = (List<Map<String, Object>>) excelData.get("studentDataList");
		int arrayIndex = 0;
		StringBuffer smss = new StringBuffer("");
		StringBuffer emails = new StringBuffer("");
		for(Map<String, Object> studentData : studentDataList)
		{
			if(((String)studentData.get("attendance")).equalsIgnoreCase("A") && !"".equalsIgnoreCase((String)studentData.get("attendance"))
					&& ((String)studentData.get("sms")).equalsIgnoreCase("Y") && !"".equalsIgnoreCase((String)studentData.get("sms")))
			{
				if(arrayIndex == 0)
					smss.append("'" + ((String)studentData.get("admissionNo")).trim() + "'");
				else
					smss.append(",'" + ((String)studentData.get("admissionNo")).trim() + "'");
					
			}
			if(((String)studentData.get("attendance")).equalsIgnoreCase("A") && !"".equalsIgnoreCase((String)studentData.get("attendance"))
					&& ((String)studentData.get("email")).equalsIgnoreCase("Y") && !"".equalsIgnoreCase((String)studentData.get("email")))
			{
				if(arrayIndex == 0)
					emails.append("'" + ((String)studentData.get("admissionNo")).trim() + "'");
				else
					emails.append(",'" + ((String)studentData.get("admissionNo")).trim() + "'");
					
			}
		}
		smssAndEmails[0] = smss.toString();
		smssAndEmails[1] = emails.toString();
		return smssAndEmails;
	}*/
	
	
	
}
