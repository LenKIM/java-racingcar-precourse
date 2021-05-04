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
	private static final char INDEX_CHARACTER = '1';

	private UserInput(String carNames) {
		setCarNames(carNames);
	}

	public static UserInput of(String carNames) {
		return new UserInput(carNames);
	}

	public CarNames getCarNames() {
		Map<CarName, Pair> carNameMap = new HashMap<>();
		String[] splitString = carNames.split(",");
		CarName[] splitNames = new CarName[splitString.length];
		duplicatedNameProcess(carNameMap, splitString, splitNames);

		List<CarName> cars = new ArrayList<>();
		Collections.addAll(cars, splitNames);
		return CarNames.from(cars);
	}

	private void duplicatedNameProcess(Map<CarName, Pair> carNameMap, String[] splitString, CarName[] splitNames) {
		for (int i = 0; i < splitString.length; i++) {
			splitNames[i] = CarName.valueOf(splitString[i]);
		}
		for (int index = 0; index < splitNames.length; index++) {
			renameUserName(carNameMap, splitNames, index);
		}
	}

	private void renameUserName(Map<CarName, Pair> carNameMap, CarName[] splitNames, int index) {
		if (proceedIfDuplicated(splitNames, carNameMap, index)) {
			return;
		}
		carNameMap.put(splitNames[index], new Pair(index, 0));
	}

	private boolean proceedIfDuplicated(CarName[] splitNames, Map<CarName, Pair> carNameMap, int index) {
		if (isDuplicated(splitNames[index], carNameMap)) {
			Pair indexInfo = carNameMap.get(splitNames[index]);
			appendIndexCharacter(splitNames, carNameMap, index, indexInfo);
			return true;
		}
		return false;
	}

	private boolean isDuplicated(CarName key, Map<CarName, Pair> carsMap) {
		return carsMap.containsKey(key);
	}

	private void appendIndexCharacter(CarName[] splitNames, Map<CarName, Pair> carNameMap, int index, Pair indexInfo) {
		if (indexInfo.frequentCount <= 0) {
			splitNames[indexInfo.index] =
				CarName.valueOf(splitNames[indexInfo.index].getValue() + (char)(indexInfo.frequentCount
					+ UserInput.INDEX_CHARACTER));
		}
		indexInfo.frequentCount += 1;
		carNameMap.put(splitNames[index], new Pair(indexInfo.index, indexInfo.frequentCount));
		String nextIndex = splitNames[index].getValue() + (char)(indexInfo.frequentCount + UserInput.INDEX_CHARACTER);
		splitNames[index] = CarName.valueOf(nextIndex);
	}

	private void setCarNames(String carNames) {
		requiredNonNull(carNames, "값을 입력해주시기 바랍니다");
		requiredNonEmpty(carNames, "값을 입력해주시기 바랍니다");

		carNames = carNames.replace(" ", "");
		this.carNames = carNames;
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
