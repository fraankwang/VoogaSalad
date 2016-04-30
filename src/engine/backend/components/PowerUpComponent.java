package engine.backend.components;

import java.util.List;

import engine.backend.rules.IAction;

public abstract class PowerUpComponent extends Component{
	private List<IAction> actions;

	public PowerUpComponent() {
		// TODO Auto-generated constructor stub
	}

	public PowerUpComponent(PowerUpComponent component) {
		this.setActions(component.getActions());
	}

	@Override
	public String getComponentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String dataName, String data) {
		
	}
	
	public List<IAction> getActions(){
		return this.actions;
	}

	public void setActions(List<IAction> actions) {
		this.actions = actions;
	}
	
	
	

}
