package com.angelabereski.marathonconnection.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "milededications")
public class MileDedication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Min(value = 0, message = "Mile marker must be greater than 0 miles")
	private Integer whichMile;

	@NotBlank
	@Size(min = 2, max = 250, message = "Supporting Comment must be greater than 2 characters")
	private String dedicationRequest;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "runSchedule_id")
	private RunSchedule runScheduleMileDedication;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User mileRequester;

	public MileDedication() {

	}

	public MileDedication(Integer whichMile, String dedicationRequest, User mileRequester) {

		this.whichMile = whichMile;
		this.dedicationRequest = dedicationRequest;
		this.mileRequester = mileRequester;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getWhichMile() {
		return whichMile;
	}

	public void setWhichMile(Integer whichMile) {
		this.whichMile = whichMile;
	}

	public String getDedicationRequest() {
		return dedicationRequest;
	}

	public void setDedicationRequest(String dedicationRequest) {
		this.dedicationRequest = dedicationRequest;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public RunSchedule getRunScheduleMileDedication() {
		return runScheduleMileDedication;
	}

	public void setRunScheduleMileDedication(RunSchedule runScheduleMileDedication) {
		this.runScheduleMileDedication = runScheduleMileDedication;
	}

	public User getMileRequester() {
		return mileRequester;
	}

	public void setMileRequester(User mileRequester) {
		this.mileRequester = mileRequester;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}
