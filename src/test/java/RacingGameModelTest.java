import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import racingcar.Accelerator;
import racingcar.RacingCar;
import racingcar.RacingCars;
import racingcar.UserInput;

class RacingGameModelTest {

	RacingGameModel sut;

	@Test
	void 사용자로부터_입력을_받아_복수의_레이싱카를_생산한다() {
		String carNames = "foo,bar";
		sut = new RacingGameModel();
		Accelerator alwaysProceed = Accelerator.STOP;
		List<RacingCar> expectedCars = new ArrayList<>();
		expectedCars.add(new RacingCar("foo", alwaysProceed));
		expectedCars.add(new RacingCar("bar", alwaysProceed));

		sut.putCarNames(UserInput.of(carNames));
		assertThat(sut.getCars().size()).isEqualTo(expectedCars.size());
		assertThat(sut.getCars()).isEqualTo(RacingCars.of(expectedCars));
	}

}