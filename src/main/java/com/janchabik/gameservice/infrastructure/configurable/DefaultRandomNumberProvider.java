package com.janchabik.gameservice.infrastructure.configurable;

import com.janchabik.gameservice.domain.services.ports.RandomNumberProvider;
import java.util.Random;

/*
 * This service has been added mostly for testing purposes.
 * Injecting this component allows testing for different random numbers, instead of relying
 * on java.util.Random someday generating a number that will break a given test case;
 * */
public class DefaultRandomNumberProvider implements RandomNumberProvider {

	public static final DefaultRandomNumberProvider INSTANCE = new DefaultRandomNumberProvider();

	private DefaultRandomNumberProvider() {

	}

	@Override
	public int randomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}
}
