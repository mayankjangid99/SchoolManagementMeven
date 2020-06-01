package com.school.common.reports.model;


public class StudentFeeDetailsSubReportModel {
	private String amountPaid;
	private String amountDue;
	private String totalAmount;
	private String feeCategoryCode;
	private String feeCategoryName;
	private Integer month;
	private String monthName;
	private String type;
	private String date;
	private String hasLastTransaction;
	
	
	public String getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(String amountDue) {
		this.amountDue = amountDue;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getFeeCategoryCode() {
		return feeCategoryCode;
	}
	public void setFeeCategoryCode(String feeCategoryCode) {
		this.feeCategoryCode = feeCategoryCode;
	}
	public String getFeeCategoryName() {
		return feeCategoryName;
	}
	public void setFeeCategoryName(String feeCategoryName) {
		this.feeCategoryName = feeCategoryName;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHasLastTransaction() {
		return hasLastTransaction;
	}
	public void setHasLastTransaction(String hasLastTransaction) {
		this.hasLastTransaction = hasLastTransaction;
	}
	
	
	
}
