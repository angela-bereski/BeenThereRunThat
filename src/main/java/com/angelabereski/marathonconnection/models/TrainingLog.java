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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "traininglogs")
public class TrainingLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 2, max = 100, message = "Name must be greater than 2 characters")
	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfRun;

	@NotBlank
	@Size(min = 2, max = 100, message = "Location must be greater than 2 characters")
	private String location;

	@Min(value = 0, message = "Mileage must be greater than 0 miles")
	private Double numMiles;

	@NotBlank
	@Size(min = 0, max = 100)
	private String ranWith;

	@NotNull
	@Size(min = 5, max = 250, message = "Reflection must not be blank.")
	private String runReflection;

	@NotNull
	@Size(min = 5, max = 250, message = "Nutrition must not be blank.")
	private String runNutrition;

	private String imagePath = "";

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User raceRunner;

	@Column(updatable = false)
	@OneToMany(mappedBy = "trainingSupport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Support> trainingSupports;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "logs_supports", joinColumns = @JoinColumn(name = "training_id"), inverseJoinColumns = @JoinColumn(name = "support_id"))
	private List<Support> supports;

	public TrainingLog() {

	}

	public TrainingLog(String name, Date dateOfRun, String location, Double numMiles, String ranWith,
			String runReflection, String runNutrition, User raceRunner) {
		this.name = name;
		this.dateOfRun = dateOfRun;
		this.location = location;
		this.numMiles = numMiles;
		this.ranWith = ranWith;
		this.runReflection = runReflection;
		this.runNutrition = runNutrition;
		this.raceRunner = raceRunner;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfRun() {
		return dateOfRun;
	}

	public void setDateOfRun(Date dateOfRun) {
		this.dateOfRun = dateOfRun;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getNumMiles() {
		return numMiles;
	}

	public void setNumMiles(Double numMiles) {
		this.numMiles = numMiles;
	}

	public String getRanWith() {
		return ranWith;
	}

	public void setRanWith(String ranWith) {
		this.ranWith = ranWith;
	}

	public String getRunReflection() {
		return runReflection;
	}

	public void setRunReflection(String runReflection) {
		this.runReflection = runReflection;
	}

	public String getRunNutrition() {
		return runNutrition;
	}

	public void setRunNutrition(String runNutrition) {
		this.runNutrition = runNutrition;
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

	public User getRaceRunner() {
		return raceRunner;
	}

	public void setRaceRunner(User raceRunner) {
		this.raceRunner = raceRunner;
	}

	public List<Support> getTrainingSupports() {
		return trainingSupports;
	}

	public void setTrainingSupports(List<Support> trainingSupports) {
		this.trainingSupports = trainingSupports;
	}

	public List<Support> getSupports() {
		return supports;
	}

	public void setSupports(List<Support> supports) {
		this.supports = supports;
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
