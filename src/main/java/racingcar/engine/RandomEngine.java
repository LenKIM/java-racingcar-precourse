package racingcar.engine;

import racingcar.Power;
import utils.RandomUtils;

public class RandomEngine implements Engine<Power> {
	@Override
	public Power get() {
		return RandomUtils.nextInt(0, 9) >= 4 ? Power.ON : Power.OFF;
	}
}
