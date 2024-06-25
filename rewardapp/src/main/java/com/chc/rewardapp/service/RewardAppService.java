package com.chc.rewardapp.service;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chc.rewardapp.dataaccess.CustomerTxnRepo;
import com.chc.rewardapp.model.CustomerRewards;
import com.chc.rewardapp.model.CustomerTxns;
import com.chc.rewardapp.model.YearMonthPointsInfo;
import com.chc.rewardapp.util.RewardAppConstants;

@Service
public class RewardAppService {
	Logger logger = LoggerFactory.getLogger(RewardAppService.class);

	private CustomerTxnRepo customerTxnRepo;

	public RewardAppService(CustomerTxnRepo customerTxnRepo) {
		this.customerTxnRepo = customerTxnRepo;
	}

	public CustomerRewards getCustomerRewardsPointsById(Integer customerId) throws Exception {
		CustomerRewards customerRewards = null;
		// Fetch all the transactions made by the customerId.
		List<CustomerTxns> transactions = customerTxnRepo.findByCustomerIdOrderByPurchaseDateDesc(customerId);
		logger.info("Fetch complete all the customer purchase transactions for CustomerId: {}", customerId);
		// Validate if customers purchase transactions are not null/ empty.
		if (transactions != null && !transactions.isEmpty()) {

			logger.info("Create a map of YearMonth and transactions specific to it for the costumerId");
			// Create a map of YearMonth and transactions specific to it for the costumerId.
			Map<YearMonth, List<CustomerTxns>> yearMonthMap = transactions.stream()
					.collect(Collectors.groupingBy(CustomerTxns::getPurchaseYearMonth));
			// Create monthly summary of points for each YearMonth.
			logger.info("Create monthly summary of points for each YearMonth");
			List<YearMonthPointsInfo> monthlySummary = yearMonthMap.entrySet().stream().map(e -> YearMonthPointsInfo
					.builder().yearMonth(e.getKey()).points(getRewardPoints(e.getValue())).build())
					.collect(Collectors.toList());
			// Build customer rewards object with monthly summary of points and total reward
			// points of customerId
			logger.info("Build customer rewards object with monthly summary of points and total reward points of customerId");
			customerRewards = CustomerRewards.builder().customerId(customerId).monthlySummary(monthlySummary)
					.totalPoints(monthlySummary.stream().mapToInt(YearMonthPointsInfo::getPoints).sum()).build();

		} else {
			logger.info("No Transactions found for CustID");
			customerRewards = customerRewards.builder().customerId(customerId).monthlySummary(null).totalPoints(0)
					.build();
			// throw new IllegalStateException();
		}

		return customerRewards;

	}

	private Integer getRewardPoints(List<CustomerTxns> transactions) {
		int totalAmount = 0;
		int totalPoints = 0;
		for (CustomerTxns transaction : transactions) {
			totalAmount = transaction.getTotalAmount().intValue();
			/**
			 * 
			 * maxTxnAmount=$100 , minTxnAmount=$50
			 * 
			 * maxRewardPoints=2 , minRewardPoints=1
			 * 
			 */
			if (totalAmount > RewardAppConstants.maxTxnAmount) {
				totalPoints = totalPoints
						+ (RewardAppConstants.maxTxnAmount - RewardAppConstants.minTxnAmount)
								* RewardAppConstants.minRewardPoints
						+ (totalAmount - RewardAppConstants.maxTxnAmount) * RewardAppConstants.maxRewardPoints;
			} else if (totalAmount > RewardAppConstants.minTxnAmount) {
				totalPoints = totalPoints
						+ (totalAmount - RewardAppConstants.minTxnAmount) * RewardAppConstants.minRewardPoints;
			}
		}
		return totalPoints;
	}
}
