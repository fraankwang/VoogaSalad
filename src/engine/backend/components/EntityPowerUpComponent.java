package engine.backend.components;

import java.util.List;

public class EntityPowerUpComponent extends PowerUpComponent{
	private List<String> applicableEntities;
	
	public EntityPowerUpComponent(EntityPowerUpComponent component) {
		super(component);
		this.setEntities(component.getEntities());
	}
	
	public void setEntities(List<String> entities){
		this.applicableEntities = entities;
	}
	
	public List<String> getEntities(){
		return this.applicableEntities;
	}

}
