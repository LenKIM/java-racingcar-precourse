import java.util.ArrayList;
import java.util.List;

import racingcar.Accelerator;
import racingcar.CarName;
import racingcar.CarNames;
import racingcar.Race;
import racingcar.RaceResults;
import racingcar.RacingCar;
import racingcar.RacingCars;
import racingcar.RoundNumber;
import racingcar.UserInput;
import racingcar.Winners;

public class RacingGameModel {

	private RoundNumber roundNumber;
	private RacingCars racingCars;
	private Race race;

	public RacingGameModel() {
	}

	public void setRacingCarNames(UserInput userInput) {
		List<RacingCar> racingCars = new ArrayList<>();
		CarNames carNames = userInput.getCarNames();
		for (CarName carName : carNames.getValue()) {
			racingCars.add(new RacingCar(carName, Accelerator.BOOSTING_RANDOM_LESS_THEN_FOUR_STOP_ENGINE));
		}
		this.racingCars = RacingCars.from(racingCars);
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

	public RaceResults getRaceResult() {
		return race.getResult();
	}

	public Race getRace() {
		return race;
	}

	public Winners getWinner() {
		return race.getResult().getWinners();
	}
}
