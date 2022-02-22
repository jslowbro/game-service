package com.janchabik.gameservice.rest;

public class GameStartMessage {

	public String userId;

	public GameStartMessage(String userId) {
		this.userId = userId;
	}

	public GameStartMessage() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
