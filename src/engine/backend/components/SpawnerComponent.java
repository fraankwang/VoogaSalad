package engine.backend.components;

import engine.backend.entities.IEntity;

public class SpawnerComponent extends Component implements IComponent{

	private String spawningEntityName;
	private double spawningRate;
	private double currentTimeStep;
	
	@Override
	public void initWithParams(String[] params) {
		// TODO Auto-generated method stub
		
	}

}
