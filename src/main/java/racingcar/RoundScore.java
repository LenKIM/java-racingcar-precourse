package racingcar;

public class RoundScore {

	private RoundNumber number;
	private Records records;

	private RoundScore(RoundNumber number, Records records) {
		this.number = number;
		this.records = records;
	}

	public static RoundScore write(RoundNumber number, Records records) {
		return new RoundScore(number, records);
	}

	public RoundNumber getNumber() {
		return number;
	}

	public Records getRecords() {
		return records;
	}
}
