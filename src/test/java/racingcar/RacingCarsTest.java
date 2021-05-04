package racingcar;

import static org.assertj.core.api.Assertions.*;
import static racingcar.CurrentLocation.*;
import static racingcar.RacingCar.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RacingCarsTest {

	RacingCars sut;

	@Test
	void 이름을_가진_자동차가_1대_일경우_IllegalArgumentException_발생한다() {
		String carNames = "foo";
		assertThatThrownBy(() -> sut = new RacingCars(UserInput.of(carNames), Accelerator.STOP))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("게임은 자동차 2대부터 시작할 수 있습니다");
	}

	@Test
	void 이름을_가진_두대의_자동차를_생산한다() {
		String carNames = "foo,bar";
		List<RacingCar> expectedCars = getStubCars(UserInput.of(carNames));

		sut = new RacingCars(UserInput.of(carNames), Accelerator.STOP);
		assertThat(sut.size()).isEqualTo(expectedCars.size());
		assertThat(sut).isEqualTo(RacingCars.of(expectedCars));
		for (int i = 0; i < sut.size(); i++) {
			RacingCar car = sut.get(i);
			assertThat(car.getCurrentLocation()).isEqualTo(STARTING_POINT);
		}
	}

	@ParameterizedTest(name = "{1} 대의 자동차의 시작지점은 STARTING_POINT 이다")
	@CsvSource(value = {
		"abc,aaa:2",
		"abc,aaa,bbb:3",
		"abc,aaa,bbb,ccc:4",
		"abc,aaa,bbb,ccc,ddd:5",
	}, delimiter = ':')
	void n대의_자동차의_시작지점은_STARTING_POINT이다(String carNames, int n) {
		sut = new RacingCars(UserInput.of(carNames), Accelerator.STOP);
		assertThat(sut.size()).isEqualTo(n);
		for (int i = 0; i < sut.size(); i++) {
			RacingCar car = sut.get(i);
			assertThat(car.getCurrentLocation()).isEqualTo(STARTING_POINT);
		}
	}

	private List<RacingCar> getStubCars(UserInput userInput) {
		List<RacingCar> racingCars = new ArrayList<>();
		for (CarName carName : userInput.getCarNames()) {
			RacingCar racingCar = new RacingCar(carName, Accelerator.STOP);
			racingCars.add(racingCar);
		}
		return racingCars;
	}
}