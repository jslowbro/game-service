package com.janchabik.gameservice.infrastructure.configurable;

import com.janchabik.gameservice.domain.model.outcomecalculation.DefaultOutComeCalculationStrategy;
import com.janchabik.gameservice.domain.model.outcomecalculation.OutComeCalculationStrategy;
import com.janchabik.gameservice.domain.services.ports.OutComeCalculationStrategyFactory;
import org.springframework.stereotype.Component;

@Component
public class OutComeCalculationStrategyFactoryImpl implements OutComeCalculationStrategyFactory {

	@Override
	public OutComeCalculationStrategy getOutComeCalculationStrategy() {
		return new DefaultOutComeCalculationStrategy(DefaultRandomNumberProvider.INSTANCE);
	}
}
