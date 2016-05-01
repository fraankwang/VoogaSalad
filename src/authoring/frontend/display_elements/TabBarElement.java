package authoring.frontend.display_elements;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.tab_displays.EntitiesTabDisplay;
import authoring.frontend.display_elements.tab_displays.LevelsTabDisplay;
import authoring.frontend.display_elements.tab_displays.ModesTabDisplay;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import authoring.frontend.interfaces.display_element_interfaces.ITabBarElement;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The TabBar element is a main UI component that contains and creates all the
 * game-related tabs. If the game must be changed to incorporate or remove
 * components, the TabBar's TabPane is modified within this class, as each of
 * the Tab's contents are set to their respective TabDisplay's Node.
 * 
 * @author Frank, benchesnut
 *
 */

public class TabBarElement implements ITabBarElement {

	private TabPane myTabPane;
	private TabDisplay myModesTabDisplay;
	private TabDisplay myLevelsTabDisplay;
	private TabDisplay myEntitiesTabDisplay;
	private static final int MODES_TAB_INDEX = 1;
	private static final int LEVELS_TAB_INDEX = 2;
	private static final int ENTITIES_TAB_INDEX = 3;
	private IAuthoringView myController;

	private boolean tabRemoved = false;

	public TabBarElement(IAuthoringView controller) {
		myController = controller;
	}

	@Override
	public void initialize() {
		myTabPane = new TabPane();

		myModesTabDisplay = new ModesTabDisplay(MODES_TAB_INDEX, myController);
		myModesTabDisplay.initialize();
		myLevelsTabDisplay = new LevelsTabDisplay(LEVELS_TAB_INDEX, myController);
		myLevelsTabDisplay.initialize();
		myEntitiesTabDisplay = new EntitiesTabDisplay(ENTITIES_TAB_INDEX, myController);
		myEntitiesTabDisplay.initialize();

		Tab modeTab = createTab(myModesTabDisplay.getName(), myModesTabDisplay.getNode());
		Tab levelTab = createTab(myLevelsTabDisplay.getName(), myLevelsTabDisplay.getNode());
		Tab entityTab = createTab(myEntitiesTabDisplay.getName(), myEntitiesTabDisplay.getNode());
		myTabPane.getTabs().addAll(modeTab, levelTab, entityTab);
	}

	@Override
	public Node getNode() {
		return myTabPane;
	}

	/**
	 * Simple tab creation method for styling the overarching tabs for the
	 * Authoring Environment.
	 * 
	 * @param name
	 * @param content
	 * @return
	 */
	private Tab createTab(String name, Node content) {
		Tab t = new Tab(name, content);
		t.setClosable(false);
		t.setStyle("-fx-font-size: 24px;" + "-fx-font-weight: bold;");
		return t;
	}

	/**
	 * Removes current TabDisplay from TabBar if the user wants to work on the
	 * tab in another window.
	 * 
	 * @param tabDisplay
	 */
	private void removeTab(ITabDisplay tabDisplay) {
		if (!tabRemoved) {
			myTabPane.getTabs().remove(tabDisplay.getTabIndex());
			tabRemoved = true;
		}
	}

	/**
	 * If the user closes the other window, the tab is placed back into the main
	 * TabPane.
	 * 
	 * @param tabDisplay
	 */
	private void addTab(ITabDisplay tabDisplay) {
		if (tabRemoved) {
			Tab tab = createTab(tabDisplay.getName(), tabDisplay.getNode());
			myTabPane.getTabs().add(tabDisplay.getTabIndex(), tab);
			tabRemoved = false;
		}
	}

	@Override
	public TabDisplay getModesTabDisplay() {
		return myModesTabDisplay;
	}

	@Override
	public TabDisplay getLevelsTabDisplay() {
		return myLevelsTabDisplay;
	}

	@Override
	public TabDisplay getEntitiesTabDisplay() {
		return myEntitiesTabDisplay;
	}

	@Override
	public void show(ITabDisplay display) {
		Stage s = new Stage();
		s.setTitle(display.getName());
		s.setOnCloseRequest(e -> addTab(display));

		BorderPane bp = new BorderPane();
		bp.setCenter(display.getNode());
		Scene scene = new Scene(bp, 1200, 800);
		s.setScene(scene);
		s.show();

		removeTab(display);
	}

	/**
	 * Initializes hotkeys for accessing the different tabs and initializes the
	 * hotkeys for whatever tab is accessed upon viewing that tab.
	 */
	public void initializeHotKeys() {
		List<TabDisplay> myTabDisplays = Arrays.asList(myModesTabDisplay, myLevelsTabDisplay, myEntitiesTabDisplay);

		myEntitiesTabDisplay.getNode().getScene().getAccelerators()
				.put(new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN), new Runnable() {
					@Override
					public void run() {
						myTabPane.getSelectionModel().select(ENTITIES_TAB_INDEX);
					}
				});

		myLevelsTabDisplay.getNode().getScene().getAccelerators()
				.put(new KeyCodeCombination(KeyCode.L, KeyCombination.SHORTCUT_DOWN), new Runnable() {
					@Override
					public void run() {
						myTabPane.getSelectionModel().select(LEVELS_TAB_INDEX);
					}
				});

		myModesTabDisplay.getNode().getScene().getAccelerators()
				.put(new KeyCodeCombination(KeyCode.M, KeyCombination.SHORTCUT_DOWN), new Runnable() {
					@Override
					public void run() {
						myTabPane.getSelectionModel().select(MODES_TAB_INDEX);
					}
				});

		myTabPane.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.TAB), new Runnable() {
			@Override
			public void run() {
				int currentIndex = myTabPane.getSelectionModel().getSelectedIndex();
				if (currentIndex < 2) {
					myTabPane.getSelectionModel().select(currentIndex + 1);
				} else {
					myTabPane.getSelectionModel().select(0);
				}

			}
		});

		myTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab selectedTab) {
				int currentIndex = myTabPane.getTabs().indexOf(selectedTab);
				TabDisplay currentTabDisplay = myTabDisplays.get(currentIndex);
				currentTabDisplay.initializeHotKeys();
			}
		});
	}

	@Override
	public void updateAll(List<Map<String, String>> modes, List<Map<String, String>> levels,
			List<Map<String, String>> entities) {
		myModesTabDisplay.update(modes);
		myLevelsTabDisplay.update(levels);
		myEntitiesTabDisplay.update(levels);
	}

}
