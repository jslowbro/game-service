package com.janchabik.gameservice.infrastructure.repositories;

import com.janchabik.gameservice.domain.model.GameStateMemento;
import java.util.Objects;

public class RoundEntity {

	private final String userId;

	private final int roundId;

	private final int gameId;

	private final GameStateMemento stateAfterRound;

	public RoundEntity(String userId, int roundId, int gameId, GameStateMemento stateAfterRound) {
		this.userId = userId;
		this.roundId = roundId;
		this.gameId = gameId;
		this.stateAfterRound = stateAfterRound;
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

	public GameStateMemento getStateAfterRound() {
		return stateAfterRound;
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
		return roundId == that.roundId && gameId == that.gameId && Objects.equals(userId, that.userId) && Objects.equals(stateAfterRound, that.stateAfterRound);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, roundId, gameId, stateAfterRound);
	}
}
