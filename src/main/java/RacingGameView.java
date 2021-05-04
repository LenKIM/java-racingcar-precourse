import java.util.List;

import racingcar.CarName;
import racingcar.CurrentLocation;
import racingcar.RaceResults;
import racingcar.Record;
import racingcar.RoundScore;

public class RacingGameView {

	public void racingCarGameStart() {
		System.out.println("게임 시작!");
	}

	public void askingForRoundCountMessage() {
		System.out.println("시도할 회수는 몇회인가요?");
	}

	public void raceResultMessage(RaceResults result) {
		System.out.println("실행 결과");
		List<RoundScore> roundScores = result.getValue();
		for (RoundScore roundScore : roundScores) {
			System.out.println(roundScore.getNumber().getCount());
			printRoundDistance(roundScore);
		}
	}

	private void printRoundDistance(RoundScore roundScore) {
		List<Record> records = roundScore.getRecords().getValue();
		for (Record record : records) {
			printRoundDistance(record.getCarName(),record.getLocation());
		}
	}

	private void printRoundDistance(CarName carName, CurrentLocation currentLocation){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < currentLocation.getValue(); i++) {
			sb.append("-");
		}
		System.out.println(carName.getValue()+ " : " + sb);
	}
}
