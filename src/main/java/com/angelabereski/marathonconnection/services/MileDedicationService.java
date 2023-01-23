package com.angelabereski.marathonconnection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angelabereski.marathonconnection.models.MileDedication;
import com.angelabereski.marathonconnection.models.RunSchedule;
import com.angelabereski.marathonconnection.repositories.MileDedicationRepository;
import com.angelabereski.marathonconnection.repositories.RunScheduleRepository;

@Service
public class MileDedicationService {

	@Autowired
	private MileDedicationRepository mRepository;

	@Autowired
	private RunScheduleRepository rRepository;

	public MileDedication findMileDedication(Long id) {
		return mRepository.findById(id).orElse(null);
	}

	public List<MileDedication> allMileDedications() {
		return mRepository.findAll();
	}

	public Optional<RunSchedule> findRunSchedule(Long id) {
		return rRepository.findById(id);
	}

	public MileDedication createMileDedication(MileDedication mileDedication) {
		return mRepository.save(mileDedication);
	}

	public MileDedication updateMileDedication(MileDedication mileDedication) {
		return mRepository.save(mileDedication);
	}

	public String destroyMileDedication(Long id) {
		mRepository.deleteById(id);
		return "Deleted Mile Dedication";
	}

	public List<MileDedication> mileDedicationsByRunSchedule(Long id) {
		return mRepository.findByRunScheduleMileDedicationId(id);
	}

}
