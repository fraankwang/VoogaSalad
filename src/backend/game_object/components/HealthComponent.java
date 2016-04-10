package backend.game_object.components;

public class HealthComponent extends Component implements IComponent{
	
	private double myHealth;
	private double myDamage;
	
	public HealthComponent(double health, double damage) {
		// TODO Auto-generated constructor stub
		myHealth = health;
		myDamage = damage;
	}
	
	public double getHealth(){
		return myHealth;
	}
	
	public void setHealth(double newHealth){
		myHealth = newHealth;
	}
	
	public void setDamage(double newDamage){
		myDamage = newDamage;
	}
	
	@Override
	public String getTag(){
		return "Health";
	}

	public double getDamage() {
		// TODO Auto-generated method stub
		return myDamage;
	}

}
