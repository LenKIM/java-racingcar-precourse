public class Application {

	public static void main(String[] args) {
		RacingGameView view = new RacingGameView();
		RacingGameModel model = new RacingGameModel();
		Program program = new Program(model, view);

		program.gameStart();
	}

}
