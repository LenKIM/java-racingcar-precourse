package racingcar;

public class RoundNumber {

	private String value;

	private RoundNumber(String value) {
		this.value = value;
	}

	public static RoundNumber valueOf(String value) {
		return new RoundNumber(value);
	}

	public static RoundNumber valueOf(Integer value) {
		return new RoundNumber(String.valueOf(value));
	}

	public int getCount() {

		return Integer.parseInt(value);
	}
}
