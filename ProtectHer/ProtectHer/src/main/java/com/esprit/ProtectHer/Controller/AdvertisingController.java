


package com.esprit.ProtectHer.Controller;

import java.io.File;

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
import com.esprit.ProtectHer.Service.AdvertisingServiceIm;

import com.esprit.ProtectHer.entity.Advertising;

import java.nio.file.Files;
import java.nio.file.Paths;








@RestController
@RequestMapping("/api")
public class AdvertisingController {
	
	@Autowired
	private AdvertisingServiceIm  advertisingServiceIm;
	@Autowired
	private AdvertisingRepository advertisingRepository;

	
	@PostMapping("/Advertisings")
	@ResponseBody
	public void addAdvertising( @RequestBody Advertising advertising)
 {
		advertisingServiceIm.addAdvertising(advertising);
		
		}
	
	@DeleteMapping("/Advertisings/{Advertising-id}")
		public void removeAdvertising(@PathVariable("Advertising-id") Long advertisingId) {
		advertisingServiceIm.deleteAdvertising(advertisingId);
		}

	
	
	
	@PutMapping("/Advertisings/{Advertising-id}")
	@ResponseBody
		
		public Advertising modifyAdvertising(@PathVariable("Advertising-id") Long advertisingtId, @RequestBody Advertising a) {
			return advertisingServiceIm.updateAdvertising(advertisingtId, a);
		}
	
	
	
	
	@PutMapping("/assign-user-to-advertising")
	@ResponseBody
	public void assignUserToAdvertising(@RequestParam("idAdvertising") Long idAdvertising, @RequestParam("id") Long id) {
		advertisingServiceIm.assignUserToAdvertising(idAdvertising, id);
	}
	
	
	@PutMapping("/PhotoAdvertisings")
	@ResponseBody
	public Advertising uploadphotoAdvertising(@RequestParam Long advertisingtId ,@RequestPart("file") MultipartFile file)
 {
		try {
			Advertising advertising = advertisingRepository.findById(advertisingtId).orElse(null);
			if (advertising != null) {
				File directory = new File("upload//");
				if (!directory.exists())
					directory.mkdir();
				byte[] bytes = new byte[0];
				bytes = file.getBytes();
				Files.write(Paths.get("upload//" + file.getOriginalFilename()), bytes);
				advertising.setPhoto(Paths.get("upload//" + file.getOriginalFilename()).toString());
				return advertisingRepository.save(advertising);
			}}
				catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			
			
		
		
		}
	}
	
	
	

	


