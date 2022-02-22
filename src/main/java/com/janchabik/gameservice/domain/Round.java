package com.janchabik.gameservice.domain;

import com.janchabik.gameservice.domain.betdeduction.BetDeductionPolicy;
import com.janchabik.gameservice.domain.outcomecalculation.OutComeCalculationStrategy;
import java.util.ArrayList;
import java.util.List;

public class Round {

	private final List<RoundEvent> roundEvents = new ArrayList<>();

	private int cashDifferenceAfterRound = 0;

	private int numberOfFreeRoundsWon = 0;

	public RoundOutCome playRound(int betAmount, BetDeductionPolicy betDeductionPolicy, OutComeCalculationStrategy outComeCalculationStrategy) {
		deductBalance(betAmount, betDeductionPolicy);
		playRound(betAmount, outComeCalculationStrategy);
		return new RoundOutCome(cashDifferenceAfterRound, numberOfFreeRoundsWon);
	}

	private void playRound(int betAmount, OutComeCalculationStrategy outComeCalculationStrategy) {
		OutComeCalculationStrategy.Outcome roundOutCome = outComeCalculationStrategy.calculateGameOutCome(betAmount);
		addCashPrize(roundOutCome.getWonCashAmount());
		addWonFreeRounds(roundOutCome.getNumberOfWonFreeRounds());
	}

	private void addWonFreeRounds(int wonRounds) {
		numberOfFreeRoundsWon += wonRounds;
		emit(new RoundEvent.RoundsWonEvent(wonRounds));
	}

	private void addCashPrize(int wonCashAmount) {
		cashDifferenceAfterRound += wonCashAmount;
		emit(new RoundEvent.CashWonEvent(wonCashAmount));
	}

	private void deductBalance(int betAmount, BetDeductionPolicy betDeductionPolicy) {
		int deductedBalance = betDeductionPolicy.deductedBalance(betAmount);
		emit(new RoundEvent.BalanceDeductedEvent(deductedBalance));
		cashDifferenceAfterRound = cashDifferenceAfterRound - deductedBalance;
	}

	private void emit(RoundEvent roundEvent) {
		roundEvents.add(roundEvent);
	}

	public List<RoundEvent> flushEvents() {
		List<RoundEvent> returnValue = new ArrayList<>(this.roundEvents);
		roundEvents.clear();
		return returnValue;
	}

}
