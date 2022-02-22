package com.janchabik.gameservice.domain;

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
	}

	class RoundsWonEvent implements RoundEvent {

		private static final Type type = Type.FREE_ROUNDS_WON;

		private final int freeRoundsWon;

		public RoundsWonEvent(int freeRoundsWon) {
			this.freeRoundsWon = freeRoundsWon;
		}

		@Override
		public Type getType() {
			return type;
		}
	}

}
