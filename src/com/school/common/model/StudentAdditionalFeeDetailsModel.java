package com.school.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "student_additional_fee_details")
public class StudentAdditionalFeeDetailsModel implements Serializable, EntityModel
{
	private static final long serialVersionUID = 7348337496389820535L;
		
	private Long studentAdditionalFeeDetailsId;
	private Integer rollNo;
	private String admissionNo;
	private String classCode;
	private String sectionCode;
	private String feeCategoryCode;
	private String schoolCode;
	private String sessionCode;
	private BigDecimal amount;
	private String type;
	private String attribute1;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;
	private String modifiedBy;
	
	public StudentAdditionalFeeDetailsModel(){
		
	}
	
	public StudentAdditionalFeeDetailsModel(Long studentAdditionalFeeDetailsId) {
		this.studentAdditionalFeeDetailsId = studentAdditionalFeeDetailsId;
	}
	
	public StudentAdditionalFeeDetailsModel(String schoolCode, String sessionCode, String classCode, String sectionCode) {
		this.schoolCode = schoolCode;
		this.sessionCode = sessionCode;
		this.classCode = classCode;
		this.sectionCode = sectionCode;
	}
	
	public StudentAdditionalFeeDetailsModel(String schoolCode, String sessionCode, String classCode, String sectionCode, String type) {
		this.schoolCode = schoolCode;
		this.sessionCode = sessionCode;
		this.classCode = classCode;
		this.sectionCode = sectionCode;
		this.type = type;
	}
	
	public StudentAdditionalFeeDetailsModel(String schoolCode, String sessionCode, String classCode, String sectionCode, String admissionNo, Integer rollNo, String feeCategoryCode, String type) {
		this.schoolCode = schoolCode;
		this.sessionCode = sessionCode;
		this.classCode = classCode;
		this.sectionCode = sectionCode;
		this.admissionNo = admissionNo;
		this.rollNo = rollNo;
		this.feeCategoryCode = feeCategoryCode;
		this.type = type;
	}
	
	public StudentAdditionalFeeDetailsModel(String schoolCode, String sessionCode, String classCode, String sectionCode, String admissionNo, Integer rollNo, String feeCategoryCode) {
		this.schoolCode = schoolCode;
		this.sessionCode = sessionCode;
		this.classCode = classCode;
		this.sectionCode = sectionCode;
		this.admissionNo = admissionNo;
		this.rollNo = rollNo;
		this.feeCategoryCode = feeCategoryCode;
	}
	
	public StudentAdditionalFeeDetailsModel(String schoolCode, String sessionCode, String classCode, String sectionCode, String admissionNo, Integer rollNo) {
		this.schoolCode = schoolCode;
		this.sessionCode = sessionCode;
		this.classCode = classCode;
		this.sectionCode = sectionCode;
		this.admissionNo = admissionNo;
		this.rollNo = rollNo;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_additional_fee_details_id", nullable = false)
	public Long getStudentAdditionalFeeDetailsId() {
		return studentAdditionalFeeDetailsId;
	}
	public void setStudentAdditionalFeeDetailsId(Long studentAdditionalFeeDetailsId) {
		this.studentAdditionalFeeDetailsId = studentAdditionalFeeDetailsId;
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

	@Column(name = "fee_category_code", nullable = false, length = 5)
	public String getFeeCategoryCode() {
		return this.feeCategoryCode;
	}

	public void setFeeCategoryCode(String feeCategoryCode) {
		this.feeCategoryCode = feeCategoryCode;
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

	@Column(name = "type", nullable = false, length = 2)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	/**
	 * @return the attribute1
	 */
	@Column(name = "attribute1", length = 10)
	public String getAttribute1() {
		return attribute1;
	}

	/**
	 * @param attribute1 the attribute1 to set
	 */
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	@Column(name = "amount", nullable = false, precision = 8, scale = 2)
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
