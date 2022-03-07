package com.esprit.ProtectHer.Controller;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.esprit.ProtectHer.Repository.AdvertisingRepository;
import com.esprit.ProtectHer.Repository.EventRepository;
import com.esprit.ProtectHer.Service.EventServiceIm;
import com.esprit.ProtectHer.entity.Advertising;
import com.esprit.ProtectHer.entity.Event;








@RestController
@RequestMapping("/api")
public class EventController {
	
	@Autowired
	private EventServiceIm  eventServiceIm;
	@Autowired
	private EventRepository eventRepository;

	
	@PostMapping("/Events")
	@ResponseBody
	public void addEvent( @RequestBody Event event)
 {
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
	public void assignAdvertisingToEvent(@RequestParam("idEvent") Long idEvent, @RequestParam("idAdvertising") Long idAdvertising) {
		eventServiceIm.assignAdvertisingToEvent(idEvent, idAdvertising);
	}
	
	@PutMapping("/PhotoEvents")
	@ResponseBody
	public Event uploadphotoEvent(@RequestParam Long EventtId ,@RequestPart("file") MultipartFile file)
 {
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
			}}
				catch (Exception e) {
					e.printStackTrace();
				}
				return null;
 }}

	


