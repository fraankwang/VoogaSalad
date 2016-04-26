package engine.backend.systems;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GameClock {

	private static final int HUNDRED = 100;
	private static int fps;
	private int currentLoopIteration;
	private double currentSecond;

	public GameClock(int fps) {
		this.fps = fps;
	}

	private double convertLoopIterationToSeconds() {
		return currentLoopIteration / (double) fps;
	}

	public void setCurrentLoopIteration(int currentLoopIteration) {
		this.currentLoopIteration = currentLoopIteration;
		currentSecond = convertLoopIterationToSeconds();
	}

	/**
	 * Updates the clock each loop iteration.
	 */
	public void updateLoopIteration() {
		currentLoopIteration++;
		currentSecond = convertLoopIterationToSeconds();
	}

	/**
	 * 
	 * @return The current second to hundredth of a second.
	 */
	public double getCurrentSecond() {
		return Math.floor(currentSecond * HUNDRED) / HUNDRED;
	}

	/**
	 * 
	 * @return The time in seconds for each loop iteration.
	 */
	public static double getTimePerLoop() {

		return round(1/fps, 2);
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
