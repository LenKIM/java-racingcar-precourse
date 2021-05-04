package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RaceResults {

	public static final RaceResults EMPTY = RaceResults.from(new ArrayList<>());

	private List<RoundScore> value;

	private RaceResults(List<RoundScore> value) {
		setValue(value);
	}

	public static RaceResults from(List<RoundScore> roundScores) {
		return new RaceResults(roundScores);
	}

	public List<RoundScore> getValue() {
		return value;
	}

	private void setValue(List<RoundScore> value) {
		if (Objects.isNull(value)){
			throw new IllegalArgumentException("경기 결과가 없습니다");
		}
		this.value = value;
	}
}
