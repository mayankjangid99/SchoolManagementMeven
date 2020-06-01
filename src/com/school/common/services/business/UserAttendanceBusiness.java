package com.school.common.services.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.school.common.model.UserAttendanceDetailsModel;
import com.school.json.model.AttendanceBarChartJsonModel;
import com.school.json.model.AttendanceCalendarJsonModel;
import com.school.json.response.AttendanceBarChartJsonResponse;
import com.school.json.response.AttendanceCalendarJsonResponse;

@Service
public class UserAttendanceBusiness 
{

	private static Logger LOG = LoggerFactory.getLogger(UserAttendanceBusiness.class);

	
	
//	fetch Student Attendance Details Monthly
	public AttendanceCalendarJsonResponse fetchUserAttendanceDetails(HttpServletRequest request, AttendanceCalendarJsonResponse attendanceCalendarJsonResponse, UserAttendanceDetailsModel attendanceDetails)
	{
		List<AttendanceCalendarJsonModel> attendanceCalendarJsonModelsList = new ArrayList<AttendanceCalendarJsonModel>();
		AttendanceCalendarJsonModel attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
		String yyStr = attendanceCalendarJsonResponse.getYear();
		String mmStr = attendanceCalendarJsonResponse.getMonth();
		/*
		String date = request.getParameter("date");
		String ddStr = null;
		String mmStr = null;
		String yyStr = null;
		String yyDigit = null;
		int dd = 0;
		int mm = 0;
		int yy = 0;
		log.info("DATE: " + date);
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
		
		attendanceCalendarJsonResponse.setYear(yyStr);
		attendanceCalendarJsonResponse.setMonth(mmStr);
		attendanceCalendarJsonResponse.setDate(ddStr);
		*/
			
			if(attendanceDetails.getD1() != null && attendanceDetails.getD1().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-01");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD1() != null && attendanceDetails.getD1().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-01");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD2() != null && attendanceDetails.getD2().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-02");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD2() != null && attendanceDetails.getD2().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-02");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD3() != null && attendanceDetails.getD3().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-03");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD3() != null && attendanceDetails.getD3().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-03");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD4() != null && attendanceDetails.getD4().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-04");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD4() != null && attendanceDetails.getD4().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-04");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD5() != null && attendanceDetails.getD5().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-05");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD5() != null && attendanceDetails.getD5().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-05");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD6() != null && attendanceDetails.getD6().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-06");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD6() != null && attendanceDetails.getD6().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-06");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD7() != null && attendanceDetails.getD7().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-07");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD7() != null && attendanceDetails.getD7().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-07");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD8() != null && attendanceDetails.getD8().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-08");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD8() != null && attendanceDetails.getD8().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-08");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD9() != null && attendanceDetails.getD9().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-09");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD9() != null && attendanceDetails.getD9().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-09");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD10() != null && attendanceDetails.getD10().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-10");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD10() != null && attendanceDetails.getD10().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-10");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD11() != null && attendanceDetails.getD11().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-11");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD11() != null && attendanceDetails.getD11().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-11");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD12() != null && attendanceDetails.getD12().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-12");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD12() != null && attendanceDetails.getD12().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-12");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD13() != null && attendanceDetails.getD13().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-13");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD13() != null && attendanceDetails.getD13().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-13");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD14() != null && attendanceDetails.getD14().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-14");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD14() != null && attendanceDetails.getD14().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-14");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD15() != null && attendanceDetails.getD15().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-15");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD15() != null && attendanceDetails.getD15().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-15");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD16() != null && attendanceDetails.getD16().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-16");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD16() != null && attendanceDetails.getD16().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-16");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD17() != null && attendanceDetails.getD17().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-17");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD17() != null && attendanceDetails.getD17().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-17");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD18() != null && attendanceDetails.getD18().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-18");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD18() != null && attendanceDetails.getD18().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-18");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD19() != null && attendanceDetails.getD19().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-19");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD19() != null && attendanceDetails.getD19().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-19");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD20() != null && attendanceDetails.getD20().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-20");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD20() != null && attendanceDetails.getD20().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-20");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD21() != null && attendanceDetails.getD21().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-21");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD21() != null && attendanceDetails.getD21().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-21");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD22() != null && attendanceDetails.getD22().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-22");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD22() != null && attendanceDetails.getD22().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-22");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD23() != null && attendanceDetails.getD23().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-23");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD23() != null && attendanceDetails.getD23().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-23");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD24() != null && attendanceDetails.getD24().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-24");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD24() != null && attendanceDetails.getD24().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-24");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD25() != null && attendanceDetails.getD25().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-25");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD25() != null && attendanceDetails.getD25().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-25");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD26() != null && attendanceDetails.getD26().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-26");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD26() != null && attendanceDetails.getD26().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-26");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD27() != null && attendanceDetails.getD27().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-27");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD27() != null && attendanceDetails.getD27().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-27");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD28() != null && attendanceDetails.getD28().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-28");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD28() != null && attendanceDetails.getD28().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-28");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD29() != null && attendanceDetails.getD29().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-29");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD29() != null && attendanceDetails.getD29().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-29");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD30() != null && attendanceDetails.getD30().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-30");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD30() != null && attendanceDetails.getD30().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-30");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			
			if(attendanceDetails.getD31() != null && attendanceDetails.getD31().equalsIgnoreCase("P"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Present");
				attendanceCalendarJsonModel.setValue("P"); 
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-31");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
			if(attendanceDetails.getD31() != null && attendanceDetails.getD31().equalsIgnoreCase("A"))
			{
				attendanceCalendarJsonModel = new AttendanceCalendarJsonModel();
				attendanceCalendarJsonModel.setTitle("1. Absent");
				attendanceCalendarJsonModel.setValue("A");
				attendanceCalendarJsonModel.setStart(yyStr+"-"+mmStr+"-31");
				attendanceCalendarJsonModel.setColor("#E33D3D");
				attendanceCalendarJsonModelsList.add(attendanceCalendarJsonModel);
			}
					
		

		attendanceCalendarJsonResponse.setAttendanceCalendarJsonModels(attendanceCalendarJsonModelsList);
		return attendanceCalendarJsonResponse;
	}
	
	
	
	
	
	
	
	
//	fetch Student Attendance Details Yearly
	public AttendanceBarChartJsonResponse fetchUserAttendanceYearly(HttpServletRequest request, List<UserAttendanceDetailsModel> attendanceDetailsList)
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
		
		Iterator<UserAttendanceDetailsModel> AttendanceIterator = attendanceDetailsList.iterator();
		while (AttendanceIterator.hasNext()) {
			UserAttendanceDetailsModel attendanceDetailsModel = (UserAttendanceDetailsModel) AttendanceIterator.next();

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
	
	
	
}
