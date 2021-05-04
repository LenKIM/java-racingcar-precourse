import java.util.Scanner;

import racingcar.RaceResults;
import racingcar.UserInput;

public class RacingGame {

	private final RacingGameModel model;
	private final RacingGameView view;
	private final Scanner sc = new Scanner(System.in);

	public RacingGame(RacingGameModel model, RacingGameView view) {
		this.model = model;
		this.view = view;
	}

	public void ready() {
		view.racingCarGameStart();
		model.setRacingCarNames(UserInput.of(sc.nextLine()));
		view.askingForRoundCountMessage();
		model.setRound(sc.nextLine());
		view.emptyLine();
	}

	public void start() {
		model.start();
		view.raceResultMessage(model.getRaceResult());

		view.printWinners(model.getWinner());
	}
}
