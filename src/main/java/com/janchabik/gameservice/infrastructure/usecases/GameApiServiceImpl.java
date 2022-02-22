package com.janchabik.gameservice.infrastructure.usecases;

import com.janchabik.gameservice.api.GameApiService;
import com.janchabik.gameservice.api.GameStateResponse;
import com.janchabik.gameservice.api.RoundApiEvent;
import com.janchabik.gameservice.domain.model.GameStateMemento;
import com.janchabik.gameservice.domain.model.RoundEvent;
import com.janchabik.gameservice.domain.services.domain.GameService;
import com.janchabik.gameservice.domain.services.ports.RoundRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameApiServiceImpl implements GameApiService {

	private final GameService gameService;

	private final RoundRepository roundRepository;

	@Autowired
	public GameApiServiceImpl(GameService gameService, RoundRepository roundRepository) {
		this.gameService = gameService;
		this.roundRepository = roundRepository;
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

	@Override
	public List<RoundApiEvent> getRoundEventsForPlayer(String userId) {
		return from(roundRepository.getRoundEventsForPlayer(userId));
	}

	@Override
	public List<RoundApiEvent> getRoundEventsForGame(int gameId) {
		return from(roundRepository.getRoundEventsForGame(gameId));
	}

	@Override
	public List<RoundApiEvent> getRoundEventsForRound(int roundId) {
		return from(roundRepository.getRoundEventsForRound(roundId));
	}

	private GameStateResponse toResponse(GameStateMemento gameStateMemento) {
		return new GameStateResponse(
				gameStateMemento.getGameId(),
				gameStateMemento.getNumberOfFreeRounds(),
				gameStateMemento.getCashBalance()
		);
	}

	private List<RoundApiEvent> from(List<RoundEvent> roundEvents) {
		return roundEvents.stream().map(this::toRoundApiEvent).collect(Collectors.toList());
	}

	private RoundApiEvent toRoundApiEvent(RoundEvent event) {
		switch (event.getType()) {
			case BALANCE_DEDUCTED:
				return new RoundApiEvent.BalanceDeductedEvent(((RoundEvent.BalanceDeductedEvent) event).getBalanceDeducted());
			case CASH_WON:
				return new RoundApiEvent.CashWonEvent(((RoundEvent.CashWonEvent) event).getCashWonAmount());
			case FREE_ROUNDS_WON:
				return new RoundApiEvent.FreeRoundsWonEvent(((RoundEvent.FreeRoundsWonEvent) event).getFreeRoundsWon());
			default:
				throw new IllegalStateException("Unexpected value: " + event.getType());
		}
	}
}
