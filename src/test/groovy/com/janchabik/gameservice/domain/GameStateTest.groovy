package com.janchabik.gameservice.domain

import com.janchabik.gameservice.domain.betdeduction.FreeRoundBetDeductionPolicy
import com.janchabik.gameservice.domain.betdeduction.PlayingForCashRoundBetDeductionPolicy
import com.janchabik.gameservice.domain.betdeduction.PlayingForFreeBetDeductionPolicy
import com.janchabik.gameservice.domain.model.GameState
import spock.lang.Specification
import spock.lang.Unroll

class GameStateTest extends Specification {
	
	def 'creating should result in new created with correct values'() {
		given:
		def cashBalance = 100;
		def numberOfFreeRounds = 4
		when:
		def gameState = new GameState(1, cashBalance, numberOfFreeRounds)
		then:
		noExceptionThrown()
		gameState.getCashBalance() == cashBalance
		gameState.getNumberOfFreeRounds() == numberOfFreeRounds
		gameState.getGameId() == 1
	}
	
	def 'creating with negative balance should throw IllegalArgumentException'() {
		when:
		new GameState(1, -1, 0)
		then:
		thrown(IllegalArgumentException.class)
	}
	
	def 'creating with 0 cash doesnt throw'() {
		given:
		def cashBalance = 0;
		def numberOfFreeRounds = 4
		when:
		new GameState(1, cashBalance, numberOfFreeRounds)
		then:
		noExceptionThrown()
	}
	
	def 'applying round outcome should result in state change for GameState'() {
		given:
		def initialCash = 100;
		def initialFreeRounds = 4
		def wonRounds = 2
		def wonCash = 30
		def gameState = new GameState(1, initialCash, initialFreeRounds)
		when:
		gameState.applyRoundOutCome(wonCash, wonRounds)
		then:
		gameState.getNumberOfFreeRounds() == initialFreeRounds + wonRounds
		gameState.getCashBalance() == initialCash + wonCash
	}
	
	def 'applying round outcome negative cash balance and 0 won free rounds should result in state change for GameState'() {
		given:
		def initialCash = 100;
		def initialFreeRounds = 4
		def wonRounds = 0
		def cashBalanceDifference = -50
		def gameState = new GameState(1, initialCash, initialFreeRounds)
		when:
		gameState.applyRoundOutCome(cashBalanceDifference, wonRounds)
		then:
		gameState.getNumberOfFreeRounds() == initialFreeRounds + wonRounds
		gameState.getCashBalance() == initialCash + cashBalanceDifference
	}
	
	def 'applying a round outcome that would result in negative cash balance should throw IllegalArgumentException'() {
		given:
		def initialCash = 100;
		def initialFreeRounds = 4
		def wonRounds = 0
		def cashBalanceDifference = -500000
		def gameState = new GameState(1, initialCash, initialFreeRounds)
		when:
		gameState.applyRoundOutCome(cashBalanceDifference, wonRounds)
		then:
		thrown(IllegalArgumentException.class)
	}
	
	
	@Unroll
	def 'betDeductionPolicy should be calculated correctly'() {
		expect:
		new GameState(1, 100, freeRounds).calculateBetDeductionPolicy(isFreePlay) == betCalculationPolicy
		where:
		freeRounds | isFreePlay || betCalculationPolicy
		0          | true       || PlayingForFreeBetDeductionPolicy.INSTANCE
		1          | true       || PlayingForFreeBetDeductionPolicy.INSTANCE
		1          | false      || FreeRoundBetDeductionPolicy.INSTANCE
		0          | false      || PlayingForCashRoundBetDeductionPolicy.INSTANCE
	}
	
	def 'calculating a betDeductionPolicy of FreeRoundBetDeductionPolicy should decrease free round number by 1'() {
		given:
		def freeRounds = 3
		def gameState = new GameState(1, 200, freeRounds)
		when:
		def policy = gameState.calculateBetDeductionPolicy(false)
		then:
		policy == FreeRoundBetDeductionPolicy.INSTANCE
		gameState.getNumberOfFreeRounds() == freeRounds - 1
	}
}
