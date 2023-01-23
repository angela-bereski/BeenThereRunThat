package com.angelabereski.marathonconnection.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angelabereski.marathonconnection.models.TrainingLog;
import com.angelabereski.marathonconnection.repositories.TrainingLogRepository;

@Service
public class TrainingLogService {

	@Autowired
	private TrainingLogRepository tRepository;

	public TrainingLog findTrainingLog(Long id) {
		return tRepository.findById(id).orElse(null);
	}

	public List<TrainingLog> allTrainingLogs() {
		return tRepository.findAll();
	}

	public TrainingLog createTrainingLog(TrainingLog trainingLog) {
		return tRepository.save(trainingLog);
	}

	public TrainingLog updateTrainingLog(TrainingLog trainingLog) {
		return tRepository.save(trainingLog);
	}

	public String destroyTrainingLog(Long id) {
		tRepository.deleteById(id);
		return "Deleted TrainingLog";
	}

}
