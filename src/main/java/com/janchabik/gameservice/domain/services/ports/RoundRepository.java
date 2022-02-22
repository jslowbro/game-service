package com.janchabik.gameservice.domain.services.ports;

import com.janchabik.gameservice.domain.model.Round;
import com.janchabik.gameservice.domain.model.RoundEvent;
import java.util.List;

public interface RoundRepository {

	void save(Round round, int gameId);

	Round newRound(int gameId);

	List<RoundEvent> getRoundEventsForPlayer(String userId);

	List<RoundEvent> getRoundEventsForGame(int gameId);

	List<RoundEvent> getRoundEventsForRound(int roundId);

}
