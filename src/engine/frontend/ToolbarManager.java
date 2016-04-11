package engine.frontend;

import java.io.File;
import java.util.Arrays;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ToolbarManager {
	private EngineView myEngineView;
	private ComboBox<String> languageCombobox;
	private ComboBox<Integer> workspacePicker;
	
	private static final ObservableList<Integer> workspaceList = FXCollections.observableArrayList();
	
	public ToolbarManager(EngineView ev){
		myEngineView = ev;
	}
		
	public ToolBar buildToolBar(){
		ToolBar toolbar = new ToolBar();
		addAuthorButton(toolbar);
		addSeperator(toolbar);
		addSaveButton(toolbar);
		addLoadButton(toolbar);
		return toolbar; 
	}
	
//	private void addLanguageComboBox(ToolBar toolbar){
//		languageCombobox = new ComboBox<String>();
//		languageCombobox.getItems().addAll(Arrays.asList(myView.getWM().getRM().loadUIStringResource("Languages").split(",")));
//		languageCombobox.valueProperty().addListener(new ChangeListener<String>() {
//			@SuppressWarnings("rawtypes")
//			@Override public void changed(ObservableValue ov, String t, String t1) {
//	        	myView.getModelManager().setLanguage(t1);
//	        	myView.getStatusView().updateLanguage(t1);
//	        }    
//	    });
//		languageCombobox.setValue(myView.getWM().getRM().loadWorkspaceStringResource("Language"));
//		toolbar.getItems().add(languageCombobox);
//	}
//	
//	protected void updateLanguageCombobox(String language){
//		languageCombobox.setValue(myView.getWM().getRM().loadWorkspaceStringResource("Language"));
//	}
//	
//	private void addHelpButton(ToolBar toolbar){
//		Button button = new Button("Help");
//		button.setOnAction(e -> openHelpBrowser());
//		toolbar.getItems().add(button);
//	}
//	
//	private void openHelpBrowser(){
//		WebView browser = new WebView();
//		WebEngine webEngine = browser.getEngine();
//		webEngine.load(myView.getWM().getRM().loadWorkspaceStringResource("HelpURL"));
//		
//		Stage stage = new Stage();
//		Scene scene = new Scene(browser,750,500, Color.web("#666970"));
//		stage.setTitle("Help View");
//        stage.setScene(scene);
//        stage.show();
//	}
//	
//	private void addImageChooser(ToolBar toolbar) {
//		FileChooser imageChooser = new FileChooser();
//		imageChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
//		Button button = new Button("Turtle Image");
//		toolbar.getItems().add(button);
//		button.setOnAction(e -> {
//			File imageFile = imageChooser.showOpenDialog(myView.getWM().getStage());
//			if(imageFile != null){
//				myView.getTurtleManager().getTurtleList().forEach(k -> k.setImage(imageFile));	
//			}
//		});
//	}
//	
//	private void addPaletteButton(ToolBar toolbar){
//		Button button = new Button("Palette");
//		toolbar.getItems().add(button);
//		button.setOnAction(e -> myView.getPalette().display());
//	}
//	
	private void addSeperator(ToolBar toolbar){
		toolbar.getItems().add(new Separator());
	}
//	
//	private void addWorkspaceOptions(ToolBar toolbar){
//		Label label = new Label("WS:");
//		Button newWorkspace = new Button("New");
//		newWorkspace.setOnAction(e -> {myWorkspaceManager.newWorkspace(); updateWorkspaceList();});
//		Button load = new Button("Load");
//		load.setOnAction(e -> loadWorkspace());
//		Button save = new Button("Save");
//		save.setOnAction(e -> saveWorkspace());
//		Button set = new Button("Set");
//		set.setOnAction(e -> setWorkspace());
//		workspacePicker = new ComboBox<Integer>(workspaceList);
//		workspacePicker.valueProperty().addListener(new ChangeListener<Integer>() {
//			@Override
//			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
//				if(newValue == null){
//					newValue = myWorkspaceManager.getNumWorkspaces() - 1;
//					workspacePicker.setValue(newValue);
//				}
//				myWorkspaceManager.switchWorkspace(newValue);
//			}
//	    });
//		updateWorkspaceList();
//		toolbar.getItems().addAll(label, newWorkspace, load, save, set, workspacePicker);
//	}
//
//	private void updateWorkspaceList(){
//		workspaceList.clear();
//		for(int i = 0; i < myWorkspaceManager.getNumWorkspaces(); i++){
//			workspaceList.add(i);
//		}
//		workspacePicker.setPromptText(Integer.toString(workspaceList.size()-1));
//	}
//	
//	/**
//	 * Loads a specific workspace
//	 */
//	private void loadWorkspace(){
//		FileChooser fileChooser = new FileChooser();
//		fileChooser.getExtensionFilters().add(new ExtensionFilter("Workspace Files", "*.properties"));
//		File resourceFile = fileChooser.showOpenDialog(myView.getWM().getStage());
//		if(resourceFile != null){
//			myView.getWM().getRM().setWorkspaceResource(resourceFile.getName());	
//			myWorkspaceManager.newWorkspace(); 
//			updateWorkspaceList();
//			languageCombobox.setValue(myView.getWM().getRM().loadWorkspaceStringResource("Language"));
//		}
//	}
//	
//	private void saveWorkspace(){
//		myView.getWM().saveWorkspace();
//	}
//	
//	/**
//	 * Sets the current workspace to a specific workspace
//	 */
//	private void setWorkspace(){
//		FileChooser fileChooser = new FileChooser();
//		fileChooser.getExtensionFilters().add(new ExtensionFilter("Workspace Files", "*.properties"));
//		File resourceFile = fileChooser.showOpenDialog(myView.getWM().getStage());
//		if(resourceFile != null){
//			myView.getWM().getRM().setWorkspaceResource(resourceFile.getName());	
//			myWorkspaceManager.setFromNewWorkspace();
//		}
//	}
//	
//	protected String getLanguage(){
//		return languageCombobox.getValue();
//	}
	
	private void addAuthorButton(ToolBar toolbar){
		Button save = new Button("Author");
		save.setOnAction(e -> {
			Stage s = new Stage();
			myEngineView.getMain().createAuthor(s);
		});
		toolbar.getItems().add(save);
	}
	
	private void addSaveButton(ToolBar toolbar){
		Button save = new Button("Save");
		save.setOnAction(e -> {try {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showSaveDialog(myEngineView.getStage());
			System.out.println(file);
//			myView.getModelManager().save(file);
		} catch (Exception e1) {
			e1.printStackTrace();
		}});
		toolbar.getItems().add(save);
	}
	
	private void addLoadButton(ToolBar toolbar){
		Button load = new Button("Load");
		load.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(myEngineView.getStage());
			System.out.println(file);
//			myView.getModelManager().interpret(file);
		});
		toolbar.getItems().add(load);
	}
}
