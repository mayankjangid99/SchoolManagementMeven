package com.school.common.model;

import java.io.Serializable;

import javax.persistence.Column;

public class UniqueIdPK implements Serializable {

	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	/**
	 * Attribute templateName
	 */
	private String templateName;

	/**
	 * Attribute templateId
	 */
	private String templateId;


    /**
     * Instantiates a new UniqueIdPK.
     */
    public UniqueIdPK() {
    }

    /**
     * Instantiates a new UniqueIdPK.
     *
                             * @param templateName
                              * @param templateId
                                                                                                                                                                                                                                                                                                            */
    public UniqueIdPK (String templateName, String templateId) {
						this.templateName = templateName;
						this.templateId = templateId;
																																																											    }

	/**
	 * Return templateName
	 */
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
	 * Return templateId
	 */
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
	 * calculate hashcode
	 */
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	/**
	 * equals method
	*/
	@Override
	public boolean equals(Object object)
	{
		return super.equals(object);
	}

}