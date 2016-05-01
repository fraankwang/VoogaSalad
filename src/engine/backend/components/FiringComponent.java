package engine.backend.components;

import java.util.ArrayList;
import java.util.List;

import engine.backend.systems.GameClock;

/**
 * @author raghavkedia
 */

public class FiringComponent extends Component{
	
	private String myAmmunition;
	private int myAmmunitionAmount;
	private double myAmmunitionSpeed;
	private double myEnemyInSightRange;
	private Vector myDirectionToFire;
	private List<String> myTargets;
	private double myFiringRate;
	private double timer;
	private double currentSecond;
	private boolean fireNow;
	
	public FiringComponent(String myAmmunition, int myAmmunitionAmount, double myAmmunitionSpeed, 
			double myEnemyInSightRange, Vector myDirectionToFire, double myFiringRate){
		this.myAmmunition = myAmmunition;
		this.myAmmunitionAmount = myAmmunitionAmount;
		this.myAmmunitionSpeed = myAmmunitionSpeed;
		this.myEnemyInSightRange = myEnemyInSightRange;
		this.myDirectionToFire = myDirectionToFire;
		
		if(myFiringRate < 0){
			timer = -1;
		}
		
		this.myFiringRate = myFiringRate;
	}
	
	/**
	 * Initializing a firing component from an existing firing component. 
	 * @param component
	 */
	public FiringComponent(FiringComponent component) {
		this.myAmmunition = component.getAmmunition();
		this.myAmmunitionAmount = component.getAmmunitionAmount();
		this.myAmmunitionSpeed = component.getAmmunitionSpeed();
		this.myEnemyInSightRange = component.getEnemyInSightRange();
		this.myDirectionToFire = component.getDirectionToFire();
		this.myFiringRate = component.getFiringRate();
		this.timer = component.getTimer();
		this.myTargets = component.getTargets();
	}
	
	public FiringComponent() {
		this.myTargets = new ArrayList<String>();
	}
	
	/**
	 * 
	 * @return The string representing the name of the ammunition.
	 */
	public String getAmmunition() {
		return myAmmunition;
	}

	/**
	 * Sets the name of the ammunition. This is the name of a type of entity.
	 * @param myAmmunition
	 */
	public void setAmmunition(String myAmmunition) {
		this.myAmmunition = myAmmunition;
	}

	/**
	 * 
	 * @return An int representing the amount of ammunition for this firing component.
	 */
	public int getAmmunitionAmount() {
		return myAmmunitionAmount;
	}

	/**
	 * Sets the amount of ammunition for this firing component.
	 * @param ammunitionAmount
	 */
	public void setAmmunitionAmount(String ammunitionAmount) {
		int newVal = Integer.parseInt(ammunitionAmount);
		this.myAmmunitionAmount = newVal;
	}
	
	/**
	 * Sets the amount of ammunition for this firing component.
	 * @param ammunitionAmount
	 */
	public void setAmmunitionAmount(int ammunitionAmount) {
		this.myAmmunitionAmount = ammunitionAmount;
	}

	/**
	 * 
	 * @return A double representing the distance for which an enemy is in sight of the entity
	 * with this firing component.
	 */
	public double getEnemyInSightRange() {
		return myEnemyInSightRange;
	}

	/**
	 * Sets the range for which an enemy is in sight of the entity with this firing component.
	 * @param myEnemyInSightRange
	 */
	public void setEnemyInSightRange(double myEnemyInSightRange) {
		this.myEnemyInSightRange = myEnemyInSightRange;
	}
	
	/**
	 * 
	 * @return The vector object which represents a direction. Will be treated as a unit vector.
	 */
	public Vector getDirectionToFire() {
		return myDirectionToFire;
	}

	/**
	 * Sets the vector object that represents a direction to fire. This will be treated as a unit vector.
	 * @param myDirectionToFire
	 */
	public void setDirectionToFire(Vector myDirectionToFire) {
		this.myDirectionToFire = myDirectionToFire;
	}

