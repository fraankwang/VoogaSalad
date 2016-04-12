package engine.backend.components;

import java.util.List;
import java.util.ResourceBundle;

public interface IComponent {
	
	public String getTag();
	public void initWithParams(List params);
	
}
