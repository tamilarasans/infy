package com.chc.rewardapp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerRewards {
	

	private Integer customerId;
	/**
	 * List of monthly summary of points.
	 */
	private List<YearMonthPointsInfo> monthlySummary; 

	/**
	 * Total points earned by customer.
	 */
	private Integer totalPoints;

}
