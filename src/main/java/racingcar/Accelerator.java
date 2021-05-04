package racingcar;

import static utils.Assertions.*;

import racingcar.engine.Engine;
import racingcar.engine.RandomEngine;

public class Accelerator {

	public static final Accelerator PROCEED_ENGINE = Accelerator.from(() -> Power.ON);
	public static final Accelerator STOP_ENGINE = Accelerator.from(() -> Power.OFF);
	public static final Accelerator BOOSTING_RANDOM_LESS_THEN_FOUR_STOP_ENGINE = Accelerator.from(new RandomEngine());

	private final Engine<Power> engine;

	private Accelerator(Engine<Power> engine) {
		requiredNonNull(engine, "Accelerator는 구동시킬수 있는 조건을 가진 엔진을 갖습니다");
		this.engine = engine;
	}

	public static Accelerator from(Engine<Power> engine) {
		return new Accelerator(engine);
	}

	public Power moveForward() {
		return engine.get();
	}

	public Engine<Power> getEngine() {
		return engine;
	}
}
