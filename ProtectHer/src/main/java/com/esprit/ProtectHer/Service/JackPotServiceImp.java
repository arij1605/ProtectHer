package com.esprit.ProtectHer.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.ProtectHer.Repository.JackPotRepository;
import com.esprit.ProtectHer.Repository.UserRepository;
import com.esprit.ProtectHer.entity.DonationType;
import com.esprit.ProtectHer.entity.JackPot;
import com.esprit.ProtectHer.entity.User;



@Service
public class JackPotServiceImp implements JackPotServiceIm {
	@Autowired
	JackPotRepository jackPotReposository;
	@Autowired
	UserRepository userRepository;

	
	@Override
	public JackPot addJackPot(JackPot jackPot) {
		jackPotReposository.save(jackPot);
		return jackPot;
	}
	
	@Override
	public void deleteJackPot(Long id) {
		jackPotReposository.deleteById(id);
	}
	
	@Override
	public JackPot updateJackPot(Long idJackPot, JackPot j) { 
		JackPot jackPot = jackPotReposository.findById(idJackPot).orElse(null);
		if (jackPot.getDonationtype().equals(DonationType.MONEY))
		jackPot.setSomme(jackPot.getSomme()  + j.getSomme());
		
		jackPotReposository.save(jackPot);
		return jackPot;
	}

	@Override
	public void assignJackPotToUser(Long idJackPot, Long id) {
		JackPot jackPot = jackPotReposository.findById(idJackPot).orElse(null);
		User user = userRepository.findById(id).orElse(null);
		List<JackPot> jackPots = user.getJackpots();
		jackPots.add(jackPot);
		user.setJackpots(jackPots);
	    userRepository.save(user);
	}

	
	
}
