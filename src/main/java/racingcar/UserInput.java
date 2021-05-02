package racingcar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserInput {

	private String carNames;

	private UserInput(String carNames) {
		setCarNames(carNames);
	}

	private void setCarNames(String carNames) {

		if (Objects.isNull(carNames) || carNames.isEmpty()) {
			throw new IllegalArgumentException("값을 입력해주시기 바랍니다");
		}

		carNames = carNames.replace(" ", "");
		this.carNames = carNames;
	}

	public static UserInput of(String carNames) {
		return new UserInput(carNames);
	}

	public List<String> getCarNames() {
		Map<String, Pair> carNameMap = new HashMap<>();
		String[] splitNames = carNames.split(",");
		char indexCharacter = '1';
		for (int index = 0; index < splitNames.length; index++) {
			if (proceedIfDuplicated(splitNames, carNameMap, indexCharacter, index)) {
				continue;
			}
			carNameMap.put(splitNames[index], new Pair(index, 0));
		}

		List<String> cars = new ArrayList<>();
		Collections.addAll(cars, splitNames);
		return cars;
	}

	private boolean proceedIfDuplicated(String[] splitNames, Map<String, Pair> carNameMap, char indexCharacter, int index) {
		if (isDuplicated(splitNames[index], carNameMap)) {
			Pair indexInfo = carNameMap.get(splitNames[index]);
			appendIndexCharacter(splitNames, carNameMap, indexCharacter, index, indexInfo);
			return true;
		}
		return false;
	}

	private boolean isDuplicated(String key, Map<String, Pair> carsMap) {
		return carsMap.containsKey(key);
	}

	private void appendIndexCharacter(String[] splitNames, Map<String, Pair> carNameMap, char indexCharacter, int index, Pair indexInfo) {
		if (indexInfo.frequentCount <= 0) {
			splitNames[indexInfo.index] = splitNames[indexInfo.index] + (char)(indexInfo.frequentCount + indexCharacter);
		}
		indexInfo.frequentCount += 1;
		carNameMap.put(splitNames[index], new Pair(indexInfo.index, indexInfo.frequentCount));
		String nextIndex = splitNames[index] + (char)(indexInfo.frequentCount + indexCharacter);
		splitNames[index] = nextIndex;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UserInput))
			return false;
		UserInput userInput = (UserInput)o;
		return Objects.equals(carNames, userInput.carNames);
	}

	@Override
	public int hashCode() {
		return Objects.hash(carNames);
	}

	private static class Pair {
		int index;
		int frequentCount;

		public Pair(int index, int frequentCount) {
			this.index = index;
			this.frequentCount = frequentCount;
		}
	}
}
