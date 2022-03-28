package com.esprit.ProtectHer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.esprit.ProtectHer.entity.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {
	List<Training>  findByEtat(Boolean etat);
	

}
