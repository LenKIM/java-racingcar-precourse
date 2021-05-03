package racingcar.engine;

@FunctionalInterface
public interface Engine<T> {
	T get();
}
