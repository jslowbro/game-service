package com.janchabik.gameservice.api;

import java.util.Objects;

public class GameStateResponse {

	private final int gameId;

	private final int numberOfFreeRounds;

	private final int cashBalance;

	public GameStateResponse(int gameId, int numberOfFreeRounds, int cashBalance) {
		this.gameId = gameId;
		this.numberOfFreeRounds = numberOfFreeRounds;
		this.cashBalance = cashBalance;
	}

	public int getGameId() {
		return gameId;
	}

	public int getNumberOfFreeRounds() {
		return numberOfFreeRounds;
	}

	public int getCashBalance() {
		return cashBalance;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GameStateResponse that = (GameStateResponse) o;
		return gameId == that.gameId && numberOfFreeRounds == that.numberOfFreeRounds && cashBalance == that.cashBalance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gameId, numberOfFreeRounds, cashBalance);
	}
}
