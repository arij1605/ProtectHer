package com.esprit.ProtectHer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.ProtectHer.Service.JackPotServiceIm;

import com.esprit.ProtectHer.entity.JackPot;






@RestController
@RequestMapping("/api")
public class JackPotController {
	@Autowired
	private JackPotServiceIm  jackPotServiceIm;
	

	
	@PostMapping("/JackPots")
	@ResponseBody
	public void addJackPot( @RequestBody JackPot jackPot)
 {
		jackPotServiceIm.addJackPot(jackPot);
		
		}
	
	@PutMapping("/JackPots/{jackPot-id}")
	@ResponseBody
		
		public JackPot modifyJackPot(@PathVariable("jackPot-id") Long jackPottId, @RequestBody JackPot j) {
			return jackPotServiceIm.updateJackPot(jackPottId, j);
		}
	
	@DeleteMapping("/JackPots/{jackPot-id}")
		public void removeJackPot(@PathVariable("jackPot-id") Long jackPotId) {
		jackPotServiceIm.deleteJackPot(jackPotId);
		}

	@PutMapping("/assign-jackjwk-to-yazerwf")
	@ResponseBody
	public void assignJackPotToUser(@RequestParam("idJackPot") Long idJackPot, @RequestParam("id") Long id) {
		jackPotServiceIm.assignJackPotToUser(idJackPot, id);
	}
	
	

	
	}
	

	


