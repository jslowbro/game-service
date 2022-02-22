package com.janchabik.gameservice.domain.services;

import com.janchabik.gameservice.domain.Round;

public interface RoundRepository {

	void save(Round round);

	Round playNewRound(String gameId);

}
