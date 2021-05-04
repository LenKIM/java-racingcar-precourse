public class Application {

	public static void main(String[] args) {
		RacingGameView view = new RacingGameView();
		RacingGameModel model = new RacingGameModel();
		RacingGame racingGame = new RacingGame(model, view);

		racingGame.ready();
		racingGame.start();
	}
}
