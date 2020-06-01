package com.school.common.model;

// Generated Apr 2, 2016 3:30:20 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AttendanceSmsDetails generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "student_attendance_sms_details")
public class StudentAttendanceSmsDetailsModel implements java.io.Serializable {

	private long id;
	private AdmissionDetailsModel admissionDetails;
	private String d1;
	private String d10;
	private String d11;
	private String d12;
	private String d13;
	private String d14;
	private String d15;
	private String d16;
	private String d17;
	private String d18;
	private String d19;
	private String d2;
	private String d20;
	private String d21;
	private String d22;
	private String d23;
	private String d24;
	private String d25;
	private String d26;
	private String d27;
	private String d28;
	private String d29;
	private String d3;
	private String d30;
	private String d31;
	private String d4;
	private String d5;
	private String d6;
	private String d7;
	private String d8;
	private String d9;
	private int months;
	private String year;

	public StudentAttendanceSmsDetailsModel() {
	}

	public StudentAttendanceSmsDetailsModel(long id, AdmissionDetailsModel admissionDetails,
			int months) {
		this.id = id;
		this.admissionDetails = admissionDetails;
		this.months = months;
	}

	public StudentAttendanceSmsDetailsModel(long id, AdmissionDetailsModel admissionDetails,
			String d1, String d10, String d11, String d12, String d13,
			String d14, String d15, String d16, String d17, String d18,
			String d19, String d2, String d20, String d21, String d22,
			String d23, String d24, String d25, String d26, String d27,
			String d28, String d29, String d3, String d30, String d31,
			String d4, String d5, String d6, String d7, String d8, String d9,
			int months, String year) {
		this.id = id;
		this.admissionDetails = admissionDetails;
		this.d1 = d1;
		this.d10 = d10;
		this.d11 = d11;
		this.d12 = d12;
		this.d13 = d13;
		this.d14 = d14;
		this.d15 = d15;
		this.d16 = d16;
		this.d17 = d17;
		this.d18 = d18;
		this.d19 = d19;
		this.d2 = d2;
		this.d20 = d20;
		this.d21 = d21;
		this.d22 = d22;
		this.d23 = d23;
		this.d24 = d24;
		this.d25 = d25;
		this.d26 = d26;
		this.d27 = d27;
		this.d28 = d28;
		this.d29 = d29;
		this.d3 = d3;
		this.d30 = d30;
		this.d31 = d31;
		this.d4 = d4;
		this.d5 = d5;
		this.d6 = d6;
		this.d7 = d7;
		this.d8 = d8;
		this.d9 = d9;
		this.months = months;
		this.year = year;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admission_details_id", nullable = false)
	public AdmissionDetailsModel getAdmissionDetails() {
		return this.admissionDetails;
	}

	public void setAdmissionDetails(AdmissionDetailsModel admissionDetails) {
		this.admissionDetails = admissionDetails;
	}

	@Column(name = "d1", length = 1)
	public String getD1() {
		return this.d1;
	}

	public void setD1(String d1) {
		this.d1 = d1;
	}

	@Column(name = "d10", length = 1)
	public String getD10() {
		return this.d10;
	}

	public void setD10(String d10) {
		this.d10 = d10;
	}

	@Column(name = "d11", length = 1)
	public String getD11() {
		return this.d11;
	}

	public void setD11(String d11) {
		this.d11 = d11;
	}

	@Column(name = "d12", length = 1)
	public String getD12() {
		return this.d12;
	}

	public void setD12(String d12) {
		this.d12 = d12;
	}

	@Column(name = "d13", length = 1)
	public String getD13() {
		return this.d13;
	}

	public void setD13(String d13) {
		this.d13 = d13;
	}

	@Column(name = "d14", length = 1)
	public String getD14() {
		return this.d14;
	}

	public void setD14(String d14) {
		this.d14 = d14;
	}

	@Column(name = "d15", length = 1)
	public String getD15() {
		return this.d15;
	}

	public void setD15(String d15) {
		this.d15 = d15;
	}

	@Column(name = "d16", length = 1)
	public String getD16() {
		return this.d16;
	}

	public void setD16(String d16) {
		this.d16 = d16;
	}

	@Column(name = "d17", length = 1)
	public String getD17() {
		return this.d17;
	}

	public void setD17(String d17) {
		this.d17 = d17;
	}

	@Column(name = "d18", length = 1)
	public String getD18() {
		return this.d18;
	}

	public void setD18(String d18) {
		this.d18 = d18;
	}

	@Column(name = "d19", length = 1)
	public String getD19() {
		return this.d19;
	}

	public void setD19(String d19) {
		this.d19 = d19;
	}

	@Column(name = "d2", length = 1)
	public String getD2() {
		return this.d2;
	}

	public void setD2(String d2) {
		this.d2 = d2;
	}

	@Column(name = "d20", length = 1)
	public String getD20() {
		return this.d20;
	}

	public void setD20(String d20) {
		this.d20 = d20;
	}

	@Column(name = "d21", length = 1)
	public String getD21() {
		return this.d21;
	}

	public void setD21(String d21) {
		this.d21 = d21;
	}

	@Column(name = "d22", length = 1)
	public String getD22() {
		return this.d22;
	}

	public void setD22(String d22) {
		this.d22 = d22;
	}

	@Column(name = "d23", length = 1)
	public String getD23() {
		return this.d23;
	}

	public void setD23(String d23) {
		this.d23 = d23;
	}

	@Column(name = "d24", length = 1)
	public String getD24() {
		return this.d24;
	}

	public void setD24(String d24) {
		this.d24 = d24;
	}

	@Column(name = "d25", length = 1)
	public String getD25() {
		return this.d25;
	}

	public void setD25(String d25) {
		this.d25 = d25;
	}

	@Column(name = "d26", length = 1)
	public String getD26() {
		return this.d26;
	}

	public void setD26(String d26) {
		this.d26 = d26;
	}

	@Column(name = "d27", length = 1)
	public String getD27() {
		return this.d27;
	}

	public void setD27(String d27) {
		this.d27 = d27;
	}

	@Column(name = "d28", length = 1)
	public String getD28() {
		return this.d28;
	}

	public void setD28(String d28) {
		this.d28 = d28;
	}

	@Column(name = "d29", length = 1)
	public String getD29() {
		return this.d29;
	}

	public void setD29(String d29) {
		this.d29 = d29;
	}

	@Column(name = "d3", length = 1)
	public String getD3() {
		return this.d3;
	}

	public void setD3(String d3) {
		this.d3 = d3;
	}

	@Column(name = "d30", length = 1)
	public String getD30() {
		return this.d30;
	}

	public void setD30(String d30) {
		this.d30 = d30;
	}

	@Column(name = "d31", length = 1)
	public String getD31() {
		return this.d31;
	}

	public void setD31(String d31) {
		this.d31 = d31;
	}

	@Column(name = "d4", length = 1)
	public String getD4() {
		return this.d4;
	}

	public void setD4(String d4) {
		this.d4 = d4;
	}

	@Column(name = "d5", length = 1)
	public String getD5() {
		return this.d5;
	}

	public void setD5(String d5) {
		this.d5 = d5;
	}

	@Column(name = "d6", length = 1)
	public String getD6() {
		return this.d6;
	}

	public void setD6(String d6) {
		this.d6 = d6;
	}

	@Column(name = "d7", length = 1)
	public String getD7() {
		return this.d7;
	}

	public void setD7(String d7) {
		this.d7 = d7;
	}

	@Column(name = "d8", length = 1)
	public String getD8() {
		return this.d8;
	}

	public void setD8(String d8) {
		this.d8 = d8;
	}

	@Column(name = "d9", length = 1)
	public String getD9() {
		return this.d9;
	}

	public void setD9(String d9) {
		this.d9 = d9;
	}

	@Column(name = "months", nullable = false)
	public int getMonths() {
		return this.months;
	}

	public void setMonths(int months) {
		this.months = months;
	}


	@Column(name = "year", nullable = false, length = 4)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
