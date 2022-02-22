package com.janchabik.gameservice.infrastructure.repositories;

import com.janchabik.gameservice.UserContext;
import com.janchabik.gameservice.domain.model.GameState;
import com.janchabik.gameservice.domain.services.ports.GameStateRepository;
import com.janchabik.gameservice.infrastructure.configurable.GameConfigurationProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameStateRepositoryImpl implements GameStateRepository {

	private final Map<String, GameState> gameStateMap = new HashMap<>();

	private final GameConfigurationProvider gameConfigurationProvider;

	@Autowired
	public GameStateRepositoryImpl(GameConfigurationProvider gameConfigurationProvider) {
		this.gameConfigurationProvider = gameConfigurationProvider;
	}

	@Override
	public Optional<GameState> findGameForUser(String userId) {
		return Optional.ofNullable(gameStateMap.get(userId));
	}

	@Override
	public GameState newGame() {
		GameState gameState = new GameState(
				gameStateMap.size(),
				gameConfigurationProvider.getStartingCashBalance(),
				gameConfigurationProvider.getStartingFreeRounds()
		);
		String userId = UserContext.getUserId();
		gameStateMap.put(userId, gameState);
		return gameState;
	}

	@Override
	public GameState save(GameState gameState) {
		return gameStateMap.put(UserContext.getUserId(), gameState);
	}
}
