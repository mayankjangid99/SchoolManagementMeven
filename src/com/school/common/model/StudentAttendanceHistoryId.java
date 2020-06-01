package com.school.common.model;

// Generated Jul 5, 2016 5:14:02 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ClassSectionId generated by hbm2java
 */
@Embeddable
public class StudentAttendanceHistoryId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String schoolCode;
	private String sessionCode;
	private String classCode;
	private String sectionCode;
	private int month;

	public StudentAttendanceHistoryId() {
	}

	public StudentAttendanceHistoryId(String schoolCode, String sessionCode, String classCode, String sectionCode, int month) {
		this.schoolCode = schoolCode;
		this.sessionCode = sessionCode;
		this.classCode = classCode;
		this.sectionCode = sectionCode;
		this.month = month;
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

	@Column(name = "month", nullable = false)
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	
}