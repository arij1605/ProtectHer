package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Candidacy;
@Repository
public interface CandidacyRepository extends CrudRepository<Candidacy,Long> {
}
