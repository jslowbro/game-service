package com.janchabik.gameservice.domain;

import java.util.Objects;

public class GameState {

	private static final int DEFAULT_STARTING_CASH_BALANCE = 5000;

	private static final int DEFAULT_NUMBER_OF_FREE_ROUNDS = 0;

	private final int gameId;

	private final String userId;

	private int numberOfFreeRounds;

	private int cashBalance;

	public GameState(int gameId, String userId, int cashBalance, int numberOfFreeRounds) {
		this.gameId = gameId;
		this.userId = userId;
		this.cashBalance = cashBalance;
		this.numberOfFreeRounds = numberOfFreeRounds;
	}

	public static GameState startGame(int gameId, String userId) {
		return new GameState(gameId, userId, DEFAULT_STARTING_CASH_BALANCE, DEFAULT_NUMBER_OF_FREE_ROUNDS);
	}

	public void applyRoundOutCome(RoundOutCome roundOutCome) {
		numberOfFreeRounds = numberOfFreeRounds - 1;
		numberOfFreeRounds = numberOfFreeRounds + roundOutCome.getFreeRoundsWon();
		cashBalance = cashBalance + roundOutCome.getCashBalanceDifference();
	}

	public String getUserId() {
		return userId;
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
				numberOfFreeRounds == gameState.numberOfFreeRounds
				&& Objects.equals(userId, gameState.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(gameId, userId, cashBalance, numberOfFreeRounds);
	}
}
