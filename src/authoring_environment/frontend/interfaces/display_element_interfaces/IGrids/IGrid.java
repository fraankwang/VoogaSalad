package authoring_environment.frontend.interfaces.display_element_interfaces.IGrids;

import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElement;

public interface IGrid extends IDisplayElement {
	
	abstract void createLeftSubGrid();
	
	abstract void createRightSubGrid();
	
	abstract void createButtonDashboard();
}
