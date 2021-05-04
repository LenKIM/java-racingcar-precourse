package racingcar;

import java.util.Objects;

public class CarName {

	private static final int MINIMUM_CAR_NAME_SIZE = 1;
	private static final int MAXIMUM_CAR_NAME_SIZE = 5;

	private String value;

	public static CarName valueOf(String value) {
		return new CarName(value);
	}

	private CarName(String value) {
		setCarNames(value);
	}

	private void setCarNames(String value) {
		if (Objects.isNull(value) || value.length() > MAXIMUM_CAR_NAME_SIZE || value.length() < MINIMUM_CAR_NAME_SIZE) {
			throw new IllegalArgumentException("자동차는 1글자 이상 5글자이하의 이름을 갖습니다.");
		}
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CarName))
			return false;
		CarName carName = (CarName)o;
		return Objects.equals(value, carName.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
