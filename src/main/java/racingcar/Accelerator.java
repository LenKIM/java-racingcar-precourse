package racingcar;

import java.util.Objects;
import java.util.function.Supplier;

public class Accelerator {

	private Accelerator() {
		throw new IllegalArgumentException("Accelerator는 항상 전진할 수 있는 조건을 갖습니다");
	}

	private final Supplier<Boolean> moveForwardCondition;

	public Accelerator(Supplier<Boolean> moveForwardCondition) {
		if (Objects.isNull(moveForwardCondition)){
			throw new IllegalArgumentException("Accelerator는 항상 전진할 수 있는 조건을 갖습니다");
		}
		this.moveForwardCondition = moveForwardCondition;
	}

	public boolean moveForward(){
		return moveForwardCondition.get();
	}
}
