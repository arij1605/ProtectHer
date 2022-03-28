package tn.esprit.protectHer.service;

import tn.esprit.protectHer.entity.User;

public interface IUserService {
	
	void removeUser(Long userId);
	
	User updateUser(Long userId, User intermediateUser);

	User assignRoleToUser(Long roleId, Long userId);

	User detachRoleFromUser(Long roleId, Long userId);

	String approvePendingUser(Integer verificationCode);

	void accountStatistics();
	
}
