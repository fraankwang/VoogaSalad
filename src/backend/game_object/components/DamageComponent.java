package backend.game_object.components;

public class DamageComponent extends Component implements IComponent{
	
	private double myDamageToHealth;
	private double myDamageToVelocity;
	//private Vector myDamageToPosition;
	
	
	public DamageComponent(double damage){
		setDamageToHealth(damage);
	}

	public double getDamageToHealth() {
		return myDamageToHealth;
	}

	public void setDamageToHealth(double myDamage) {
		this.myDamageToHealth = myDamage;
	}

	public double getDamageToVelocity() {
		return myDamageToVelocity;
	}

	public void setDamageToVelocity(double myDamageToVelocity) {
		this.myDamageToVelocity = myDamageToVelocity;
	}
	
}
