import java.util.Scanner;

import racingcar.Accelerator;
import racingcar.UserInput;

public class Program {

	private final RacingGameModel model;
	private final RacingGameView view;
	private Scanner sc = new Scanner(System.in);

	public Program(RacingGameModel model, RacingGameView view) {
		this.model = model;
		this.view = view;
	}

	public void gameStart() {
		view.racingCarGameStart();
		String userInput = sc.nextLine();
		Accelerator accelerator = Accelerator.ALWAYS_PROCEED;
		model.putCarNamesWithAccelerator(UserInput.of(userInput), accelerator);

	}
}
