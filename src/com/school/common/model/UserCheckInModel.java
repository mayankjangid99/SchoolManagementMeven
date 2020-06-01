package com.school.common.model;

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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_check_in", uniqueConstraints = @UniqueConstraint(columnNames = {
		"date", "user_id" }))
public class UserCheckInModel 
{
	private long userCheckInId;
	private Date date;
	private Date checkIn;
	private Date checkOut;
	private String status;
	private SessionDetailsModel sessionDetails;
	private UserModel user;
	
	public UserCheckInModel(){}
	
	public UserCheckInModel(long userCheckInId, Date date, Date checkIn,
			Date checkOut, String status, SessionDetailsModel sessionDetails, UserModel user) {
		super();
		this.userCheckInId = userCheckInId;
		this.date = date;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.status = status;
		this.sessionDetails = sessionDetails;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_check_in_id", unique = true, nullable = false)
	public long getUserCheckInId() {
		return userCheckInId;
	}

	public void setUserCheckInId(long userCheckInId) {
		this.userCheckInId = userCheckInId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "check_in", nullable = false)
	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "check_out")
	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	@Column(name = "status", nullable = false, length = 1)	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_session", nullable = false)
	public SessionDetailsModel getSessionDetails() {
		return sessionDetails;
	}

	public void setSessionDetails(SessionDetailsModel sessionDetails) {
		this.sessionDetails = sessionDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
	

	@Override
	public String toString() {
		return "UserCheckInModel [userAttendanceId=" + userCheckInId
				+ ", date=" + date + ", checkIn=" + checkIn + ", checkOut="
				+ checkOut + ", schoolSession=" + sessionDetails + ", user="
				+ user + "]";
	}
	
	
	
}
