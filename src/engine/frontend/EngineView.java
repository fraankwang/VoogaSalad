package engine.frontend;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EngineView{

	public static final String DEFAULT_UI_RESOURCE = "engine/frontend/engine_window";
	private ResourceBundle myUIResources;
	
	private VBox myBody;
	
	private BoardPane myBoardPane;
	private TowerPane myTowerPane;
	private StatusPane myStatusPane;
	
	public EngineView(){
		myBoardPane = new BoardPane(this);
		myTowerPane = new TowerPane(this);
		myStatusPane = new StatusPane(this);
		myUIResources = ResourceBundle.getBundle(DEFAULT_UI_RESOURCE);
	}
	
	/**
	 * builds a "body HBox" for the current view
	 * @return
	 */
	public Scene getScene(){
		int padding = loadUIIntResource("OuterPadding");
		int width = loadUIIntResource("WindowWidth");
		int height = loadUIIntResource("WindowHeight");
		
		HBox top = new HBox(padding);
		HBox bottom = new HBox(padding);
		
		top.setPadding(new Insets(padding,0,0, padding));
		bottom.setPadding(new Insets(0, padding, 0, padding));
		
		fillTopHBox(top);
		fillBottomHBox(bottom);
		
		myBody = new VBox(padding);
		myBody.getChildren().addAll(top, bottom);
		
		Scene scene = new Scene(myBody, width, height, Color.WHITE);
		return scene;
	}
	
	/**
	 * Fills the top Hbox with the BoardPane and the TowerPane
	 * @param vbox
	 */
	private void fillTopHBox(HBox hbox){
		Node board = myBoardPane.buildNode();
		Node tower = myTowerPane.buildNode();
		hbox.getChildren().addAll(board, tower);
	}
	
	/**
	 * Fills the bottom HBox with the statuspane
	 * @param vbox
	 */
	private void fillBottomHBox(HBox hbox){
		Node status = myStatusPane.buildNode();
		hbox.getChildren().addAll(status);
	}
	
	protected int loadUIIntResource(String input){
		return Integer.parseInt(myUIResources.getString(input));
	}
	
	/**
	 * Loads an int resource from the UI resource bundle and string key
	 * @param r resource bundle
	 * @param input String key
	 */
	protected String loadUIStringResource(String input){
		return myUIResources.getString(input);
	}
	
}
