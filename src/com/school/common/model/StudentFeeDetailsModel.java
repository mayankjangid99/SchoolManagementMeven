package com.school.common.model;

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
@Table(name = "student_fee_details")
public class StudentFeeDetailsModel 
{
	private Long studentFeeDetailsId;
	private Long previousStudentFeeDetailsId;
	private Integer rollNo;
	private String admissionNo;
	private BigDecimal amountPaid;
	private BigDecimal amountDue;
	private BigDecimal totalAmount;
	private String classCode;
	private String sectionCode;
	private String feeCategoryCode;
	private String schoolCode;
	private String sessionCode;
	private Integer month;
	private String monthName;
	private String type;
	private String receiptNo;
	private Date createdOn = new Date();
	private String createdBy;
	private String chequeNo;
	private String bankName;
	private String bankBranch;
	private String paymentCategoryCode;
	
	public StudentFeeDetailsModel(){
		
	}
	
	public StudentFeeDetailsModel(Long studentFeeDetailsId, Long previousStudentFeeDetailsId, Integer rollNo, BigDecimal totalAmount,
			String admissionNo,	BigDecimal amountPaid, BigDecimal amountDue, String classCode, String sectionCode, String feeCategoryCode,
			String schoolCode, String sessionCode, Integer month, String monthName, String type, String receiptNo, Date createdOn, String createdBy, String paymentCategoryCode) {
		this.studentFeeDetailsId = studentFeeDetailsId;
		this.previousStudentFeeDetailsId = previousStudentFeeDetailsId;
		this.rollNo = rollNo;
		this.admissionNo = admissionNo;
		this.totalAmount = totalAmount;
		this.amountPaid = amountPaid;
		this.amountDue = amountDue;
		this.classCode = classCode;
		this.sectionCode = sectionCode;
		this.feeCategoryCode = feeCategoryCode;
		this.schoolCode = schoolCode;
		this.sessionCode = sessionCode;
		this.month = month;
		this.monthName = monthName;
		this.type = type;
		this.receiptNo = receiptNo;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.paymentCategoryCode = paymentCategoryCode;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_fee_details_id", unique = true, nullable = false)
	public Long getStudentFeeDetailsId() {
		return studentFeeDetailsId;
	}
	public void setStudentFeeDetailsId(Long studentFeeDetailsId) {
		this.studentFeeDetailsId = studentFeeDetailsId;
	}
	
	@Column(name = "previous_student_fee_details_id")
	public Long getPreviousStudentFeeDetailsId() {
		return previousStudentFeeDetailsId;
	}

	public void setPreviousStudentFeeDetailsId(Long previousStudentFeeDetailsId) {
		this.previousStudentFeeDetailsId = previousStudentFeeDetailsId;
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
	
	@Column(name = "amount_paid", nullable = false, precision = 8, scale = 2)
	public BigDecimal getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	@Column(name = "amount_due", nullable = false, precision = 8, scale = 2)
	public BigDecimal getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
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

	@Column(name = "month", nullable = true)
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	@Column(name = "month_name", nullable = false, length = 10)
	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	@Column(name = "type", nullable = false, length = 2)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Column(name = "total_amount", nullable = false, precision = 8, scale = 2)
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "receipt_no", nullable = false, length = 50)
	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "payment_category_code", nullable = false, length = 5)
	public String getPaymentCategoryCode() {
		return paymentCategoryCode;
	}

	public void setPaymentCategoryCode(String paymentCategoryCode) {
		this.paymentCategoryCode = paymentCategoryCode;
	}

	@Column(name = "created_by", length = 50)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "cheque_no", length = 50, nullable = true)
	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	@Column(name = "bank_name", length = 100, nullable = true)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "bank_branch", length = 50, nullable = true)
	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	
	
	
}
