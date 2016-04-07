package engine.frontend;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EngineView{

	public static final String DEFAULT_UI_RESOURCE = "view/ui";
	private ResourceBundle myUIResources;
	
	private VBox myBody;
	
	private BoardPane myBoardView;
	private TowerPane myTowerView;
	private StatusPane myStatusView;
	
	public EngineView(){
		myBoardView = new BoardPane(this);
		myTowerView = new TowerPane(this);
		myStatusView = new StatusPane(this);
	}
	
	/**
	 * builds a "body HBox" for the current view
	 * @return
	 */
	protected Scene getScene(){
		VBox top = new VBox(padding);
		VBox bottom = new VBox(padding);
		
		top.setPadding(new Insets(padding,0,padding, padding));
		bottom.setPadding(new Insets(padding, padding,padding, 0));
		
		fillTopVBox(top);
		fillBottomVBox(bottom);
		
		myBody = new HBox(padding);
		myBody.getChildren().addAll(left, center, right);
	}
	
	/**
	 * Fills the given vbox with the top row elements
	 * Top row has the board and the towers
	 * @param vbox vbox to be filled
	 */
	private void fillTopVBox(VBox vbox){
		Node commands = myUserCommandView.buildNode();
		Node variables = myUserVariableView.buildNode();
		vbox.getChildren().addAll(commands, variables);
	}
	
	/**
	 * Fills the given vbox with the bottom row elements
	 * Bottom Row holds the status and options 
	 * @param vbox vbox to be filled
	 */
	private void fillBottomVBox(VBox vbox){
		Node status = 
		vbox.getChildren().addAll(status, history);
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
