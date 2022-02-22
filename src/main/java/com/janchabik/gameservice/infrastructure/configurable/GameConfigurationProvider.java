package com.janchabik.gameservice.infrastructure.configurable;

import org.springframework.stereotype.Component;

@Component
public class GameConfigurationProvider {

	private final int startingBalance = 5000;

	private final int startingFreeRounds = 0;

	private final int minBet = 1;

	private final int maxBet = 10;

	public int getStartingCashBalance() {
		return startingBalance;
	}

	public int getStartingFreeRounds() {
		return startingFreeRounds;
	}

	public int getMinBet() {
		return minBet;
	}

	public int getMaxBet() {
		return maxBet;
	}
}
