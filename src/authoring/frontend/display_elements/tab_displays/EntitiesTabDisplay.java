package authoring.frontend.display_elements.tab_displays;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.EntityEditorDisplay;
import authoring.frontend.display_elements.grids.tab_grids.EntitiesTabGrid;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import authoring.backend.data.ObservableList;
import engine.backend.entities.Entity;

/**
 * 
 * @author benchesnut
 *
 */

public class EntitiesTabDisplay extends TabDisplay {

	private TabPane myEntitiesTabPane;
	private ObservableList<Entity> myEntityList;

	public EntitiesTabDisplay(int tabIndex, IAuthoringView controller) {
		super(tabIndex, controller);
		myController = controller;
		myEntityList = myController.getEntityList();
		myEntityList.addObserver(this);
	}

	public void initialize() {
		myEntitiesTabPane = new TabPane(); // tab of entity types
		myEditorDisplay = new EntityEditorDisplay(myController);
		myEditorDisplay.initialize();

		createNewTab("Unknown Type");

		Tab addNewTypeTab = new Tab("Add New...", null);
		myEntitiesTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab selectedTab) {
				if (selectedTab == addNewTypeTab) {
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
		newTab.setOnSelectionChanged(e -> {
			if (newTab.isSelected()) {
				myGrid = grid;
			}
		});
		myEntitiesTabPane.getTabs().add(newTab);
		myEntitiesTabPane.getSelectionModel().select(newTab);
	}

	@Override
	public String getName() {
		return "Entities";
	}

}
