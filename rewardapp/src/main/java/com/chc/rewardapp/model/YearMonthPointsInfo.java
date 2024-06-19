package com.chc.rewardapp.model;

import java.time.YearMonth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class YearMonthPointsInfo {


	private YearMonth yearMonth;

	private Integer points;
}
