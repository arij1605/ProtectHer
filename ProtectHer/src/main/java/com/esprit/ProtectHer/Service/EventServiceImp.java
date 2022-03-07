package com.esprit.ProtectHer.Service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.ProtectHer.Repository.AdvertisingRepository;
import com.esprit.ProtectHer.Repository.EventRepository;
import com.esprit.ProtectHer.entity.Advertising;
import com.esprit.ProtectHer.entity.Event;


import lombok.extern.slf4j.Slf4j;







@Service
@Slf4j
public class EventServiceImp implements EventServiceIm {
	@Autowired
	EventRepository eventReposository;
	@Autowired
	AdvertisingRepository advertisingRepository;
	
	@Override
	public Event addEvent(Event event) {
		eventReposository.save(event);
		return event;
	}
	
	
	@Override
	public void deleteEvent(Long id) {
		eventReposository.deleteById(id);
	}


	@Override
	public Event updateEvent(Long idEvent, Event e) {
		Event event = retrieveEvent(idEvent);
		if(event != null) {
			event.setEventDate(e.getEventDate());
			event.setConcept(e.getConcept());
			event.setDonationtype(e.getDonationtype());
			eventReposository.save(event);
		}
		return event;
	}
	
	@Override
	public Event retrieveEvent(Long id) {
		Event event = eventReposository.findById(id).orElse(null);
		log.info("event : " + event);
		return event;
	}


	//@Transactional
	public void assignAdvertisingToEvent(Long idAdvertising, Long idEvent) {
		Event event = eventReposository.findById(idEvent).orElse(null);
		Advertising advertising = advertisingRepository.findById(idAdvertising).orElse(null);
		List<Advertising> advertisings = event.getAdvertisings();
		advertisings.add(advertising);
		event.setAdvertisings(advertisings);
		eventReposository.save(event);
	}
	

	
	
}
