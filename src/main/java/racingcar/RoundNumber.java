package racingcar;

import static utils.Assertions.*;

public class RoundNumber {

	private String value;

	private RoundNumber(String value) {
		setValue(value);
	}

	public static RoundNumber valueOf(String value) {
		return new RoundNumber(value);
	}

	public static RoundNumber valueOf(Integer value) {
		return new RoundNumber(String.valueOf(value));
	}

	private void setValue(String value) {
		requiredNonNull(value, "Round 번호가 없습니다");
		requiredNonEmpty(value, "Round 번호가 없습니다");
		this.value = value;
	}

	public int getCount() {
		return Integer.parseInt(value);
	}
}
