package com.janchabik.gameservice.api;

import java.util.List;
import java.util.Objects;

public class RoundDTO {

	private final int roundId;

	private final int gameId;

	private final String userId;

	private final List<RoundApiEvent> roundApiEventList;

	private final GameStateDTO stateAfterRound;

	public RoundDTO(int roundId, int gameId, String userId, List<RoundApiEvent> roundApiEventList, GameStateDTO stateAfterRound) {
		this.roundId = roundId;
		this.gameId = gameId;
		this.userId = userId;
		this.roundApiEventList = roundApiEventList;
		this.stateAfterRound = stateAfterRound;
	}

	public int getRoundId() {
		return roundId;
	}

	public List<RoundApiEvent> getRoundApiEventList() {
		return roundApiEventList;
	}

	public int getGameId() {
		return gameId;
	}

	public String getUserId() {
		return userId;
	}

	public GameStateDTO getStateAfterRound() {
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
		RoundDTO roundDTO = (RoundDTO) o;
		return roundId == roundDTO.roundId && gameId == roundDTO.gameId && Objects.equals(userId, roundDTO.userId) && Objects.equals(roundApiEventList,
				roundDTO.roundApiEventList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(roundId, gameId, userId, roundApiEventList);
	}
}
