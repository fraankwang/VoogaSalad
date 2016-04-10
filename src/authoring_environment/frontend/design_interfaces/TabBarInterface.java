package authoring_environment.frontend.design_interfaces;

/**
 * This interface will allow other components in the UI to have access to the
 * different tab options, including Game, Modes, Levels, Enemies, and Towers.
 * @author benchesnut
 *
 */
public interface TabBarInterface extends AuthoringDisplayElement {

	PanelInterface getGamePanel();
	
	PanelInterface getModesPanel();
	
	PanelInterface getLevelsPanel();
	
	PanelInterface getTowersPanel();
	
	PanelInterface getEnemiesPanel();
}
