package com.angelabereski.marathonconnection.models;

import java.util.Date;
import java.util.List;

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "supports")
public class Support {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 2, max = 250, message = "Supporting Comment must be greater than 2 characters")
	private String supportWords;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "logs_supports", joinColumns = @JoinColumn(name = "support_id"), inverseJoinColumns = @JoinColumn(name = "training_id"))
	private List<TrainingLog> logs;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "training_id")
	private TrainingLog trainingSupport;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User supportCreator;

	public Support() {

	}

	public Support(String supportWords, User supportCreator) {

		this.supportWords = supportWords;
		this.supportCreator = supportCreator;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupportWords() {
		return supportWords;
	}

	public void setSupportWords(String supportWords) {
		this.supportWords = supportWords;
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

	public TrainingLog getTrainingSupport() {
		return trainingSupport;
	}

	public void setTrainingSupport(TrainingLog trainingSupport) {
		this.trainingSupport = trainingSupport;
	}

	public User getSupportCreator() {
		return supportCreator;
	}

	public void setSupportCreator(User supportCreator) {
		this.supportCreator = supportCreator;
	}

	public List<TrainingLog> getLogs() {
		return logs;
	}

	public void setLogs(List<TrainingLog> logs) {
		this.logs = logs;
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
