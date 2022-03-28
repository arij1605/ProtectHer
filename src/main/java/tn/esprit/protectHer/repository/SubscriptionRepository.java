package tn.esprit.protectHer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.protectHer.entity.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

	List<Subscription> findByConverted(Boolean converted);
	
}
