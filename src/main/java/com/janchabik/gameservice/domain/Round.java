package com.janchabik.gameservice.domain;

import com.janchabik.gameservice.domain.betdeduction.BetDeductionPolicy;
import com.janchabik.gameservice.domain.outcomecalculation.OutComeCalculationStrategy;
import java.util.ArrayList;
import java.util.List;

// TODO add aggregate version
public final class Round {

	private final int roundId;

	private final int minBetAmount;

	private final int maxBetAmount;

	private final List<RoundEvent> roundEvents = new ArrayList<>();

	private int cashDifferenceAfterRound = 0;

	private int numberOfFreeRoundsWon = 0;

	public Round(int roundId, int minBetAmount, int maxBetAmount) {
		this.roundId = roundId;
		if (minBetAmount <= 0) {
			throw new IllegalArgumentException("Min bet amount cannot be lower than 0");
		}
		if (maxBetAmount <= 0) {
			throw new IllegalArgumentException("Max bet amount cannot be lower than 0");
		}
		if (maxBetAmount < minBetAmount) {
			throw new IllegalArgumentException("Max bet amount cant be lower than min bet amount");
		}
		this.minBetAmount = minBetAmount;
		this.maxBetAmount = maxBetAmount;
	}

	public void playRound(int betAmount, BetDeductionPolicy betDeductionPolicy, OutComeCalculationStrategy outComeCalculationStrategy) {
		deductBalance(betAmount, betDeductionPolicy);
		applyRoundOutCome(betAmount, outComeCalculationStrategy);
	}

	private void applyRoundOutCome(int betAmount, OutComeCalculationStrategy outComeCalculationStrategy) {
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

	public int getCashDifferenceAfterRound() {
		return cashDifferenceAfterRound;
	}

	public int getNumberOfFreeRoundsWon() {
		return numberOfFreeRoundsWon;
	}

	public int getRoundId() {
		return roundId;
	}
}
