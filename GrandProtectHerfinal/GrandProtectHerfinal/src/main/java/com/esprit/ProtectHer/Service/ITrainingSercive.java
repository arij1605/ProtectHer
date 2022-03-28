package com.esprit.ProtectHer.Service;

import java.util.List;

import com.esprit.ProtectHer.entity.Training;


public interface ITrainingSercive {

	void addLearnerToTraining(long id, long idTraining);

	void cancelTraining(Long idTraining);

	Training updateTraining(Training t);

	Training retrieveTraining(Long idTraining);

	List<Training> retrieveAlltrainings();

	

}
