package com.angelabereski.marathonconnection.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angelabereski.marathonconnection.models.Support;

@Repository
public interface SupportRepository extends CrudRepository<Support, Long> {

	List<Support> findAll();

	Optional<Support> findById(Long id);

	List<Support> findByTrainingSupport(Long id);

}
