package com.school.common.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subject_details")
public class SubjectDetailsModel  implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String subjectCode;
	private String subjectName;
	
	private Set<ClassSubjectModel> classSubject = new HashSet<ClassSubjectModel>(0);
	
	
	public SubjectDetailsModel()
	{
		
	}
	
	public SubjectDetailsModel(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public SubjectDetailsModel(String subjectCode, String subjectName,
			Set<ClassSubjectModel> classSubject) {
		super();
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.classSubject = classSubject;
	}
	@Id
	@Column(name = "subject_code", nullable = false, unique = true, length = 5)
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	@Column(name = "subject_name", nullable = false, unique = true, length = 50)
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subjectDetails", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<ClassSubjectModel> getClassSubject() {
		return classSubject;
	}

	public void setClassSubject(Set<ClassSubjectModel> classSubject) {
		this.classSubject = classSubject;
	}
	
	
}
