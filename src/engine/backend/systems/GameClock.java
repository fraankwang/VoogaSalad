package engine.backend.systems;

public class GameClock {

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
	 * @return The current second.
	 */
	public double getCurrentSecond() {
		return currentSecond;
	}

	/**
	 * 
	 * @return The time in seconds for each loop iteration.
	 */
	public static double getTimePerLoop() {
		return 1.0 / (double) fps;
	}

}
