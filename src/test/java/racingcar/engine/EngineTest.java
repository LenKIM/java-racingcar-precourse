package racingcar.engine;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import racingcar.Power;
import utils.RandomUtils;

class EngineTest {

	Engine<Power> sut;

	@Test
	void 엔진은_조건을_성립하지_않을경우_POWER_OFF을_가진다() {
		sut = () -> 1 == 2 ? Power.ON : Power.OFF;
		assertThat(sut.get()).isEqualTo(Power.OFF);
	}

	@Test
	void 엔진은_조건을_성립하는_경우_POWER_ON을_가진다() {
		sut = () -> 1 == 1 ? Power.ON : Power.OFF;
		assertThat(sut.get()).isEqualTo(Power.ON);
	}

	@RepeatedTest(value = 10, name = "[{currentRepetition} | {totalRepetitions}] "
		+ "랜덤의_결과가_4_이상일_경우_POWER_ON을_3이하의경우에는_POWER_OFF를_가진다")
	void 랜덤의_결과가_4_이상일_경우_POWER_ON을_3이하의경우에는_POWER_OFF를_가진다() {
		int random = RandomUtils.nextInt(0, 9);
		sut = () -> random >= 4 ? Power.ON : Power.OFF;
		if (random >= 4) {
			assertThat(sut.get()).isEqualTo(Power.ON);
		}
		if (random < 3) {
			assertThat(sut.get()).isEqualTo(Power.OFF);
		}
	}
}
