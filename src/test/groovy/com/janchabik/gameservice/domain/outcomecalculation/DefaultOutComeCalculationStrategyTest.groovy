package com.janchabik.gameservice.domain.outcomecalculation

import com.janchabik.gameservice.domain.utils.RandomNumberProvider
import spock.lang.Specification
import spock.lang.Unroll

class DefaultOutComeCalculationStrategyTest extends Specification {
	
	@Unroll
	def 'should correctly calculate outcome'() {
		expect:
		new DefaultOutComeCalculationStrategy(mockRandomNumberProvider(firstRandom, secondRandom)).calculateGameOutCome(betAmount) == new Outcome(cashPrize, freeRoundsWon)
		where:
		firstRandom | secondRandom | betAmount || cashPrize || freeRoundsWon
		60          | 75           | 5         || 0         || 0
		1           | 50           | 10        || 500       || 0
		10          | 1            | 17        || 850       || 1
		11          | 3            | 6         || 60        || 1
		20          | 67           | 8         || 80        || 0
		21          | 67           | 7         || 21        || 0
		30          | 8            | 9         || 27        || 1
		
	}
	
	
	def mockRandomNumberProvider(int firstNumber, int secondNumber) {
		return Mock(RandomNumberProvider) {
			1 * randomNumber(_, _) >> firstNumber
			1 * randomNumber(_, _) >> secondNumber
		}
	}
}

