package com.esprit.ProtectHer.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.ProtectHer.Repository.EventRepository;
import com.esprit.ProtectHer.Repository.JackPotRepository;
import com.esprit.ProtectHer.Repository.UserRepository;
import com.esprit.ProtectHer.entity.DonationType;
import com.esprit.ProtectHer.entity.Event;
import com.esprit.ProtectHer.entity.JackPot;




@Service
public class JackPotServiceImp implements JackPotServiceIm {
	@Autowired
	JackPotRepository jackPotRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EventRepository eventRepository;

	
	@Override
	public JackPot addJackPot(JackPot jackPot) {
		jackPotRepository.save(jackPot);
		return jackPot;
	}
	
	@Override
	public void deleteJackPot(Long id) {
		jackPotRepository.deleteById(id);
	}
	
	@Override
	public JackPot updateJackPot(Long idJackPot, JackPot j) { 
		JackPot jackPot = jackPotRepository.findById(idJackPot).orElse(null);
		if (jackPot.getDonationtype().equals(DonationType.MONEY))
		jackPot.setSomme(jackPot.getSomme()  + j.getSomme());
		
		jackPotRepository.save(jackPot);
		return jackPot;
	}
	///////aminn shwf lehne 

	/*@Override
	public void assignJackPotToUser(Long idJackPot, Long id) {
		JackPot jackPot = jackPotRepository.findById(idJackPot).orElse(null);
		User user = userRepository.findById(id).orElse(null);
		List<JackPot> jackPots = user.getJackpots();
		jackPots.add(jackPot);
		user.setJackpots(jackPots);
	    userRepository.save(user);
	}
*/
	/*@Override
	public void assignEventToJackPot(Long idEvent, Long idJackpot) {
		JackPot jackPot = jackPotRepository.findById(idJackpot).orElse(null);
		Event event = eventRepository.findById(idEvent).orElse(null);
		List<Event> events = jackPot.getEvents();
		events.add(event);
		jackPot.setEvents(events);
	    jackPotRepository.save(jackPot);
	}*/
		
	@Override
	public void assignJackPotToEvent(Long idJackPot, Long idEvent) {
		Event event = eventRepository.findById(idEvent).orElse(null);
		JackPot jackPot = jackPotRepository.findById(idJackPot).orElse(null);
		List<JackPot> jackPots = event.getJackpots();
		jackPots.add(jackPot);
		event.setJackpots(jackPots);
		eventRepository.save(event);
	}

	@Override
	public void assignJackPotToUser(Long idJackPot, Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
