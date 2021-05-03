import racingcar.Accelerator;
import racingcar.RacingCars;
import racingcar.UserInput;
import racingcar.engine.RandomEngine;

public class RacingGameModel {

	private UserInput userInput;

	public void putCarNames(UserInput userInput) {
		this.userInput = userInput;
	}

	public RacingCars getCars() {
		return new RacingCars(userInput, Accelerator.from(new RandomEngine()));
	}
}
