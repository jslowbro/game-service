package com.janchabik.gameservice.domain.betdeduction;

public class PlayingForFreeBetDeductionPolicy implements BetDeductionPolicy {

	private static final PlayingForFreeBetDeductionPolicy INSTANCE = new PlayingForFreeBetDeductionPolicy();

	private PlayingForFreeBetDeductionPolicy() { }

	@Override
	public int deductedBalance(int betAmount) {
		return 0;
	}

	@Override
	public String policyName() {
		return this.getClass().toString();
	}
}
