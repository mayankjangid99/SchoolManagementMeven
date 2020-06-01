package com.school.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transport")
public class TransportModel implements Serializable, EntityModel{

	private static final long serialVersionUID = 1L;

	private Long transportId;
	private String route;
	private String pickUpStop;
	private String pickUpTime;
	private String vehicle;
	private String dropStop;
	private String dropTime;
	private BigDecimal transportFee;
	private String type;
	private String status;
	private String modifiedBy;
	private Date modifiedOn;
	private String createdBy;
	private Date createdOn;
	private SchoolProfileModel schoolProfile;

    public TransportModel() {
    }
    
    public TransportModel (Long transportId) {
	        this.transportId = transportId;
	}


	
	/**
	 * @return transportId
	 */
    @Id
	@Column(name = "transport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
		public Long getTransportId() {
		return transportId;
	}

	/**
	 * @param transportId new value for transportId
	 */
	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}
	
	
	/**
	 * @return the route
	 */
	@Column(name = "route", nullable = false, length = 50)
	public String getRoute() {
		return route;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(String route) {
		this.route = route;
	}

	/**
	 * @return the pickUpStop
	 */
	@Column(name = "pick_up_stop", nullable = false, length = 50)
	public String getPickUpStop() {
		return pickUpStop;
	}

	/**
	 * @param pickUpStop the pickUpStop to set
	 */
	public void setPickUpStop(String pickUpStop) {
		this.pickUpStop = pickUpStop;
	}

	/**
	 * @return the pickUpTime
	 */
	@Column(name = "pick_up_time", nullable = false, length = 10)
	public String getPickUpTime() {
		return pickUpTime;
	}

	/**
	 * @param pickUpTime the pickUpTime to set
	 */
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	/**
	 * @return the vehicle
	 */
	@Column(name = "vehicle", nullable = false, length = 20)
	public String getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * @return the dropStop
	 */
	@Column(name = "drop_stop", nullable = false, length = 50)
	public String getDropStop() {
		return dropStop;
	}

	/**
	 * @param dropStop the dropStop to set
	 */
	public void setDropStop(String dropStop) {
		this.dropStop = dropStop;
	}

	/**
	 * @return the dropTime
	 */
	@Column(name = "drop_time", nullable = false, length = 10)
	public String getDropTime() {
		return dropTime;
	}

	/**
	 * @param dropTime the dropTime to set
	 */
	public void setDropTime(String dropTime) {
		this.dropTime = dropTime;
	}

	/**
	 * @return the transportFee
	 */
	@Column(name = "transport_fee", nullable = false, precision = 7, scale = 2)
	public BigDecimal getTransportFee() {
		return transportFee;
	}

	/**
	 * @param transportFee the transportFee to set
	 */
	public void setTransportFee(BigDecimal transportFee) {
		this.transportFee = transportFee;
	}

		
	/**
	 * @return the type
	 */
	@Column(name = "type", nullable = false, length = 2)
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	@Column(name = "status", nullable = false, length = 1)
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}	

	/**
	 * @return createdBy
	 */
	@Column(name = "created_by", nullable = false, length = 50)
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", columnDefinition = "DATETIME", nullable = false)
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
	 * @return the modifiedBy
	 */
	@Column(name = "modified_by", length = 50)
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedOn
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_on", columnDefinition = "DATETIME")
	public Date getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn the modifiedOn to set
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_code", nullable = false)
    public SchoolProfileModel getSchoolProfile() {
		return schoolProfile;
	}

	public void setSchoolProfile(SchoolProfileModel schoolProfile) {
		this.schoolProfile = schoolProfile;
	}

}