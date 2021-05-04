import java.util.ArrayList;
import java.util.List;

import racingcar.CarName;
import racingcar.CurrentLocation;
import racingcar.RaceResults;
import racingcar.Record;
import racingcar.RoundScore;
import racingcar.Winners;

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
			System.out.println();
			printRoundDistance(roundScore);
		}
	}

	private void printRoundDistance(RoundScore roundScore) {
		List<Record> records = roundScore.getRecords().getValue();
		for (Record record : records) {
			printRoundDistance(record.getCarName(), record.getLocation());
		}
	}

	private void printRoundDistance(CarName carName, CurrentLocation currentLocation) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < currentLocation.getValue(); i++) {
			sb.append("-");
		}
		System.out.println(carName.getValue() + " : " + sb);
	}

	public void printWinners(Winners winner) {
		StringBuilder sb = new StringBuilder();
		List<String> winnerNames = new ArrayList<>();
		for (Record record : winner.getValue()) {
			winnerNames.add(record.getCarName().getValue());
		}
		sb.append(String.join(",",winnerNames));
		sb.append("가 최종 우승했습니다.");
		System.out.println(sb);
	}
}
