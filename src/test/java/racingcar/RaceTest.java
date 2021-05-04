package racingcar;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RaceTest {

	RacingCars alwaysProceedRacingCars;
	RoundNumber five_times_rounds;
	Race sut;
	private RacingCar fooCar;
	private RacingCar barCar;

	@BeforeEach
	void setUp() {
		fooCar = new RacingCar(CarName.valueOf("foo"), Accelerator.PROCEED);
		barCar = new RacingCar(CarName.valueOf("bar"), Accelerator.PROCEED);

		alwaysProceedRacingCars = RacingCars.of(Lists.list(fooCar, barCar));
		five_times_rounds = RoundNumber.valueOf("5");
	}

	@Test
	void 레이스를_생성한다() {
		assertThatThrownBy(() -> Race.of(alwaysProceedRacingCars, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("Round 정보가 없습니다");

		assertThatThrownBy(() -> Race.of(null, five_times_rounds)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("경주할 자동차가 없습니다");

		sut = Race.of(alwaysProceedRacingCars, five_times_rounds);

		assertThat(sut).isNotNull();
	}

	@Test
	void 레이스는_5번의_라운드_후에_전체_경기_결과을_기록한다() {
		sut = Race.of(alwaysProceedRacingCars, five_times_rounds);
		assertThat(sut.isFinished()).isFalse();

		sut.start();

		assertThat(sut.isFinished()).isTrue();
		assertThat(sut.getResult().getValue().size()).isEqualTo(5);
	}

	@Test
	void 경기를_시작하기_전에는_경기_결과를_알수_없다() {
		sut = Race.of(alwaysProceedRacingCars, five_times_rounds);
		assertThat(sut.isFinished()).isFalse();

		assertThatThrownBy(() -> sut.getResult()).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("게임이 아직 시작되지 않았습니다");
	}
}