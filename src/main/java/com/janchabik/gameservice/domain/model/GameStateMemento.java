package com.janchabik.gameservice.domain.model;

import java.util.Objects;

public class GameStateMemento {

	private final int gameId;

	private final int numberOfFreeRounds;

	private final int cashBalance;

	public GameStateMemento(int gameId, int numberOfFreeRounds, int cashBalance) {
		this.gameId = gameId;
		this.numberOfFreeRounds = numberOfFreeRounds;
		this.cashBalance = cashBalance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GameStateMemento that = (GameStateMemento) o;
		return gameId == that.gameId && numberOfFreeRounds == that.numberOfFreeRounds && cashBalance == that.cashBalance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gameId, numberOfFreeRounds, cashBalance);
	}
}
