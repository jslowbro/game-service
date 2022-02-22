package com.janchabik.gameservice.domain

import com.janchabik.gameservice.domain.betdeduction.FreeRoundBetDeductionPolicy
import com.janchabik.gameservice.domain.betdeduction.PlayingForCashRoundBetDeductionPolicy
import com.janchabik.gameservice.domain.betdeduction.PlayingForFreeBetDeductionPolicy
import com.janchabik.gameservice.domain.outcomecalculation.OutComeCalculationStrategy
import com.janchabik.gameservice.domain.outcomecalculation.Outcome
import spock.lang.Specification
import spock.lang.Unroll

class RoundTest extends Specification {
	
	@Unroll
	def 'should correctly calculate round outcome'() {
		expect:
		def round = new Round(1, 1, 10)
		round.playRound(betAmount, betDeductionPolicy, mockOutComeCalculationStrategy)
		round.getCashDifferenceAfterRound() == cashDifference
		round.getNumberOfFreeRoundsWon() == numberOfFreeRoundsWon
		where:
		betAmount | betDeductionPolicy                             | mockOutComeCalculationStrategy  || cashDifference || numberOfFreeRoundsWon
		10        | PlayingForCashRoundBetDeductionPolicy.INSTANCE | mockOutComeCalcStrategy(150, 1) || 140            || 1
		10        | PlayingForCashRoundBetDeductionPolicy.INSTANCE | mockOutComeCalcStrategy(200, 0) || 190            || 0
		10        | PlayingForCashRoundBetDeductionPolicy.INSTANCE | mockOutComeCalcStrategy(0, 1)   || -10            || 1
		10        | PlayingForCashRoundBetDeductionPolicy.INSTANCE | mockOutComeCalcStrategy(0, 0)   || -10            || 0
		5         | PlayingForFreeBetDeductionPolicy.INSTANCE      | mockOutComeCalcStrategy(40, 0)  || 40             || 0
		10        | PlayingForFreeBetDeductionPolicy.INSTANCE      | mockOutComeCalcStrategy(150, 4) || 150            || 4
		9         | FreeRoundBetDeductionPolicy.INSTANCE           | mockOutComeCalcStrategy(80, 0)  || 80             || 0
		9         | FreeRoundBetDeductionPolicy.INSTANCE           | mockOutComeCalcStrategy(80, 8)  || 80             || 8
	}
	
	
	def 'should throw IllegalArgumentException when minBetAmount is lower than 0'() {
		when:
		new Round(1, -10, 10)
		then:
		thrown(IllegalArgumentException.class)
	}
	
	def 'should throw IllegalArgumentException when minBetAmount is 0'() {
		when:
		new Round(1, 0, 10)
		then:
		thrown(IllegalArgumentException.class)
	}
	
	def 'should throw IllegalArgumentException when maxBetAmount is 0'() {
		when:
		new Round(1, 1, 0)
		then:
		thrown(IllegalArgumentException.class)
	}
	
	def 'should throw IllegalArgumentException when maxBetAmount is lower than 0'() {
		when:
		new Round(1, 1, 0)
		then:
		thrown(IllegalArgumentException.class)
	}
	
	def 'should throw IllegalArgumentException when maxBetAmount is lower than minBetAmount'() {
		when:
		new Round(1, 1, 0)
		then:
		thrown(IllegalArgumentException.class)
	}
	
	
	
	def mockOutComeCalcStrategy(int cashPrize, int freeGamesWon) {
		return Mock(OutComeCalculationStrategy) {
			calculateGameOutCome(_) >> new Outcome(cashPrize, freeGamesWon)
		}
	}
}
