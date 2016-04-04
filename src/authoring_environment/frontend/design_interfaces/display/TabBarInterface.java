package authoring_environment.frontend.design_interfaces.display;

import authoring_environment.frontend.design_interfaces.AuthoringDisplayElement;

public interface TabBarInterface extends AuthoringDisplayElement {

	void show(DisplayInterface display);
	
	DisplayInterface getGameDisplay();
	
	DisplayInterface getModesDisplay();
	
	DisplayInterface getLevelsDisplay();
	
	DisplayInterface getTowersDisplay();
	
	DisplayInterface getEnemiesDisplay();
}
