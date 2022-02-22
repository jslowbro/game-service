package com.janchabik.gameservice.domain.model.betdeduction;

public class FreeRoundBetDeductionPolicy implements BetDeductionPolicy {

	public static final FreeRoundBetDeductionPolicy INSTANCE = new FreeRoundBetDeductionPolicy();

	@Override
	public int deductedBalance(int betAmount) {
		return 0;
	}

	@Override
	public String policyName() {
		return this.getClass().toString();
	}
}
