package com.angelabereski.marathonconnection.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angelabereski.marathonconnection.models.TrainingLog;

@Repository
public interface TrainingLogRepository extends CrudRepository<TrainingLog, Long> {

	List<TrainingLog> findAll();

	Optional<TrainingLog> findById(Long id);

	Optional<TrainingLog> findByName(String name);

}
