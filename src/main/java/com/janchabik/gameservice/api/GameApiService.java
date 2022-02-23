package com.janchabik.gameservice.api;

public interface GameApiService {

	GameStateResponse startGame();

	GameStateResponse playFreeRound(int betAmount);

	GameStateResponse playCashRound(int betAmount);

}
