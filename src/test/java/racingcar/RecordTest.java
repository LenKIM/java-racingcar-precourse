package racingcar;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecordTest {

	Record sut;

	@Test
	void 레코드를_기록한다() {
		CarName name = CarName.valueOf("foo");
		CurrentLocation location = CurrentLocation.valueOf(0);

		assertThatThrownBy(() -> Record.write(null, location)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("레이싱카 이름이 없습니다");

		assertThatThrownBy(() -> Record.write(name, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("현재 위치의 정보가 입력되지 않았습니다");

		sut = Record.write(name, location);
		assertThat(sut.getCarName()).isEqualTo(name);
		assertThat(sut.getLocation()).isEqualTo(location);
	}

}
