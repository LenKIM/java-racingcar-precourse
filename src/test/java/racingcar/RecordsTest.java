package racingcar;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

class RecordsTest {

	Records sut;

	@Test
	void 복수의_레코드가_들어간_리스트로_Records를_만들_수_있다() {
		Record recordFooDummy = Record.write(CarName.valueOf("Foo"), CurrentLocation.STARTING_POINT);
		Record recordBarDummy = Record.write(CarName.valueOf("Bar"), CurrentLocation.STARTING_POINT);
		sut = Records.from(Lists.list(recordFooDummy, recordBarDummy));
		List<Record> value = sut.getValue();
		assertThat(sut.getValue()).isEqualTo(value);
	}

	@Test
	void 아무것도_들어가지_않을때는_예외를_호출한다() {
		assertThatThrownBy(() -> Records.from(null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("작성된 기록이 없습니다");
	}
}
