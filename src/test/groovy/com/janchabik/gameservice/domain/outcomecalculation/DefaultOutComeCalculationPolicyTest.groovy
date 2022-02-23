package com.janchabik.gameservice.domain.outcomecalculation

import com.janchabik.gameservice.domain.model.outcomecalculation.DefaultOutComeCalculationPolicy
import com.janchabik.gameservice.domain.model.outcomecalculation.Outcome
import com.janchabik.gameservice.domain.services.ports.RandomNumberProvider
import spock.lang.Specification
import spock.lang.Unroll

class DefaultOutComeCalculationPolicyTest extends Specification {
	
	@Unroll
	def 'should correctly calculate outcome'() {
		expect:
		new DefaultOutComeCalculationPolicy(mockRandomNumberProvider(firstRandom, secondRandom)).calculateGameOutCome(betAmount) == new Outcome(cashPrize, freeRoundsWon)
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
	
	def 'shouldnt throw when RNG supplies a number between 1 and 100'() {
		when:
		for (int i = 1; i <= 100; i++) {
			new DefaultOutComeCalculationPolicy(mockRandomNumberProvider(i, i)).calculateGameOutCome(i)
		}
		then:
		noExceptionThrown()
		
		
	}
	
	def mockRandomNumberProvider(int firstNumber, int secondNumber) {
		return Mock(RandomNumberProvider) {
			1 * randomNumber(_, _) >> firstNumber
			1 * randomNumber(_, _) >> secondNumber
		}
	}
}

