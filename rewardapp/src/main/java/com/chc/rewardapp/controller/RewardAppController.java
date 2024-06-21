package com.chc.rewardapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chc.rewardapp.model.CustomerRewards;
import com.chc.rewardapp.service.RewardAppService;

@RestController
@RequestMapping("/api/rewardpoints")
public class RewardAppController {
	Logger logger= LoggerFactory.getLogger(RewardAppController.class);
	@Autowired
	private RewardAppService rewardAppService;
	
	@RequestMapping("/get/{customerId}")
	public ResponseEntity<CustomerRewards> getCustomerRewardsPointsById(@PathVariable Integer customerId) throws Exception {
		logger.info("Get customer details "+customerId.intValue());
		CustomerRewards customerRewards = rewardAppService.getCustomerRewardsPointsById(customerId);
		return ResponseEntity.ok().body(customerRewards);
	}

}
