package com.esprit.ProtectHer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



import com.esprit.ProtectHer.Service.EmailServiceImpl;
import com.esprit.ProtectHer.Service.IUserService;


import com.esprit.ProtectHer.entity.Training;
import com.esprit.ProtectHer.entity.User;

import com.esprit.ProtectHer.Repository.TrainingRepository;



@RestController
@Api(tags = "Gestion des Utilisateurs")
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	EmailServiceImpl emailService;
	
	@Autowired
	TrainingRepository trainingRepository;
	
	// http://localhost:8081/SpringMVC/User/addUser
		@ApiOperation(value = "Ajouter un utilisateur")
		@PostMapping("/addUser")	
		@ResponseBody
		public void addUser(@RequestBody User user) {
			userService.addUser(user);
		}
	// http://localhost:8081/SpringMVC/Formateur/ajouetEtAffecterFormationAFormateur/
			@ApiOperation(value = " add Formation  And  Assign To Former")
			@PostMapping("/addTrainingAndAssignToFormer")
			@ResponseBody
			public void addTrainingAndAssignToFormerr(@RequestBody Training training,@RequestParam("id") long id) {
					userService.addTrainingAndAssignToFormer(training, id);
				}
			
	// http://localhost:8081/SpringMVC/apprenant/getapprenant/{Formation}
						@ApiOperation(value = "Rechercher  un apprenant")
						@GetMapping("/getLearnerByTraining")
						@ResponseBody
						public void  getLearnerByTraining() {
						}
						
	// http://localhost:8081/SpringMVC/User/cancel-USER/{id}
						
				@ApiOperation(value = "Annuler un utilisateur ")
				@PutMapping("/removeUser/{id}")
				@ResponseBody
				public void removeUser(@PathVariable("id") Long id) {
					userService.cancelUser(id);
						}
				
				// http://localhost:8081/SpringMVC/user/modify-User
				@ApiOperation(value = "Modifier un utilisateur")
				@PutMapping("/modify-user")
				@ResponseBody
				public User modifyUser(@RequestBody User u) {
					return userService.updateUser(u);
				}
				
		// http://localhost:8081/SpringMVC/user/retrieve-user/{userId}
				@ApiOperation(value = "Rechercher un utilisateur")
				@GetMapping("/retrieve-user/{userId}")
				@ResponseBody
				public User retrieveUser(@PathVariable("userId") Long userId) {
					return userService.retrieveUser(userId);
				}
				
		// http://localhost:8081/SpringMVC/user/assignCertificationToUser
							@ApiOperation(value = "assigner une certification à un utilisateur")
							@PutMapping("/assignCertificationToUser")
							@ResponseBody
							public void CertificationToUser(@RequestParam("id") Long id, @RequestParam("idCertification") Long idCertification) {
								userService.assignCertificationToUser(id, idCertification);
							}

}
