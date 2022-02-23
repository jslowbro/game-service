package com.janchabik.gameservice.domain.services.ports;

import com.janchabik.gameservice.domain.model.outcomecalculation.OutComeCalculationPolicy;

public interface OutComeCalculationStrategyFactory {

	OutComeCalculationPolicy getOutComeCalculationStrategy();
}
