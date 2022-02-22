package com.janchabik.gameservice.api;

import java.util.List;

public interface GameApiService {

	GameStateResponse startGame();

	GameStateResponse playFreeRound(int betAmount);

	GameStateResponse playCashRound(int betAmount);

	List<RoundApiEvent> getRoundEventsForPlayer(String userId);

	List<RoundApiEvent> getRoundEventsForGame(int gameId);

	List<RoundApiEvent> getRoundEventsForRound(int roundId);
}
