package engine.backend.game_object;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import engine.backend.rules.IAction;
import engine.backend.rules.LevelAction;
import utility.hud.Property;

/**
 * 
 * @author Raghav Kedia
 * 
 *
 */
public class GameStatistics implements IModifiable{
	
	private static final String PREFIX = "set";
	private int initialNumLives;
	private double initialResources;
	private int initialLevel;
	private String initialMode;
	
	private List<Integer> endOfLevelLives;
	private List<Double> endOfLevelResources;
	
	private Property currentNumLives;
	private Property currentResources;
	private Property currentLevelIndex;
	private Property currentMode;

	public GameStatistics(int numLives, double resources) {
		setInitialNumLives(numLives);
		setInitialResources(resources);
		
		currentNumLives = new Property(initialNumLives, "lives");
		currentResources = new Property(initialResources, "resources");
		currentLevelIndex = new Property(initialLevel, "level");
		currentMode = new Property((initialMode = new String()), "mode");
		
		setCurrentNumLives(numLives);	
		setCurrentResources(resources);
//		endOfLevelLives = new ArrayList<Integer>();
//		endOfLevelResources = new ArrayList<Double>();
	}
	
	public GameStatistics() {
		
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
		return (int) currentNumLives.getValue();
	}
	
	public Property getCurrentLivesProperty(){
		return this.currentNumLives;
	}

	public void setCurrentNumLives(int currentNumLives) {
		this.currentNumLives.setValue(currentNumLives);
	}
	
	public void setCurrentNumLives(String deltaNumLives){
		int newValue = getCurrentNumLives() + Integer.parseInt(deltaNumLives);
		setCurrentNumLives(newValue);
	}

	public double getInitialResources() {
		return initialResources;
	}

	public void setInitialResources(double initialResources) {
		this.initialResources = initialResources;
	}

	public double getCurrentResources() {
		return (double) currentResources.getValue();
	}
	
	public Property getCurrentResourcesProperty(){
		return this.currentResources;
	}

	public void setCurrentResources(double currentResources) {
		this.currentResources.setValue(currentResources);
	}
	
	public void setCurrentResources(String delta){
		double newValue = getCurrentResources() + Double.parseDouble(delta);
		setCurrentResources(newValue);
	}

	public int getCurrentLevelIndex() {
		checkEndOfGame();
		return (int) currentLevelIndex.getValue();
	}
	
	public Property getCurrentLevelProperty(){
		return this.currentLevelIndex;
	}


	public void setCurrentMode(String c){
		currentMode.setValue(c);
	}

	public String getCurrentMode() {
		checkEndOfGame();
		return (String) currentMode.getValue();
	}
	
	public Property getCurrentModeProperty(){
		return this.currentMode;
	}
	
	public void setCurrentLevelIndex(int currentLevelIndex) {
		this.currentLevelIndex.setValue(currentLevelIndex);
	}
	
	public void setCurrentLevelIndex(String delta){
		this.currentLevelIndex.setValue(Integer.parseInt(delta));
	}
	
	private void checkEndOfGame(){
		if(getCurrentNumLives() == 0){
			setCurrentLevelIndex(-1);
		}
	}

	@Override
	public void applyAction(IAction action) {
		String instanceVar = ((LevelAction) action).getVariableToModify();
		String deltaVal = ((LevelAction) action).getDeltaValue();
		Method setMethod;

		try {
			String methodName = PREFIX + instanceVar;
			setMethod = this.getClass().getMethod(methodName, String.class);
			setMethod.invoke(this, deltaVal);

			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}



