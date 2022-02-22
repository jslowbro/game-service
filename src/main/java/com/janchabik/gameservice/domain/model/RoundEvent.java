package com.janchabik.gameservice.domain.model;

// TODO add timestamps for better tracking
public interface RoundEvent {

	Type getType();

	enum Type {
		BALANCE_DEDUCTED, CASH_WON, FREE_ROUNDS_WON
	}

	class BalanceDeductedEvent implements RoundEvent {

		private static final Type type = Type.BALANCE_DEDUCTED;

		private final int balanceDeducted;

		public BalanceDeductedEvent(int balanceDeducted) {

			this.balanceDeducted = balanceDeducted;
		}

		public int getBalanceDeducted() {
			return balanceDeducted;
		}

		@Override
		public Type getType() {
			return type;
		}
	}

	class CashWonEvent implements RoundEvent {

		private static final Type type = Type.CASH_WON;

		private final int cashWonAmount;

		public CashWonEvent(int cashWonAmount) {
			this.cashWonAmount = cashWonAmount;
		}

		@Override
		public Type getType() {
			return type;
		}

		public int getCashWonAmount() {
			return cashWonAmount;
		}
	}

	class FreeRoundsWonEvent implements RoundEvent {

		private static final Type type = Type.FREE_ROUNDS_WON;

		private final int freeRoundsWon;

		public FreeRoundsWonEvent(int freeRoundsWon) {
			this.freeRoundsWon = freeRoundsWon;
		}

		public int getFreeRoundsWon() {
			return freeRoundsWon;
		}

		@Override
		public Type getType() {
			return type;
		}
	}

}
