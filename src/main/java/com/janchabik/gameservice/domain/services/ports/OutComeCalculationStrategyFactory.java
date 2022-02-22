package com.janchabik.gameservice.domain.services.ports;

import com.janchabik.gameservice.domain.model.outcomecalculation.OutComeCalculationStrategy;

public interface OutComeCalculationStrategyFactory {

	OutComeCalculationStrategy getOutComeCalculationStrategy();
}
