package com.janchabik.gameservice.domain.betdeduction;

// This is different from PlayingForFreeBetDeductionPolicy for auditing purposes
public class PlayingForFreeBetDeductionPolicy implements BetDeductionPolicy {

	public static final PlayingForFreeBetDeductionPolicy INSTANCE = new PlayingForFreeBetDeductionPolicy();

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
