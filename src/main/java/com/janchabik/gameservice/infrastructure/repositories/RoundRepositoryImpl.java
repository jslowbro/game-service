package com.janchabik.gameservice.infrastructure.repositories;

import com.janchabik.gameservice.UserContext;
import com.janchabik.gameservice.api.GameHistoryQueries;
import com.janchabik.gameservice.api.GameStateDTO;
import com.janchabik.gameservice.api.RoundApiEvent;
import com.janchabik.gameservice.api.RoundDTO;
import com.janchabik.gameservice.domain.model.GameStateMemento;
import com.janchabik.gameservice.domain.model.Round;
import com.janchabik.gameservice.domain.model.RoundEvent;
import com.janchabik.gameservice.domain.services.ports.RoundRepository;
import com.janchabik.gameservice.infrastructure.configurable.GameConfigurationProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoundRepositoryImpl implements RoundRepository, GameHistoryQueries {

	private final Map<RoundEntity, List<RoundEvent>> roundMap = new HashMap<>();

	private final GameConfigurationProvider gameConfigurationProvider;

	@Autowired
	public RoundRepositoryImpl(GameConfigurationProvider gameConfigurationProvider) {
		this.gameConfigurationProvider = gameConfigurationProvider;
	}

	@Override
	public void save(Round round, GameStateMemento gameStateMemento) {
		RoundEntity roundEntity = new RoundEntity(UserContext.getUserId(), round.getRoundId(), gameStateMemento.getGameId(), gameStateMemento);
		roundMap.put(roundEntity, round.flushEvents());
	}

	@Override
	public Round newRound(int gameId) {
		return new Round(roundMap.size(), gameConfigurationProvider.getMinBet(), gameConfigurationProvider.getMaxBet());
	}

	@Override
	public List<RoundDTO> getRoundEventsForPlayer(String userId) {
		return findRoundsByPredicate(e -> e.getKey().getUserId().equals(userId));
	}

	@Override
	public List<RoundDTO> getRoundEventsForGame(int gameId) {
		return findRoundsByPredicate(e -> e.getKey().getGameId() == gameId);
	}

	@Override
	public List<RoundDTO> getRoundEventsForRound(int roundId) {
		return findRoundsByPredicate(e -> e.getKey().getRoundId() == roundId);
	}

	private List<RoundDTO> findRoundsByPredicate(Predicate<Map.Entry<RoundEntity, List<RoundEvent>>> predicate) {
		return roundMap.entrySet().stream()
				.filter(predicate)
				.map(
						e -> new RoundDTO(
								e.getKey().getRoundId(),
								e.getKey().getGameId(),
								e.getKey().getUserId(),
								from(e.getValue()),
								toDTO(e.getKey().getStateAfterRound()))
				).collect(Collectors.toList());
	}

	private GameStateDTO toDTO(GameStateMemento stateAfterRound) {
		return new GameStateDTO(stateAfterRound.getGameId(), stateAfterRound.getNumberOfFreeRounds(), stateAfterRound.getCashBalance());
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
