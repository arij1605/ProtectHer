package tn.esprit.protectHer.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.concurrent.ThreadLocalRandom;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.protectHer.entity.AuthUser;
import tn.esprit.protectHer.entity.JWTResponse;
import tn.esprit.protectHer.entity.MessageResponse;
import tn.esprit.protectHer.entity.Role;
import tn.esprit.protectHer.entity.RoleType;
import tn.esprit.protectHer.entity.User;
import tn.esprit.protectHer.repository.RoleRepository;
import tn.esprit.protectHer.repository.UserRepository;
import tn.esprit.protectHer.security.JWTUtils;
import tn.esprit.protectHer.service.MessageServiceImpl;
import tn.esprit.protectHer.service.UserDetailsImpl;
import tn.esprit.protectHer.util.SMSResponse;
import tn.esprit.protectHer.util.UserDetails;

@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
@RestController
@Api(tags = "Authentication Management")
@RequestMapping("/authentication")
public class AuthenticationController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private MessageServiceImpl messageServiceImpl;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JWTUtils jwtUtils;

	@ApiOperation(value = "Sign In")
	@PostMapping("/signIn")
	public UserDetails authenticateUser(@Valid @RequestBody AuthUser authUser) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authUser.getUserName(), authUser.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		User user = userRepository.findByUserName(userDetails.getUsername()).get();

		return new UserDetails(user, jwtUtils.generateJwtToken(authentication));
	}

	@ApiOperation(value = "Sign Up")
	@PostMapping("/signUp")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest) {
		if (userRepository.existsByUserName(signUpRequest.getUserName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		
		signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
		
		signUpRequest.setActive(false);
		
		Set<Role> roles = signUpRequest.getRoles();
		Set<String> strRoles = new HashSet<>();
		
		Set<Role> userRoles = new HashSet<>();

		if (roles == null) {
			Role adminRole = roleRepository.findByRole(RoleType.ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			userRoles.add(adminRole);
		} else {
			for(Role role: roles) {
				strRoles.add(role.toString());
			}
			strRoles.forEach(role -> {
				switch (role) {
				case "ASSOCIATION":
					Role associationRole = roleRepository.findByRole(RoleType.ASSOCIATION)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					userRoles.add(associationRole);

					break;
				case "DONOR":
					Role donorRole = roleRepository.findByRole(RoleType.DONOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					userRoles.add(donorRole);

					break;
				case "FORMER":
					Role formerRole = roleRepository.findByRole(RoleType.FORMER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					userRoles.add(formerRole);
					
					break;
				case "LAWYER":
					Role lawyerRole = roleRepository.findByRole(RoleType.LAWYER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					userRoles.add(lawyerRole);

					break;
				case "PSYCHOTHERAPIST":
					Role psychotherapistRole = roleRepository.findByRole(RoleType.PSYCHOTHERAPIST)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					userRoles.add(psychotherapistRole);

					break;
				default:
					Role adminRole = roleRepository.findByRole(RoleType.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					userRoles.add(adminRole);
				}
			});
		}

		signUpRequest.setRoles(userRoles);
		
		int verificationCode = ThreadLocalRandom.current().nextInt(100000, 1000000);

		signUpRequest.setVerificationCode(verificationCode);
		
		userRepository.save(signUpRequest);
		
		messageServiceImpl.sendSMS(new SMSResponse("+21626946568", "PotectHer verification code is: " + verificationCode));

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
