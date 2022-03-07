package com.esprit.ProtectHer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.ProtectHer.Repository.AdvertisingRepository;
import com.esprit.ProtectHer.Repository.UserRepository;
import com.esprit.ProtectHer.entity.Advertising;
import com.esprit.ProtectHer.entity.User;

import lombok.extern.slf4j.Slf4j;







@Service
@Slf4j
public class AdvertisingServiceImp implements AdvertisingServiceIm {
	@Autowired
	AdvertisingRepository advertisingReposository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Advertising addAdvertising(Advertising advertising) {
		
		advertisingReposository.save(advertising);
		return advertising;
	}
	
	
	@Override
	public void deleteAdvertising(Long id) {
		advertisingReposository.deleteById(id);
	}



	@Override
	public Advertising updateAdvertising(Long idAdvertising, Advertising a) {
		Advertising advertising = retrieveAdvertising(idAdvertising);
		advertising.setAdvertisingDate(a.getAdvertisingDate());
		advertising.setDescription(a.getDescription());
		advertisingReposository.save(advertising);
		return advertising;
	
	}
	
	@Override
	public Advertising retrieveAdvertising(Long id) {
		Advertising advertising = advertisingReposository.findById(id).orElse(null);
		log.info("advertising : " + advertising);
		return advertising;
	}
	
	@Override
	public void assignUserToAdvertising(Long idAdvertising, Long id) {
		Advertising advertising = advertisingReposository.findById(idAdvertising).orElse(null);
		User user = userRepository.findById(id).orElse(null);
		advertising.setUsers(user);
		advertisingReposository.save(advertising);
	}
	
	
}
