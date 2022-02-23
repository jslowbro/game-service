package com.janchabik.gameservice.domain.services.ports;

import com.janchabik.gameservice.domain.model.Round;
import com.janchabik.gameservice.domain.model.RoundEvent;
import com.janchabik.gameservice.infrastructure.repositories.RoundEntity;
import java.util.List;
import java.util.Map;

public interface RoundRepository {

	void save(Round round, int gameId);

	Round newRound(int gameId);

}
