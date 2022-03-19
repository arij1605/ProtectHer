package com.esprit.ProtectHer.Service;


import com.esprit.ProtectHer.entity.JackPot;

public interface JackPotServiceIm {

	public JackPot addJackPot(JackPot jackPot);

	void deleteJackPot(Long id);

	JackPot updateJackPot(Long jackPottId, JackPot j);

	public void assignJackPotToUser(Long idJackPot, Long id);

	//void assignEventToJackPot(Long idEvent, Long idJackpot);
	
	void assignJackPotToEvent(Long idJackpot, Long idEvent);
}
