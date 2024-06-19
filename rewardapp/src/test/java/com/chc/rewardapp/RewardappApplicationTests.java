package com.chc.rewardapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chc.rewardapp.model.CustomerRewards;

//@SpringBootTest
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RewardappApplicationTests {

	@LocalServerPort
	private int port;	
	
	@Autowired
	private TestRestTemplate restTemplate;

	private final String API_CUSTOMER_SUMMARY = "api/rewardpoints/get";
	
	@Test
	void customerRewardsEndPointTest1() throws Exception {
		String baseUrl = "http://localhost:" + port + "/";	
		System.out.println("url val...."+baseUrl);
		ResponseEntity<CustomerRewards> responseEntity = restTemplate.getForEntity(baseUrl + API_CUSTOMER_SUMMARY +"/2", CustomerRewards.class);
		assert(responseEntity.getStatusCode() == HttpStatus.OK);
		CustomerRewards rewardsInfo = responseEntity.getBody();
		assert(rewardsInfo != null  && rewardsInfo.getTotalPoints() > 0);
	}
	

	@Test
	void customerRewardsEndPointTest2() throws Exception {
		String baseUrl = "http://localhost:" + port + "/";		
		ResponseEntity<CustomerRewards> responseEntity = restTemplate.getForEntity(baseUrl + API_CUSTOMER_SUMMARY +"/1", CustomerRewards.class);
		assert(responseEntity.getStatusCode() == HttpStatus.OK);
		CustomerRewards rewardsInfo = responseEntity.getBody();
		assert(rewardsInfo != null && rewardsInfo.getTotalPoints() ==0);
	}

}
