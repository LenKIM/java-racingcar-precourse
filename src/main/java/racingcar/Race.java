package racingcar;

import static utils.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class Race {

	private RacingCars racingCars;
	private RoundNumber roundNumber;
	private RaceStatus raceStatus = RaceStatus.READY;
	private RaceResults result;

	private Race(RacingCars racingCars, RoundNumber roundNumber) {
		setRacingCars(racingCars);
		setRound(roundNumber);
		result = RaceResults.EMPTY;
	}

	public static Race of(RacingCars racingCars, RoundNumber roundNumber) {
		return new Race(racingCars, roundNumber);
	}

	private void setRacingCars(RacingCars racingCars) {
		requiredNonNull(racingCars, "경주할 자동차가 없습니다");
		this.racingCars = racingCars;
	}

	private void setRound(RoundNumber roundNumber) {
		requiredNonNull(roundNumber, "Round 정보가 없습니다");
		this.roundNumber = roundNumber;
	}

	public boolean isFinished() {
		return raceStatus == RaceStatus.FINISH;
	}

	public RaceResults getResult() {
		if (raceStatus == RaceStatus.READY) {
			throw new IllegalArgumentException("게임이 아직 시작되지 않았습니다");
		}
		return result;
	}

	public void start() {
		List<RoundScore> roundScores = new ArrayList<>();
		for (int round = 0; round < roundNumber.getCount(); round++) {
			RoundScore roundScore = RoundScore.writeRoundNumberAndRecords(RoundNumber.valueOf(round),
				racingCars.moveForward());
			roundScores.add(roundScore);
		}
		result = RaceResults.from(roundScores);
		raceStatus = RaceStatus.FINISH;
	}
}
