package tn.esprit.protectHer.service;

import tn.esprit.protectHer.entity.Subscription;

public interface ISubscriptionService {

	Subscription createSubscription(Subscription subscription);

	void deleteSubscription(Long subscriptionId);

	Subscription retrieveSubscription(Long subscriptionId);

	Subscription updateSubscription(Long subscriptionId, Subscription intermediateSubscription);

	Subscription assignUserToSubscription(Long userId, Long subscriptionId);

}
