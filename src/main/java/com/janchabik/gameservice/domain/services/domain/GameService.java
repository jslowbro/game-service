package com.janchabik.gameservice.domain.services.domain;

import com.janchabik.gameservice.domain.model.GameStateMemento;

public interface GameService {

	GameStateMemento startGame();

	GameStateMemento playFreeRound(int betAmount);

	GameStateMemento playCashRound(int betAmount);
}
