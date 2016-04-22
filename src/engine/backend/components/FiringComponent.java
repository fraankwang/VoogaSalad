package engine.backend.components;

import java.util.List;

/**
 * @author raghavkedia
 */

public class FiringComponent extends Component {
	
	private String myAmmunition;
	private int myAmmunitionAmount;
	private double myAmmunitionSpeed;
	private double myEnemyInSightRange;
	private Vector myDirectionToFire;
	private List<String> myTargets;
	private double myFiringRate;
	private double currentTimeStep;
	
	public FiringComponent(){
	
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

	public List<String> getTargets() {
		return myTargets;
	}

	public void setTargets(List<String> myTargets) {
		this.myTargets = myTargets;
	}

	public double getCurrentTimeStep() {
		return currentTimeStep;
	}

	public void incrementCurrentTimeStep(){
		currentTimeStep++;
	}
	
	public void resetCurrentTimeStep(){
		currentTimeStep = 0;
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

	@Override
	public String getComponentInfo() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("MyAmmunition:");
		sb.append(myAmmunition);
		sb.append(",");
		sb.append("MyAmmunitionSpeed:");
		sb.append(myAmmunitionSpeed);
		sb.append(",");
		sb.append("MyEnemyInSightRange:");
		sb.append(myEnemyInSightRange);
		sb.append(",");
		sb.append("MyTargets:");
		sb.append(myTargets);
		sb.append(",");
		sb.append("MyFiringRate:");
		sb.append(myFiringRate);
		
		return sb.toString();
	}

	@Override
	public void update(String dataName, String data) {
		switch (dataName) {
		
		case "MyAmmunition":
			this.myAmmunition = data;
			return;
		case "MyAmmunitionSpeed":
			this.myAmmunitionSpeed = Double.parseDouble(data);
			return;
		case "MyEnemyInSightRange":
			this.myEnemyInSightRange = Double.parseDouble(data);
			return;
		case "MyFiringRate":
			this.myFiringRate = Double.parseDouble(data);
			return;
			
		}
	}
	
}
