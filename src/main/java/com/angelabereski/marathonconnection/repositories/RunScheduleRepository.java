package com.angelabereski.marathonconnection.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angelabereski.marathonconnection.models.RunSchedule;

@Repository
public interface RunScheduleRepository extends CrudRepository<RunSchedule, Long> {

	List<RunSchedule> findAll();

	Optional<RunSchedule> findById(Long id);

}
