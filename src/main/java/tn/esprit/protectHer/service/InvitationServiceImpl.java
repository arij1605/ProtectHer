package tn.esprit.protectHer.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.protectHer.entity.Invitation;
import tn.esprit.protectHer.entity.User;
import tn.esprit.protectHer.repository.InvitationRepository;
import tn.esprit.protectHer.repository.UserRepository;

@Service
public class InvitationServiceImpl implements IInvitationService {

	@Autowired
	InvitationRepository invitationRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Invitation createInvitation(Invitation invitation) {
		invitation.setStatus(false);
		return invitationRepository.save(invitation);
	}

	@Override
	public void deleteInvitation(Long invitationId) {
		invitationRepository.deleteById(invitationId);
	}

	@Override
	public Invitation retrieveInvitation(Long invitationId) {
		return invitationRepository.findById(invitationId).orElse(null);
	}

	@Override
	public Invitation updateInvitation(Long invitationId, Invitation intermediateInvitation) {
		Invitation invitation = retrieveInvitation(invitationId);
		invitation.setEmail(Optional.ofNullable(intermediateInvitation.getEmail()).orElse(invitation.getEmail()));
		invitation.setFirstName(Optional.ofNullable(intermediateInvitation.getFirstName()).orElse(invitation.getFirstName()));
		invitation.setLastName(Optional.ofNullable(intermediateInvitation.getLastName()).orElse(invitation.getLastName()));
		invitation.setGender(Optional.ofNullable(intermediateInvitation.getGender()).orElse(invitation.getGender()));
		return invitationRepository.save(invitation);
	}
	
	@Override
	public Invitation assignUserToInvitation(Long userId, Long invitationId) {
		Invitation invitation = invitationRepository.findById(invitationId).orElse(null);
		User user = userRepository.findById(userId).orElse(null);
		invitation.setSender(user);
		return invitationRepository.save(invitation);
	}

}
