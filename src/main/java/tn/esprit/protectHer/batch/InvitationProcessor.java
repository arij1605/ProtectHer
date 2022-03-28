package tn.esprit.protectHer.batch;

import javax.mail.SendFailedException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.protectHer.entity.GenderType;
import tn.esprit.protectHer.entity.Invitation;
import tn.esprit.protectHer.entity.User;
import tn.esprit.protectHer.repository.InvitationRepository;
import tn.esprit.protectHer.repository.UserRepository;
import tn.esprit.protectHer.service.EmailServiceImpl;

@Slf4j
public class InvitationProcessor implements ItemProcessor<Invitation, Invitation> {

	@Autowired
	UserRepository userRepository;

	@Autowired
	InvitationRepository invitationRepository;

	@Autowired
	EmailServiceImpl emailServiceImpl;

	@Override
	public Invitation process(Invitation invitation) throws Exception {
		try {
			User sender = userRepository.retrieveSenderByInvitationId(invitation.getInvitationId());
			emailServiceImpl.sendEmail(invitation.getEmail(), "JOIN ProtectHer",
					(invitation.getGender().equals(GenderType.MALE) ? "Welcome Mr. " : "Welcome Ms. ")
							+ invitation.getFirstName() + ", \nYou are invited to join our plateform protectHer.");
			invitation.setSender(sender);
			invitation.setStatus(true);
		} catch (

		SendFailedException sendFailedException) {
			log.debug("error: ", sendFailedException.getMessage());
		}
		return invitation;
	}

}
