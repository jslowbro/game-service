package com.janchabik.gameservice.domain.services.domain;

import com.janchabik.gameservice.domain.model.GameStateMemento;

public interface GameService {

	GameStateMemento startGame(String userId);

	GameStateMemento playFreeRound(String userId, int betAmount);

	GameStateMemento playCashRound(String userId, int betAmount);
}
