package racingcar;

import static utils.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class Records {

	public static final Records EMPTY = Records.from(new ArrayList<>());

	private List<Record> value;

	private Records(List<Record> value) {
		setValue(value);
	}

	public static Records from(List<Record> value) {
		return new Records(value);
	}

	public List<Record> getValue() {
		return value;
	}

	private void setValue(List<Record> value) {
		requiredNonNull(value, "작성된 기록이 없습니다");
		this.value = value;
	}
}
