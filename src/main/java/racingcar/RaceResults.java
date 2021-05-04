package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RaceResults {

	public static final RaceResults EMPTY = RaceResults.from(new ArrayList<>());

	private List<RoundScore> value;
	private List<Record> winners = new ArrayList<>();

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
		if (Objects.isNull(value)) {
			throw new IllegalArgumentException("경기 결과가 없습니다");
		}
		this.value = value;
	}

	public List<Record> getWinners() {
		Records records = getFinalRoundRecord();
		int maxValue = Integer.MIN_VALUE;
		for (Record record : records.getValue()) {
			maxValue = Math.max(maxValue, record.getLocation().getValue());
		}

		for (Record record : records.getValue()) {
			findFarthestCars(winners, maxValue, record);
		}
		return winners;
	}

	private void findFarthestCars(List<Record> winners, int maxValue, Record record) {
		if (isFarthestCar(maxValue, record)) {
			winners.add(record);
		}
	}

	private boolean isFarthestCar(int maxValue, Record record) {
		return record.getLocation().getValue() == maxValue;
	}

	public Records getFinalRoundRecord() {
		return getValue().get(getValue().size() - 1).getRecords();
	}
}
