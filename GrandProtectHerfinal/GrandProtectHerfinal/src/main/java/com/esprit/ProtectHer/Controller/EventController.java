package com.esprit.ProtectHer.Controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.mail.SendFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.ProtectHer.Repository.EventRepository;
import com.esprit.ProtectHer.Service.EmailServiceImpl;

import com.esprit.ProtectHer.Service.EventServiceImp;
import com.esprit.ProtectHer.entity.Event;
import com.esprit.ProtectHer.entity.PagingHeaders;
import com.esprit.ProtectHer.entity.PagingResponse;
import com.esprit.ProtectHer.entity.User;


import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;

@RestController
@RequestMapping("/api")
public class EventController {

	@Autowired
	private EventServiceImp eventServiceIm;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@PostMapping("/Events")
	@ResponseBody
	public void addEvent(@RequestBody Event event) {
		eventServiceIm.addEvent(event);

	}

	@DeleteMapping("/Events/{event-id}")
	public void removeEvent(@PathVariable("event-id") Long eventId) {
		eventServiceIm.deleteEvent(eventId);
	}

	@PutMapping("/Events/{event-id}")
	@ResponseBody
	public Event modifyEvent(@PathVariable("event-id") Long eventId, @RequestBody Event e) {
		return eventServiceIm.updateEvent(eventId, e);
	}

	@PutMapping("/assign-adver-to-Event")
	@ResponseBody
	public void assignAdvertisingToEvent(@RequestParam("idEvent") Long idEvent,
			@RequestParam("idAdvertising") Long idAdvertising) {
		eventServiceIm.assignAdvertisingToEvent(idEvent, idAdvertising);
	}

	@PutMapping("send-email")
	@ResponseBody
	public void sendEmail(@RequestParam("event-id") Long eventId) throws SendFailedException {
		Event event = eventRepository.findById(eventId).orElse(null);
		List<User> users = event.getUsers();
		if (event.getUsers() != null) {
			for (User user : users) {
				emailServiceImpl.sendEmail(user.getEmail(), "Join Our Event", " Alert !!!, \n You are invited to join our event.");
			}
		}
	}

	@PutMapping("/PhotoEvents")
	@ResponseBody
	public Event uploadphotoEvent(@RequestParam Long EventtId, @RequestPart("file") MultipartFile file) {
		try {
			Event event = eventRepository.findById(EventtId).orElse(null);
			if (event != null) {
				File directory = new File("upload//");
				if (!directory.exists())
					directory.mkdir();
				byte[] bytes = new byte[0];
				bytes = file.getBytes();
				Files.write(Paths.get("upload//" + file.getOriginalFilename()), bytes);
				event.setPhotoE(Paths.get("upload//" + file.getOriginalFilename()).toString());
				return eventRepository.save(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Event>> get(@And({

			@Spec(path = "concept", params = "concept", spec = Like.class),

			@Spec(path = "eventDate", params = "eventDate", spec = Equal.class),
			@Spec(path = "eventDate", params = { "eventDateGt",
					"eventDateLt" }, spec = Between.class) }) Specification<Event> spec,
			Sort sort, @RequestHeader HttpHeaders headers) {
		final PagingResponse response = eventServiceIm.get(spec, headers, sort);
		return new ResponseEntity<>(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);

	}

	public HttpHeaders returnHttpHeaders(PagingResponse response) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
		headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
		headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
		headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
		headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
		return headers;
	}
	
	@GetMapping("/stat/{idEvent}")
	@ResponseBody
	public float statfor (@PathVariable ("idEvent")Long idEvent) {
		
		return eventServiceIm.stat(idEvent) ;
		
	}
	
	
	
	@GetMapping("/retrieve-event/{idEvent}")
	@ResponseBody
	public Event retrieveEvent(@PathVariable("idEvent") Long idEvent) {
		return eventServiceIm.retrieveEvent(idEvent);
	}
	
	
}
