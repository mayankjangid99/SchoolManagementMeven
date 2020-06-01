package com.school.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.generic.dao.DaoManagerBean;
import com.school.common.model.ClassFeeCategoryModel;
import com.school.common.model.ClassSectionModel;
import com.school.common.model.ClassSubjectModel;
import com.school.common.model.ReportCardConfigModel;
import com.school.common.model.SchoolPaymentCategoryModel;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.UserModel;

@Repository
public class SchoolProfileDao extends DaoManagerBean
{
	private static Logger LOG = LoggerFactory.getLogger(SchoolProfileDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	private Session getOpenSession()
	{
		return sessionFactory.openSession();
	}
	
	
	public SchoolProfileModel getSchoolProfileBySchoolCode(SchoolProfileModel schoolProfile)
	{
		Criteria criteria = getCurrentSession().createCriteria(SchoolProfileModel.class);
		criteria.add(Restrictions.eq("schoolCode", schoolProfile.getSchoolCode()));
		SchoolProfileModel schoolProfileModel = (SchoolProfileModel) criteria.uniqueResult();
		return schoolProfileModel;
	}
	
	
	public SchoolProfileModel getSchoolProfileByUserId(UserModel user)
	{
		Criteria criteria = getCurrentSession().createCriteria(SchoolProfileModel.class, "schoolProfile");
		criteria.createAlias("schoolProfile.userDetailses", "userDetailses");
		criteria.createAlias("userDetailses.user", "user");
		criteria.add(Restrictions.eq("user.userId", user.getUserId()));
		SchoolProfileModel schoolProfileModel = (SchoolProfileModel) criteria.uniqueResult();
		return schoolProfileModel;
	}
	
	
	public List<SchoolProfileModel> getAllSchoolProfile()
	{
		@SuppressWarnings("unchecked")
		List<SchoolProfileModel> list = getCurrentSession().createQuery("FROM SchoolProfileModel").list();
		return list;
	}
	
	
	public List<SchoolProfileModel> getAllSchoolProfileCacheable()
	{
		@SuppressWarnings("unchecked")
		List<SchoolProfileModel> list = getCurrentSession().createQuery("FROM SchoolProfileModel").setCacheable(true).list();
		return list;
	}
	
	
	public void saveSchoolProfile(SchoolProfileModel schoolProfile)
	{
		Session session = getOpenSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(schoolProfile);
			transaction.commit();
			session.clear();
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: saveSchoolProfile() in SchoolProfileDao ", e);
		}
		finally {
			session.close();
		}		
	}
	
	
	public List<SchoolProfileModel> fetchSchoolProfile(SchoolProfileModel schoolProfile)
	{
		@SuppressWarnings("unchecked")
		List<SchoolProfileModel> schoolProfileList = getCurrentSession().createQuery("FROM SchoolProfileModel").list();
		return schoolProfileList;
	}
	
	
	public boolean updateSchoolProfile(SchoolProfileModel schoolProfile) {
		int i = getCurrentSession().createQuery("UPDATE SchoolProfileModel SET logo=:lo, name=:na, address=:ad, district=:di, city=:ci, "
				+ "postcode=:po, fax=:fa, email=:em, website=:we, startYear=:sy, registrationDate=:re, phone=:ph, state=:st WHERE schoolCode=:sc")
				.setParameter("lo", schoolProfile.getLogo()).setParameter("na", schoolProfile.getName()).setParameter("ad", schoolProfile.getAddress())
				.setParameter("di", schoolProfile.getDistrict()).setParameter("ci", schoolProfile.getCity()).setParameter("po", schoolProfile.getPostcode())
				.setParameter("fa", schoolProfile.getFax()).setParameter("em", schoolProfile.getEmail()).setParameter("we", schoolProfile.getWebsite())
				.setParameter("sy", schoolProfile.getStartYear()).setParameter("re", schoolProfile.getRegistrationDate()).setParameter("ph", schoolProfile.getPhone())
				.setParameter("st", schoolProfile.getState()).setParameter("sc", schoolProfile.getSchoolCode()).executeUpdate();
		if(i > 0)
			return true;
		else
			return false;
	}
	
