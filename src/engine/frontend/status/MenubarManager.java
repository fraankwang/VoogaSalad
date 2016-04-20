package engine.frontend.status;

import java.io.File;
import java.util.ResourceBundle;

import engine.frontend.overall.EngineView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class MenubarManager {
	private EngineView myEngineView;
	
	private static final ObservableList<Integer> workspaceList = FXCollections.observableArrayList();
	
	public static final String DEFAULT_RESOURCE = "engine/resources/menubar";
	private ResourceBundle myResources;
	
	public MenubarManager(EngineView ev){
		myEngineView = ev;
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
	}
		
	public MenuBar buildMenuBar(){
		MenuBar menubar = new MenuBar();
		Menu filemenu = buildFileMenu();
		Menu capturemenu = buildCaptureMenu();
		final Menu menu3 = new Menu("Help");
		menubar.getMenus().addAll(filemenu, filemenu, menu3);

		return menubar; 
	}
	
	private Menu buildFileMenu(){
		Menu menu = new Menu(myResources.getString("FilePrompt"));
		MenuItem save = new MenuItem(myResources.getString("SavePrompt"));
		save.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showSaveDialog(myEngineView.getStage());
			System.out.println(file);
		});
		
		MenuItem load = new MenuItem(myResources.getString("LoadPrompt"));
		load.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(myEngineView.getStage());
			System.out.println(file);
		});
		
		menu.getItems().addAll(load, save);
		return menu;
	}
	
	private Menu buildCaptureMenu(){
		Menu menu = new Menu(myResources.getString("MenuPrompt"));
		return menu;
	}
	
}
