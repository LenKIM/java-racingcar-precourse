import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import racingcar.Accelerator;
import racingcar.CarName;
import racingcar.RacingCar;
import racingcar.RacingCars;
import racingcar.UserInput;
import racingcar.engine.RandomEngine;

class RacingGameModelTest {

	RacingGameModel sut;

	@BeforeEach
	void setUp() {
		sut = new RacingGameModel();
	}
	
	@Disabled
	@Test
	void 사용자로부터_입력을_받아_복수의_레이싱카를_생산한다() {

		Accelerator randomAccelerator = Accelerator.from(new RandomEngine());
		List<RacingCar> expectedCars = new ArrayList<>();
		expectedCars.add(new RacingCar(CarName.valueOf("foo"), randomAccelerator));
		expectedCars.add(new RacingCar(CarName.valueOf("bar"), randomAccelerator));

		String carNames = "foo,bar";
		sut.setRacingCarNames(UserInput.of(carNames));
		RacingCars actual = sut.getRacingCars();

		RacingCars expected = RacingCars.of(expectedCars);

		assertThat(actual).isEqualTo(expected);
		assertThat(actual.size()).isEqualTo(expectedCars.size());
	}

	@Test
	void 레이싱게임을_시작합니다() {
		String carNames = "foo,bar";
		sut = new RacingGameModel();
		sut.setRacingCarNames(UserInput.of(carNames));
		sut.setRound("1");

		sut.start();

		assertThat(sut.getRace().isFinished()).isTrue();
	}
}