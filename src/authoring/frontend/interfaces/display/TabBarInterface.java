package authoring.frontend.interfaces.display;

import authoring.frontend.interfaces.AuthoringDisplayElement;

public interface TabBarInterface extends AuthoringDisplayElement {

	void show(DisplayInterface display);
	
	DisplayInterface getGameDisplay();
	
	DisplayInterface getModesDisplay();
	
	DisplayInterface getLevelsDisplay();
	
	DisplayInterface getTowersDisplay();
	
	DisplayInterface getEnemiesDisplay();
}
