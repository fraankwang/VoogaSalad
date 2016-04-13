package authoring.frontend;

import authoring.controller.ControllerInterface;
import authoring.frontend.interfaces.ViewManagerInterface;
import authoring.frontend.interfaces.display.TabBarInterface;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewManager implements ViewManagerInterface {

	private MenuBar menuBar;
	private TabBarInterface tabBar;
	private ControllerInterface controller;
	
	public ViewManager(ControllerInterface c) {
		controller = c;
	}
	
	@Override
	public void initialize(Stage s) {
		createMenuBar();
		tabBar = new TabBar();
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		borderPane.setCenter(tabBar.buildNode());
		Scene scene = new Scene(borderPane, 1200, 800, Color.WHITE);
		s.setScene(scene);
		s.show();
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
