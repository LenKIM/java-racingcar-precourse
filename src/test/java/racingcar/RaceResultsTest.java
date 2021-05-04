package racingcar;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RaceResultsTest {

	RaceResults sut;
	private CarName foo;
	private CarName bar;
	private CarName kim;
	private CarName park;

	@Test
	void 경기결과를_생성합니다() {
		List<RoundScore> scores = new ArrayList<>();
		sut = RaceResults.from(scores);
		assertThat(sut).isNotNull();
	}

	@Test
	void 경기결과에는_각_라운드마다_기록된_결과가_기록됩니다() {
		List<RoundScore> scores = new ArrayList<>();
		scores.add(RoundScore.writeRoundNumberAndRecords(RoundNumber.valueOf(1), Records.EMPTY));
		scores.add(RoundScore.writeRoundNumberAndRecords(RoundNumber.valueOf(2), Records.EMPTY));
		scores.add(RoundScore.writeRoundNumberAndRecords(RoundNumber.valueOf(3), Records.EMPTY));
		sut = RaceResults.from(scores);

		assertThat(sut.getValue()).isEqualTo(scores);
	}

	@Test
	void 경기결과가_없을_경우_예외를_호출합니다() {
		assertThatThrownBy(() -> RaceResults.from(null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("경기 결과가 없습니다");
	}

	@Test
	void 경기결과에_따른_우승자를_반환한다() {
		RacingCars cars = getDummyRacingCars();
		Race race = Race.of(cars, RoundNumber.valueOf(5));
		race.start();

		sut = race.getResult();
		List<Record> winners = new ArrayList<>();
		winners.add(Record.write(foo, CurrentLocation.valueOf(5)));
		winners.add(Record.write(bar, CurrentLocation.valueOf(5)));

		assertThat(sut.getWinners()).isEqualTo(Winners.from(winners));
	}

	private RacingCars getDummyRacingCars() {
		RacingCar fooCar = new RacingCar(foo, Accelerator.PROCEED);
		RacingCar barCar = new RacingCar(bar, Accelerator.PROCEED);
		RacingCar kimCar = new RacingCar(kim, Accelerator.STOP);
		RacingCar parkCar = new RacingCar(park, Accelerator.STOP);
		return RacingCars.from(Lists.list(fooCar, barCar, kimCar, parkCar));
	}

	@BeforeEach
	void setUp() {
		foo = CarName.valueOf("Foo");
		bar = CarName.valueOf("bar");
		kim = CarName.valueOf("kim");
		park = CarName.valueOf("park");
	}
}