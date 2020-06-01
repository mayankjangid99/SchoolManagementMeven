package com.school.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass (UniqueIdPK.class)
@Table(name = "UNIQUE_ID")
public class UniqueIdModel implements Serializable, EntityModel{

	private static final long serialVersionUID = 1L;

	private String templateName;
	private String templateId;
	private Long idWidth;
	private Long lastId;
	private String createdBy;
	private Date createdOn;
	private String deletedFlag;
	private String defaultFlag;
	private SchoolProfileModel schoolProfile;

    public UniqueIdModel() {
    }
    
    public UniqueIdModel (String templateName, String templateId, Long idWidth, Long lastId, String createdBy, Date createdOn, String deletedFlag, String defaultFlag,
    		SchoolProfileModel schoolProfile) {
	        this.templateName = templateName;
	        this.templateId = templateId;
	        this.idWidth = idWidth;
	        this.lastId = lastId;
	        this.createdBy = createdBy;
	        this.createdOn = createdOn;
	        this.deletedFlag = deletedFlag;
	        this.defaultFlag = defaultFlag;
	        this.schoolProfile = schoolProfile;
	    }


	/**
	 * @return templateName
	 */
	@Id
	@Column(name = "template_name")
		public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName new value for templateName
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}


	/**
	 * @return templateId
	 */
	@Id
	@Column(name = "template_id")
		public String getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId new value for templateId
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}


	/**
	 * @return idWidth
	 */
	@Column(name = "id_width")
		public Long getIdWidth() {
		return idWidth;
	}

	/**
	 * @param idWidth new value for idWidth
	 */
	public void setIdWidth(Long idWidth) {
		this.idWidth = idWidth;
	}


	/**
	 * @return lastId
	 */
	@Column(name = "last_id")
		public Long getLastId() {
		return lastId;
	}

	/**
	 * @param lastId new value for lastId
	 */
	public void setLastId(Long lastId) {
		this.lastId = lastId;
	}


	/**
	 * @return createdBy
	 */
	@Column(name = "created_by")
		public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy new value for createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	/**
	 * @return createdOn
	 */
	@Column(name = "created_on")
		public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn new value for createdOn
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	/**
	 * @return deletedFlag
	 */
	@Column(name = "deleted_flag")
		public String getDeletedFlag() {
		return deletedFlag;
	}

	/**
	 * @param deletedFlag new value for deletedFlag
	 */
	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}


	/**
	 * @return defaultFlag
	 */
	@Column(name = "default_flag")
		public String getDefaultFlag() {
		return defaultFlag;
	}

	/**
	 * @param defaultFlag new value for defaultFlag
	 */
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_code", nullable = false)
    public SchoolProfileModel getSchoolProfile() {
		return schoolProfile;
	}

	public void setSchoolProfile(SchoolProfileModel schoolProfile) {
		this.schoolProfile = schoolProfile;
	}

	/**
     * Returns a string representation of the value object.
     *
     * @return the string
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("templateName: " + templateName);
	        stringBuffer.append("templateId: " + templateId);
	        stringBuffer.append("idWidth: " + idWidth);
	        stringBuffer.append("lastId: " + lastId);
	        stringBuffer.append("createdBy: " + createdBy);
	        stringBuffer.append("createdOn: " + createdOn);
	        stringBuffer.append("deletedFlag: " + deletedFlag);
	        stringBuffer.append("defaultFlag: " + defaultFlag);
	        stringBuffer.append("schoolProfile: " + schoolProfile);
	        return stringBuffer.toString();
    }
}