package com.janchabik.gameservice.rest;

import com.janchabik.gameservice.api.GameHistoryQueries;
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

	private final GameHistoryQueries gameHistoryService;

	@Autowired
	public GameHistoryResource(GameHistoryQueries gameApiService) {
		this.gameHistoryService = gameApiService;
	}

	@GetMapping("/user/{userId}")
	public List<RoundDTO> findAllEventsByUserId(@PathVariable String userId) {
		return gameHistoryService.getRoundEventsForPlayer(userId);
	}

	@GetMapping("/gameId/{gameId}")
	public List<RoundDTO> findAllRoundEventsByGameId(@PathVariable Integer gameId) {
		return gameHistoryService.getRoundEventsForGame(gameId);
	}

	@GetMapping("/roundId/{roundId}")
	public List<RoundDTO> findAllEventsByUserId(@PathVariable Integer roundId) {
		return gameHistoryService.getRoundEventsForRound(roundId);
	}
}
