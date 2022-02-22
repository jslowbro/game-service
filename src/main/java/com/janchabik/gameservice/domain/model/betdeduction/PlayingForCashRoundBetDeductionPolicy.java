package com.janchabik.gameservice.domain.model.betdeduction;

public class PlayingForCashRoundBetDeductionPolicy implements BetDeductionPolicy {

	public static final PlayingForCashRoundBetDeductionPolicy INSTANCE = new PlayingForCashRoundBetDeductionPolicy();

	private PlayingForCashRoundBetDeductionPolicy() {
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
