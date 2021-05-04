package racingcar;

import java.util.ArrayList;
import java.util.List;

import utils.Assertions;

public class Records {

	public static final Records EMPTY = Records.from(new ArrayList<>());

	private List<Record> value;

	public static Records from(List<Record> value) {
		return new Records(value);
	}

	private Records(List<Record> value) {
		setValue(value);
	}

	private void setValue(List<Record> value) {
		Assertions.requiredNonNull(value, "작성된 기록이 없습니다");
		this.value = value;
	}

	public List<Record> getValue() {
		return value;
	}
}
