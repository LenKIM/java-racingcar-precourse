package racingcar;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RaceResultsTest {

	RaceResults sut;

	@Test
	void 경기결과를_생성합니다() {
		List<RoundScore> scores = new ArrayList<>();
		sut = RaceResults.from(scores);
		assertThat(sut).isNotNull();
	}

	@Test
	void 경기결과에는_각_라운드마다_기록된_결과가_기록됩니다() {
		List<RoundScore> scores = new ArrayList<>();
		scores.add(RoundScore.write(RoundNumber.valueOf(1), Records.EMPTY));
		scores.add(RoundScore.write(RoundNumber.valueOf(2), Records.EMPTY));
		scores.add(RoundScore.write(RoundNumber.valueOf(3), Records.EMPTY));
		sut = RaceResults.from(scores);

		assertThat(sut.getValue()).isEqualTo(scores);
	}

	@Test
	void 경기결과가_없을_경우_예외를_호출합니다() {
		assertThatThrownBy(() -> RaceResults.from(null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("경기 결과가 없습니다");
	}
}