package com.school.common.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.UserAttendanceDao;
import com.school.common.facade.FacadeDaoManager;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.model.UserAttendanceDetailsModel;
import com.school.common.services.business.UserAttendanceBusiness;
import com.school.json.model.AttendanceCalendarJsonModel;
import com.school.json.response.AttendanceBarChartJsonResponse;
import com.school.json.response.AttendanceCalendarJsonResponse;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserAttendanceServices 
{
	@Autowired
	private UserAttendanceDao dao;
	
	@Autowired
	private UserAttendanceBusiness attendanceBusiness;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private FacadeDaoManager facadeDaoManager;
	

	
	
//	################################################################## USER Attendance ##########################################################
	public ModelAndView resultUserAttendanceDetails(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("staff/resultUserAttendanceDetails");
		model.addObject("date", request.getParameter("date"));
		return model;
	}

//	fetch Student Attendance Details 
	public AttendanceCalendarJsonResponse fetchUserAttendanceDetails(HttpServletRequest request)
	{
		AttendanceCalendarJsonResponse attendanceCalendarJsonResponse = new AttendanceCalendarJsonResponse();
		String currentSession = SessionManagerDataHelper.getSchoolCurrentSession();
		long userId = SessionManagerDataHelper.getUserProfileUtil().getUserId();
		String date = request.getParameter("date");
		String ddStr = null;
		String mmStr = null;
		String yyStr = null;
		String yyDigit = null;
		int dd = 0;
		int mm = 0;
		int yy = 0;
		
		if(date != null && !date.equalsIgnoreCase(""))
		{
			ddStr = date.split("-")[0];
			mmStr = date.split("-")[1];
			yyStr = date.split("-")[2];
			dd = Integer.parseInt(ddStr);
			mm = Integer.parseInt(mmStr);
			yy = Integer.parseInt(yyStr);
		}
		
		String nextMonth = request.getParameter("nextMonth");
		if(nextMonth != null && !nextMonth.equals(""))
		{
			if(nextMonth.equalsIgnoreCase("Y"))
			{
				mm++;
				mmStr = mm+"";
				if(mm < 10)
				{
					mmStr = "0" + mm;
				} else if(mm > 12){
					mm = 1;
					yy++;
					mmStr = "0" + mm;
					yyStr = yy+"";
				}
			}
			else
			{
				mm--;
				mmStr = mm+"";
				if(mm < 1){
					mm = 12;
					yy--;
					mmStr = mm+"";
					yyStr = yy+"";
				} else if(mm < 10) {
					mmStr = "0" + mm;
				}
			}
		}
		yyStr = yyStr.trim();
		attendanceCalendarJsonResponse.setDate(ddStr);
		attendanceCalendarJsonResponse.setMonth(mmStr);
		attendanceCalendarJsonResponse.setYear(yyStr);
		attendanceCalendarJsonResponse.setAttendanceCalendarJsonModels(new ArrayList<AttendanceCalendarJsonModel>());
		
		yyDigit = yyStr.substring(yyStr.length()-2, yyStr.length());
		if(!currentSession.contains(yyDigit)){
			attendanceCalendarJsonResponse.setHasError("true");
			attendanceCalendarJsonResponse.setErrorMsg("Sorry, You can't access before or after school session.");
			return attendanceCalendarJsonResponse;
		}
		
		UserAttendanceDetailsModel attendanceDetailsList = dao.fetchUserAttendanceDetails(userId, yyStr, mm);
		if(attendanceDetailsList != null)
			attendanceCalendarJsonResponse = attendanceBusiness.fetchUserAttendanceDetails(request, attendanceCalendarJsonResponse, attendanceDetailsList);
		return attendanceCalendarJsonResponse;
	}
	
	
	
//	fetch Student Attendance Details Yearly
	public AttendanceBarChartJsonResponse fetchUserAttendanceYearly(HttpServletRequest request)
	{
		String currentSession = SessionManagerDataHelper.getSchoolCurrentSession();
		long userId = SessionManagerDataHelper.getUserProfileUtil().getUserId();
		
		List<UserAttendanceDetailsModel> list = dao.fetchUserAttendanceYearly(currentSession, userId);
		AttendanceBarChartJsonResponse AttendanceBarChartJsonResponse= attendanceBusiness.fetchUserAttendanceYearly(request, list);
		return AttendanceBarChartJsonResponse;
	}
}
