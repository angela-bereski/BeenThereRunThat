package com.angelabereski.marathonconnection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angelabereski.marathonconnection.models.Support;
import com.angelabereski.marathonconnection.models.TrainingLog;
import com.angelabereski.marathonconnection.repositories.SupportRepository;
import com.angelabereski.marathonconnection.repositories.TrainingLogRepository;

@Service
public class SupportService {

	@Autowired
	private SupportRepository sRepository;

	@Autowired
	private TrainingLogRepository tRepository;

	public Support findSupport(Long id) {
		return sRepository.findById(id).orElse(null);
	}

	public List<Support> allSupports() {
		return sRepository.findAll();
	}

	public Optional<TrainingLog> findTrainingLog(Long id) {
		return tRepository.findById(id);
	}

	public Support createSupport(Support support) {
		return sRepository.save(support);
	}

	public Support updateSupport(Support support) {
		return sRepository.save(support);
	}

	public String destroySupport(Long id) {
		sRepository.deleteById(id);
		return "Deleted Supporting Comment";
	}

	public List<Support> supportsByTrainingLog(Long id) {
		return sRepository.findByTrainingSupport(id);
	}

}
