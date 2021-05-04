package racingcar;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RaceTest {

	RacingCars alwaysProceedRacingCars;
	RoundNumber five_times_rounds;
	Race sut;
	private CarName foo;
	private CarName bar;
	private CarName kim;
	private CarName park;

	@BeforeEach
	void setUp() {
		foo = CarName.valueOf("Foo");
		bar = CarName.valueOf("bar");
		kim = CarName.valueOf("kim");
		park = CarName.valueOf("park");

		RacingCar fooCar = new RacingCar(foo, Accelerator.PROCEED_ENGINE);
		RacingCar barCar = new RacingCar(bar, Accelerator.PROCEED_ENGINE);

		alwaysProceedRacingCars = RacingCars.from(Lists.list(fooCar, barCar));
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

	@Test
	void 아무도_전진하지_않았다면_모두가_우승자이다() {
		RacingCars cars = getRacingCarsWithNoProceedAccelerator();
		List<Record> winners = getWinnerRecords();

		sut = Race.of(cars, RoundNumber.valueOf(5));
		assertThat(sut.isFinished()).isFalse();
		sut.start();
		assertThat(sut.isFinished()).isTrue();
		RaceResults result = sut.getResult();
		assertThat(result.getWinners()).isEqualTo(Winners.from(winners));
	}

	private List<Record> getWinnerRecords() {
		List<Record> winners = new ArrayList<>();
		winners.add(Record.write(foo, CurrentLocation.valueOf(0)));
		winners.add(Record.write(bar, CurrentLocation.valueOf(0)));
		winners.add(Record.write(kim, CurrentLocation.valueOf(0)));
		winners.add(Record.write(park, CurrentLocation.valueOf(0)));
		return winners;
	}

	private RacingCars getRacingCarsWithNoProceedAccelerator() {
		RacingCar fooCar = new RacingCar(foo, Accelerator.STOP_ENGINE);
		RacingCar barCar = new RacingCar(bar, Accelerator.STOP_ENGINE);
		RacingCar kimCar = new RacingCar(kim, Accelerator.STOP_ENGINE);
		RacingCar parkCar = new RacingCar(park, Accelerator.STOP_ENGINE);
		return RacingCars.from(Lists.list(fooCar, barCar, kimCar, parkCar));
	}
}
