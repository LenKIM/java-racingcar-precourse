package racingcar;

import java.util.List;
import java.util.Objects;

import utils.Assertions;

public class CarNames {

	private List<CarName> value;

	private CarNames(List<CarName> value) {
		setValue(value);
	}

	public static CarNames from(List<CarName> value){
		return new CarNames(value);
	}

	public List<CarName> getValue() {
		return value;
	}

	private void setValue(List<CarName> value) {
		Assertions.requiredNonNull(value, "경주차 이름 정보가 없습니다");
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CarNames))
			return false;
		CarNames carNames = (CarNames)o;
		return Objects.equals(value, carNames.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
