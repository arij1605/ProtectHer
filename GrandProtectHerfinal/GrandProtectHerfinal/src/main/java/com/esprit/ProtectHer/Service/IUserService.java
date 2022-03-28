package com.esprit.ProtectHer.Service;

import com.esprit.ProtectHer.entity.Training;
import com.esprit.ProtectHer.entity.User;

public interface IUserService {

	void addUser(User user);

	void addTrainingAndAssignToFormer(Training training, long id);

	void getLearnerByTraining();

	void cancelUser(Long id);

	User updateUser(User u);

	User retrieveUser(Long userId);

	void assignCertificationToUser(Long id, Long idCertification);

}
