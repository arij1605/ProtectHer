package com.esprit.ProtectHer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.ProtectHer.Service.UserServiceIm;
import com.esprit.ProtectHer.entity.User;




@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserServiceIm  userServiceIm;
	

	
	@PostMapping("/Users")
	@ResponseBody
	public void addUser( @RequestBody User user)
 {
		userServiceIm.addUser(user);
		
		}
	}
	

	


