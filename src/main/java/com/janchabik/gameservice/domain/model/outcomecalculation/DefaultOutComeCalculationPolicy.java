package com.janchabik.gameservice.domain.model.outcomecalculation;

import com.janchabik.gameservice.domain.services.ports.RandomNumberProvider;

public class DefaultOutComeCalculationPolicy implements OutComeCalculationPolicy {

	private final static int MAX_PERCENTAGE = 100;

	private final static int MIN_PERCENTAGE = 1;

	private final RandomNumberProvider randomNumberProvider;

	public DefaultOutComeCalculationPolicy(RandomNumberProvider randomNumberProvider) {
		this.randomNumberProvider = randomNumberProvider;
	}

	@Override
	public Outcome calculateGameOutCome(int betAmount) {
		int wonAmount = calculateWinAmount(betAmount);
		int freeGamesWon = calculateFreeGamesAmount();
		return new Outcome(wonAmount, freeGamesWon);
	}

	private int calculateFreeGamesAmount() {
		int generatedNumber = randomNumber();
		if (1 <= generatedNumber && generatedNumber <= 10) {
			return 1;
		} else {
			return 0;
		}
	}

	private int calculateWinAmount(int betAmount) {
		int generatedNumber = randomNumber();
		if (isABigWin(generatedNumber)) {
			return betAmount * 50;
		} else if (isMediumWin(generatedNumber)) {
			return betAmount * 10;
		} else if (isSmallWin(generatedNumber)) {
			return betAmount * 3;
		}
		return 0;
	}

	private boolean isSmallWin(int generatedNumber) {
		return 21 <= generatedNumber && generatedNumber <= 30;
	}

	private boolean isMediumWin(int generatedNumber) {
		return 11 <= generatedNumber && generatedNumber <= 20;
	}

	private boolean isABigWin(int generatedNumber) {
		return 1 <= generatedNumber && generatedNumber <= 10;
	}

	private int randomNumber() {
		return randomNumberProvider.randomNumber(MIN_PERCENTAGE, MAX_PERCENTAGE);
	}
}
