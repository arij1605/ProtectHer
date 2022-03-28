package tn.esprit.protectHer.batch;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.protectHer.entity.Invitation;
import tn.esprit.protectHer.repository.InvitationRepository;

public class InvitationWriter implements ItemWriter<Invitation> {

	@Autowired
    InvitationRepository invitationRepository;

    @Override
    public void write(List<? extends Invitation> list) throws Exception {
      invitationRepository.saveAllAndFlush(list);
    }

}
