package com.janchabik.gameservice.domain.services.domain;

import com.janchabik.gameservice.UserContext;
import com.janchabik.gameservice.domain.model.GameState;
import com.janchabik.gameservice.domain.model.GameStateMemento;
import com.janchabik.gameservice.domain.model.Round;
import com.janchabik.gameservice.domain.services.ports.GameStateRepository;
import com.janchabik.gameservice.domain.services.ports.OutComeCalculationStrategyFactory;
import com.janchabik.gameservice.domain.services.ports.RoundRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DomainGameService implements GameService {

	private final GameStateRepository gameStateRepository;

	private final RoundRepository roundRepository;

	private final OutComeCalculationStrategyFactory outComeCalculationStrategyFactory;

	@Autowired
	public DomainGameService(
			GameStateRepository gameStateRepository,
			RoundRepository roundRepository,
			OutComeCalculationStrategyFactory outComeCalculationStrategyFactory
	) {
		this.gameStateRepository = gameStateRepository;
		this.roundRepository = roundRepository;
		this.outComeCalculationStrategyFactory = outComeCalculationStrategyFactory;
	}

	@Override
	public GameStateMemento startGame() {
		String userId = UserContext.getUserId();
		Optional<GameState> gameState = gameStateRepository.findGameForUser(userId);
		if (gameState.isPresent()) {
			throw new IllegalArgumentException("Game already started for player" + userId);
		} else {
			return gameStateRepository.newGame().memento();
		}
	}

	@Override
	public GameStateMemento playFreeRound(int betAmount) {
		return playRound(betAmount, true);
	}

	@Override
	public GameStateMemento playCashRound(int betAmount) {
		return playRound(betAmount, false);
	}

	private GameStateMemento playRound(int betAmount, boolean isFree) {
		String userId = UserContext.getUserId();
		Optional<GameState> gameState = gameStateRepository.findGameForUser(userId);
		if (gameState.isPresent()) {
			return playRound(gameState.get(), betAmount, isFree);
		} else {
			throw new IllegalArgumentException("No game found for player" + userId);
		}
	}

	private GameStateMemento playRound(GameState gameState, int betAmount, boolean isFree) {
		Round round = roundRepository.newRound(gameState.getGameId());
		round.playRound(betAmount, gameState.calculateBetDeductionPolicy(isFree), outComeCalculationStrategyFactory.getOutComeCalculationStrategy());
		gameState.applyRoundOutCome(round.getCashDifferenceAfterRound(), round.getNumberOfFreeRoundsWon());
		save(gameState, round);
		return gameState.memento();
	}

	@Transactional
	void save(GameState gameState, Round round) {
		gameStateRepository.save(gameState);
		roundRepository.save(round, gameState.getGameId());
	}

}
