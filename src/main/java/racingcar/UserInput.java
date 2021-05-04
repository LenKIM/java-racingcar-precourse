package racingcar;

import static utils.Assertions.*;

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

	public static UserInput of(String carNames) {
		return new UserInput(carNames);
	}

	public CarNames getCarNames() {
		Map<CarName, Pair> carNameMap = new HashMap<>();
		String[] split = carNames.split(",");
		CarName[] splitNames = new CarName[split.length];
		for (int i = 0; i < split.length; i++) {
			splitNames[i] = CarName.valueOf(split[i]);
		}
		char indexCharacter = '1';
		for (int index = 0; index < splitNames.length; index++) {
			if (proceedIfDuplicated(splitNames, carNameMap, indexCharacter, index)) {
				continue;
			}
			carNameMap.put(splitNames[index], new Pair(index, 0));
		}

		List<CarName> cars = new ArrayList<>();
		Collections.addAll(cars, splitNames);
		return CarNames.from(cars);
	}

	private void setCarNames(String carNames) {
		requiredNonNull(carNames, "값을 입력해주시기 바랍니다");
		requiredNonEmpty(carNames, "값을 입력해주시기 바랍니다");

		carNames = carNames.replace(" ", "");
		this.carNames = carNames;
	}

	private boolean proceedIfDuplicated(CarName[] splitNames, Map<CarName, Pair> carNameMap,
		char indexCharacter, int index) {
		if (isDuplicated(splitNames[index], carNameMap)) {
			Pair indexInfo = carNameMap.get(splitNames[index]);
			appendIndexCharacter(splitNames, carNameMap, indexCharacter, index, indexInfo);
			return true;
		}
		return false;
	}

	private boolean isDuplicated(CarName key, Map<CarName, Pair> carsMap) {
		return carsMap.containsKey(key);
	}

	private void appendIndexCharacter(CarName[] splitNames, Map<CarName, Pair> carNameMap,
		char indexCharacter, int index, Pair indexInfo) {

		if (indexInfo.frequentCount <= 0) {
			splitNames[indexInfo.index] =
				CarName.valueOf(splitNames[indexInfo.index].getValue() + (char)(indexInfo.frequentCount + indexCharacter));
		}
		indexInfo.frequentCount += 1;
		carNameMap.put(splitNames[index], new Pair(indexInfo.index, indexInfo.frequentCount));
		String nextIndex = splitNames[index].getValue() + (char)(indexInfo.frequentCount + indexCharacter);
		splitNames[index] = CarName.valueOf(nextIndex);
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
