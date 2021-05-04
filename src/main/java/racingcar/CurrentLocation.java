package racingcar;

import java.util.Objects;

public class CurrentLocation {

	public static final CurrentLocation STARTING_POINT = CurrentLocation.valueOf(0);

	private int value;

	private CurrentLocation(int value) {
		setValue(value);
	}

	public static CurrentLocation valueOf(int i) {
		return new CurrentLocation(i);
	}

	public int getValue() {
		return value;
	}

	private void setValue(int value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CurrentLocation))
			return false;
		CurrentLocation that = (CurrentLocation)o;
		return value == that.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
