package com.janchabik.gameservice.api;

import java.util.List;

public interface GameHistoryQueries {


	List<RoundDTO> getRoundEventsForPlayer(String userId);

	List<RoundDTO> getRoundEventsForGame(int gameId);

	List<RoundDTO> getRoundEventsForRound(int roundId);
}
