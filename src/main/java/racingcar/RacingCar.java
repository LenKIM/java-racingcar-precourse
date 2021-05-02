package racingcar;

import java.util.Objects;

public class RacingCar {

	public final static int STARTING_POINT = 0;

	private String name;
	private int currentLocation = STARTING_POINT;
	private Accelerator accelerator;

	private RacingCar() {
		throw new IllegalArgumentException("RacingCar 는 이름을 갖습니다");
	}

	public RacingCar(String name, Accelerator accelerator) {
		setName(name);
		setAccelerator(accelerator);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		if (Objects.isNull(name) || name.length() > 5 || name.length() <= 0){
			throw new IllegalArgumentException("자동차는 1글자 이상 5글자이하의 이름을 갖습니다.");
		}
		this.name = name;
	}

	private void setAccelerator(Accelerator accelerator) {
		if (Objects.isNull(accelerator)){
			throw new IllegalArgumentException("자동차는 엑셀레이터를 필수로 갖습니다.");
		}

		this.accelerator = accelerator;
	}

	public int getCurrentLocation() {
		return currentLocation;
	}

	public Accelerator getAccelerator() {
		return accelerator;
	}

	public void moveForward(){
		if (accelerator.moveForward()){
			currentLocation += 1;
		}
	}
}
