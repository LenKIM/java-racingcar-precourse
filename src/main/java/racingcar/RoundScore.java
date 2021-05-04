package racingcar;

import utils.Assertions;

public class RoundScore {

	private RoundNumber number;
	private Records records;

	private RoundScore(RoundNumber number, Records records) {
		setNumber(number);
		setRecords(records);
	}

	public static RoundScore writeRoundNumberAndRecords(RoundNumber number, Records records) {
		return new RoundScore(number, records);
	}

	public void setNumber(RoundNumber number) {
		Assertions.requiredNonNull(number, "라운드 정보가 없습니다");
		this.number = number;
	}

	public Records getRecords() {
		return records;
	}

	public void setRecords(Records records) {
		Assertions.requiredNonNull(number, "경기 기록이 없습니다");
		this.records = records;
	}
}
