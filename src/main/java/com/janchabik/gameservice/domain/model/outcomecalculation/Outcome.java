package com.janchabik.gameservice.domain.model.outcomecalculation;

import java.util.Objects;

public class Outcome {

	private final int wonCashAmount;

	private final int numberOfWonFreeRounds;

	public Outcome(int wonCashAmount, int numberOfWonFreeRounds) {
		validate(wonCashAmount, numberOfWonFreeRounds);
		this.wonCashAmount = wonCashAmount;
		this.numberOfWonFreeRounds = numberOfWonFreeRounds;
	}

	private void validate(int wonCashAmount, int numberOfWonFreeRounds) {
		if (wonCashAmount < 0) {
			throw new IllegalArgumentException("Won cash amount cannot be lower than 0");
		}
		if (numberOfWonFreeRounds < 0) {
			throw new IllegalArgumentException("Won free rounds amount  cannot be lower than 0");
		}
	}

	public int getWonCashAmount() {
		return wonCashAmount;
	}

	public int getNumberOfWonFreeRounds() {
		return numberOfWonFreeRounds;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Outcome outcome = (Outcome) o;
		return wonCashAmount == outcome.wonCashAmount && numberOfWonFreeRounds == outcome.numberOfWonFreeRounds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(wonCashAmount, numberOfWonFreeRounds);
	}
}
