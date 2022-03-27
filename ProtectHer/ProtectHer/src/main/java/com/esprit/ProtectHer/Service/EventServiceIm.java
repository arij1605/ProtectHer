package com.esprit.ProtectHer.Service;



import com.esprit.ProtectHer.entity.Event;


public interface EventServiceIm {

	public Event addEvent(Event event);

	Event updateEvent(Long idEvent, Event e);
	public float  stat (Long idEvent) ;
	void deleteEvent(Long id);

	Event retrieveEvent(Long id);

	public void assignAdvertisingToEvent(Long idEvent, Long idAdvertising);}

