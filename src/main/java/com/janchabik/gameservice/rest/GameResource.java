package com.janchabik.gameservice.rest;

import com.janchabik.gameservice.UserContext;
import com.janchabik.gameservice.api.GameApiService;
import com.janchabik.gameservice.api.GameStateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameResource {

	private final GameApiService gameApiService;

	public GameResource(GameApiService gameApiService) {
		this.gameApiService = gameApiService;
	}

	@PostMapping("/game/startGame")
	public GameStateResponse startGame(@RequestBody GameStartMessage gameStartMessage) {
		UserContext.setContext(gameStartMessage.getUserId());
		return gameApiService.startGame();
	}

	@PostMapping("/game/round/playForCash")
	public GameStateResponse playRoundForCash(@RequestBody PlayRoundRequest playRoundRequest) {
		UserContext.setContext(playRoundRequest.getUserId());
		return gameApiService.playCashRound(playRoundRequest.getBetAmount());
	}
}
