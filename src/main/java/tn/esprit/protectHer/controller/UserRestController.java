package tn.esprit.protectHer.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
@RestController
@Api(tags = "User Management")
@RequestMapping("/user")
public class UserRestController {
	@Value("C:\\Users\\emnam\\Documents\\GitHub\\protectHer-Frontend\\src\\assets\\upload")
	private String uploadPath;
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
				File directory = new File(uploadPath);
				if (!directory.exists())
					directory.mkdir();
				byte[] bytes = new byte[0];
				bytes = file.getBytes();
				Files.write(Paths.get(uploadPath + file.getOriginalFilename()), bytes);
				user.setPicturePath("assets/upload/" + file.getOriginalFilename());
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
	@ApiOperation(value = "Approve a pending user")
	@PutMapping("/approvePendingUser")
	@ResponseBody
	public String approvePendingUser(@RequestParam Integer verificationCode) {
		return userService.approvePendingUser(verificationCode);
	}
	
	@ApiOperation(value = "Retrieve all users")
	@GetMapping("/retrieveAllUsers")
	@ResponseBody
	public Iterable<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

}
