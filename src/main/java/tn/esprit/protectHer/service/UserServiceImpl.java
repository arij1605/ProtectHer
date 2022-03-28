package tn.esprit.protectHer.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.protectHer.entity.GenderType;
import tn.esprit.protectHer.entity.Role;
import tn.esprit.protectHer.entity.User;
import tn.esprit.protectHer.repository.RoleRepository;
import tn.esprit.protectHer.repository.UserRepository;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void removeUser(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		if(user != null) userRepository.deleteById(userId);
	}
	
	@Override
	public User updateUser(Long userId, User intermediateUser) {
		User user = userRepository.findById(userId).orElse(null);
		user.setAddress(Optional.ofNullable(intermediateUser.getAddress()).orElse(user.getAddress()));
		user.setBirthday(Optional.ofNullable(intermediateUser.getBirthday()).orElse(user.getBirthday()));
		user.setEmail(Optional.ofNullable(intermediateUser.getEmail()).orElse(user.getEmail()));
		user.setFirstName(Optional.ofNullable(intermediateUser.getFirstName()).orElse(user.getFirstName()));
		user.setGender(Optional.ofNullable(intermediateUser.getGender()).orElse(user.getGender()));
		user.setLastName(Optional.ofNullable(intermediateUser.getLastName()).orElse(user.getLastName()));
		user.setPassword(Optional.ofNullable(intermediateUser.getPassword()).orElse(user.getPassword()));
		user.setUserName(Optional.ofNullable(intermediateUser.getUserName()).orElse(user.getUserName()));
		return userRepository.save(user);
	}
	
	@Override
	public User assignRoleToUser(Long roleId, Long userId) {
		Role role = roleRepository.findById(roleId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		Set<Role> roles = user.getRoles();
		roles.add(role);
		user.setRoles(roles);
		return userRepository.save(user);
	}
	
	@Override
	public User detachRoleFromUser(Long roleId, Long userId) {
		Role role = roleRepository.findById(roleId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		Set<Role> roles = user.getRoles();
		roles.remove(role);
		user.setRoles(roles);
		return userRepository.save(user);
		
	}
	
	@Override
	public String approvePendingUser(Integer verificationCode) {
		User user = userRepository.findByVerificationCode(verificationCode);
		if (user != null && !user.getActive()) {
			user.setActive(true);
			user.setVerificationCode(null);
			userRepository.save(user);
			return "User approved successfully.";
		}
		return "No correspondance.";
	}
	
	@Override
	@Scheduled(fixedRate = 3600000)
	public void accountStatistics() {
		Integer False = userRepository.findByActive(false).size();
		Integer True = userRepository.findByActive(true).size();
		Integer Male = userRepository.findByGender(GenderType.MALE).size();
		Integer Female = userRepository.findByGender(GenderType.FEMALE).size();
		Integer Users = ((List<User>) userRepository.findAll()).size();
		log.info("We have detected " + (float) False*100/Users + "% of accounts showed as Pending and " + (float) True*100/Users + "% of accounts showed as Active.");
		log.info("We have detected " + (float) Male*100/Users + "% of accounts refer to Men and " + (float) Female*100/Users + "% of accounts refer to Women.");

	}

}
