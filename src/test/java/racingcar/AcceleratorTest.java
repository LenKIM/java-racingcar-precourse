package racingcar;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import racingcar.engine.Engine;

class AcceleratorTest {

	Accelerator sut;

	@Test
	void 엑셀레이터는_엔진을_가진다() {
		Engine<Power> engineEngine = () -> Power.ON;
		sut = Accelerator.from(engineEngine);
		assertThat(sut.getEngine()).isEqualTo(engineEngine);
	}

	@Test
	void 엑셀레이터의_엔진이_Null_일_경우_IllegalArgumentException_발생한다() {
		assertThatThrownBy(() -> Accelerator.from(null))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("Accelerator는 구동시킬수 있는 조건을 가진 엔진을 갖습니다");
	}
}
