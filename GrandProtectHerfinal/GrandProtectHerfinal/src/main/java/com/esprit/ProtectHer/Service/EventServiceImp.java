package com.esprit.ProtectHer.Service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.esprit.ProtectHer.Repository.AdvertisingRepository;
import com.esprit.ProtectHer.Repository.EventRepository;
import com.esprit.ProtectHer.entity.Advertising;
import com.esprit.ProtectHer.entity.Event;
import com.esprit.ProtectHer.entity.PagingHeaders;
import com.esprit.ProtectHer.entity.PagingResponse;


import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;



@Service
@Slf4j
public class EventServiceImp implements EventServiceIm {
	@Autowired
	EventRepository eventRepository;
	@Autowired
	AdvertisingRepository advertisingRepository;
	
	@Override
	public Event addEvent(Event event) {
		eventRepository.save(event);
		return event;
	}
	
	
	@Override
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}


	@Override
	public Event updateEvent(Long idEvent, Event e) {
		Event event = retrieveEvent(idEvent);
		if(event != null) {
			event.setEventDate(e.getEventDate());
			event.setConcept(e.getConcept());
			event.setDonationtype(e.getDonationtype());
			eventRepository.save(event);
		}
		return event;
	}
	
	@Override
	public Event retrieveEvent(Long id) {
		Event event = eventRepository.findById(id).orElse(null);
		log.info("event : " + event);
		return event;
	}


	//@Transactional
	public void assignAdvertisingToEvent(Long idAdvertising, Long idEvent) {
		Event event = eventRepository.findById(idEvent).orElse(null);
		Advertising advertising = advertisingRepository.findById(idAdvertising).orElse(null);
		List<Advertising> advertisings = event.getAdvertisings();
		advertisings.add(advertising);
		event.setAdvertisings(advertisings);
		eventRepository.save(event);
	}
	
	//statiq
	@Override
	public float  stat (Long idEvent) {
		Event e = eventRepository.getById(idEvent) ;
		int n = 0 , tn ; 
		
		float p ;
		n = e.getAdvertisings().size() ;
		tn = e.getCapacite() ;
		p = (n*100)/tn ;
		
		return p ;
			}
	
	
	/*recherche tri*/
	public PagingResponse get(Specification<Event> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            final List<Event> entities = get(spec, sort);
            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }
	
	private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }

    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }
	
    /* @param spec     *
    * @param pageable pagination data
    * @return retrieve elements with pagination
    */
   public PagingResponse get(Specification<Event> spec, Pageable pageable) {
       Page<Event> page = eventRepository.findAll(spec, pageable);
       List<Event> content = page.getContent();
       return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
   }
   public List<Event> get(Specification<Event> spec, Sort sort) {
       return eventRepository.findAll(spec, sort);
   } 
   
   
   
	
   
}
