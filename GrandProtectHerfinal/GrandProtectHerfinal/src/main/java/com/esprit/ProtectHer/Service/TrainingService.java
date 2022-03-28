package com.esprit.ProtectHer.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.esprit.ProtectHer.entity.Role;

import com.esprit.ProtectHer.entity.Training;
import com.esprit.ProtectHer.entity.User;

import com.esprit.ProtectHer.Repository.TrainingRepository;
import com.esprit.ProtectHer.Repository.UserRepository;

@Service
@Slf4j
public class TrainingService implements ITrainingSercive {
	@Autowired
	TrainingRepository trainingRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
     public void addLearnerToTraining(long id, long idTraining) {	
			Training training = trainingRepository.findById(idTraining).orElse(null);
			User user = userRepository.findById(id).orElse(null);
			if(user.getRole().equals(Role.LEARNER)) {
				Set<User> users = training.getUser();
			if((training.getNbMaxParticipant() > users.size())) {
				users.add(user);
			training.setUser(users);
			trainingRepository.save(training);
			
		}
	}

	}

	@Override
	public void cancelTraining(Long idTraining) {
		trainingRepository.deleteById(idTraining);
		
	}

	@Override
	public Training updateTraining(Training t) {
		Training training = retrieveTraining(t.getIdTraining());
		training.setDateDebut(t.getDateDebut());
		training.setDateFin(t.getDateFin());
		training.setDomaine(t.getDomaine());
		training.setNbHeure(t.getNbHeure());
		training.setNbMaxParticipant(t.getNbMaxParticipant());
		training.setFrais(t.getFrais());
		training.setRaiting(t.getRaiting());
		training.setEtat(t.getEtat());
		trainingRepository.save(training);
		return training;
	}

	@Override
	public Training retrieveTraining(Long idTraining) {
		Training training = trainingRepository.findById(idTraining).orElse(null);
		log.info("training : " + training);
		return training;
	}

	@Override
	public List<Training> retrieveAlltrainings() {
		List<Training> trainings = (List<Training>) trainingRepository.findAll();
		for (Training training : trainings) {
			log.info("training: " + training);
		}
		return trainings;
	}
	}
