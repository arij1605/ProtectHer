package tn.esprit.protectHer.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.protectHer.entity.Role;
import tn.esprit.protectHer.entity.RoleType;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByRole(RoleType roleType);
}
