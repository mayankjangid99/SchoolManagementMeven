package com.school.common.model;

// Generated Jul 5, 2016 5:14:02 PM by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * ClassSection generated by hbm2java
 */
@Entity
@Table(name = "class_section")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "classSection")
public class ClassSectionModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private ClassSectionId id;
	private ClassInformationModel classInformation;
	private SchoolProfileModel schoolProfile;
	private SectionInformationModel sectionInformation;
	private SessionDetailsModel sessionDetails;

	public ClassSectionModel() {
	}

	public ClassSectionModel(ClassSectionId id, ClassInformationModel classInformation,
			SchoolProfileModel schoolProfile, SectionInformationModel sectionInformation,
			SessionDetailsModel sessionDetails) {
		this.id = id;
		this.classInformation = classInformation;
		this.schoolProfile = schoolProfile;
		this.sectionInformation = sectionInformation;
		this.sessionDetails = sessionDetails;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "classCode", column = @Column(name = "class_code", nullable = false, length = 5)),
			@AttributeOverride(name = "sectionCode", column = @Column(name = "section_code", nullable = false, length = 5)),
			@AttributeOverride(name = "schoolCode", column = @Column(name = "school_code", nullable = false, length = 5)),
			@AttributeOverride(name = "sessionCode", column = @Column(name = "session_code", nullable = false, length = 10))})
	public ClassSectionId getId() {
		return this.id;
	}

	public void setId(ClassSectionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_code", nullable = false, insertable = false, updatable = false)
	public ClassInformationModel getClassInformation() {
		return this.classInformation;
	}

	public void setClassInformation(ClassInformationModel classInformation) {
		this.classInformation = classInformation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_code", nullable = false, insertable = false, updatable = false)
	public SchoolProfileModel getSchoolProfile() {
		return this.schoolProfile;
	}

	public void setSchoolProfile(SchoolProfileModel schoolProfile) {
		this.schoolProfile = schoolProfile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_code", nullable = false, insertable = false, updatable = false)
	public SectionInformationModel getSectionInformation() {
		return this.sectionInformation;
	}

	public void setSectionInformation(SectionInformationModel sectionInformation) {
		this.sectionInformation = sectionInformation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_code", nullable = false, insertable = false, updatable = false)
	public SessionDetailsModel getSessionDetails() {
		return sessionDetails;
	}

	public void setSessionDetails(SessionDetailsModel sessionDetails) {
		this.sessionDetails = sessionDetails;
	}

	
}
