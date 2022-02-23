package com.janchabik.gameservice.infrastructure.configurable;

import com.janchabik.gameservice.domain.model.outcomecalculation.DefaultOutComeCalculationPolicy;
import com.janchabik.gameservice.domain.model.outcomecalculation.OutComeCalculationPolicy;
import com.janchabik.gameservice.domain.services.ports.OutComeCalculationStrategyFactory;
import org.springframework.stereotype.Component;

@Component
public class OutComeCalculationStrategyFactoryImpl implements OutComeCalculationStrategyFactory {

	@Override
	public OutComeCalculationPolicy getOutComeCalculationStrategy() {
		return new DefaultOutComeCalculationPolicy(DefaultRandomNumberProvider.INSTANCE);
	}
}
