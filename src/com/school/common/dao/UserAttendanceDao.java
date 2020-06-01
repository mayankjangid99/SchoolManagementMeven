package com.school.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.model.UserAttendanceDetailsModel;

@Repository
public class UserAttendanceDao 
{

	private static Logger LOG = LoggerFactory.getLogger(StudentDetailsDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DatabaseDataHelper dataHelper;
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	private Session getOpenSession()
	{
		return sessionFactory.openSession();
	}
	
	public boolean saveUpdateUserAttendance(UserAttendanceDetailsModel userAttendanceDetails, String attendance, int mm, int dd)
	{
		int i = getCurrentSession().createQuery("UPDATE UserAttendanceDetailsModel SET d" + dd + "=:att WHERE months=:mo AND user.userId=:uid")
				.setParameter("att", attendance).setParameter("mo", mm).setParameter("uid", userAttendanceDetails.getUser().getUserId()).executeUpdate();
		if(i > 1)
			return true;
		else
			return false;
	}
	
	
	
	

	
//	fetch Student Attendance Details 
	public UserAttendanceDetailsModel fetchUserAttendanceDetails(long userId, String yy, int mm)
	{
		Query query = getCurrentSession().createQuery("FROM UserAttendanceDetailsModel WHERE year=:yy AND months=:mo AND user.userId=:uid");
			
		UserAttendanceDetailsModel userAttendanceDetails = (UserAttendanceDetailsModel) query.setParameter("yy", yy).setParameter("mo", mm).setParameter("uid", userId).uniqueResult();
		return userAttendanceDetails;
	}
	

	
	
//	fetch Student Attendance Details Yearly
	@SuppressWarnings("unchecked")
	public List<UserAttendanceDetailsModel> fetchUserAttendanceYearly(String currentSession, long userId)
	{
		Query query = getCurrentSession().createQuery("FROM UserAttendanceDetailsModel WHERE user.userId=:uid");
			
		List<UserAttendanceDetailsModel> list = query.setParameter("uid", userId).list();
		return list;
	}

	
	
	
}
