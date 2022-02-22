package com.janchabik.gameservice.domain.model.betdeduction;

public interface BetDeductionPolicy {

	int deductedBalance(int betAmount);

	String policyName();
}
