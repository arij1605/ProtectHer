package tn.esprit.protectHer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.protectHer.entity.Invitation;
import tn.esprit.protectHer.service.IInvitationService;

@RestController
@Api(tags = "Invitation Management")
@RequestMapping("/invitation")
public class InvitationRestController {

	@Autowired
	private IInvitationService invitationService;

	@ApiOperation(value = "Create an invitation")
	@PostMapping("/createInvitation")
	@ResponseBody
	public void createInvitation(@RequestBody Invitation invitation) {
		invitationService.createInvitation(invitation);
	}

	@ApiOperation(value = "Delete an invitation")
	@DeleteMapping("/deleteInvitation")
	@ResponseBody
	public void deleteInvitation(@RequestParam Long invitationId) {
		invitationService.deleteInvitation(invitationId);
	}

	@ApiOperation(value = "Retrieve an invitation")
	@GetMapping("/retrieveInvitation")
	@ResponseBody
	public Invitation retrieveRequest(@RequestParam Long invitationId) {
		return invitationService.retrieveInvitation(invitationId);
	}

	@ApiOperation(value = "Update an invitation")
	@PutMapping("/updateInvitation")
	@ResponseBody
	public Invitation updateInvitation(@RequestParam Long invitationId,
			@RequestBody Invitation invitation) {
		return invitationService.updateInvitation(invitationId, invitation);
	}

	@ApiOperation(value = "Assign a user to an invitation")
	@PutMapping("/assignUserToInvitation")
	@ResponseBody
	public Invitation assignUserToInvitation(@RequestParam("user-id") Long userId,
			@RequestParam("invitation-id") Long invitationId) {
		return invitationService.assignUserToInvitation(userId, invitationId);
	}

}
