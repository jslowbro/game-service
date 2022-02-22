package com.janchabik.gameservice.domain.betdeduction;

public class PlayingCashRoundBetDeductionPolicy implements BetDeductionPolicy {

	private static final PlayingCashRoundBetDeductionPolicy INSTANCE = new PlayingCashRoundBetDeductionPolicy();

	private PlayingCashRoundBetDeductionPolicy() {
	}

	@Override
	public int deductedBalance(int betAmount) {
		return betAmount;
	}

	@Override
	public String policyName() {
		return this.getClass().toString();
	}
}
