package racingcar;

import static org.assertj.core.api.Assertions.*;
import static racingcar.CurrentLocation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RacingCarTest {

	private RacingCar sut;
	private CarName name;
	private Accelerator acceleratorStub;

	@BeforeEach
	void setUp() {
		name = CarName.valueOf("Foo");
		acceleratorStub = Accelerator.STOP;
	}

	@Test
	void RacingCar는_이름을_가진다() {
		sut = new RacingCar(name, acceleratorStub);
		assertThat(sut.getCarName()).isEqualTo(name);
	}

	@Test
	void RacingCar는_이름이_없을경우_IllegalArgumentException_발생한다() {
		assertThatThrownBy(() -> sut = new RacingCar(CarName.valueOf(""), acceleratorStub))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("자동차는 1글자 이상 5글자이하의 이름을 갖습니다.");

		assertThatThrownBy(() -> sut = new RacingCar(null, acceleratorStub))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("레이싱카의 이름이 없습니다");
	}

	@Test
	void RacingCar는_5글자_초과의_이름을_가질_경우_IllegalArgumentException_발생한다() {
		assertThatThrownBy(() -> sut = new RacingCar(CarName.valueOf("FooFoo"), acceleratorStub))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("자동차는 1글자 이상 5글자이하의 이름을 갖습니다.");
	}

	@Test
	void RacingCar는_생성되는_동시에_시작지점의_정보를_가진다() {
		sut = new RacingCar(name, acceleratorStub);
		assertThat(sut.getCurrentLocation()).isEqualTo(STARTING_POINT);
	}

	@Test
	void RacingCar는_엑셀레이터를_갖지_못할_경우_IllegalArgumentException_발생한다() {
		assertThatThrownBy(() -> sut = new RacingCar(name, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("자동차는 엑셀레이터를 필수로 갖습니다.");
	}

	@Test
	void 엑셀레이터의_전원이_동작하는_경우_자동차는_전진한다() {
		Accelerator accelerator = Accelerator.PROCEED;
		assertThat(accelerator.moveForward()).isEqualTo(Power.ON);
		CurrentLocation MOVED_RACING_CAR = CurrentLocation.valueOf(STARTING_POINT.getValue() + 1);

		sut = new RacingCar(name, accelerator);
		assertThat(sut.getCurrentLocation()).isEqualTo(STARTING_POINT);

		sut.moveForward();
		assertThat(sut.getCurrentLocation()).isEqualTo(MOVED_RACING_CAR);
	}

	@Test
	void 엑셀레이터의_전원이_동작하지_않는_경우_자동차는_전진하지_않는다() {
		Accelerator accelerator = Accelerator.STOP;
		assertThat(accelerator.moveForward()).isEqualTo(Power.OFF);

		sut = new RacingCar(name, accelerator);
		assertThat(sut.getCurrentLocation()).isEqualTo(STARTING_POINT);

		sut.moveForward();
		assertThat(sut.getCurrentLocation()).isEqualTo(STARTING_POINT);
	}

}