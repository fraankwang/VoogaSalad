package engine.backend.components;

import engine.backend.systems.GameClock;

/**
 * Creates a spawn, a object that holds the name of the entity being spawned,
 * the spawn rate, start, end and duration of the entity being spawned.
 * 
 * @author
 *
 */
public class Spawn {

	private String spawningEntityName;
	private int spawningRate;
	private int waveIndex;
	private int numEntities;
	private double timer;
	private double currentSecond;

	public Spawn(String name, int rate, int index, int numEntities) {
		setSpawningEntityName(name);
		setSpawningRate(rate);
		setWaveIndex(index);
		setNumEntities(numEntities);
	}

	/**
	 * 
	 * @return spawningRate
	 */

	public double getSpawningRate() {
		return spawningRate;
	}

	/**
	 * sets the SpawningRate
	 * 
	 * @param spawningRate
	 */
	public void setSpawningRate(int spawningRate) {
		this.spawningRate = spawningRate;
	}

	public String getSpawningEntityName() {
		return spawningEntityName;
	}

	public double getTimer() {
		return timer;
	}

	public void resetTimer() {
		timer = spawningRate * 100;
	}

	/**
	 * Sets the Time and decrements from the GameClock
	 * 
	 * @param currentSecond
	 */
	public void setTimer(double currentSecond) {
		if (this.currentSecond != currentSecond) {
			this.currentSecond = currentSecond;
			timer = timer - GameClock.getTimePerLoop();
		}
	}

	public void decrementTimer() {
		timer = timer - GameClock.getTimePerLoop();
	}

	public void setSpawningEntityName(String spawningEntityName) {
		this.spawningEntityName = spawningEntityName;
	}

	public int getWaveIndex() {
		return waveIndex;
	}

	public void setWaveIndex(int waveIndex) {
		this.waveIndex = waveIndex;
	}

	public int getNumEntities() {
		return numEntities;
	}

	public void setNumEntities(int numEntities) {
		this.numEntities = numEntities;
	}

	public String getInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append(spawningEntityName);
		sb.append(".");
		sb.append(waveIndex);
		sb.append(".");
		sb.append(numEntities);
		sb.append(".");
		sb.append(spawningRate);
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("EntityName: ");
		sb.append(spawningEntityName);
		sb.append(", ");
		sb.append("WaveIndex: ");
		sb.append(waveIndex);
		sb.append(", ");
		sb.append("NumEntities: ");
		sb.append(numEntities);
		sb.append(", ");
		sb.append("Rate: ");
		sb.append(spawningRate);
		return sb.toString();
	}

}
