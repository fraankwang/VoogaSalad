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

	private ObservableList<AuthoringEntity> myEntityList;
	private TabPane myEntitiesTabPane;
	private Map<String, Map<String, String>> myEntities;

	private static final List<String> DEFAULT_GENRES = Arrays.asList("Tower", "Enemy", "Ammo", "Custom");

	public EntitiesTabDisplay(int tabIndex, IAuthoringView controller) {
		super(tabIndex, controller);
		myController = controller;
		myEntityList = myController.getEntityList();
		myEntityList.addObserver(this);
	}

	public void initialize() {
		myEntitiesTabPane = new TabPane();
		myEditorDisplay = new EntityEditorDisplay(myController);
		myEditorDisplay.initialize();
		myEntities = new TreeMap<String, Map<String, String>>();

		for (String genre : DEFAULT_GENRES) {
			createNewTab(genre, false);
		}

		createAddNewGenreTab();
		setContextMenu();
		myEntitiesTabPane.getSelectionModel().select(0);
	}

	/**
	 * Specifies the Add New Type Tab. Error checks to do nothing if the user
	 * inputs nothing or a tab that already exists.
	 */
	private void createAddNewGenreTab() {
		Tab addNewTypeTab = new Tab("Add New...", null);

		myEntitiesTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab selectedTab) {
				if (selectedTab == addNewTypeTab) {

					String newGenre = myGrid.promptNewName();

					if (newGenre != "" && !getGenres().contains(newGenre)) {
						myEntitiesTabPane.getTabs().remove(addNewTypeTab);
						createNewTab(newGenre, true);
						myEntitiesTabPane.getTabs().add(addNewTypeTab);
					}

					myEntitiesTabPane.getSelectionModel().select(oldTab);
				}

			}
		});

		myEntitiesTabPane.getTabs().add(addNewTypeTab);

	}

	/**
	 * Allows for tab renaming. Updates myGrid's genre variable.
	 */
	private void setContextMenu() {
		ContextMenu tabContextMenu = new ContextMenu();
		MenuItem tabMenu = new MenuItem("Change Genre name");

		tabMenu.setOnAction(e -> {
			String name = myGrid.promptNewName();
			((GridViewPanel) myGrid.getPrimaryDisplay()).setPanelBarDescription(name + " Entities");
			myEntitiesTabPane.getSelectionModel().getSelectedItem().setText(name);
			((EntitiesTabGrid) myGrid).setGenre(name);
		});

		tabContextMenu.getItems().add(tabMenu);
		myEntitiesTabPane.setContextMenu(tabContextMenu);

	}

	/**
	 * Creates a new EntitiesTabGrid, which contains all the UI displays for
	 * each tab called @param name. Closing the tab will give the user a warning
	 * and selecting the tab will set the current grid to the grid associated
	 * with the genre tab.
	 * 
	 * @param name
	 * @param closeable
	 */
	private void createNewTab(String name, boolean closeable) {
		EntitiesTabGrid grid = new EntitiesTabGrid(myController, this);
		grid.setGenre(name);
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
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {

		List<Map<String, String>> data = (List<Map<String, String>>) arg;
		update(data);

	}
	

	public void update(List<Map<String, String>> data) {
		Tab tempTab = myEntitiesTabPane.getSelectionModel().getSelectedItem();
		updateMyEntities(data);
		
		for (Tab t : myEntitiesTabPane.getTabs()) {
			if (!t.getText().equals("Add New...")) {
				myEntitiesTabPane.getSelectionModel().select(t);
				((EntitiesTabGrid) myGrid).updateEntitiesPrimaryDisplay(data, t.getText());
			}
		}

		myEntitiesTabPane.getSelectionModel().select(tempTab);
	}
	

	/**
	 * Maps each of the entities to their names. This information is pulled by
	 * other parts of the GUI to keep an up-to-date list of all entities the
	 * user has created.
	 * 
	 * @param data
	 */
	private void updateMyEntities(List<Map<String, String>> data) {
		myEntities.clear();

		for (Map<String, String> entity : data) {
			myEntities.put(entity.get("Name"), entity);
		}
	}

	@Override
	public Map<String, String> getDefaultAttributesMap() {
		Map<String, String> map = new TreeMap<String, String>();
		List<String> defaultAttributes = ((TabGrid) myGrid).getDefaultAttributes();
		
		for (String attribute : defaultAttributes) {
			map.put(attribute, null);
		}
		
		return map;
	}

	public void initializeHotKeys() {
		((TabGrid) myGrid).initializeHotKeys();
	}

	/**
	 * Returns a set of all genres created by the user (including the default
	 * genres).
	 * 
	 * @return
	 */
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

	@Override
	public Node getNode() {
		return myEntitiesTabPane;
	}

	public Map<String, Map<String, String>> getEntities() {
		return myEntities;
	}

	@Override
	public String getName() {
		return "Entities";
	}

}
