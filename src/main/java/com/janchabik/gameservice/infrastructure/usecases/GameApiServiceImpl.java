package com.janchabik.gameservice.infrastructure.usecases;

import com.janchabik.gameservice.api.GameApiService;
import com.janchabik.gameservice.api.GameStateResponse;
import com.janchabik.gameservice.domain.model.GameStateMemento;
import com.janchabik.gameservice.domain.services.domain.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameApiServiceImpl implements GameApiService {

	private final GameService gameService;


	@Autowired
	public GameApiServiceImpl(
			GameService gameService
	) {
		this.gameService = gameService;
	}

	@Override
	public GameStateResponse startGame() {
		return toResponse(gameService.startGame());
	}

	@Override
	public GameStateResponse playFreeRound(int betAmount) {
		return toResponse(gameService.playFreeRound(betAmount));
	}

	@Override
	public GameStateResponse playCashRound(int betAmount) {
		return toResponse(gameService.playCashRound(betAmount));
	}

	private GameStateResponse toResponse(GameStateMemento gameStateMemento) {
		return new GameStateResponse(
				gameStateMemento.getGameId(),
				gameStateMemento.getNumberOfFreeRounds(),
				gameStateMemento.getCashBalance()
		);
	}


}
