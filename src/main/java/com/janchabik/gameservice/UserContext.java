package com.janchabik.gameservice;

public class UserContext {

	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	private UserContext() {
	}

	public static void setContext(String userId) {
		threadLocal.set(userId);
	}

	public static String getUserId() {
		return threadLocal.get();
	}
}
