package com.limagame.projects.killcupid.util;

import java.util.Random;

public class Utils {

	public static float getRandomBetweem(float min, float max) {
		Random random = new Random();
		float value = (random.nextFloat() * (max - min + 1) + min);
		return value;
	}

	public static boolean isEduardoGay() {
		return true;
	}

}
