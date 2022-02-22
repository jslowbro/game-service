package com.janchabik.gameservice.domain.services.ports;

import com.janchabik.gameservice.domain.model.Round;

public interface RoundRepository {

	void save(Round round);

	Round newRound(int gameId);

}
