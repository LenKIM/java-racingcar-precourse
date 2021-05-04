package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RacingCars {

	private final static int MINIMUM_CAR_SIZE = 1;

	private List<RacingCar> value;

	private RacingCars(List<RacingCar> value) {
		setValue(value);
	}

	public static RacingCars from(List<RacingCar> value) {
		return new RacingCars(value);
	}

	private void setValue(List<RacingCar> value) {
		if (value.size() <= MINIMUM_CAR_SIZE) {
			throw new IllegalArgumentException("게임은 자동차 2대부터 시작할 수 있습니다");
		}
		this.value = value;
	}

	public int size() {
		return value.size();
	}

	public RacingCar get(int index) {
		return value.get(index);
	}

	public Records moveForward(){
		List<Record> records = new ArrayList<>();
		for (RacingCar racingCar : this.value) {
			racingCar.moveForward();
			records.add(Record.write(racingCar.getCarName(), racingCar.getCurrentLocation()));
		}
		return Records.from(records);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RacingCars))
			return false;
		RacingCars that = (RacingCars)o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
