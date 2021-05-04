package racingcar;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CarNamesTest {

	CarNames sut;

	@Test
	void 자동차이름을_가진_콜렉션을_생성합니다() {
		String userStringInput = "foo,bar";
		UserInput userInput = UserInput.of(userStringInput);
		List<CarName> value = getDummyCarNameList();

		sut = userInput.getCarNames();

		assertThat(sut).isEqualTo(CarNames.from(value));
	}

	@Test
	void 자동차이름이_없을_경우_예외를_호출한다() {
		assertThatThrownBy(() -> CarNames.from(null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("경주차 이름 정보가 없습니다");
	}

	private List<CarName> getDummyCarNameList() {
		List<CarName> value = new ArrayList<>();
		value.add(CarName.valueOf("foo"));
		value.add(CarName.valueOf("bar"));
		return value;
	}

}
