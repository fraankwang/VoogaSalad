package authoring.frontend.display_elements;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.configuration.Constants;
import authoring.frontend.editor_features.ImageImporter;
import authoring.frontend.editor_features.ObjectChooser;
import authoring.frontend.interfaces.display_element_interfaces.IMenuBarElement;
import authoring.frontend.interfaces.display_element_interfaces.ITabBarElement;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import main.Main;

/**
 * The MenuBarElement acts as a primary UI component that creates and manages
 * the MenuBar, which can be accessed at all times throughout the game authoring
 * environment.
 * 
 * @author Frank, benchesnut
 *
 */

public class MenuBarElement implements IMenuBarElement {

	private MenuBar myMenuBar;
	private ITabBarElement myTabBar;
	private ObjectChooser myImageChooser;
	private ImageImporter myImageImporter;
	private IAuthoringView myController;
	private Main myMain;
	private Stage myStage;

	public MenuBarElement(ObjectChooser ic, IAuthoringView controller, Stage s, Main main) {
		myImageChooser = ic;
		myController = controller;
		myMain = main;
		myStage = s;
	}

	@Override
	public Node getNode() {
		return myMenuBar;
	}

	@Override
	public void initialize() {
		myMenuBar = new MenuBar();
		Menu file = createFileMenu();
		Menu create = createCreateMenu();
		Menu help = createHelpMenu();
		myImageImporter = new ImageImporter(myImageChooser);
		myImageImporter.initialize();

		myMenuBar.getMenus().addAll(file, create, help);
	}

	private Menu createCreateMenu() {
		MenuItem createEntity = new MenuItem(Constants.getString("CREATE_ENTITY"));
		createEntity.setOnAction(e -> {
			myController.getAuthoringViewManager().getTabBarElement().getEntitiesTabDisplay().createNew();
		});
		MenuItem createLevel = new MenuItem(Constants.getString("CREATE_LEVEL"));
		createLevel.setOnAction(e -> {
			myController.getAuthoringViewManager().getTabBarElement().getLevelsTabDisplay().createNew();
		});
		MenuItem createMode = new MenuItem(Constants.getString("CREATE_MODE"));
		createMode.setOnAction(e -> {
			myController.getAuthoringViewManager().getTabBarElement().getModesTabDisplay().createNew();
		});

		Menu createMenu = new Menu(Constants.getString("CREATE_MENU"));
		createMenu.getItems().addAll(createEntity, createLevel, createMode);
		return createMenu;

	}

	private Menu createFileMenu() {
		Menu file = new Menu(Constants.getString("FILE_MENU"));

		MenuItem importImages = new MenuItem("Import Images...");
		importImages.setOnAction(e -> myImageImporter.openImporter());

		MenuItem importGame = createImportGame();
		MenuItem exportGame = createExportGame();

		Menu open = new Menu(Constants.getString("SEPARATE_MENU"));

		MenuItem openModes = new MenuItem(Constants.getString("OPEN_MODE"));
		openModes.setOnAction(e -> myTabBar.show(myTabBar.getModesTabDisplay()));
		MenuItem openLevels = new MenuItem(Constants.getString("OPEN_LEVEL"));
		openLevels.setOnAction(e -> myTabBar.show(myTabBar.getLevelsTabDisplay()));
		MenuItem openEntities = new MenuItem(Constants.getString("OPEN_ENTITY"));
		openEntities.setOnAction(e -> myTabBar.show(myTabBar.getEntitiesTabDisplay()));

		open.getItems().addAll(openModes, openLevels, openEntities);
		file.getItems().addAll(open, importImages, importGame, exportGame);
		return file;
	}

	private MenuItem createExportGame() {
		MenuItem exportGame = new MenuItem(Constants.getString("EXPORT_MENU"));
		exportGame.setOnAction(e -> {
			FileChooser gameSaver = new FileChooser();
			gameSaver.setTitle("Save Game File");
			gameSaver.getExtensionFilters().add(new ExtensionFilter("Game Files", "*.xml"));
			File gameFile = gameSaver.showSaveDialog(null);

			Map<String, String> exportGameMap = new HashMap<String, String>();
			exportGameMap.put("Type", "Export");
			exportGameMap.put("URL", gameFile.getName());

			myController.writeData(exportGameMap);
			myMain.createPlayer(myStage);
		});

		return exportGame;
	}

	private MenuItem createImportGame() {
		MenuItem importGame = new MenuItem(Constants.getString("IMPORT_MENU"));

		importGame.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Game File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Game Files", "*.xml"));
			File gameFile = fileChooser.showOpenDialog(null);

			Map<String, String> importGameMap = new HashMap<String, String>();
			importGameMap.put("Type", "Import");
			importGameMap.put("URL", gameFile.getName());

			myController.writeData(importGameMap);

			try {
				// String string = ObjectToXMLWriter.documentToString(gameFile);
				// GameWorld game = (GameWorld) writer.xMLToObject(string);

				// List<Map<String, String>> myModes =
				// List<Map<String, String>> myLevels
				// List<Map<String, String>> myEntities
				// myController.updateAll(myModes, myLevels, myEntities);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});

		return importGame;
	}

	/**
	 * Creates help menu.
	 * 
	 * @return
	 */
	private Menu createHelpMenu() {
		Menu help = new Menu(Constants.getString("HELP_MENU"));

		MenuItem openWiki = new MenuItem(Constants.getString("ABOUT_MENU"));
		openWiki.setOnAction(
				e -> openWebPage("https://github.com/duke-compsci308-spring2016/voogasalad_DrumpfTower/wiki"));
		MenuItem openTDWiki = new MenuItem(Constants.getString("HOWTO_MENU"));
		openTDWiki.setOnAction(e -> openWebPage(
				"https://docs.google.com/document/d/1XtS7cTKnU7g7cwGhlDpgI7qe-cJQXso01z_qSoRJRwk/edit?usp=sharing"));

		help.getItems().addAll(openWiki, openTDWiki);
		return help;
	}

	/**
	 * Opens page
	 * 
	 * @param url
	 */
	private void openWebPage(String url) {
		Stage helpStage = new Stage();

		WebView webView = new WebView();
		webView.getEngine().load(url);
		webView.setPrefSize(Constants.getInt("SCENE_WIDTH"), Constants.getInt("SCENE_HEIGHT"));

		Group root = new Group();
		root.getChildren().add(webView);

		Scene scene = new Scene(root, Constants.getInt("SCENE_WIDTH"), Constants.getInt("SCENE_HEIGHT"));
		helpStage.setScene(scene);
		helpStage.show();

	}

	@Override
	public void link(ITabBarElement tabBar) {
		myTabBar = tabBar;

	}

	@Override
	public ImageImporter getImageImporter() {
		return myImageImporter;
	}

}
