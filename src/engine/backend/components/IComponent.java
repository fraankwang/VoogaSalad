package engine.backend.components;

import java.util.List;

public interface IComponent {
	
	public String getTag();
	public void initWithParams(List params);
	
}
