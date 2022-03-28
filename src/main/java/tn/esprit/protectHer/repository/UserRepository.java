package tn.esprit.protectHer.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.protectHer.entity.GenderType;
import tn.esprit.protectHer.entity.Role;
import tn.esprit.protectHer.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUserName(String userName);

	Boolean existsByUserName(String userName);

	Boolean existsByEmail(String email);

	@Query("SELECT u FROM User u join u.invitations i WHERE i.invitationId = :invitationId")
	User retrieveSenderByInvitationId(@Param("invitationId") Long invitationId);

	User findByVerificationCode(Integer verificationCode);

	List<User> findByActive(Boolean active);

	List<User> findByGender(GenderType gender);

}
