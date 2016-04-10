package backend.game_object.components;

public class DamageComponent extends Component implements IComponent{
	
	private double myDamage;
	
	public DamageComponent(double damage){
		setDamage(damage);
	}

	public double getDamage() {
		return myDamage;
	}

	public void setDamage(double myDamage) {
		this.myDamage = myDamage;
	}
	
	@Override
	public String getTag(){
		return "Damage";
	}
	
}
