package com.janchabik.gameservice.infrastructure.repositories;

import java.util.Objects;

public class RoundEntity {

	private final String userId;

	private final int roundId;

	private final int gameId;

	public RoundEntity(String userId, int roundId, int gameId) {
		this.userId = userId;
		this.roundId = roundId;
		this.gameId = gameId;
	}

	public String getUserId() {
		return userId;
	}

	public int getRoundId() {
		return roundId;
	}

	public int getGameId() {
		return gameId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RoundEntity that = (RoundEntity) o;
		return roundId == that.roundId && gameId == that.gameId &&
				Objects.equals(userId, that.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, roundId, gameId);
	}
}