	public boolean existPromotedSchoolData(String schoolCode, String sessionCode) throws Exception {
			List<ClassSectionModel> classSectionList = findDynamicQuery("FROM ClassSectionModel c WHERE c.schoolProfile.schoolCode=:p1 AND c.sessionDetails.sessionCode=:p2", new Object[] {schoolCode, sessionCode});
			if(classSectionList != null && !classSectionList.isEmpty())
				return true;
		return false;
	}
	
	
	public Map<String, Object> getPromoteSchoolData(String schoolCode, String sessionCode) {
		Object[] param = new Object[] {schoolCode};
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<ClassSectionModel> classSectionList = findDynamicQuery("FROM ClassSectionModel c WHERE c.schoolProfile.schoolCode=:p1", param);
			List<ClassSubjectModel> classSubjectList = findDynamicQuery("FROM ClassSubjectModel c WHERE c.schoolProfile.schoolCode=:p1", param);
			List<SchoolPaymentCategoryModel> schoolPaymentList = findDynamicQuery("FROM SchoolPaymentCategoryModel c WHERE c.schoolProfile.schoolCode=:p1", param);
			List<ClassFeeCategoryModel> classFeeCategoryList = findDynamicQuery("FROM ClassFeeCategoryModel c WHERE c.schoolProfile.schoolCode=:p1", param);
			List<ReportCardConfigModel> reportCardConfigList = findDynamicQuery("FROM ReportCardConfigModel c WHERE c.schoolProfile.schoolCode=:p1", param);
			
			map.put("classSectionList", classSectionList);
			map.put("classSubjectList", classSubjectList);
			map.put("schoolPaymentList", schoolPaymentList);
			map.put("classFeeCategoryList", classFeeCategoryList);
			map.put("reportCardConfigList", reportCardConfigList);
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERROR: updatePromoteSchoolData() in SchoolProfileDao ", e);
		}
		return map;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public boolean updatePromoteSchoolData(Map<String, Object> mapData) {
		boolean flag = false;
		Session session = getOpenSession();
		try {
			List<ClassSectionModel> classSectionList = (List<ClassSectionModel>) mapData.get("classSectionList");
			List<ClassSubjectModel> classSubjectList =  (List<ClassSubjectModel>) mapData.get("classSubjectList");
			List<SchoolPaymentCategoryModel> schoolPaymentList =  (List<SchoolPaymentCategoryModel>) mapData.get("schoolPaymentList");
			List<ClassFeeCategoryModel> classFeeCategoryList =  (List<ClassFeeCategoryModel>) mapData.get("classFeeCategoryList");
			List<ReportCardConfigModel> reportCardConfigList =  (List<ReportCardConfigModel>) mapData.get("reportCardConfigList");
			Transaction transaction = session.beginTransaction();			
			if(classSectionList != null && !classSectionList.isEmpty()) {
				for(ClassSectionModel classSection : classSectionList) {
					session.persist(classSection);
				}
			}

			if(classSubjectList != null && !classSubjectList.isEmpty()) {
				for(ClassSubjectModel classSubject : classSubjectList) {
					session.persist(classSubject);
				}
			}

			if(schoolPaymentList != null && !schoolPaymentList.isEmpty()) {
				for(SchoolPaymentCategoryModel schoolPayment : schoolPaymentList) {
					session.persist(schoolPayment);
				}
			}

			if(classFeeCategoryList != null && !classFeeCategoryList.isEmpty()) {
				for(ClassFeeCategoryModel classFeeCategory : classFeeCategoryList) {
					session.persist(classFeeCategory);
				}
			}
			
			if(reportCardConfigList != null && !reportCardConfigList.isEmpty()) {
				for(ReportCardConfigModel reportCardConfig : reportCardConfigList) {
					session.persist(reportCardConfig);
				}
			}

			transaction.commit();
			session.clear();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERROR: updatePromoteSchoolData() in SchoolProfileDao ", e);
		}
		finally {
			session.close();
		}
		return flag;
	}
}
