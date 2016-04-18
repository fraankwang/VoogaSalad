package engine.backend.components;

public class ArmorComponent {
	
	private double resistanceToDamage;
	
	public void setResistanceToDamage(double value){
		this.resistanceToDamage = value;
	}
	
	public void setResistanceToDamage(String value){
		double newValue = Double.parseDouble(value);
		this.resistanceToDamage = newValue;
	}
}
