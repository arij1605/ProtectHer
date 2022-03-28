package tn.esprit.protectHer.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.protectHer.entity.User;
import tn.esprit.protectHer.repository.UserRepository;
import tn.esprit.protectHer.service.IUserService;

@RestController
@Api(tags = "User Management")
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private IUserService userService;

	@Autowired
	private UserRepository userRepository;
	
	@ApiOperation(value = "Upload a picture to an user")
	@PutMapping("/uploadPictureToUser")
	public User uploadPictureToUser(@RequestParam Long userId,
			@RequestPart("file") MultipartFile file) {
		try {
			User user = userRepository.findById(userId).orElse(null);
			if (user != null) {
				File directory = new File("upload//");
				if (!directory.exists())
					directory.mkdir();
				byte[] bytes = new byte[0];
				bytes = file.getBytes();
				Files.write(Paths.get("upload//" + file.getOriginalFilename()), bytes);
				user.setPicturePath(Paths.get("upload//" + file.getOriginalFilename()).toString());
				return userRepository.save(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ApiOperation(value = "Update a user")
	@PutMapping("/updateUser")
	@ResponseBody
	public User updateUser(@RequestParam Long userId, @RequestBody User intermediateUser) {
		return userService.updateUser(userId, intermediateUser);
	}
	
	@ApiOperation(value = "Remove a user")
	@DeleteMapping("/removeUser")
	@ResponseBody
	public String removeUser(@RequestParam Long userId) {
		userService.removeUser(userId);
		return "OK";
	}
	
	@ApiOperation(value = "Assign a role to a user")
	@PutMapping("/assignRoleToUser")
	@ResponseBody
	public User assignRoleToUser(@RequestParam("role-id") Long roleId, @RequestParam("user-id") Long userId) {
		return userService.assignRoleToUser(roleId, userId);
	}
	
	@ApiOperation(value = "Detach a role from a user")
	@PutMapping("/detachRoleFromUser")
	@ResponseBody
	public User detachRoleFromUser(@RequestParam("role-id") Long roleId, @RequestParam("user-id") Long userId) {
		return userService.detachRoleFromUser(roleId, userId);
	}
	@ApiOperation(value = "Approve a pending employee")
	@PutMapping("/approvePendingEmployee")
	@ResponseBody
	public String approvePendingEmployee(@RequestParam Integer verificationCode) {
		return userService.approvePendingUser(verificationCode);
	}

}
