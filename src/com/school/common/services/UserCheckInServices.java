package com.school.common.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.school.common.dao.UserCheckInDao;
import com.school.common.facade.FacadeDaoManager;
import com.school.common.generic.AppHttpSessionListener;
import com.school.common.generic.SessionManagerDataHelper;
import com.school.common.generic.UserProfileUtils;
import com.school.common.model.SessionDetailsModel;
import com.school.common.model.UserAttendanceDetailsModel;
import com.school.common.model.UserCheckInModel;
import com.school.common.model.UserModel;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserCheckInServices 
{
	@Autowired
	private UserCheckInDao userCheckInDao;
	
	@Autowired
	private FacadeDaoManager facadeDaoManager;
	
	@SuppressWarnings("unchecked")
	public JSONObject checkInUser(HttpServletRequest request)
	{
		String status = request.getParameter("status");
		Long userId = SessionManagerDataHelper.getUserProfileUtil().getUserId();
		String schoolSessionCode = SessionManagerDataHelper.getSchoolCurrentSession();
		Date date = new Date();
		UserCheckInModel userCheckIn = new UserCheckInModel();
		userCheckIn.setCheckIn(date);
		userCheckIn.setDate(date);
		SessionDetailsModel sessionDetails = new SessionDetailsModel();
		sessionDetails.setSessionCode(schoolSessionCode);
		userCheckIn.setSessionDetails(sessionDetails);
		
		UserModel user = new UserModel();
		user.setUserId(userId);
		userCheckIn.setUser(user);

		UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil();
		JSONObject jsonObject = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
		
		UserCheckInModel userAttendanceModel = userCheckInDao.checkCheckInUser(userCheckIn);
		if(userAttendanceModel != null && "checkOut".equalsIgnoreCase(status))
		{
			userCheckIn.setCheckOut(date);
			userCheckIn.setStatus("O");
			jsonObject.put("checkIn", dateFormat.format(userAttendanceModel.getCheckIn()));
			jsonObject.put("checkOut", dateFormat.format(userCheckIn.getCheckOut()));
			userProfileUtils.setCheckIn(dateFormat.format(userAttendanceModel.getCheckIn()));
			userProfileUtils.setCheckOut(dateFormat.format(userCheckIn.getCheckOut()));
			userCheckInDao.updateCheckInUser(userCheckIn);
		}
		else if(userAttendanceModel == null && "checkIn".equalsIgnoreCase(status))
		{
			userCheckIn.setStatus("I");
			jsonObject.put("checkIn", dateFormat.format(userCheckIn.getCheckIn()));
			userProfileUtils.setCheckIn(dateFormat.format(userCheckIn.getCheckIn()));
			userCheckInDao.saveCheckInUser(userCheckIn);
			Calendar now = Calendar.getInstance();
		    // 
		    //System.out.println("Current Year is : " + now.get(Calendar.YEAR));
		    // month start from 0 to 11
		    int mm = (now.get(Calendar.MONTH) + 1);
		    int dd = now.get(Calendar.DATE);
		    UserAttendanceDetailsModel userAttendanceDetails = new UserAttendanceDetailsModel();
		    userAttendanceDetails.setUser(user);
		    facadeDaoManager.getUserAttendanceDetailsDao().saveUpdateUserAttendance(userAttendanceDetails, "P", mm, dd);
		}
		else if(userAttendanceModel != null)
		{
			userCheckIn.setStatus("I");
			userProfileUtils.setCheckIn(dateFormat.format(userAttendanceModel.getCheckIn()));
			jsonObject.put("checkIn", dateFormat.format(userAttendanceModel.getCheckIn()));
			userCheckInDao.updateCheckInStatus(userCheckIn);
		}
		
		if("checkIn".equalsIgnoreCase(status))
		{
			jsonObject.put("status", "checkOut");
			userProfileUtils.setCheckInStatus("checkOut");
			SessionManagerDataHelper.setUserProfileUtils(userProfileUtils);
			return jsonObject;
		}
		else
		{
			jsonObject.put("status", "checkIn");
			userProfileUtils.setCheckInStatus("checkIn");
			SessionManagerDataHelper.setUserProfileUtils(userProfileUtils);
			return jsonObject;
		}
	}
	
	
	 public void checkOut(){
	        System.out.println("checkOut in Services");

			Date date = new Date();
			UserCheckInModel userCheckIn = new UserCheckInModel();
			userCheckIn.setCheckIn(date);
			userCheckIn.setDate(date);
			
		    HttpSession httpSession = AppHttpSessionListener.getSession();
			
		    if(httpSession != null)
		    {
				SessionDetailsModel sessionDetails = new SessionDetailsModel();
				sessionDetails.setSessionCode(SessionManagerDataHelper.getSchoolCurrentSession(httpSession));
				userCheckIn.setSessionDetails(sessionDetails);
				
				UserModel user = new UserModel();
				user.setUserId(SessionManagerDataHelper.getUserProfileUtil(httpSession).getUserId());
				userCheckIn.setUser(user);
		        userCheckIn.setCheckOut(date);
				userCheckIn.setStatus("O");
	
				UserCheckInModel userAttendanceModel = userCheckInDao.checkCheckInUser(userCheckIn);
				if(userAttendanceModel != null)
				{
					userCheckInDao.updateCheckInUser(userCheckIn);
		
					SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
					UserProfileUtils userProfileUtils = SessionManagerDataHelper.getUserProfileUtil(httpSession);
					userProfileUtils.setCheckIn(dateFormat.format(userAttendanceModel.getCheckIn()));
					userProfileUtils.setCheckOut(dateFormat.format(userCheckIn.getCheckOut()));
					SessionManagerDataHelper.setUserProfileUtils(httpSession, userProfileUtils);
				}
		    }
	 	}
	
	
	
	
	
}
