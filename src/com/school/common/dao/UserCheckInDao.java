package com.school.common.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.model.UserCheckInModel;

@Repository
public class UserCheckInDao 
{

	private static Logger log = LoggerFactory.getLogger(StudentDetailsDao.class);
	
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
	
	
	public UserCheckInModel checkCheckInUser(UserCheckInModel userAttendance)
	{
		Criteria criteria = getCurrentSession().createCriteria(UserCheckInModel.class, "userAttendance");
		criteria.createAlias("userAttendance.sessionDetails", "sessionDetails");
		criteria.createAlias("userAttendance.user", "user");
		criteria.add(Restrictions.eq("date", userAttendance.getDate()));
		criteria.add(Restrictions.eq("user.userId", userAttendance.getUser().getUserId()));
		criteria.add(Restrictions.eq("sessionDetails.sessionCode", userAttendance.getSessionDetails().getSessionCode()));
		UserCheckInModel userAttendanceModel = (UserCheckInModel) criteria.uniqueResult();
		return userAttendanceModel;
	}
	
	
	public void saveCheckInUser(UserCheckInModel userAttendance)
	{
		Session session = getOpenSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(userAttendance);
			transaction.commit();
			session.flush();
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: saveCheckInUser() in UserCheckInDao ", e);
		}
		finally {
			session.close();
		}
	}
	
	public boolean updateCheckInUser(UserCheckInModel userAttendance)
	{
		int i = getCurrentSession().createQuery("UPDATE UserCheckInModel SET checkOut=:co, status=:s WHERE date=:da AND user.userId=:ur")
				.setParameter("co", userAttendance.getCheckOut()).setParameter("s", userAttendance.getStatus())
				.setParameter("da", userAttendance.getDate()).setParameter("ur", userAttendance.getUser().getUserId()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	public boolean updateCheckInStatus(UserCheckInModel userAttendance)
	{
		int i = getCurrentSession().createQuery("UPDATE UserCheckInModel SET status=:s WHERE date=:da AND user.userId=:ur")
				.setParameter("s", userAttendance.getStatus()).setParameter("da", userAttendance.getDate())
				.setParameter("ur", userAttendance.getUser().getUserId()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
}
