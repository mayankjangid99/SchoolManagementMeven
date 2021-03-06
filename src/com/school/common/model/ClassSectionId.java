package com.school.common.model;

// Generated Jul 5, 2016 5:14:02 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ClassSectionId generated by hbm2java
 */
@Embeddable
public class ClassSectionId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String classCode;
	private String sectionCode;
	private String schoolCode;
	private String sessionCode;

	public ClassSectionId() {
	}

	public ClassSectionId(String classCode, String sectionCode,
			String schoolCode) {
		this.classCode = classCode;
		this.sectionCode = sectionCode;
		this.schoolCode = schoolCode;
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
		return this.sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
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

	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ClassSectionId))
			return false;
		ClassSectionId castOther = (ClassSectionId) other;

		return ((this.getClassCode() == castOther.getClassCode()) || (this
				.getClassCode() != null && castOther.getClassCode() != null && this
				.getClassCode().equals(castOther.getClassCode())))
				&& ((this.getSectionCode() == castOther.getSectionCode()) || (this
						.getSectionCode() != null
						&& castOther.getSectionCode() != null && this
						.getSectionCode().equals(castOther.getSectionCode())))
				&& ((this.getSchoolCode() == castOther.getSchoolCode()) || (this
						.getSchoolCode() != null
						&& castOther.getSchoolCode() != null && this
						.getSchoolCode().equals(castOther.getSchoolCode())));
	}

	

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getClassCode() == null ? 0 : this.getClassCode().hashCode());
		result = 37
				* result
				+ (getSectionCode() == null ? 0 : this.getSectionCode()
						.hashCode());
		result = 37
				* result
				+ (getSchoolCode() == null ? 0 : this.getSchoolCode()
						.hashCode());
		return result;
	}

}
