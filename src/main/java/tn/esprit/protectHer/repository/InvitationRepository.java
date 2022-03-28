package tn.esprit.protectHer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.protectHer.entity.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
	
}
