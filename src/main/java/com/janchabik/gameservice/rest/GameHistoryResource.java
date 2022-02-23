package com.janchabik.gameservice.rest;

import com.janchabik.gameservice.api.GameApiService;
import com.janchabik.gameservice.api.GameHistoryService;
import com.janchabik.gameservice.api.RoundApiEvent;
import com.janchabik.gameservice.api.RoundDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game-history")
public class GameHistoryResource {

	private final GameHistoryService gameApiService;

	@Autowired
	public GameHistoryResource(GameHistoryService gameApiService) {
		this.gameApiService = gameApiService;
	}

	@GetMapping("/user/{userId}")
	public List<RoundDTO> findAllEventsByUserId(@PathVariable String userId) {
		return gameApiService.getRoundEventsForPlayer(userId);
	}

	@GetMapping("/gameId/{gameId}")
	public List<RoundDTO> findAllRoundEventsByGameId(@PathVariable Integer gameId) {
		return gameApiService.getRoundEventsForGame(gameId);
	}

	@GetMapping("/round/{roundId}")
	public List<RoundDTO> findAllEventsByUserId(@PathVariable Integer roundId) {
		return gameApiService.getRoundEventsForRound(roundId);
	}
}
