package com.janchabik.gameservice.domain;

import com.janchabik.gameservice.domain.betdeduction.BetDeductionPolicy;
import com.janchabik.gameservice.domain.betdeduction.FreeRoundBetDeductionPolicy;
import com.janchabik.gameservice.domain.betdeduction.PlayingForCashRoundBetDeductionPolicy;
import com.janchabik.gameservice.domain.betdeduction.PlayingForFreeBetDeductionPolicy;
import java.util.Objects;

// TODO add aggregate version
public final class GameState {

	private final int gameId;

	private int numberOfFreeRounds;

	private int cashBalance;

	public GameState(int gameId, int cashBalance, int numberOfFreeRounds) {
		this.gameId = gameId;
		if (cashBalance < 0) {
			throw new IllegalArgumentException("Starting cash balance cannot be negative");
		}
		this.cashBalance = cashBalance;
		this.numberOfFreeRounds = numberOfFreeRounds;
	}

	public void applyRoundOutCome(int cashBalanceDifference, int freeRoundsWon) {
		int newNumberOfFreeRounds = numberOfFreeRounds + freeRoundsWon;
		int newCashBalance = cashBalance + cashBalanceDifference;
		validate(freeRoundsWon, newCashBalance);
		numberOfFreeRounds = newNumberOfFreeRounds;
		cashBalance = newCashBalance;
	}

	public BetDeductionPolicy calculateBetDeductionPolicy(boolean isFreePlay) {
		if (isFreePlay) {
			return PlayingForFreeBetDeductionPolicy.INSTANCE;
		} else if (numberOfFreeRounds > 0) {
			numberOfFreeRounds--;
			return FreeRoundBetDeductionPolicy.INSTANCE;
		} else {
			return PlayingForCashRoundBetDeductionPolicy.INSTANCE;
		}
	}

	private void validate(int freeRoundsWon, int newCashBalance) {
		if (freeRoundsWon < 0) {
			throw new IllegalArgumentException("Cannot win negative free rounds");
		}
		if (newCashBalance < 0) {
			throw new IllegalArgumentException("Cannot go into a negative cash balance");
		}
	}

	public int getCashBalance() {
		return cashBalance;
	}

	public int getNumberOfFreeRounds() {
		return numberOfFreeRounds;
	}

	public int getGameId() {
		return gameId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GameState gameState = (GameState) o;
		return gameId == gameState.gameId &&
				cashBalance == gameState.cashBalance &&
				numberOfFreeRounds == gameState.numberOfFreeRounds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gameId, cashBalance, numberOfFreeRounds);
	}
}
