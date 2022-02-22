package com.janchabik.gameservice.domain;

import java.util.Objects;

public class GameState {

	private static final int DEFAULT_STARTING_CASH_BALANCE = 5000;

	private static final int DEFAULT_NUMBER_OF_FREE_ROUNDS = 0;

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

	public static GameState startGame(int gameId) {
		return new GameState(gameId, DEFAULT_STARTING_CASH_BALANCE, DEFAULT_NUMBER_OF_FREE_ROUNDS);
	}

	public void applyRoundOutCome(int cashBalanceDifference, int freeRoundsWon) {
		int newNumberOfFreeRounds = numberOfFreeRounds - 1 + freeRoundsWon;
		int newCashBalance = cashBalance + cashBalanceDifference;
		validate(freeRoundsWon, newNumberOfFreeRounds, newCashBalance);
		numberOfFreeRounds = newNumberOfFreeRounds;
		cashBalance = newCashBalance;
	}

	private void validate(int freeRoundsWon, int newNumberOfFreeRounds, int newCashBalance) {
		if (freeRoundsWon < 0) {
			throw new IllegalArgumentException("Cannot win negative free rounds");
		}
		if (newNumberOfFreeRounds < 0) {
			throw new IllegalArgumentException("Cannot go into negative free rounds");
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
