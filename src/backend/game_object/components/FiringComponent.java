package backend.game_object.components;

import java.util.List;

import backend.game_object.entities.IEntity;

public class FiringComponent extends Component implements IComponent{
	
	private IEntity myAmmunition;
	private int myAmmunitionAmount;
	private double myEnemyInSightRange;
	
	@Override
	public String getTag(){
		return "Firing";
	}

	public IEntity getAmmunition() {
		return myAmmunition;
	}

	public void setAmmunition(IEntity myAmmunition) {
		this.myAmmunition = myAmmunition;
	}

	public int getAmmunitionAmount() {
		return myAmmunitionAmount;
	}

	public void setAmmunitionAmount(int myAmmunitionAmount) {
		this.myAmmunitionAmount = myAmmunitionAmount;
	}

	public double getEnemyInSightRange() {
		return myEnemyInSightRange;
	}

	public void setEnemyInSightRange(double myEnemyInSightRange) {
		this.myEnemyInSightRange = myEnemyInSightRange;
	}

	@Override
	public void initWithParams(List params) {
		// TODO Auto-generated method stub
		
	}
	
}
