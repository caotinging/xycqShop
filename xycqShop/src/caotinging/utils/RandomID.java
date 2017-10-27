package caotinging.utils;

import java.util.UUID;

public class RandomID {
	
	public static String getRandomID() {
		String randomUUID = UUID.randomUUID().toString();
		randomUUID = randomUUID.replace("-", "");
		return randomUUID;
	}
}
