package com.janchabik.gameservice.domain.betdeduction;

public interface BetDeductionPolicy {

	int deductedBalance(int betAmount);

	String policyName();
}
