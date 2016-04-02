package authoring_environment.frontend;

import authoring_environment.frontend.design_interfaces.*;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class ViewManager implements ViewManagerInterface {

	private MenuBar menuBar;
	private TabBarInterface tabBar;
	
	public ViewManager() {
		createMenuBar();
		tabBar = new TabBar();
	}
	
	@Override
	public Scene initializeScene() {
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setCenter(tabBar.buildNode());
		Scene scene = new Scene(borderPane, 1200, 800, Color.WHITE);
		return scene;
	}

	public void createMenuBar() {
		menuBar = new MenuBar();
		Menu file = new Menu("File");
		Menu create = new Menu("Create");
		Menu help = new Menu("Help");
		menuBar.getMenus().addAll(file, create, help);
	}
	
	@Override
	public MenuBar getMenuBar() {
		return menuBar;
	}

	@Override
	public TabBarInterface getTabBar() {
		return tabBar;
	}

}
