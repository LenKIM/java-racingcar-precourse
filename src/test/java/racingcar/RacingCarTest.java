package racingcar;

import static org.assertj.core.api.Assertions.*;
import static racingcar.RacingCar.*;

import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RacingCarTest {

	private RacingCar sut;
	private String name;
	private Accelerator acceleratorStub;

	@BeforeEach
	void setUp() {
		name = "Foo";
		acceleratorStub = new Accelerator(() -> null);
	}

	@Test
	void RacingCar는_이름을_가진다() {
		sut = new RacingCar(name, acceleratorStub);
		assertThat(sut.getName()).isEqualTo(name);
	}

	@Test
	void RacingCar는_이름이_없을경우_IllegalArgumentException_발생한다() {
		assertThatThrownBy(() -> sut = new RacingCar("", acceleratorStub))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("자동차는 1글자 이상 5글자이하의 이름을 갖습니다.");

		assertThatThrownBy(() -> sut = new RacingCar(null, acceleratorStub))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("자동차는 1글자 이상 5글자이하의 이름을 갖습니다.");
	}

	@Test
	void RacingCar는_5글자_초과의_이름을_가질_경우_IllegalArgumentException_발생한다() {
		assertThatThrownBy(() -> sut = new RacingCar("FooFoo", acceleratorStub))
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
	void RacingCar는_전진조건이_명시된_엑셀레이터를_가진다() {
		Supplier<Boolean> moveForwardCondition = () -> true;
		sut = new RacingCar(name, new Accelerator(moveForwardCondition));
		assertThat(sut.getAccelerator()).isNotNull();
	}

	@Test
	void RacingCar는_전진조건이_명시되지_않는_엑셀레이터를_가질경우_IllegalArgumentException_발생한다() {
		Supplier<Boolean> moveForwardCondition = null;

		assertThatThrownBy(() -> sut = new RacingCar(name, new Accelerator(moveForwardCondition)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("Accelerator는 항상 전진할 수 있는 조건을 갖습니다");
	}

	@Test
	void 엑셀레이터가_True로서_동작하는경우_자동차는_전진한다() {
		Supplier<Boolean> moveForwardCondition = () -> true;
		Accelerator accelerator = new Accelerator(moveForwardCondition);
		assertThat(accelerator.moveForward()).isTrue();
		int MOVED_RACING_CAR = STARTING_POINT + 1;

		sut = new RacingCar(name, accelerator);
		assertThat(sut.getCurrentLocation()).isEqualTo(STARTING_POINT);

		sut.moveForward();
		assertThat(sut.getCurrentLocation()).isEqualTo(MOVED_RACING_CAR);
	}

	@Test
	void 엑셀레이터가_False로서_동작하는경우_자동차는_전진하지_않는다() {

		Supplier<Boolean> moveForwardCondition = () -> false;
		Accelerator accelerator = new Accelerator(moveForwardCondition);
		assertThat(accelerator.moveForward()).isFalse();

		sut = new RacingCar(name, accelerator);
		assertThat(sut.getCurrentLocation()).isEqualTo(STARTING_POINT);

		sut.moveForward();
		assertThat(sut.getCurrentLocation()).isEqualTo(STARTING_POINT);
	}

}