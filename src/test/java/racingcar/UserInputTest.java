package racingcar;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserInputTest {

	UserInput sut;

	@Test
	void 자동차이름을_입력하지_않을경우_IllegalArgumentException_발생한다() {
		assertThatThrownBy(() -> UserInput.of("")).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("값을 입력해주시기 바랍니다");

		assertThatThrownBy(() -> UserInput.of(null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("값을 입력해주시기 바랍니다");
	}

	@Test
	void 공백을_포함한_자동차_이름을_입력시_공백을_제거한다() {
		String carNames = "f o o,  bar,kim  ";
		String expectedNames = "foo,bar,kim";
		assertThat(UserInput.of(carNames)).isEqualTo(UserInput.of(expectedNames));
	}

	@Test
	void 자동차이름을_입력하면_자동차_리스트를_반환한다() {
		String carNames = "foo,bar,kim";
		sut = UserInput.of(carNames);
		List<String> carNameList = sut.getCarNames();

		List<String> expectedCarNameList = new ArrayList<>();
		expectedCarNameList.add("foo");
		expectedCarNameList.add("bar");
		expectedCarNameList.add("kim");

		assertThat(carNameList).isEqualTo(expectedCarNameList);
	}

	@ParameterizedTest(name = "입력값 {0}을 중복된 이름을 {1}와 같이 순번을 추가한 형태로 변환시킨다.")
	@CsvSource(value = {
		"kim,kim,kim:kim1,kim2,kim3:3",
		"foo,foo,kim,kim:foo1,foo2,kim1,kim2:4",
		"foo,foo,kim,kim,kim:foo1,foo2,kim1,kim2,kim3:5",
		"foo,foo,kim,kim,bar,bar:foo1,foo2,kim1,kim2,bar1,bar2:6",
	},
		delimiter = ':')
	void 동일한_자동차_이름의_경우_XXA_XXB_XXC_와같이_이름_끝에_순번을_추가한다(String userNames, String expectedUserNames, int expectedSize) {
		UserInput sut = UserInput.of(userNames);
		List<String> carNames = sut.getCarNames();

		List<String> expectedNames = new ArrayList<>();
		Collections.addAll(expectedNames, expectedUserNames.split(","));
		assertThat(carNames).isEqualTo(expectedNames);
		assertThat(carNames.size()).isEqualTo(expectedSize);
	}
}