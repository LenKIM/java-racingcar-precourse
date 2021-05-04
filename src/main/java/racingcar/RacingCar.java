package racingcar;

import java.util.Objects;

public class RacingCar {

	private CarName carName;
	private CurrentLocation currentLocation = CurrentLocation.STARTING_POINT;
	private Accelerator accelerator;

	public RacingCar(CarName carName, Accelerator accelerator) {
		setCarName(carName);
		setAccelerator(accelerator);
	}

	public CarName getCarName() {
		return carName;
	}

	private void setCarName(CarName carName) {
		if (Objects.isNull(carName)){
			throw new IllegalArgumentException("레이싱카의 이름이 없습니다");
		}
		this.carName = carName;
	}

	public CurrentLocation getCurrentLocation() {
		return currentLocation;
	}

	private void setCurrentLocation(CurrentLocation currentLocation) {
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
			setCurrentLocation(CurrentLocation.valueOf(getCurrentLocation().getValue() + 1));
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RacingCar))
			return false;
		RacingCar car = (RacingCar)o;
		return Objects.equals(carName, car.carName) && Objects.equals(accelerator, car.accelerator);
	}

	@Override
	public int hashCode() {
		return Objects.hash(carName, accelerator);
	}
}
