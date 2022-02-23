package com.janchabik.gameservice.api;

public interface RoundApiEvent {

	enum Type {
		BALANCE_DEDUCTED, CASH_WON, FREE_ROUNDS_WON
	}

	class BalanceDeductedEvent implements RoundApiEvent {

		public final Type type = Type.BALANCE_DEDUCTED;

		public final int balanceDeducted;

		public BalanceDeductedEvent(int balanceDeducted) {
			this.balanceDeducted = balanceDeducted;
		}

		public Type getType() {
			return type;
		}

		public int getBalanceDeducted() {
			return balanceDeducted;
		}
	}

	class CashWonEvent implements RoundApiEvent {

		public final Type type = Type.CASH_WON;

		public final int cashWonAmount;

		public CashWonEvent(int cashWonAmount) {
			this.cashWonAmount = cashWonAmount;
		}

		public Type getType() {
			return type;
		}

		public int getCashWonAmount() {
			return cashWonAmount;
		}
	}

	class FreeRoundsWonEvent implements RoundApiEvent {

		public final Type type = Type.FREE_ROUNDS_WON;

		public final int freeRoundsWon;

		public FreeRoundsWonEvent(int freeRoundsWon) {
			this.freeRoundsWon = freeRoundsWon;
		}

		public Type getType() {
			return type;
		}

		public int getFreeRoundsWon() {
			return freeRoundsWon;
		}
	}
}
