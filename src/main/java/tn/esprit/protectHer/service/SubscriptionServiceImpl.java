package tn.esprit.protectHer.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.protectHer.entity.Subscription;
import tn.esprit.protectHer.entity.User;
import tn.esprit.protectHer.repository.SubscriptionRepository;
import tn.esprit.protectHer.repository.UserRepository;

@Service
public class SubscriptionServiceImpl implements ISubscriptionService {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Subscription createSubscription(Subscription subscription) {
		subscription.setConverted(false);
		return subscriptionRepository.save(subscription);
	}
	
	@Override
	public void deleteSubscription(Long subscriptionId) {
		subscriptionRepository.deleteById(subscriptionId);
	}
	
	@Override
	public Subscription retrieveSubscription(Long subscriptionId) {
		return subscriptionRepository.findById(subscriptionId).orElse(null);
	}
	
	@Override
	public Subscription updateSubscription(Long subscriptionId, Subscription intermediateSubscription) {
		Subscription subscription = retrieveSubscription(subscriptionId);
		subscription.setEmail(Optional.ofNullable(intermediateSubscription.getEmail()).orElse(subscription.getEmail()));
		subscription.setFirstName(Optional.ofNullable(intermediateSubscription.getFirstName()).orElse(subscription.getFirstName()));
		subscription.setLastName(Optional.ofNullable(intermediateSubscription.getLastName()).orElse(subscription.getLastName()));
		return subscriptionRepository.save(subscription);
	}
	
	@Override
	public Subscription assignUserToSubscription(Long userId, Long subscriptionId) {
		User user = userRepository.findById(userId).orElse(null);
		Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);
		subscription.setOwner(user);
		return subscriptionRepository.save(subscription);
	}
	
}


