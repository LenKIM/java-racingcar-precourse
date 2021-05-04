package utils;

import java.util.Objects;

public class Assertions {

	public static void requiredNonNull(Object object, String exceptionMessage) {
		if (Objects.isNull(object)){
			throw new IllegalArgumentException(exceptionMessage);
		}
	}

	public static void requiredNonEmpty(String string, String exceptionMessage) {
		if (string.trim().isEmpty()){
			throw new IllegalArgumentException(exceptionMessage);
		}
	}
}
