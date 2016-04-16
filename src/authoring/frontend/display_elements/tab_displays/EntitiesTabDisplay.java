package authoring.frontend.display_elements.tab_displays;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.EntityEditorDisplay;
import authoring.frontend.display_elements.grids.Grid;
import authoring.frontend.display_elements.grids.tab_grids.EntitiesTabGrid;
import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels.UnmodifiableEntityAttributesPanel;
import engine.backend.components.IComponent;
import engine.backend.entities.IEntity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;

/**
 * 
 * @author benchesnut
 *
 */

public class EntitiesTabDisplay extends TabDisplay {

	private TabPane myEntitiesTabPane;
	private Grid myActiveGrid;

	public EntitiesTabDisplay(int tabIndex, IAuthoringView controller) {
		super(tabIndex, controller);
		myController = controller;
	}

	public void initialize() {
		myEntitiesTabPane = new TabPane();	// tab of entity types
		myEditorDisplay = new EntityEditorDisplay(myController);
		myEditorDisplay.initialize();
		myActiveGrid = new EntitiesTabGrid(myController, this);
		myActiveGrid.initialize();
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
	public Node getNode() {
		return myEntitiesTabPane;
	}
	
	public void createNewTab(String name) {
		EntitiesTabGrid grid = new EntitiesTabGrid(myController, this);
		grid.initialize();
		Tab newTab = new Tab(name, grid.getNode());
		myEntitiesTabPane.getTabs().add(newTab);
		myEntitiesTabPane.getSelectionModel().select(newTab);
	}

	@Override
	public void update(Observable o, Object arg) {
		//List<Map<String, String>> entities = (List<Map<String, String>>) arg;
		//for (Map<String, String> entity: entities) {
		//	entity.
		//}
		
		// for each entity:
		// create an ImageView
		// create an UnmodifiableEntityAttributesPanel
	}
	
	@Override
	public String getName() {
		return "Entities";
	}
	
}
