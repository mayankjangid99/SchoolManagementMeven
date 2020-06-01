package com.school.common.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "payment_category")
public class PaymentCategoryModel 
{
	private String paymentCategoryCode;
	private String paymentCategoryName;
	
	private Set<SchoolPaymentCategoryModel> schoolPaymentCategories;
	
	
	public PaymentCategoryModel(){
		
	}
	
	public PaymentCategoryModel(String paymentCategoryCode) {
		this.paymentCategoryCode = paymentCategoryCode;
	}

	public PaymentCategoryModel(String paymentCategoryCode, String paymentCategoryName,
			Set<SchoolPaymentCategoryModel> schoolPaymentCategories) {
		this.paymentCategoryCode = paymentCategoryCode;
		this.paymentCategoryName = paymentCategoryName;
		this.schoolPaymentCategories = schoolPaymentCategories;
	}
	
	@Id
	@Column(name = "payment_category_code", nullable = false, length = 5)
	public String getPaymentCategoryCode() {
		return paymentCategoryCode;
	}

	public void setPaymentCategoryCode(String paymentCategoryCode) {
		this.paymentCategoryCode = paymentCategoryCode;
	}

	
	@Column(name = "payment_category_name", nullable = false, length = 50)
	public String getPaymentCategoryName() {
		return paymentCategoryName;
	}

	public void setPaymentCategoryName(String paymentCategoryName) {
		this.paymentCategoryName = paymentCategoryName;
	}

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SchoolPaymentCategoryModel> getSchoolPaymentCategories() {
		return schoolPaymentCategories;
	}

	public void setSchoolPaymentCategories(
			Set<SchoolPaymentCategoryModel> schoolPaymentCategories) {
		this.schoolPaymentCategories = schoolPaymentCategories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((paymentCategoryCode == null) ? 0 : paymentCategoryCode
						.hashCode());
		result = prime
				* result
				+ ((paymentCategoryName == null) ? 0 : paymentCategoryName
						.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentCategoryModel other = (PaymentCategoryModel) obj;
		if (paymentCategoryCode == null) {
			if (other.paymentCategoryCode != null)
				return false;
		} else if (!paymentCategoryCode.equals(other.paymentCategoryCode))
			return false;
		if (paymentCategoryName == null) {
			if (other.paymentCategoryName != null)
				return false;
		} else if (!paymentCategoryName.equals(other.paymentCategoryName))
			return false;
		return true;
	}
	
	
}
