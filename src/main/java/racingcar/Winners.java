package racingcar;

import static utils.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Winners {

	private List<Record> value;

	private Winners(List<Record> value) {
		setValue(value);
	}

	public static Winners emptyList() {
		return new Winners(new ArrayList<>());
	}

	public static Winners from(List<Record> winners) {
		return new Winners(winners);
	}

	public void add(Record record) {
		value.add(record);
	}

	public List<Record> getValue() {
		return value;
	}

	public void setValue(List<Record> value) {
		requiredNonNull(value, "우승자 정보가 없습니다");
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Winners))
			return false;
		Winners winners = (Winners)o;
		return Objects.equals(value, winners.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
