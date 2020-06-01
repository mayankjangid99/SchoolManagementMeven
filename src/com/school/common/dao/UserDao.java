package com.school.common.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.generic.dao.DaoManagerBean;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.UserAttendanceDetailsModel;
import com.school.common.model.UserDetailsModel;
import com.school.common.model.UserModel;
import com.school.common.model.UserRoleModel;
import com.school.common.model.UserSettingModel;

@Repository
public class UserDao  extends DaoManagerBean
{
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger LOG = LoggerFactory.getLogger(UserDao.class);
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	private Session openSession() {
		return sessionFactory.openSession();
	}
	
	
	public UserModel loginUser(String username) {
		UserModel user = (UserModel) getCurrentSession().createQuery("FROM UserModel WHERE username=:u")
				.setParameter("u", username).uniqueResult();
		return user;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<UserRoleModel> getAllUserRole() {
		Criteria criteria = getCurrentSession().createCriteria(UserRoleModel.class);
		criteria.addOrder(Order.asc("userRoleName"));
		List<UserRoleModel> list = criteria.list();
		return list;
	}


	public UserModel getUserByUsername(UserModel userModel) {
		Criteria criteria = getCurrentSession().createCriteria(UserModel.class);
		criteria.add(Restrictions.eq("username", userModel.getUsername()));
		UserModel user = (UserModel) criteria.uniqueResult();
		return user;
	}
	
	
	public UserModel getUserByUsernameAndEmail(UserModel userModel) {
		Criteria criteria = getCurrentSession().createCriteria(UserModel.class, "user");
		criteria.createAlias("user.userDetails", "userDetails");
		criteria.add(Restrictions.eq("username", userModel.getUsername()));
		criteria.add(Restrictions.eq("userDetails.email", userModel.getUserDetails().getEmail()));
		UserModel user = (UserModel) criteria.uniqueResult();
		return user;
	}
	
	
	
	public UserModel getUserByUserId(UserModel userModel) {
		Criteria criteria = getCurrentSession().createCriteria(UserModel.class);
		criteria.add(Restrictions.eq("userId", userModel.getUserId()));
		UserModel user = (UserModel) criteria.uniqueResult();
		return user;
	}
	
	
	public boolean updateUserIP(UserModel user) {
		int i = getCurrentSession().createQuery("UPDATE UserModel SET ip=:ip WHERE userId=:uid").setParameter("ip", user.getIp())
				.setParameter("uid", user.getUserId()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	public boolean updateUserLogin(UserModel user) {
		int i = getCurrentSession().createQuery("UPDATE UserModel SET login=:lg WHERE userId=:uid")
				.setParameter("lg", user.isLogin()).setParameter("uid", user.getUserId()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	public boolean updateUserIPAndLogin(UserModel user) {
		int i = getCurrentSession().createQuery("UPDATE UserModel SET ip=:ip, login=:lg WHERE userId=:uid").setParameter("ip", user.getIp())
				.setParameter("lg", user.isLogin()).setParameter("uid", user.getUserId()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	public boolean updateUserDetailsToken(UserDetailsModel userDetails) {
		int i = getCurrentSession().createQuery("UPDATE UserDetailsModel SET token=:tk WHERE userDetailsId=:uid")
				.setParameter("tk", userDetails.getToken()).setParameter("uid", userDetails.getUserDetailsId()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	public boolean updateAllUserLogin()
	{
		int i = getCurrentSession().createQuery("UPDATE UserModel SET login=:lg").setParameter("lg", false).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	
	public void saveUser(UserModel user, String currentYear)
	{
		Session session = openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(user.getUserDetails());
			session.persist(user.getUserDetails().getUserSetting());
			session.persist(user);
			transaction.commit();
			session.flush();
			boolean flag = false;
			if(!"ROLE_SUPADMINN".equalsIgnoreCase(user.getUserRole().getUserRoleId())
					|| !"ROLE_USER".equalsIgnoreCase(user.getUserRole().getUserRoleId())){
				flag = true;
			}
			if(flag) {
				for(int i = 1; i <= 12; i++) {
					transaction = session.beginTransaction();
					UserAttendanceDetailsModel userAttendanceDetails = new UserAttendanceDetailsModel();
					userAttendanceDetails.setMonths(i);
					userAttendanceDetails.setUser(user);
					userAttendanceDetails.setYear(currentYear);
					session.persist(userAttendanceDetails);				
					transaction.commit();
					session.flush();
				}
			}
		} catch (HibernateException e) {
			//e.printStackTrace();
			LOG.error("ERROR: saveUser() in UserDao ", e);
		}
		finally {
			session.close();
		}
	}
	
	
	public boolean updateUser(UserModel user) {
		int i = getCurrentSession().createQuery("UPDATE UserModel SET active=:ac, code=:co, password=:pa, salt=:sa WHERE userId=:ui")
		.setParameter("ac", user.isActive()).setParameter("co", user.getCode())
		.setParameter("pa", user.getPassword()).setParameter("sa", user.getSalt()).setParameter("ui", user.getUserId()).executeUpdate();
		
		int i2 = getCurrentSession().createQuery("UPDATE UserDetailsModel SET firstname=:fi, middlename=:mi, lastname=:la, image=:im, modifiedBy=:mb, modifiedOn=:mo, password=:pa, email=:em, classCode=:cc, sectionCode=:sc WHERE userDetailsId=:udi")
				.setParameter("fi", user.getUserDetails().getFirstname()).setParameter("mi", user.getUserDetails().getMiddlename())
				.setParameter("la", user.getUserDetails().getLastname()).setParameter("im", user.getUserDetails().getImage())
				.setParameter("mb", user.getUserDetails().getModifiedBy()).setParameter("mo", user.getUserDetails().getModifiedOn())
				.setParameter("pa", user.getPassword()).setParameter("em", user.getUserDetails().getEmail()).setParameter("cc", user.getUserDetails().getClassCode())
				.setParameter("sc", user.getUserDetails().getSectionCode()).setParameter("udi", user.getUserDetails().getUserDetailsId()).executeUpdate();
		
		if(i > 0 && i2 > 0)
			return true;
		else
			return false;
	}
	
	
	public boolean updateUserSchoolProfile(SchoolProfileModel schoolProfile, Long userDetailsId) {
		int i = getCurrentSession().createQuery("UPDATE UserDetailsModel SET schoolProfile.schoolCode=:sc WHERE userDetailsId=:udi")
				.setParameter("sc", schoolProfile.getSchoolCode()).setParameter("udi", userDetailsId).executeUpdate();
		
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<UserModel> fetchUser(UserModel user, String pActive) {
		Criteria criteria = openSession().createCriteria(UserModel.class, "user");
		
		criteria.createAlias("user.userRole", "userRole");
		criteria.createAlias("user.userDetails", "userDetails");
		
		if(!"".equals(user.getUserDetails().getSchoolProfile().getSchoolCode()) && user.getUserDetails().getSchoolProfile().getSchoolCode() != null)
		{
			criteria.createAlias("userDetails.schoolProfile", "schoolProfile");
			criteria.add(Restrictions.eq("schoolProfile.schoolCode", user.getUserDetails().getSchoolProfile().getSchoolCode()));
		}
		
		if(user.getUserRole() != null)
			if(!"".equalsIgnoreCase(user.getUserRole().getUserRoleId()) && user.getUserRole().getUserRoleId() != null)
				criteria.add(Restrictions.eq("userRole.userRoleId", user.getUserRole().getUserRoleId()));
		
		if(!"".equalsIgnoreCase(user.getUserDetails().getEmail()) && user.getUserDetails().getEmail() != null)
			criteria.add(Restrictions.like("userDetails.email", "%" + user.getUserDetails().getEmail() + "%"));
		
		if("A".equalsIgnoreCase(pActive))
			criteria.add(Restrictions.eq("active", true));
		else if("I".equalsIgnoreCase(pActive))
			criteria.add(Restrictions.eq("active", false));

		if(!"".equalsIgnoreCase(user.getUsername()) && user.getUsername() != null)
			criteria.add(Restrictions.like("username", "%" + user.getUserDetails().getUsername() + "%"));

		if(!"".equalsIgnoreCase(user.getUserDetails().getFirstname()) && user.getUserDetails().getFirstname() != null)
			criteria.add(Restrictions.like("userDetails.firstname", "%" + user.getUserDetails().getFirstname() + "%"));

		if(!"".equalsIgnoreCase(user.getUserDetails().getMiddlename()) && user.getUserDetails().getMiddlename() != null)
			criteria.add(Restrictions.like("userDetails.middlename", "%" + user.getUserDetails().getMiddlename() + "%"));

		if(!"".equalsIgnoreCase(user.getUserDetails().getLastname()) && user.getUserDetails().getLastname() != null)
			criteria.add(Restrictions.like("userDetails.lastname", "%" + user.getUserDetails().getLastname() + "%"));
		
		if(user.getUserDetails().getParentDetails() != null && user.getUserDetails().getParentDetails().getParentDetailsId() != null && user.getUserDetails().getParentDetails().getParentDetailsId() != 0)
			criteria.add(Restrictions.eq("userDetails.parentDetails.parentDetailsId", user.getUserDetails().getParentDetails().getParentDetailsId()));
		
		List<UserModel> list = criteria.list();
		return list;		
	}
	
	
	
	public boolean changeUserActive(UserModel user)
	{
		int  i = getCurrentSession().createQuery("UPDATE UserModel SET active=:a WHERE userId=:u")
				.setParameter("a", user.isActive()).setParameter("u", user.getUserId()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	public boolean updatePassword(UserModel user)
	{
		int  i = getCurrentSession().createQuery("UPDATE UserModel SET password=:p WHERE username=:u")
				.setParameter("p", user.getPassword()).setParameter("u", user.getUsername()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	public boolean updatePasswordInUserDetails(UserModel user)
	{
		int  i = getCurrentSession().createQuery("UPDATE UserDetailsModel SET password=:p, pwd=:pw WHERE username=:u")
				.setParameter("p", user.getUserDetails().getPassword()).setParameter("pw", user.getUserDetails().getPwd()).setParameter("u", user.getUsername()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	public boolean changeUserTheme(UserSettingModel userSetting){
		int  i = getCurrentSession().createQuery("UPDATE UserSettingModel SET theme=:t WHERE username=:u")
				.setParameter("t", userSetting.getTheme()).setParameter("u", userSetting.getUsername()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	
	public UserDetailsModel getUserDetailsByParentDetailsId(Long parentDetailsId)
	{
		Criteria criteria = getCurrentSession().createCriteria(UserDetailsModel.class);
		criteria.add(Restrictions.eq("parentDetails.parentDetailsId", parentDetailsId));
		UserDetailsModel userDetails = (UserDetailsModel) criteria.uniqueResult();
		return userDetails;
	}
	
	
	public List<UserDetailsModel> getUserDetailsBySchoolCodeWithLike(String schoolCode, String username, String status) throws Exception {
		String queryString = "SELECT c.firstname, c.middlename, c.lastname, c.image, c.email, c.mobile, c.username FROM UserDetailsModel c where c.schoolProfile.schoolCode =:p1";
		Object[] params = new Object[2];
		params[0] = schoolCode;
		if("A".equalsIgnoreCase(status) || "I".equalsIgnoreCase(status)) {
			if("A".equalsIgnoreCase(status))
				params[1] = true;
			else if("I".equalsIgnoreCase(status))
				params[1] = false;
			queryString += " c.user.active =: p2";
		}
		return findDynamicQueryWithFields(queryString, params, UserDetailsModel.class);
	}
}
