package engine.backend.components;

/**
 * Creates a spawn, a object that holds the name of the entity being spawned, the spawn rate, start, end
 * and duration of the entity being spawned.
 * @author 
 *
 */
public class Spawn {

	private String spawningEntityName;
	private double spawningRate;
	private int waveIndex;
	private int numEntities;
	private double timer;
	private double currentSecond;

	public Spawn(String name, double rate, int index, int numEntities){
		setSpawningEntityName(name);
		setSpawningRate(rate);
		setWaveIndex(index);
		setNumEntities(numEntities);
	}
	
	public double getSpawningRate() {
		return spawningRate;
	}

	public void setSpawningRate(double spawningRate) {
		this.spawningRate = spawningRate;
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
	
}
