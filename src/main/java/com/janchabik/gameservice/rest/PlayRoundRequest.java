package com.janchabik.gameservice.rest;

import java.util.Objects;

public class PlayRoundRequest {

	public String userId;

	public int betAmount;

	public PlayRoundRequest(String userId, int betAmount) {
		this.userId = userId;
		this.betAmount = betAmount;
	}

	public PlayRoundRequest() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PlayRoundRequest that = (PlayRoundRequest) o;
		return betAmount == that.betAmount && Objects.equals(userId, that.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, betAmount);
	}
}
