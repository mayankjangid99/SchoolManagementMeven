package com.school.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "student_report_card")
public class StudentReportCardModel implements Serializable, EntityModel {
	
	private static final long serialVersionUID = 7348337496389820535L;
	
	private Long studentReportCardId;
	private String schoolCode;
	private String sessionCode;
	private String classCode;
	private String sectionCode;
	private Integer rollNo;
	private String admissionNo;
	private String subjectCode;
	private String termId;
	private String unitTestId;
	private Long marks;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;
	private String modifiedBy;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_report_card_id", nullable = false)
	public Long getStudentReportCardId() {
		return studentReportCardId;
	}
	public void setStudentReportCardId(Long studentReportCardId) {
		this.studentReportCardId = studentReportCardId;
	}

	@Column(name = "school_code", nullable = false, length = 5)
	public String getSchoolCode() {
		return this.schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	
	@Column(name = "session_code", nullable = false, length = 10)
	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}
	
	@Column(name = "class_code", nullable = false, length = 5)
	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	@Column(name = "section_code", nullable = false, length = 5)
	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	@Column(name = "roll_no", nullable = false)
	public Integer getRollNo() {
		return rollNo;
	}
	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}
	
	@Column(name = "admission_no", nullable = false, length = 10)
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	@Column(name = "subject_code", nullable = false, length = 5)
	public String getSubjectCode() {
		return this.subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * @return the termId
	 */
	@Column(name = "term_id", length = 30)
	public String getTermId() {
		return termId;
	}

	/**
	 * @param termId the termId to set
	 */
	public void setTermId(String termId) {
		this.termId = termId;
	}

	@Column(name = "unit_test_id", length = 20)
	public String getUnitTestId() {
		return unitTestId;
	}

	public void setUnitTestId(String unitTestId) {
		this.unitTestId = unitTestId;
	}

	@Column(name = "marks", nullable = false)
	public Long getMarks() {
		return marks;
	}

	public void setMarks(Long marks) {
		this.marks = marks;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", nullable = false, columnDefinition = "DATETIME")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "created_by", nullable = false, length = 50)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedOn
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_on", columnDefinition = "DATETIME")
	public Date getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn the modifiedOn to set
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	/**
	 * @return the modifiedBy
	 */
	@Column(name = "modified_by", length = 50)
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
