package authoring.frontend.display_elements;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.editor_features.ImageImporter;
import authoring.frontend.editor_features.ObjectChooser;
import authoring.frontend.interfaces.display_element_interfaces.IMenuBarElement;
import authoring.frontend.interfaces.display_element_interfaces.ITabBarElement;
import backend.xml_converting.GameWorldToXMLWriter;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
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
		Menu create = new Menu("Create");
		Menu help = createHelpMenu();
		myImageImporter = new ImageImporter(myImageChooser);
		myImageImporter.initialize();

		myMenuBar.getMenus().addAll(file, create, help);
	}

	private Menu createFileMenu() {
		Menu file = new Menu("File");
		
		MenuItem importImages = new MenuItem("Import Images...");
		importImages.setOnAction(e -> myImageImporter.openImporter());
		
		MenuItem importGame = createImportGame();
		MenuItem exportGame = new MenuItem("Export Game");
		Map<String, String> createGameMap = new HashMap<String, String>();
		createGameMap.put("Type", "Create");
		exportGame.setOnAction(e -> {
			myController.writeData(createGameMap);
			myMain.createPlayer(myStage);
		});
		
		Menu open = new Menu("Open in separate window");

		MenuItem openModes = new MenuItem("Open Modes Tab");
		openModes.setOnAction(e -> myTabBar.show(myTabBar.getModesTabDisplay()));
		MenuItem openLevels = new MenuItem("Open Levels Tab");
		openLevels.setOnAction(e -> myTabBar.show(myTabBar.getLevelsTabDisplay()));
		MenuItem openEntities = new MenuItem("Open Entities Tab");
		openEntities.setOnAction(e -> myTabBar.show(myTabBar.getEntitiesTabDisplay()));

		open.getItems().addAll(openModes, openLevels, openEntities);
		file.getItems().addAll(open, importImages, importGame, exportGame);
		return file;
	}

	private MenuItem createImportGame() {
		MenuItem importGame = new MenuItem("Import Game");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Game File");
//		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("xml"));
//		File gameFile = fileChooser.showOpenDialog(null);
		
		GameWorldToXMLWriter writer = new GameWorldToXMLWriter();
		
		return importGame;
	}

	private Menu createHelpMenu() {
		Menu help = new Menu("Help");

		MenuItem openWiki = new MenuItem("About");
		openWiki.setOnAction(
				e -> openWebPage("https://github.com/duke-compsci308-spring2016/voogasalad_DrumpfTower/wiki"));
		MenuItem openTDWiki = new MenuItem("How to play Tower Defense");
		openTDWiki.setOnAction(e -> openWebPage("https://en.wikipedia.org/wiki/Tower_defense"));

		help.getItems().addAll(openWiki, openTDWiki);
		return help;
	}

	private void openWebPage(String url) {
		Stage helpStage = new Stage();

		WebView webView = new WebView();
		webView.getEngine().load(url);
		webView.setPrefSize(800, 800);

		Group root = new Group();
		root.getChildren().add(webView);

		Scene scene = new Scene(root, 800, 800);
		helpStage.setScene(scene);
		helpStage.show();

	}

	@Override
	public void link(ITabBarElement tabBar) {
		myTabBar = tabBar;

	}

}
