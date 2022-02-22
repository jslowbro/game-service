package com.janchabik.gameservice.domain;

import java.util.Objects;

public class RoundOutCome {

	private final int cashBalanceDifference;

	private final int freeGamesWon;

	public RoundOutCome(int cashBalanceDifference, int freeRoundsWon) {
		validate(freeRoundsWon);
		this.cashBalanceDifference = cashBalanceDifference;
		this.freeGamesWon = freeRoundsWon;
	}

	private void validate(int freeRoundsWon) {
		if (freeRoundsWon < 0) {
			throw new IllegalArgumentException("Cannot win negative free rounds");
		}
	}

	public int getCashBalanceDifference() {
		return cashBalanceDifference;
	}

	public int getFreeRoundsWon() {
		return freeGamesWon;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RoundOutCome that = (RoundOutCome) o;
		return cashBalanceDifference == that.cashBalanceDifference && freeGamesWon == that.freeGamesWon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cashBalanceDifference, freeGamesWon);
	}
}
