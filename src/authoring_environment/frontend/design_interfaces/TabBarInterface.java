package authoring_environment.frontend.design_interfaces;

public interface TabBarInterface extends AuthoringDisplayElement {

	void show(DisplayInterface display);
	
	DisplayInterface getGameDisplay();
	
	DisplayInterface getModesDisplay();
	
	DisplayInterface getLevelsDisplay();
	
	DisplayInterface getTowersDisplay();
	
	DisplayInterface getEnemiesDisplay();
}
