package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.editor_displays.EntityEditorDisplay;
import authoring_environment.frontend.display_elements.grids.Grid;
import authoring_environment.frontend.display_elements.grids.tab_grids.EntitiesTabGrid;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * 
 * @author benchesnut
 *
 */

public class EntitiesTabDisplay extends TabDisplay {

	private TabPane myEntitiesTabPane;
	private Grid myActiveGrid;

	public EntitiesTabDisplay(IController controller) {
		super(controller);
		myController = controller;
	}

	public void initialize() {
		myEntitiesTabPane = new TabPane();	// tab of entity types
		myEditorDisplay = new EntityEditorDisplay(myController);
		myActiveGrid = new EntitiesTabGrid(myController, this);
		myGrid = myActiveGrid;
		
		createNewTab("Unknown Type");
		Tab addNewTypeTab = new Tab("Add New...", null);
		myEntitiesTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			
			  @Override
			    public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab selectedTab) {
			        if(selectedTab == addNewTypeTab) {
			        	myEntitiesTabPane.getTabs().remove(addNewTypeTab);
			        	createNewTab("Unknown Type");
			        	myEntitiesTabPane.getTabs().add(addNewTypeTab);
			        }
			    }
		});
		myEntitiesTabPane.getTabs().add(addNewTypeTab);
	}
	
	@Override
	public Node buildNode() {
		initialize();
		return myEntitiesTabPane;
	}
	
	public void createNewTab(String name) {
		EntitiesTabGrid grid = new EntitiesTabGrid(myController, this);
		Tab newTab = new Tab(name, grid.buildNode());
		myEntitiesTabPane.getTabs().add(newTab);
		myEntitiesTabPane.getSelectionModel().select(newTab);
	}
}
