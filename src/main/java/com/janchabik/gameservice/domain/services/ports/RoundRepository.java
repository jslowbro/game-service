package com.janchabik.gameservice.domain.services.ports;

import com.janchabik.gameservice.domain.model.GameState;
import com.janchabik.gameservice.domain.model.GameStateMemento;
import com.janchabik.gameservice.domain.model.Round;

public interface RoundRepository {

	void save(Round round, GameStateMemento gameStateMemento);

	Round newRound(int gameId);

}
