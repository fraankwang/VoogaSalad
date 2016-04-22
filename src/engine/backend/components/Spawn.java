package engine.backend.components;

public class Spawn {

	private String spawningEntityName;
	private double spawningRate;
	private double spawningStartTime;
	private double spawningEndTime;
	private double timer;
	private double currentSecond;

	public Spawn(String name, double rate, double start, double end) {
		setSpawningEntityName(name);
		setSpawningRate(rate);
		setSpawningStartTime(start);
		setSpawningEndTime(end);
	}

	public double getSpawningRate() {
		return spawningRate;
	}

	public void setSpawningRate(double spawningRate) {
		this.spawningRate = spawningRate;
	}

	public double getSpawningStartTime() {
		return spawningStartTime;
	}

	public void setSpawningStartTime(double spawningStartTime) {
		this.spawningStartTime = spawningStartTime;
	}

	public double getSpawningEndTime() {
		return spawningEndTime;
	}

	public void setSpawningEndTime(double spawningEndTime) {
		this.spawningEndTime = spawningEndTime;
	}

	public String getSpawningEntityName() {
		return spawningEntityName;
	}

	public double getTimer() {
		return timer;
	}

	public void resetTimer() {
		timer = spawningRate;
	}

	public void setTimer(double currentSecond) {
		if (this.currentSecond != currentSecond) {
			this.currentSecond = currentSecond;
			timer = timer - 1;
		}
	}

	public void setSpawningEntityName(String spawningEntityName) {
		this.spawningEntityName = spawningEntityName;
	}

}
