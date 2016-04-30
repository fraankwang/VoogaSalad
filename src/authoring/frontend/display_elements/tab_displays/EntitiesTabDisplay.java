package authoring.frontend.display_elements.tab_displays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.TreeMap;

import authoring.backend.data.ObservableList;
import authoring.backend.game_objects.AuthoringEntity;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.EntityEditorDisplay;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.grids.tab_grids.EntitiesTabGrid;
import authoring.frontend.display_elements.panels.GridViewPanel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class EntitiesTabDisplay extends TabDisplay {

	private static final List<String> DEFAULT_GENRES = Arrays.asList("Tower", "Enemy", "Ammo");

	private TabPane myEntitiesTabPane;
	private ObservableList<AuthoringEntity> myEntityList;
	private Map<String, Map<String, String>> myEntities;

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
		myEntities = new TreeMap<String, Map<String, String>>();

		for (String genre : DEFAULT_GENRES) {
			createNewTab(genre, false);
		}

		setTabPaneActions();
		myEntitiesTabPane.getSelectionModel().select(0);
	}

	@Override
	public Node getNode() {
		return myEntitiesTabPane;
	}

	private void setTabPaneActions() {
		Tab addNewTypeTab = new Tab("Add New...", null);

		myEntitiesTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab selectedTab) {
				if (selectedTab == addNewTypeTab) {

					String newGenre = myGrid.promptNewName();

					if (newGenre != "") {
						myEntitiesTabPane.getTabs().remove(addNewTypeTab);
						createNewTab(newGenre, true);
						myEntitiesTabPane.getTabs().add(addNewTypeTab);
					} else {
						myEntitiesTabPane.getSelectionModel().select(oldTab);
					}

				}

			}
		});

		myEntitiesTabPane.getTabs().add(addNewTypeTab);

		ContextMenu tabContextMenu = new ContextMenu();
		MenuItem tabMenu = new MenuItem("Change Genre name");
		tabMenu.setOnAction(e -> {
			String name = myGrid.promptNewName();
			((GridViewPanel) myGrid.getPrimaryDisplay()).setPanelBarDescription(name + " Entities");
			myEntitiesTabPane.getSelectionModel().getSelectedItem().setText(name);
		});
		tabContextMenu.getItems().add(tabMenu);
		myEntitiesTabPane.setContextMenu(tabContextMenu);
	}

	private void createNewTab(String name, boolean closeable) {
		EntitiesTabGrid grid = new EntitiesTabGrid(myController, this);
		grid.initialize();

		((GridViewPanel) grid.getPrimaryDisplay()).setPanelBarDescription(name + " Entities");
		Tab newTab = new Tab(name, grid.getNode());
		newTab.setClosable(closeable);
		newTab.setOnSelectionChanged(e -> {
			if (newTab.isSelected()) {
				myGrid = grid;
			}

			newTab.setOnClosed(f -> {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Alert Deletion Warning");
				alert.setHeaderText(
						"Deleting this genre will not delete the entities you have created within the genre.");
				alert.setContentText("To revisit these entities, Add New genre with the same name.");
				alert.show();
			});

		});
		myEntitiesTabPane.getTabs().add(newTab);
		myEntitiesTabPane.getSelectionModel().select(newTab);
	}

	/**
	 * For Entities, because there are multiple genres, it sends all the data to
	 * each of the different genre tabs with the genre name and only selects the
	 * entities whose genres match.
	 */
	@Override
	public void update(Observable o, Object arg) {
		Tab tempTab = myEntitiesTabPane.getSelectionModel().getSelectedItem();

		@SuppressWarnings("unchecked")
		List<Map<String, String>> data = (List<Map<String, String>>) arg;

		for (Tab t : myEntitiesTabPane.getTabs()) {
			if (!t.getText().equals("Add New...")) {
				myEntitiesTabPane.getSelectionModel().select(t);
				((EntitiesTabGrid) myGrid).updateEntitiesPrimaryDisplay(data, t.getText());
			}
		}

		myEntities.clear();
		for (Map<String, String> entity: data) {
			myEntities.put(entity.get("Name"), entity);
		}
		myEntitiesTabPane.getSelectionModel().select(tempTab);
	}

	@Override
	public Map<String, String> getDefaultAttributesMap() {
		Map<String, String> map = new TreeMap<String, String>();

		List<String> defaultAttributes = ((TabGrid) myGrid).getDefaultAttributes();
		for (String attribute : defaultAttributes) {
			map.put(attribute, null);
		}
		
		System.out.println("*****1. EntitiesTabDisplay: got default entities attributes");
		System.out.println(map);
		return map;
	}

	public void initializeHotKeys() {
		((TabGrid) myGrid).initializeHotKeys();

	}

	@Override
	public String getName() {
		return "Entities";
	}

	public Set<String> getGenres() {
		Set<String> genres = new HashSet<String>();
		myEntitiesTabPane.getTabs().forEach(t -> genres.add(t.getText()));
		genres.remove("Add New...");
		return genres;
	}

	/**
	 * Takes duplicates of all Entities by Genre and their Images.
	 * 
	 * @return
	 */
	public Map<String, String> getEntityImages() {
		Tab tempTab = myEntitiesTabPane.getSelectionModel().getSelectedItem();

		Map<String, String> entities = new TreeMap<String, String>();
		for (Tab t : myEntitiesTabPane.getTabs()) {
			if (!t.getText().equals("Add New...")) {
				myEntitiesTabPane.getSelectionModel().select(t);
				Map<String, String> genreEntities = (TreeMap<String, String>) ((EntitiesTabGrid) myGrid).getEntities();
				for (String name : genreEntities.keySet()) {
					String imagePath = genreEntities.get(name);
					entities.put(name, imagePath);
				}
			}
		}
		myEntitiesTabPane.getSelectionModel().select(tempTab);
		return entities;
	}

	public Map<String, Map<String, String>> getEntities() {
		return myEntities;
	}

}
