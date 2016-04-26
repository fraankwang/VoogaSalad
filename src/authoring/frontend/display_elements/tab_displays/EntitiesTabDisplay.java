package authoring.frontend.display_elements.tab_displays;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.TreeMap;

import authoring.backend.data.ObservableList;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.EntityEditorDisplay;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.grids.tab_grids.EntitiesTabGrid;
import authoring.frontend.display_elements.panels.GridViewPanel;
import authoring.frontend.editor_features.LocalImage;
import engine.backend.entities.Entity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class EntitiesTabDisplay extends TabDisplay {

	private TabPane myEntitiesTabPane;
	private ObservableList<Entity> myEntityList;
	private String genreName;

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

		createNewTab("Tower", false);
		createNewTab("Enemy", false);
		createNewTab("Ammo", false);
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
					String newGenre = promptGenreName();
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
			String name = promptGenreName();
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

	private String promptGenreName() {
		Stage promptStage = new Stage();
		genreName = "";
		VBox promptBox = new VBox();
		promptBox.setAlignment(Pos.CENTER);
		Label prompt = new Label("Enter new genre name:");
		TextField textBox = new TextField();
		textBox.setMaxWidth(200);
		promptBox.getChildren().add(prompt);
		promptBox.getChildren().add(textBox);
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		Button cancelButton = new Button("Cancel");
		Button saveButton = new Button("Save");
		cancelButton.setOnAction(e -> promptStage.close());
		textBox.setOnAction(e -> {
			genreName = textBox.getText();
			promptStage.close();
		});

		saveButton.setOnAction(e -> {
			genreName = textBox.getText();
			promptStage.close();
		});
		buttonBox.getChildren().addAll(cancelButton, saveButton);
		promptBox.getChildren().add(buttonBox);
		Scene promptScene = new Scene(promptBox, 300, 200);
		promptStage.setScene(promptScene);
		promptStage.showAndWait();
		return genreName;
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
				((EntitiesTabGrid) myGrid).update(data, t.getText());
			}
		}

		myEntitiesTabPane.getSelectionModel().select(tempTab);
	}

	@Override
	public Map<String, String> getDefaultAttributesMap() {
		Map<String, String> map = new TreeMap<String, String>();

		map.put("DisplayComponent_Image", null);
		map.put("DisplayComponent_CanBeShown", null);
		map.put("Name", null);
		map.put("Genre", null);

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
	 * Takes duplicates of all Entities by Genre and their ImageViews.
	 * 
	 * @return
	 */
	public Map<String, ImageView> getEntities() {
		Tab tempTab = myEntitiesTabPane.getSelectionModel().getSelectedItem();

		Map<String, ImageView> entities = new TreeMap<String, ImageView>();
		for (Tab t : myEntitiesTabPane.getTabs()) {
			if (!t.getText().equals("Add New...")) {
				myEntitiesTabPane.getSelectionModel().select(t);
				Map<String, ImageView> genreEntities = (TreeMap<String, ImageView>) ((EntitiesTabGrid) myGrid)
						.getEntities();
				for (String name : genreEntities.keySet()) {
					String imagePath = ((LocalImage) genreEntities.get(name).getImage()).getURL();
					Image newImage = new Image(imagePath);
					ImageView newImageView = new ImageView(newImage);
					entities.put(name, newImageView);
				}
			}
		}
		myEntitiesTabPane.getSelectionModel().select(tempTab);
		return entities;
	}

}
