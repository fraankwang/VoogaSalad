package engine.backend.systems;

public class GameClock {
	
	private int fps;
	private int currentLoopIteration;
	private double currentSecond;
	
	public GameClock(int fps) {
		this.fps = fps;
	}
	
	private double convertLoopIterationToSeconds() {
		return currentLoopIteration/fps;
	}
	
	public void updateLoopIteration() {
		currentLoopIteration++;
		currentSecond = convertLoopIterationToSeconds();
	}
	
	public double getCurrentSecond() {
		return currentSecond;
	}

}
