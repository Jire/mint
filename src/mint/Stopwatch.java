package mint;

import static java.lang.System.currentTimeMillis;

@Experimental
public final class Stopwatch {

	private final long time;

	private Stopwatch(long time) {
		this.time = time;
	}

	public long elapsed() {
		return currentTimeMillis() - time;
	}

	public static Stopwatch start() {
		return new Stopwatch(currentTimeMillis());
	}

}