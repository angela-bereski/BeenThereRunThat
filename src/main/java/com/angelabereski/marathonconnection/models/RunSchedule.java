package com.angelabereski.marathonconnection.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "runschedules")
public class RunSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfRun;

	@Min(value = 0, message = "Mileage must be greater than 0 miles")
	private Double numMiles;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User raceRunnerF;

	@Column(updatable = false)
	@OneToMany(mappedBy = "runScheduleMileDedication", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MileDedication> dedications;

	public RunSchedule() {

	}

	public RunSchedule(Date dateOfRun, Double numMiles, User raceRunnerF) {
		this.dateOfRun = dateOfRun;
		this.numMiles = numMiles;
		this.raceRunnerF = raceRunnerF;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateOfRun() {
		return dateOfRun;
	}

	public void setDateOfRun(Date dateOfRun) {
		this.dateOfRun = dateOfRun;
	}

	public Double getNumMiles() {
		return numMiles;
	}

	public void setNumMiles(Double numMiles) {
		this.numMiles = numMiles;
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

	public User getRaceRunnerF() {
		return raceRunnerF;
	}

	public void setRaceRunnerF(User raceRunnerF) {
		this.raceRunnerF = raceRunnerF;
	}

	public List<MileDedication> getDedications() {
		return dedications;
	}

	public void setDedications(List<MileDedication> dedications) {
		this.dedications = dedications;
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
