package com.angelabereski.marathonconnection.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.angelabereski.marathonconnection.models.MileDedication;

public interface MileDedicationRepository extends CrudRepository<MileDedication, Long> {

	List<MileDedication> findAll();

	Optional<MileDedication> findById(Long id);

	List<MileDedication> findByRunScheduleMileDedicationId(Long id);

}
