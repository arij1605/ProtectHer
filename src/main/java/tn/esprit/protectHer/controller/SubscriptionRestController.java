package tn.esprit.protectHer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.protectHer.entity.Subscription;
import tn.esprit.protectHer.service.ISubscriptionService;

@RestController
@Api(tags = "Subscription Management")
@RequestMapping("/subscription")
public class SubscriptionRestController {

	@Autowired
	private ISubscriptionService subscriptionService;
	
	@ApiOperation(value = "Create a subscription")
	@PostMapping("/createSubscription")
	@ResponseBody
	public Subscription createSubscription(@RequestBody Subscription subscription) {
		String firstName = subscription.getFirstName();
		String lastName = subscription.getLastName();
		subscription.setEmail(subscription.getEmail().toLowerCase());		
		subscription.setEndDate(subscription.getEndDate());
		subscription.setFirstName(firstName.substring(0, 1).toUpperCase() + firstName.substring(1));
		subscription.setLastName(lastName.substring(0, 1).toUpperCase() + lastName.substring(1));
		return subscriptionService.createSubscription(subscription);
	}

	@ApiOperation(value = "Delete a subscription")
	@DeleteMapping("/deleteSubscription")
	@ResponseBody
	public void deleteSubscription(@RequestParam Long subscriptionId) {
		subscriptionService.deleteSubscription(subscriptionId);
	}

	@ApiOperation(value = "Retrieve a subscription")
	@GetMapping("/retrieveSubscription")
	@ResponseBody
	public Subscription retrieveRequest(@RequestParam Long subscriptionId) {
		return subscriptionService.retrieveSubscription(subscriptionId);
	}

	@ApiOperation(value = "Update a subscription")
	@PutMapping("/updateSubscription")
	@ResponseBody
	public Subscription updateSubscription(@RequestParam Long subscriptionId, @RequestBody Subscription subscription) {
		return subscriptionService.updateSubscription(subscriptionId, subscription);
	}
	
	@ApiOperation(value = "Assign a user to a subscription")
	@PutMapping("/assignUserToSubscription")
	@ResponseBody
	public Subscription assignUserToSubscription(@RequestParam("user-id") Long userId, @RequestParam("subscription-id") Long subscriptionId) {
		return subscriptionService.assignUserToSubscription(userId, subscriptionId);
	}

}
