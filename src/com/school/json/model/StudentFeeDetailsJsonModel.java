package com.school.json.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class StudentFeeDetailsJsonModel {

	private Long studentFeeDetailsId;
	private Integer rollNo;
	private String admissionNo;
	private String amountPaid;
	private String amountDue;
	private String totalAmount;
	private String classCode;
	private String sectionCode;
	private String feeCategoryCode;
	private String feeCategoryName;
	private String schoolCode;
	private String sessionCode;
	private String month;
	private String monthName;
	private String type;
	private String receiptNo;



	@JsonProperty(value="studentFeeDetailsId")
	public Long getStudentFeeDetailsId() {
		return studentFeeDetailsId;
	}
	public void setStudentFeeDetailsId(Long studentFeeDetailsId) {
		this.studentFeeDetailsId = studentFeeDetailsId;
	}
	
	@JsonProperty(value="rollNo")
	public Integer getRollNo() {
		return rollNo;
	}
	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}
	
	@JsonProperty(value="admissionNo")
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	
	@JsonProperty(value="amountPaid")
	public String getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	@JsonProperty(value="amountDue")
	public String getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(String amountDue) {
		this.amountDue = amountDue;
	}
	
	@JsonProperty(value="classCode")
	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	@JsonProperty(value="sectionCode")
	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	@JsonProperty(value="feeCategoryCode")
	public String getFeeCategoryCode() {
		return this.feeCategoryCode;
	}

	public void setFeeCategoryCode(String feeCategoryCode) {
		this.feeCategoryCode = feeCategoryCode;
	}
	
	@JsonProperty(value="feeCategoryName")
	public String getFeeCategoryName() {
		return feeCategoryName;
	}
	public void setFeeCategoryName(String feeCategoryName) {
		this.feeCategoryName = feeCategoryName;
	}
	
	@JsonProperty(value="schoolCode")
	public String getSchoolCode() {
		return this.schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	
	@JsonProperty(value="sessionCode")
	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	@JsonProperty(value="month")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@JsonProperty(value="monthName")
	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	@JsonProperty(value="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@JsonProperty(value="totalAmount")
	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@JsonProperty(value="receiptNo")
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	
	
}
