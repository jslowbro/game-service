package com.janchabik.gameservice.rest;

import com.janchabik.gameservice.UserContext;
import com.janchabik.gameservice.api.GameApiService;
import com.janchabik.gameservice.api.GameStateDTO;
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
	public GameStateDTO startGame(@RequestBody GameStartMessage gameStartMessage) {
		UserContext.setContext(gameStartMessage.getUserId());
		return gameApiService.startGame();
	}

	@PostMapping("/game/round/playForCash")
	public GameStateDTO playRoundForCash(@RequestBody PlayRoundRequest playRoundRequest) {
		UserContext.setContext(playRoundRequest.getUserId());
		return gameApiService.playCashRound(playRoundRequest.getBetAmount());
	}


	@PostMapping("/game/round/playForFree")
	public GameStateDTO playRoundForFree(@RequestBody PlayRoundRequest playRoundRequest) {
		UserContext.setContext(playRoundRequest.getUserId());
		return gameApiService.playFreeRound(playRoundRequest.getBetAmount());
	}
}
