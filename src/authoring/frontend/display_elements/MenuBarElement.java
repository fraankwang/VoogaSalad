package authoring.frontend.display_elements;

import authoring.frontend.interfaces.display_element_interfaces.IMenuBarElement;
import authoring.frontend.interfaces.display_element_interfaces.ITabBarElement;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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

	public MenuBarElement() {

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

		myMenuBar.getMenus().addAll(file, create, help);
	}

	private Menu createFileMenu() {
		Menu file = new Menu("File");

		Menu open = new Menu("Open in separate window");
		MenuItem openGame = new MenuItem("Open Game Tab");
		openGame.setOnAction(e -> myTabBar.show(myTabBar.getGameTabDisplay()));
		MenuItem openModes = new MenuItem("Open Modes Tab");
		openModes.setOnAction(e -> myTabBar.show(myTabBar.getModesTabDisplay()));
		MenuItem openLevels = new MenuItem("Open Levels Tab");
		openLevels.setOnAction(e -> myTabBar.show(myTabBar.getLevelsTabDisplay()));
		MenuItem openEntities = new MenuItem("Open Entities Tab");
		openEntities.setOnAction(e -> myTabBar.show(myTabBar.getEntitiesTabDisplay()));

		file.getItems().add(open);
		open.getItems().addAll(openGame, openModes, openLevels, openEntities);
		return file;
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
