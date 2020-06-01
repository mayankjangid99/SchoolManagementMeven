package com.school.common.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.common.model.StudentFeeDetailsModel;

@Repository
public class StudentFeeDetailsDao 
{
	@Autowired
	private SessionFactory sessionFactory;

	
	private static Logger log = LoggerFactory.getLogger(ClassSectionDao.class);
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	private Session openSession()
	{
		return sessionFactory.openSession();
	}
	
	/*@SuppressWarnings("unchecked")
	public List<ClassFeeCategoryModel> getFeeCategoryListByClassCodeSchoolCodeAndType(String schoolCode, String sessionCode, String classCode, String sectionCode, String type) {
			Criteria criteria = getCurrentSession().createCriteria(ClassFeeCategoryModel.class);
			criteria.addOrder(Order.asc("schoolProfile.schoolCode"));
			
			if(schoolCode != null && !"".equals(schoolCode))
				criteria.add(Restrictions.eq("schoolProfile.schoolCode", schoolCode));
			
			if(sessionCode != null && !"".equals(sessionCode))
				criteria.add(Restrictions.eq("sessionDetails.sessionCode", sessionCode));
			
			if(classCode != null && !"".equals(classCode))
				criteria.add(Restrictions.eq("classInformation.classCode", classCode));

			if(sectionCode != null && !"".equals(sectionCode))
				criteria.add(Restrictions.eq("sectionInformation.sectionCode", sectionCode));
			
			if(type != null && !"".equals(type))
				criteria.add(Restrictions.eq("type", type));
			
			List<ClassFeeCategoryModel> classSectionList = criteria.list();
			return classSectionList;
	}*/
	
	
	@SuppressWarnings("unchecked")
	public List<StudentFeeDetailsModel> getAllSubmittedStudentFeeDetails(String schoolCode, String sessionCode, String classCode, String sectionCode, String admissionNo, Integer rollNo)
	{
		Criteria criteria = getCurrentSession().createCriteria(StudentFeeDetailsModel.class);
		
		if(schoolCode != null && !"".equals(schoolCode))
			criteria.add(Restrictions.eq("schoolCode", schoolCode));
		
		if(sessionCode != null && !"".equals(sessionCode))
			criteria.add(Restrictions.eq("sessionCode", sessionCode));
		
		if(classCode != null && !"".equals(classCode))
			criteria.add(Restrictions.eq("classCode", classCode));
		
		if(sectionCode != null && !"".equals(sectionCode))
			criteria.add(Restrictions.eq("sectionCode", sectionCode));
		
		if(admissionNo != null && !"".equals(admissionNo))
			criteria.add(Restrictions.eq("admissionNo", admissionNo));
		
		if(rollNo != null && rollNo > 0)
			criteria.add(Restrictions.eq("rollNo", rollNo));
		
		List<StudentFeeDetailsModel> list = criteria.list();
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<StudentFeeDetailsModel> getAllSubmittedStudentFeeDetails(StudentFeeDetailsModel studentFeeDetails)
	{
		Criteria criteria = getCurrentSession().createCriteria(StudentFeeDetailsModel.class);
		
		if(studentFeeDetails.getSchoolCode() != null && !"".equals(studentFeeDetails.getSchoolCode()))
			criteria.add(Restrictions.eq("schoolCode", studentFeeDetails.getSchoolCode()));
		
		if(studentFeeDetails.getSessionCode() != null && !"".equals(studentFeeDetails.getSessionCode()))
			criteria.add(Restrictions.eq("sessionCode", studentFeeDetails.getSessionCode()));
		
		if(studentFeeDetails.getClassCode() != null && !"".equals(studentFeeDetails.getClassCode()))
			criteria.add(Restrictions.eq("classCode", studentFeeDetails.getClassCode()));
		
		if(studentFeeDetails.getSectionCode() != null && !"".equals(studentFeeDetails.getSectionCode()))
			criteria.add(Restrictions.eq("sectionCode", studentFeeDetails.getSectionCode()));
		
		if(studentFeeDetails.getAdmissionNo() != null && !"".equals(studentFeeDetails.getAdmissionNo()))
			criteria.add(Restrictions.eq("admissionNo", studentFeeDetails.getAdmissionNo()));
		
		if(studentFeeDetails.getRollNo() != null && studentFeeDetails.getRollNo() > 0)
			criteria.add(Restrictions.eq("rollNo", studentFeeDetails.getRollNo()));
		
		List<StudentFeeDetailsModel> list = criteria.list();
		return list;
	}
	
	
	/*public StudentFeeDetailsModel getStudentFeeDetailsByTypeAndFeeCode(StudentFeeDetailsModel studentFeeDetails)
	{
		String query = null;
		if("M".equals(studentFeeDetails.getType()))
			query = "FROM StudentFeeDetailsModel WHERE rollNo=:rol AND admissionNo=:adn AND month=:mon AND feeCategoryCode=:fcc AND sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ";
		else
			query = "FROM StudentFeeDetailsModel WHERE rollNo=:rol AND admissionNo=:adn AND feeCategoryCode=:fcc AND sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ";
		Query query2 = getCurrentSession().createQuery(query);
		StudentFeeDetailsModel feeDetailsModel = null;
		if("M".equals(studentFeeDetails.getType())) {
			feeDetailsModel = (StudentFeeDetailsModel) query2
			.setParameter("rol", studentFeeDetails.getRollNo()).setParameter("adn", studentFeeDetails.getAdmissionNo()).setParameter("mon", studentFeeDetails.getMonth()).setParameter("fcc", studentFeeDetails.getFeeCategoryCode())
			.setParameter("scc", studentFeeDetails.getSectionCode()).setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).uniqueResult();
		}
		else {
			feeDetailsModel = (StudentFeeDetailsModel) query2
			.setParameter("rol", studentFeeDetails.getRollNo()).setParameter("adn", studentFeeDetails.getAdmissionNo()).setParameter("fcc", studentFeeDetails.getFeeCategoryCode())
			.setParameter("scc", studentFeeDetails.getSectionCode()).setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).uniqueResult();			
		}
		return feeDetailsModel;
	}*/
	
	
	@SuppressWarnings("unchecked")
	public List<StudentFeeDetailsModel> getAllSubmittedStudentFeeDetailsByTypeAndRollAndAdmis(StudentFeeDetailsModel studentFeeDetails)
	{
		String query = null;
		if("M".equals(studentFeeDetails.getType()))
			query = "FROM StudentFeeDetailsModel WHERE rollNo=:rol AND admissionNo=:adn AND month=:mon AND sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ";
		else
			query = "FROM StudentFeeDetailsModel WHERE rollNo=:rol AND admissionNo=:adn AND sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ";
		Query query2 = getCurrentSession().createQuery(query);
		List<StudentFeeDetailsModel> feeDetailsList = new ArrayList<StudentFeeDetailsModel>();
		if("M".equals(studentFeeDetails.getType())) {
			feeDetailsList = query2
			.setParameter("rol", studentFeeDetails.getRollNo()).setParameter("adn", studentFeeDetails.getAdmissionNo()).setParameter("mon", studentFeeDetails.getMonth())
			.setParameter("scc", studentFeeDetails.getSectionCode()).setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).list();
		}
		else {
			feeDetailsList = query2
			.setParameter("rol", studentFeeDetails.getRollNo()).setParameter("adn", studentFeeDetails.getAdmissionNo())
			.setParameter("scc", studentFeeDetails.getSectionCode()).setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).list();			
		}
		return feeDetailsList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<StudentFeeDetailsModel> getAllSubmittedStudentFeeDetailsByTypeAndFeeCodeAndRollAndAdmis(StudentFeeDetailsModel studentFeeDetails)
	{
		String query = null;
		if("M".equals(studentFeeDetails.getType()))
			query = "FROM StudentFeeDetailsModel WHERE rollNo=:rol AND admissionNo=:adn AND month=:mon AND sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ AND feeCategoryCode=:fcc";
		else
			query = "FROM StudentFeeDetailsModel WHERE rollNo=:rol AND admissionNo=:adn AND sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ AND feeCategoryCode=:fcc";
		Query query2 = getCurrentSession().createQuery(query);
		List<StudentFeeDetailsModel> feeDetailsList = new ArrayList<StudentFeeDetailsModel>();
		if("M".equals(studentFeeDetails.getType())) {
			feeDetailsList = query2
			.setParameter("rol", studentFeeDetails.getRollNo()).setParameter("adn", studentFeeDetails.getAdmissionNo()).setParameter("mon", studentFeeDetails.getMonth())
			.setParameter("scc", studentFeeDetails.getSectionCode()).setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).setParameter("fcc", studentFeeDetails.getFeeCategoryCode()).list();
		}
		else {
			feeDetailsList = query2
			.setParameter("rol", studentFeeDetails.getRollNo()).setParameter("adn", studentFeeDetails.getAdmissionNo())
			.setParameter("scc", studentFeeDetails.getSectionCode()).setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).setParameter("fcc", studentFeeDetails.getFeeCategoryCode()).list();			
		}
		return feeDetailsList;
	}
	
	
	public boolean saveStudentFeeDetails(StudentFeeDetailsModel studentFeeDetails)
	{
		Session session = openSession();
		boolean flag = false;
		try {
			Transaction transaction = session.beginTransaction();
			session.save(studentFeeDetails);
			transaction.commit();
			session.flush();
			flag = true;
		} catch (HibernateException e) {
			//e.printStackTrace();
			log.error("ERROR: saveStudentFeeDetails() in StudentFeeDetailsDao ", e);
		}
		finally {
			session.close();
		}
		return flag;
	}
	
	public boolean updateStudentFeeDetails(StudentFeeDetailsModel studentFeeDetails)
	{
		int flag = 0;
		if(studentFeeDetails.getType().equals("M")) {
			flag = getCurrentSession().createQuery("UPDATE StudentFeeDetailsModel SET amountPaid=:amp, amountDue=:amd WHERE rollNo=:rol AND admissionNo=:adn AND month=:mon AND feeCategoryCode=:fcc AND sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ")
			.setParameter("amp", studentFeeDetails.getAmountPaid()).setParameter("amd", studentFeeDetails.getAmountDue()).setParameter("rol", studentFeeDetails.getRollNo()).setParameter("adn", studentFeeDetails.getAdmissionNo())
			.setParameter("mon", studentFeeDetails.getMonth()).setParameter("fcc", studentFeeDetails.getFeeCategoryCode()).setParameter("scc", studentFeeDetails.getSectionCode()).setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).executeUpdate();
		}
		if(studentFeeDetails.getType().equals("Y")) {
			flag = getCurrentSession().createQuery("UPDATE StudentFeeDetailsModel SET amountPaid=:amp, amountDue=:amd WHERE rollNo=:rol AND admissionNo=:adn AND feeCategoryCode=:fcc AND sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ")
			.setParameter("amp", studentFeeDetails.getAmountPaid()).setParameter("amd", studentFeeDetails.getAmountDue()).setParameter("rol", studentFeeDetails.getRollNo()).setParameter("adn", studentFeeDetails.getAdmissionNo())
			.setParameter("fcc", studentFeeDetails.getFeeCategoryCode()).setParameter("scc", studentFeeDetails.getSectionCode()).setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).executeUpdate();
		}
		if(flag == 0)
			return false;
		else
			return true;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<StudentFeeDetailsModel> getSubmittedAllStudentsFeeDetailsByType(StudentFeeDetailsModel studentFeeDetails)
	{
		String query = null;
		if("M".equals(studentFeeDetails.getType()))
			query = "FROM StudentFeeDetailsModel WHERE month=:mon AND sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ";
		else
			query = "FROM StudentFeeDetailsModel WHERE sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ";
		Query query2 = getCurrentSession().createQuery(query);
		List<StudentFeeDetailsModel> feeDetailsModelList = null;
		if("M".equals(studentFeeDetails.getType())) {
			feeDetailsModelList = query2
			.setParameter("mon", studentFeeDetails.getMonth()).setParameter("scc", studentFeeDetails.getSectionCode())
			.setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).list();
		}
		else {
			feeDetailsModelList = query2
			.setParameter("scc", studentFeeDetails.getSectionCode()).setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).list();			
		}
		return feeDetailsModelList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<StudentFeeDetailsModel> getSubmittedAllStudentsFeeDetailsByTypeAndFeeCode(StudentFeeDetailsModel studentFeeDetails)
	{
		String query = null;
		if("M".equals(studentFeeDetails.getType()))
			query = "FROM StudentFeeDetailsModel WHERE month=:mon AND sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ AND feeCategoryCode=:fcc";
		else
			query = "FROM StudentFeeDetailsModel WHERE sectionCode=:scc AND classCode=:clc AND sessionCode=:ssc AND schoolCode=:slc AND type=:typ AND feeCategoryCode=:fcc";
		Query query2 = getCurrentSession().createQuery(query);
		List<StudentFeeDetailsModel> feeDetailsModelList = null;
		if("M".equals(studentFeeDetails.getType())) {
			feeDetailsModelList = query2
			.setParameter("mon", studentFeeDetails.getMonth()).setParameter("scc", studentFeeDetails.getSectionCode())
			.setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).setParameter("fcc", studentFeeDetails.getFeeCategoryCode()).list();
		}
		else {
			feeDetailsModelList = query2
			.setParameter("scc", studentFeeDetails.getSectionCode()).setParameter("clc", studentFeeDetails.getClassCode()).setParameter("ssc", studentFeeDetails.getSessionCode())
			.setParameter("slc", studentFeeDetails.getSchoolCode()).setParameter("typ", studentFeeDetails.getType()).setParameter("fcc", studentFeeDetails.getFeeCategoryCode()).list();			
		}
		return feeDetailsModelList;
	}
	
	/*@SuppressWarnings("unchecked")
	public List<StudentFeeDetailsModel> getAllSubmittedStudentFeeDetailsByReceiptNo(String receiptNo)
	{
		//return getCurrentSession().createQuery("FROM StudentFeeDetailsModel WHERE receiptNo=:ren").setParameter("ren", studentFeeDetails.getReceiptNo())..list();
		Criteria criteria = getCurrentSession().createCriteria(StudentFeeDetailsModel.class);
		criteria.addOrder(Order.asc("createdOn"));
		criteria.add(Restrictions.eq("receiptNo", receiptNo));
		return criteria.list();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<StudentFeeDetailsModel> getAllSubmittedStudentFeeDetailsByReceiptNo(String receiptNo, String schoolCode, String sessionCode, String classCode, String sectionCode)
	{
		//return getCurrentSession().createQuery("FROM StudentFeeDetailsModel WHERE receiptNo=:ren").setParameter("ren", studentFeeDetails.getReceiptNo())..list();
		Criteria criteria = getCurrentSession().createCriteria(StudentFeeDetailsModel.class);
		criteria.addOrder(Order.asc("createdOn"));
		criteria.add(Restrictions.eq("receiptNo", receiptNo));
		criteria.add(Restrictions.eq("schoolCode", schoolCode));
		criteria.add(Restrictions.eq("sessionCode", sessionCode));
		criteria.add(Restrictions.eq("classCode", classCode));
		criteria.add(Restrictions.eq("sectionCode", sectionCode));
		return criteria.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public StudentFeeDetailsModel getPreviousSubmittedStudentsFeeDetailsWithFieldByTypeAndFeeCode(StudentFeeDetailsModel studentFeeDetails)
	{
		String queryString = null;
		if("M".equals(studentFeeDetails.getType()))
			queryString = "Select student_fee_details_id, previous_student_fee_details_id, roll_no, admission_no, amount_paid, amount_due, class_code, section_code, fee_category_code, school_code, session_code, month, month_name, type, total_amount, receipt_no, created_on, created_by, payment_category_code"
					+ " FROM student_fee_details WHERE roll_no='"+studentFeeDetails.getRollNo()+"' AND admission_no='"+studentFeeDetails.getAdmissionNo()+"' AND fee_category_code='"+studentFeeDetails.getFeeCategoryCode()+"' AND month='"+studentFeeDetails.getMonth()+"' "
					+ "AND section_code='"+studentFeeDetails.getSectionCode()+"' AND class_code='"+studentFeeDetails.getClassCode()+"' AND session_code='"+studentFeeDetails.getSessionCode()+"' AND school_code='"+studentFeeDetails.getSchoolCode()+"' AND type='"+studentFeeDetails.getType()+"' Order BY student_fee_details_id DESC LIMIT 1";
		else
			queryString = "Select student_fee_details_id, previous_student_fee_details_id, roll_no, admission_no, amount_paid, amount_due, class_code, section_code, fee_category_code, school_code, session_code, month, month_name, type, total_amount, receipt_no, created_on, created_by, payment_category_code"
					+ " FROM student_fee_details WHERE roll_no='"+studentFeeDetails.getRollNo()+"' AND admission_no='"+studentFeeDetails.getAdmissionNo()+"' AND fee_category_code='"+studentFeeDetails.getFeeCategoryCode()+"' AND section_code='"+studentFeeDetails.getSectionCode()+"'"
					+ " AND class_code='"+studentFeeDetails.getClassCode()+"' AND session_code='"+studentFeeDetails.getSessionCode()+"' AND school_code='"+studentFeeDetails.getSchoolCode()+"' AND type='"+studentFeeDetails.getType()+"' Order BY student_fee_details_id DESC LIMIT 1";
		Query query = getCurrentSession().createSQLQuery(queryString);
		List<Object[]> objects = query.list();
		List<StudentFeeDetailsModel> feeDetailsModelList = new ArrayList<StudentFeeDetailsModel>();
		StudentFeeDetailsModel feeDetails = null;
		for(Object[] obj : objects){
			feeDetails = new StudentFeeDetailsModel();
			feeDetails.setStudentFeeDetailsId(((BigInteger)obj[0]).longValue());
			feeDetails.setPreviousStudentFeeDetailsId(obj[1] != null ? ((BigInteger)obj[1]).longValue() : null);
			feeDetails.setRollNo((Integer)obj[2]);
			feeDetails.setAdmissionNo((String)obj[3]);
			feeDetails.setAmountPaid((BigDecimal)obj[4]);
			feeDetails.setAmountDue((BigDecimal)obj[5]);
			feeDetails.setClassCode((String)obj[6]);
			feeDetails.setSectionCode((String)obj[7]);
			feeDetails.setFeeCategoryCode((String)obj[8]);
			feeDetails.setSchoolCode((String)obj[9]);
			feeDetails.setSessionCode((String)obj[10]);
			feeDetails.setMonth((Integer)obj[11]);
			feeDetails.setMonthName((String)obj[12]);
			feeDetails.setType((String)obj[13]);
			feeDetails.setTotalAmount((BigDecimal)obj[14]);
			feeDetails.setReceiptNo((String)obj[15]);
			feeDetails.setCreatedOn((Date)obj[16]);
			feeDetails.setCreatedBy((String)obj[17]);
			feeDetails.setPaymentCategoryCode((String)obj[18]);
			feeDetailsModelList.add(feeDetails);
		}
		if(feeDetailsModelList.size() > 0)
			return feeDetailsModelList.get(0);
		return null;
	}
}