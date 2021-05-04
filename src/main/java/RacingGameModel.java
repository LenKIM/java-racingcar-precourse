import racingcar.Accelerator;
import racingcar.Race;
import racingcar.RaceResults;
import racingcar.RacingCars;
import racingcar.RoundNumber;
import racingcar.UserInput;
import racingcar.engine.RandomEngine;

public class RacingGameModel {

	private RoundNumber roundNumber;
	private RacingCars racingCars;
	private Race race;

	public RacingGameModel() {
	}

	public void setRacingCarNames(UserInput userInput) {
		this.racingCars = new RacingCars(userInput, Accelerator.from(new RandomEngine()));
	}

	public RacingCars getRacingCars() {
		return racingCars;
	}

	public void setRound(String value) {
		roundNumber = RoundNumber.valueOf(value);
	}

	public void start() {
		race = Race.of(racingCars, roundNumber);
		race.start();
	}

	public RaceResults result() {
		return race.getResult();
	}

	public Race getRace() {
		return race;
	}
}
