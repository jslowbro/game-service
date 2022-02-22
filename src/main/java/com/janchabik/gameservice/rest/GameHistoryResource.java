package com.janchabik.gameservice.rest;

import com.janchabik.gameservice.api.GameApiService;
import com.janchabik.gameservice.api.RoundApiEvent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game-history")
public class GameHistoryResource {

	private final GameApiService gameApiService;

	@Autowired
	public GameHistoryResource(GameApiService gameApiService) {
		this.gameApiService = gameApiService;
	}

	@GetMapping("/user/{userId}")
	public List<RoundApiEvent> findAllEventsByUserId(@PathVariable String userId) {
		return gameApiService.getRoundEventsForPlayer(userId);
	}

	@GetMapping("/gameId/{gameId}")
	public List<RoundApiEvent> findAllRoundEventsByGameId(@PathVariable Integer gameId) {
		return gameApiService.getRoundEventsForGame(gameId);
	}

	@GetMapping("/round/{roundId}")
	public List<RoundApiEvent> findAllEventsByUserId(@PathVariable Integer roundId) {
		return gameApiService.getRoundEventsForRound(roundId);
	}
}
