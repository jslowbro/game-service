package com.janchabik.gameservice.rest;

import com.janchabik.gameservice.UserContext;
import com.janchabik.gameservice.api.GameApiService;
import com.janchabik.gameservice.api.GameStateResponse;
import org.springframework.web.bind.annotation.GetMapping;
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
	public GameStateResponse gameStateResponse(@RequestBody GameStartMessage gameStartMessage) {
		UserContext.setContext(gameStartMessage.userId);
		return gameApiService.startGame();
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello world";
	}
}
