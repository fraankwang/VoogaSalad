package engine.frontend.status;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import engine.frontend.overall.EngineView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class MenubarManager {
	private EngineView myEngineView;
	
	private static final ObservableList<Integer> workspaceList = FXCollections.observableArrayList();
	
	public static final String DEFAULT_RESOURCE = "engine/frontend/status/menubar";
	private ResourceBundle myResources;
	
	private MenuBar menubar;
	
	public MenubarManager(EngineView ev){
		myEngineView = ev;
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
	}

	public MenuBar buildMenuBar(){
		menubar = new MenuBar();
		
		Menu filemenu = buildFileMenu();
		Menu capturemenu = buildCaptureMenu();
		final Menu menu3 = new Menu("Help");
		menubar.getMenus().addAll(filemenu, capturemenu, menu3);

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
		MenuItem setImageFileType = new MenuItem(myResources.getString("ImageFileTypePrompt"));
		setImageFileType.setOnAction(e -> {
			List<String> choices = Arrays.asList(ImageIO.getWriterFormatNames());
			ChoiceDialog<String> dialog = new ChoiceDialog<>(myEngineView.getGameCapture().getImageFileType(), choices);
			dialog.setTitle(myResources.getString("ImageDialogTitle"));
			dialog.setHeaderText(myResources.getString("ImageDialogHeader"));
			dialog.setContentText(myResources.getString("ImageDialogContent"));

			Optional<String> result = dialog.showAndWait();
			result.ifPresent(choice -> myEngineView.getGameCapture().setImageFileType(choice));
		});
		
		MenuItem setNumFramesPerSecond = new MenuItem(myResources.getString("NumFramesPerSecondPrompt"));
		setNumFramesPerSecond.setOnAction(e -> {
			List<Integer> choices = new ArrayList<Integer>();
			for(int i = 1; i <= 60; i++){
				choices.add(i);
			}
			ChoiceDialog<Integer> dialog = new ChoiceDialog<>(myEngineView.getGameCapture().getFramesPerSecond(), choices);
			dialog.setTitle(myResources.getString("FPSDialogTitle"));
			dialog.setHeaderText(myResources.getString("FPSDialogHeader"));
			dialog.setContentText(myResources.getString("FPSDialogContent"));
			Optional<Integer> result = dialog.showAndWait();
			result.ifPresent(choice -> myEngineView.getGameCapture().setFramesPerSecond(choice));
		});
		
		MenuItem setSaveLocation = new MenuItem(myResources.getString("FileSaveLocationPrompt"));
		setSaveLocation.setOnAction(e -> {
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle(myResources.getString("SaveLocationTitle"));
			chooser.setInitialDirectory(myEngineView.getGameCapture().getSaveLocation());
			File selectedDirectory = chooser.showDialog(myEngineView.getStage());
			myEngineView.getGameCapture().setSaveLocation(selectedDirectory);
		});
		
		MenuItem setFileName = new MenuItem(myResources.getString("FileNamePrompt"));
		setFileName.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog(myEngineView.getGameCapture().getFileName());
			dialog.setTitle(myResources.getString("FileDialogTitle"));
			dialog.setHeaderText(myResources.getString("FileDialogHeader"));
			dialog.setContentText(myResources.getString("FileDialogContent"));
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(name -> myEngineView.getGameCapture().setFileName(name));
		});
		
		menu.getItems().addAll(setImageFileType, setNumFramesPerSecond, setSaveLocation, setFileName);
		return menu;
	}
	
}
