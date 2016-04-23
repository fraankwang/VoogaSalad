package engine.backend.components;

import java.util.List;

/**
 * 
 * @author raghavkedia
 *
 */

public class FiringComponent extends Component implements IComponent{
	
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
		this.myFiringRate = myFiringRate;
	}
	
	public FiringComponent(FiringComponent component) {
		this.myAmmunition = component.getAmmunition();
		this.myAmmunitionAmount = component.getAmmunitionAmount();
		this.myAmmunitionSpeed = component.getAmmunitionSpeed();
		this.myEnemyInSightRange = component.getEnemyInSightRange();
		this.myDirectionToFire = component.getDirectionToFire();
		this.myFiringRate = component.getFiringRate();
		this.timer = component.getTimer();
	}
	
	public String getAmmunition() {
		return myAmmunition;
	}

	public void setAmmunition(String myAmmunition) {
		this.myAmmunition = myAmmunition;
	}

	public int getAmmunitionAmount() {
		return myAmmunitionAmount;
	}

	public void setAmmunitionAmount(String ammunitionAmount) {
		int newVal = Integer.parseInt(ammunitionAmount);
		this.myAmmunitionAmount = newVal;
	}
	
	public void setAmmunitionAmount(int ammunitionAmount) {
		this.myAmmunitionAmount = ammunitionAmount;
	}

	public double getEnemyInSightRange() {
		return myEnemyInSightRange;
	}

	public void setEnemyInSightRange(double myEnemyInSightRange) {
		this.myEnemyInSightRange = myEnemyInSightRange;
	}
	
	public Vector getDirectionToFire() {
		return myDirectionToFire;
	}

	public void setDirectionToFire(Vector myDirectionToFire) {
		this.myDirectionToFire = myDirectionToFire;
	}

	public double getAmmunitionSpeed() {
		return myAmmunitionSpeed;
	}

	public void setAmmunitionSpeed(String myAmmunitionSpeed) {
		double newVal = Double.parseDouble(myAmmunitionSpeed);
		this.myAmmunitionSpeed = newVal;
	}
	
	public void setAmmunitionSpeed(double speed) {
		this.myAmmunitionSpeed = speed;
	}
	
	@Override
	public void initWithParams(String[] params) {
		myAmmunition = params[0];
		myAmmunitionAmount = Integer.parseInt(params[1]);
		myAmmunitionSpeed = Double.parseDouble(params[2]);
		myEnemyInSightRange = Double.parseDouble(params[3]);
		//myDirectionToFire = Double.parseDouble(params[4]);
	}
	
	/*
	 * Returns a list of strings representing the entities that can be targets of the entity with this
	 * firing component. 
	 */
	public List<String> getTargets() {
		return myTargets;
	}
	
	public boolean fireNow(){
		return fireNow;
	}
	
	public void setFireNow(boolean bool){
		fireNow = bool;
	}
	
	public void setTargets(List<String> myTargets) {
		this.myTargets = myTargets;
	}

	public double getTimer() {
		return timer;
	}

	public void setTimer(double currentSecond){
		if(this.currentSecond != currentSecond){
			this.currentSecond = currentSecond;
			timer = timer - 1;
		}
	}
	
	public void resetTimer(){
		timer = myFiringRate;
	}

	public double getFiringRate() {
		return myFiringRate;
	}

	public void setFiringRate(String firingRate) {
		double newVal = Double.parseDouble(firingRate);
		this.myFiringRate = newVal;
	}
	
	public void setFiringRate(double firingRate) {
		this.myFiringRate = firingRate;
	}
	
}
