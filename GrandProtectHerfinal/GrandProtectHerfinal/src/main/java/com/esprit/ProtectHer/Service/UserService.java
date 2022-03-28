package com.esprit.ProtectHer.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.esprit.ProtectHer.entity.Certification;
/*import com.esprit.ProtectHer.entity.Certification;*/
import com.esprit.ProtectHer.entity.Role;

import com.esprit.ProtectHer.entity.Training;
import com.esprit.ProtectHer.entity.User;

import com.esprit.ProtectHer.Repository.TrainingRepository;
import com.esprit.ProtectHer.Repository.UserRepository;

import com.esprit.ProtectHer.Repository.CertificationRepository;


@Service
@Slf4j
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TrainingRepository trainingRepository;
	
	@Autowired
	CertificationRepository certificationRepository;

	@Override
	public void addUser(User user) {
		userRepository.save(user);

	}
	
	@Override
	public void addTrainingAndAssignToFormer(Training training, long id) {
		User user = userRepository.findById(id).orElse(null);
		if (user.getRole().equals(Role.FORMER)) {
			Set<Training> trainings = user.getTraining();
			trainings.add(training);
			user.setTraining(trainings);
			userRepository.save(user);
		}

	}
	
	@Override
	@Scheduled(fixedRate = 30000)
		public void getLearnerByTraining() {
		List<Training> trainings = (List<Training>) trainingRepository.findAll();
			for(Training training :trainings)
			{
			    log.info("The training : " + training.getDomaine()  +  "contains"  + training.getUser().size() +  "learners");
			}
    }

	@Override
	public void cancelUser(Long id) {
		userRepository.deleteById(id);	
	}

	@Override
	public User updateUser(User u) {
		User user = retrieveUser(u.getId());
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setAdress(u.getAdress());
		user.setBirthDate(u.getBirthDate());
		user.setEmail(u.getEmail());
		userRepository.save(user);
		return user;
	}

	@Override
	public User retrieveUser(Long id) {
		User user = userRepository.findById(id).orElse(null);
		log.info("user : " + user);
		return user;
	}

	@Override
	public void assignCertificationToUser(Long id, Long idCertification) {
		User user = userRepository.findById(id).orElse(null);
		Certification certification = certificationRepository.findById(idCertification).orElse(null);
		if (user.getRole().equals(Role.LEARNER)) {
			Set<Certification> certifications = user.getCertification();
			certifications.add(certification);
			user.setCertification(certifications);
			userRepository.save(user);
		
	}
}}
