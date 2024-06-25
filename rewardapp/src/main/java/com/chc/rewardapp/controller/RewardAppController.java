package com.chc.rewardapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chc.rewardapp.model.CustomerRewards;
import com.chc.rewardapp.service.RewardAppService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rewardpoints")
public class RewardAppController {
	Logger logger = LoggerFactory.getLogger(RewardAppController.class);

	private RewardAppService rewardAppService;

	RewardAppController(RewardAppService rewardAppService) {
		this.rewardAppService = rewardAppService;

	}

	@GetMapping("/get/{customerId}")
	public ResponseEntity<CustomerRewards> getCustomerRewardsPointsById(@Valid @PathVariable Integer customerId)
			throws Exception {
		if (customerId == null || customerId <= 0) {
			throw new IllegalArgumentException("Customer ID must be positive number");
		}
		logger.info("Get customer details ", customerId);
		CustomerRewards customerRewards = rewardAppService.getCustomerRewardsPointsById(customerId);
		return ResponseEntity.ok().body(customerRewards);
	}

}
