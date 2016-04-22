package engine.backend.game_object;

import java.util.ArrayList;
import java.util.List;

public class ModeStatistics {

	private int initialNumLives;
	private int currentNumLives;
	private double initialResources;
	private double currentResources;
	private List<Integer> endOfLevelLives;
	private List<Double> endOfLevelResources;

	public ModeStatistics(int numLives, double resources) {
		setInitialNumLives(numLives);
		setCurrentNumLives(numLives);
		setInitialResources(resources);
		setCurrentResources(resources);
		endOfLevelLives = new ArrayList<Integer>();
		endOfLevelResources = new ArrayList<Double>();
	}

	public void addEndOfLevelLives(int numLives) {
		endOfLevelLives.add(numLives);
	}

	public void addEndOfLevelResources(double resources) {
		endOfLevelResources.add(resources);
	}

	public int getInitialNumLives() {
		return initialNumLives;
	}

	public void setInitialNumLives(int initialNumLives) {
		this.initialNumLives = initialNumLives;
	}

	public int getCurrentNumLives() {
		return currentNumLives;
	}

	public void setCurrentNumLives(int currentNumLives) {
		this.currentNumLives = currentNumLives;
	}

	public double getInitialResources() {
		return initialResources;
	}

	public void setInitialResources(double initialResources) {
		this.initialResources = initialResources;
	}

	public double getCurrentResources() {
		return currentResources;
	}

	public void setCurrentResources(double currentResources) {
		this.currentResources = currentResources;
	}

}
