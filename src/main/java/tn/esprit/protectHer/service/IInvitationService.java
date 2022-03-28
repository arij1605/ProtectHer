package tn.esprit.protectHer.service;

import tn.esprit.protectHer.entity.Invitation;

public interface IInvitationService {

	Invitation createInvitation(Invitation invitation);
	
	void deleteInvitation(Long invitationId);

	Invitation retrieveInvitation(Long invitationId);

	Invitation updateInvitation(Long invitationId, Invitation intermediateInvitation);
	
	Invitation assignUserToInvitation(Long userId, Long invitationId);

}
