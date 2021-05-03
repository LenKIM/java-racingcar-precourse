package racingcar;

import java.util.Objects;

public class RacingCar {

	public static final int STARTING_POINT = 0;

	private static final int MINIMUM_CAR_NAME_SIZE = 1;
	private static final int MAXIMUM_CAR_NAME_SIZE = 5;

	private String name;
	private int currentLocation = STARTING_POINT;
	private Accelerator accelerator;

	public RacingCar(String name, Accelerator accelerator) {
		setName(name);
		setAccelerator(accelerator);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		if (Objects.isNull(name) || name.length() > MAXIMUM_CAR_NAME_SIZE || name.length() < MINIMUM_CAR_NAME_SIZE) {
			throw new IllegalArgumentException("자동차는 1글자 이상 5글자이하의 이름을 갖습니다.");
		}
		this.name = name;
	}

	public int getCurrentLocation() {
		return currentLocation;
	}

	private void setCurrentLocation(int currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Accelerator getAccelerator() {
		return accelerator;
	}

	private void setAccelerator(Accelerator accelerator) {
		if (Objects.isNull(accelerator)) {
			throw new IllegalArgumentException("자동차는 엑셀레이터를 필수로 갖습니다.");
		}
		this.accelerator = accelerator;
	}

	public void moveForward() {
		if (accelerator.moveForward().equals(Power.ON)) {
			setCurrentLocation(getCurrentLocation() + 1);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RacingCar))
			return false;
		RacingCar car = (RacingCar)o;
		return Objects.equals(name, car.name) && Objects.equals(accelerator, car.accelerator);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, accelerator);
	}
}
