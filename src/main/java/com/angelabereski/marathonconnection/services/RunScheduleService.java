package com.angelabereski.marathonconnection.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angelabereski.marathonconnection.models.RunSchedule;
import com.angelabereski.marathonconnection.repositories.RunScheduleRepository;

@Service
public class RunScheduleService {

	@Autowired
	private RunScheduleRepository rRepository;

	public RunSchedule findRunSchedule(Long id) {
		return rRepository.findById(id).orElse(null);
	}

	public List<RunSchedule> allRunSchedules() {
		return rRepository.findAll();
	}

	public RunSchedule createRunSchedule(RunSchedule runSchedule) {
		return rRepository.save(runSchedule);
	}

	public RunSchedule updateRunSchedule(RunSchedule runSchedule) {
		return rRepository.save(runSchedule);
	}

	public String destroyRunSchedule(Long id) {
		rRepository.deleteById(id);
		return "Deleted Run Schedule";
	}

}
