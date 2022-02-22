package com.janchabik.gameservice.infrastructure.repositories;

import com.janchabik.gameservice.UserContext;
import com.janchabik.gameservice.domain.model.Round;
import com.janchabik.gameservice.domain.model.RoundEvent;
import com.janchabik.gameservice.domain.services.ports.RoundRepository;
import com.janchabik.gameservice.infrastructure.configurable.GameConfigurationProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoundRepositoryImpl implements RoundRepository {

	private final Map<RoundEntity, List<RoundEvent>> roundMap = new HashMap<>();

	private final GameConfigurationProvider gameConfigurationProvider;

	@Autowired
	public RoundRepositoryImpl(GameConfigurationProvider gameConfigurationProvider) {
		this.gameConfigurationProvider = gameConfigurationProvider;
	}

	@Override
	public void save(Round round, int gameId) {
		RoundEntity roundEntity = new RoundEntity(UserContext.getUserId(), round.getRoundId(), gameId);
		roundMap.put(roundEntity, round.flushEvents());
	}

	@Override
	public Round newRound(int gameId) {
		RoundEntity roundEntity = new RoundEntity(UserContext.getUserId(), roundMap.size(), gameId);
		roundMap.put(roundEntity, new ArrayList<>());
		return new Round(roundEntity.getRoundId(), gameConfigurationProvider.getMinBet(), gameConfigurationProvider.getMaxBet());
	}

	@Override
	public List<RoundEvent> getRoundEventsForPlayer(String userId) {
		return roundMap.entrySet().stream()
				.filter(e -> e.getKey().getUserId().equals(userId))
				.flatMap(e -> e.getValue().stream())
				.collect(Collectors.toList());
	}

	@Override
	public List<RoundEvent> getRoundEventsForGame(int gameId) {
		return roundMap.entrySet().stream()
				.filter(e -> e.getKey().getGameId() == gameId)
				.flatMap(e -> e.getValue().stream())
				.collect(Collectors.toList());
	}

	@Override
	public List<RoundEvent> getRoundEventsForRound(int roundId) {
		return roundMap.entrySet().stream()
				.filter(e -> e.getKey().getGameId() == roundId)
				.flatMap(e -> e.getValue().stream())
				.collect(Collectors.toList());
	}
}
