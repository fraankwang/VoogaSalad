package engine.backend.game_object;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import engine.backend.rules.IAction;
import engine.backend.rules.LevelAction;
import exception.DrumpfTowerException;
import exception.ExceptionLoader;
import utility.hud.Property;

/**
 *
 * @author Raghav Kedia
 *
 *
 */
public class GameStatistics implements IModifiable {

	private static final int ZERO = 0;
	private static final String LACK_ACCESS = "LackAccessToClass";
	private static final String PREFIX = "set";
	private static final String METHOD_DNE = "MethodDoesNotExist";
	private static final String SECURITY_EXCEPTION = "SecurityException";
	private static final String ILLEGAL_ARGS = "IllegalArguments";

	private int initialNumLives;
	private double initialResources;
	private int initialLevel;
	private String initialMode;
	private int highestLevelUnlocked;
	private int nextAvailableEntityID;
	private List<Integer> endOfLevelLives;
	private List<Double> endOfLevelResources;

	private Property currentNumLives;
	private Property currentResources;
	private Property currentLevelIndex;
	private Property currentMode;

	public GameStatistics(int numLives, double resources) {

		setInitialNumLives(numLives);
		setInitialResources(resources);

		currentLevelIndex = new Property(initialLevel, "level");
		currentMode = new Property((initialMode = new String()), "mode");

		setCurrentNumLives(numLives);
		setCurrentResources(resources);

		nextAvailableEntityID = 0;
		endOfLevelLives = new ArrayList<Integer>();
		endOfLevelResources = new ArrayList<Double>();
	}

	public GameStatistics() {

	}

	public void addEndOfLevelLives(int numLives) {
		endOfLevelLives.add(numLives);
	}

	/**
	 * adds how resources at the end of the level
	 *
	 * @param resources
	 */
	public void addEndOfLevelResources(double resources) {
		endOfLevelResources.add(resources);
	}

	/**
	 *
	 * @return initial number of lives
	 */
	public int getInitialNumLives() {
		return initialNumLives;
	}

	/**
	 * Sets the initial number of lives
	 *
	 * @param initialNumLives
	 */
	public void setInitialNumLives(int initialNumLives) {
		this.initialNumLives = initialNumLives;
		currentNumLives = new Property(initialNumLives, "lives");
	}

	/**
	 * Gets the current number of lives
	 *
	 * @return
	 */
	public int getCurrentNumLives() {
		return (int) currentNumLives.getValue();
	}

	public Property getCurrentLivesProperty() {
		return this.currentNumLives;
	}

	/**
	 * Set current number of lives
	 *
	 * @param currentNumLives
	 */
	public void setCurrentNumLives(int currentNumLives) {
		this.currentNumLives.setValue(currentNumLives);
	}

	/**
	 * Updates the number of lives by parsing a string parameter into a integer
	 *
	 * @param deltaNumLives
	 */
	public void setCurrentNumLives(String deltaNumLives) {

		int newValue = getCurrentNumLives() + Integer.parseInt(deltaNumLives);
		setCurrentNumLives(newValue);
	}

	/**
	 *
	 * @return initial resources
	 */
	public double getInitialResources() {
		return initialResources;
	}

	/**
	 * sets the initial resources value
	 *
	 * @param initialResources
	 */
	public void setInitialResources(double initialResources) {
		this.initialResources = initialResources;
		currentResources = new Property(initialResources, "resources");
	}

	public double getCurrentResources() {
		return (double) currentResources.getValue();
	}

	public Property getCurrentResourcesProperty() {
		return this.currentResources;
	}

	public void setCurrentResources(double curResources) {
		this.currentResources.setValue(curResources);
		// System.out.println("VALUE UPDATED TO " + currentResources.getValue() + "AND SHOULD BE " + curResources);
	}

	public void setCurrentResources(String delta) {
		double newValue = getCurrentResources() + Double.parseDouble(delta);
		setCurrentResources(newValue);
	}

	public int getCurrentLevelIndex() {
		// checkEndOfLevel();
		return (int) currentLevelIndex.getValue();
	}

	public void updateHighestLevelUnlocked(int index) {
		if (index > highestLevelUnlocked) {
			highestLevelUnlocked = index;
		}
	}

	public int getHighestLevelUnlocked() {
		return highestLevelUnlocked;
	}

	public Property getCurrentLevelProperty() {
		return this.currentLevelIndex;
	}

	public void setCurrentMode(String c) {
		currentMode.setValue(c);
	}

	public String getCurrentMode() {
		// checkEndOfGame();
		return (String) currentMode.getValue();
	}

	public boolean noMoreLives() {
		return getCurrentNumLives() <= ZERO;
	}

	public Property getCurrentModeProperty() {
		return this.currentMode;
	}

	public void setCurrentLevelIndex(int currentLevelIndex) {
		this.currentLevelIndex.setValue(currentLevelIndex);
		updateHighestLevelUnlocked(currentLevelIndex);
	}

	public void setCurrentLevelIndex(String delta) {
		this.currentLevelIndex.setValue(Integer.parseInt(delta));
		updateHighestLevelUnlocked((int) currentLevelIndex.getValue());
	}

	private void checkEndOfLevel() {
		if (getCurrentNumLives() == 0) {
			setCurrentLevelIndex(-1);
		}
	}

	public int getNextAvailableID() {
		nextAvailableEntityID++;
		return nextAvailableEntityID;
	}

	@Override
	/**
	 * Applies action to the level and invokes the method on the level action
	 */
	public void applyAction(IAction action) {
		String instanceVar = ((LevelAction) action).getVariableToModify();
		String deltaVal = ((LevelAction) action).getDeltaValue();
		Method setMethod;
		ExceptionLoader myExceptionLoader = new ExceptionLoader();

		try {
			String methodName = PREFIX + instanceVar;
			setMethod = this.getClass().getMethod(methodName, String.class);
			setMethod.invoke(this, deltaVal);
		} catch (IllegalAccessException e) {
			new DrumpfTowerException(myExceptionLoader.getString(LACK_ACCESS));
		} catch (NoSuchMethodException e) {
			new DrumpfTowerException(myExceptionLoader.getString(METHOD_DNE));
		} catch (SecurityException e) {
			new DrumpfTowerException(myExceptionLoader.getString(SECURITY_EXCEPTION));
		} catch (IllegalArgumentException | InvocationTargetException e) {
			new DrumpfTowerException(myExceptionLoader.getString(ILLEGAL_ARGS));
		}
	}

	public void resetResourcesAndLives() {
		setCurrentNumLives(initialNumLives);
		setCurrentResources(initialResources);
	}
}
