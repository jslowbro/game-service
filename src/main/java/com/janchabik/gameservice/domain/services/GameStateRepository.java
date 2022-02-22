package com.janchabik.gameservice.domain.services;

import com.janchabik.gameservice.domain.GameState;
import java.util.Optional;

public interface GameStateRepository {

	Optional<GameState> findGameForUser(String userId);

	GameState newGame(String userId);

	GameState save(GameState gameState);

}
