package com.angelabereski.marathonconnection.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 2, max = 100, message = "First name must be greater than 2 characters")
	private String firstName;

	@NotBlank
	@Size(min = 2, max = 100, message = "Last name must be greater than 2 characters")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Size(min = 2, max = 100)
	@Email(message = "Please enter a valid email")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 100, message = "Password must be at least 8 characters")
	private String password;

	@Transient
	@NotBlank(message = "Confirm password is required")
	private String confirm;

	@Column(updatable = false)
	@OneToMany(mappedBy = "raceRunner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TrainingLog> runnerLogs;

	@Column(updatable = false)
	@OneToMany(mappedBy = "raceRunnerF", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RunSchedule> runnerFutures;

	@Column(updatable = false)
	@OneToMany(mappedBy = "supportCreator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Support> createdSupport;

	@Column(updatable = false)
	@OneToMany(mappedBy = "mileRequester", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MileDedication> createdMileRequests;

	public User() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<TrainingLog> getRunnerLogs() {
		return runnerLogs;
	}

	public void setRunnerLogs(List<TrainingLog> runnerLogs) {
		this.runnerLogs = runnerLogs;
	}

	public List<Support> getCreatedSupport() {
		return createdSupport;
	}

	public void setCreatedSupport(List<Support> createdSupport) {
		this.createdSupport = createdSupport;
	}

	public List<MileDedication> getCreatedMileRequests() {
		return createdMileRequests;
	}

	public void setCreatedMileRequests(List<MileDedication> createdMileRequests) {
		this.createdMileRequests = createdMileRequests;
	}

	public List<RunSchedule> getRunnerFutures() {
		return runnerFutures;
	}

	public void setRunnerFutures(List<RunSchedule> runnerFutures) {
		this.runnerFutures = runnerFutures;
	}

}