	/**
	 * 
	 * @return The speed at which the ammunition for this firing component will travel in pixels per seconds.
	 */
	public double getAmmunitionSpeed() {
		return myAmmunitionSpeed;
	}

	/**
	 * Sets the speed at which the ammunition of this firing component will travel in pixels per second. 
	 * @param myAmmunitionSpeed
	 */
	public void setAmmunitionSpeed(String myAmmunitionSpeed) {
		double newVal = Double.parseDouble(myAmmunitionSpeed);
		this.myAmmunitionSpeed = newVal;
	}
	
	/**
	 * Sets the speed at which the ammunition of this firing component will travel in pixels per second. 
	 * @param myAmmunitionSpeed
	 */
	public void setAmmunitionSpeed(double speed) {
		this.myAmmunitionSpeed = speed;
	}
	
	 /**
	  * 
	  * @return A list of strings representing the entities that can be targets of the entity with this firing component. 
	  */
	public List<String> getTargets() {
		return myTargets;
	}
	
	/**
	 * 
	 * @return 
	 */
	public boolean fireNow(){
		return fireNow;
	}
	
	/**
	 * 
	 * @param bool
	 */
	public void setFireNow(boolean bool){
		fireNow = bool;
	}
	
	public void setFireNow(String bool){
		fireNow  = Boolean.parseBoolean(bool);
	}
	
	/**
	 * Sets a list of targets based on the entity names of possible targets.
	 * @param myTargets
	 */
	public void setTargets(List<String> myTargets) {
		this.myTargets = myTargets;
	}

	/**
	 * 
	 * @return A double value for timer for time till next possible time to fire.
	 */
	public double getTimer() {
		return timer;
	}
	
	/**
	 * Sets the double for the timer for time till next possible time to fire.
	 * @param currentSecond
	 */
	public void setTimer(double currentSecond){
		if(this.currentSecond != currentSecond){
			this.currentSecond = currentSecond;
			timer = timer - 1;
		}
	}
	
	public void decrementTimer(){
		timer = timer - GameClock.getTimePerLoop();
	}
	
	/**
	 * Resets the timer to to the firing rate set. Will scale firing rate by appropriate factor.
	 */
	public void resetTimer(){
		timer = myFiringRate * 100;
	}

	/**
	 * 
	 * @return A double representing how frequently the entity with this firing component can fire in seconds.
	 */
	public double getFiringRate() {
		return myFiringRate;
	}
	
	/**
	 * Sets how frequently a entity with this firing component can fire if a target is in rannge.
	 * @param firingRate
	 */
	public void setFiringRate(String firingRate) {
		double newVal = Double.parseDouble(firingRate);
		this.myFiringRate = newVal;
	}
	
	/**
	 * Sets how frequently a entity with this firing component can fire if a target is in rannge.
	 * @param firingRate
	 */
	public void setFiringRate(double firingRate) {
		this.myFiringRate = firingRate;
	}

	@Override
	public String getComponentInfo() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Ammunition:");
		sb.append(myAmmunition);
		sb.append(",");
		sb.append("AmmunitionSpeed:");
		sb.append(myAmmunitionSpeed);
		sb.append(",");
		sb.append("EnemyInSightRange:");
		sb.append(myEnemyInSightRange);
		sb.append(",");
		sb.append("Targets:");
		sb.append(myTargets);
		sb.append(",");
		sb.append("FiringRate:");
		sb.append(myFiringRate);
		
		return sb.toString();
	}

	@Override
	public void update(String dataName, String data) {
		switch (dataName) {
		
		case "Ammunition":
			this.myAmmunition = data;
			return;
		case "AmmunitionSpeed":
			this.myAmmunitionSpeed = Double.parseDouble(data);
			return;
		case "EnemyInSightRange":
			this.myEnemyInSightRange = Double.parseDouble(data);
			return;
		case "Targets":
			String[] targets = data.split("_");
			for (String target : targets) {
				this.myTargets.add(target);
			}
			return;
		case "FiringRate":
			this.myFiringRate = Double.parseDouble(data);
			return;
			
		}
	}
	
	public int getNumDirections(){
		return 1;
	}
	
}
