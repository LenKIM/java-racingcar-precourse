import racingcar.Accelerator;
import racingcar.RacingCars;
import racingcar.UserInput;

public class RacingGameModel {

	private UserInput userInput;
	private Accelerator accelerator;

	public RacingGameModel() {
		accelerator = Accelerator.ALWAYS_PROCEED;
	}

	public void putCarNamesWithAccelerator(UserInput userInput, Accelerator accelerator) {
		this.userInput = userInput;
		this.accelerator = accelerator;
	}

	public RacingCars getCars() {
		return new RacingCars(userInput, accelerator);
	}
}
