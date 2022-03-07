package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Offre;
import tn.esprit.project.Entities.ResponseComplaint;

@Repository
public interface OffreRepository extends JpaRepository<Offre,Long> {
}
