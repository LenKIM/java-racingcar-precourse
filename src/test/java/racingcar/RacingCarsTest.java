package racingcar;

import static org.assertj.core.api.Assertions.*;
import static racingcar.CurrentLocation.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import racingcar.engine.RandomEngine;

class RacingCarsTest {

	RacingCars sut;

	@Test
	void 이름을_가진_자동차가_1대_일경우_IllegalArgumentException_발생한다() {
		String carNames = "foo";
		assertThatThrownBy(() -> {
			UserInput userInput = UserInput.of(carNames);
			sut = RacingCars.from(getStubRacingCars(userInput));
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("게임은 자동차 2대부터 시작할 수 있습니다");
	}

	@ParameterizedTest(name = "{1} 대의 자동차의 시작지점은 STARTING_POINT 이다")
	@CsvSource(value = {
		"abc,aaa:2",
		"abc,aaa,bbb:3",
		"abc,aaa,bbb,ccc:4",
		"abc,aaa,bbb,ccc,ddd:5",
	}, delimiter = ':')
	void n대의_자동차의_시작지점은_STARTING_POINT이다(String carStringNames, int n) {
		UserInput userInput = UserInput.of(carStringNames);
		List<RacingCar> racingCars = new ArrayList<>();
		for (CarName carName : userInput.getCarNames().getValue()) {
			racingCars.add(new RacingCar(carName, Accelerator.from(new RandomEngine())));
		}
		sut = RacingCars.from(racingCars);
		assertThat(sut.size()).isEqualTo(n);
		for (int i = 0; i < sut.size(); i++) {
			RacingCar car = sut.get(i);
			assertThat(car.getCurrentLocation()).isEqualTo(STARTING_POINT);
		}
	}

	@Test
	void 레이싱카들은_1_ROUND_가_끝나면_기록이_저장된다() {
		String carStringNames = "Foo,Bar,Kim";
		UserInput userInput = UserInput.of(carStringNames);
		CarNames carNames = userInput.getCarNames();
		List<RacingCar> racingCars = new ArrayList<>();
		for (CarName carName : carNames.getValue()) {
			racingCars.add(new RacingCar(carName, Accelerator.PROCEED));
		}
		sut = RacingCars.from(racingCars);

		CarNames names = userInput.getCarNames();
		int size = names.getValue().size();

		Records records = sut.moveForward();

		assertThat(records.getValue().size()).isEqualTo(size);
		List<Record> value = records.getValue();
		for (int i = 0; i < value.size(); i++) {
			Record record = value.get(i);
			assertThat(record.getCarName()).isEqualTo(names.getValue().get(i));
			assertThat(record.getLocation()).isEqualTo(CurrentLocation.valueOf(1));
		}
	}

	private List<RacingCar> getStubRacingCars(UserInput userInput) {
		List<RacingCar> racingCars = new ArrayList<>();
		for (CarName carName : userInput.getCarNames().getValue()) {
			RacingCar racingCar = new RacingCar(carName, Accelerator.STOP);
			racingCars.add(racingCar);
		}
		return racingCars;
	}
}