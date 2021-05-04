package racingcar;

import java.util.Objects;

public class Record {

	private CarName carName;
	private CurrentLocation currentLocation;

	private Record(CarName carName, CurrentLocation currentLocation) {
		setCarName(carName);
		setLocation(currentLocation);
	}

	public static Record write(CarName name, CurrentLocation location) {
		return new Record(name, location);
	}

	public void setCarName(CarName carName) {
		if (Objects.isNull(carName)){
			throw new IllegalArgumentException("레이싱카 이름이 없습니다");
		}
		this.carName = carName;
	}

	public void setLocation(CurrentLocation currentLocation) {
		if (Objects.isNull(currentLocation)){
			throw new IllegalArgumentException("현재 위치의 정보가 입력되지 않았습니다");
		}
		this.currentLocation = currentLocation;
	}

	public CurrentLocation getLocation() {
		return currentLocation;
	}

	public CarName getCarName() {
		return carName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Record))
			return false;
		Record record = (Record)o;
		return Objects.equals(carName, record.carName) && Objects.equals(currentLocation,
			record.currentLocation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(carName, currentLocation);
	}
}
