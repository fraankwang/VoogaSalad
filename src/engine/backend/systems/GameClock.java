package engine.backend.systems;

public class GameClock {
	
	private int fps;
	private int currentLoopIteration;
	private double currentSecond;
	
	public GameClock(int fps) {
		this.fps = fps;
	}
	
	private double convertLoopIterationToSeconds() {
		return currentLoopIteration/ (double) fps;
	}
	
	public void setCurrentLoopIteration(int currentLoopIteration) {
		this.currentLoopIteration = currentLoopIteration;
		currentSecond = convertLoopIterationToSeconds();
	}
	
	public void updateLoopIteration() {
		currentLoopIteration++;
		currentSecond = convertLoopIterationToSeconds();
	}
	
	public double getCurrentSecond() {
		return currentSecond;
	}

}
