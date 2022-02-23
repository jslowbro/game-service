package com.janchabik.gameservice.api;

public interface GameApiService {

	GameStateDTO startGame();

	GameStateDTO playFreeRound(int betAmount);

	GameStateDTO playCashRound(int betAmount);

}
