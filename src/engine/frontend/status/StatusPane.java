package engine.frontend.status;

import engine.frontend.overall.EngineView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class StatusPane {
	private EngineView myEngineView;
	private Pane myPane;
	
	public StatusPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(){
		myPane = new Pane();
		myPane.setStyle("-fx-background-color: #ffffff;");
		myPane.setMinSize(myEngineView.loadUIIntResource("StatusWidth"), myEngineView.loadUIIntResource("StatusHeight")/2);
		myPane.setPrefSize(myEngineView.loadUIIntResource("StatusWidth"), myEngineView.loadUIIntResource("StatusHeight"));
		myPane.setMaxSize(myEngineView.loadUIIntResource("StatusWidth"), myEngineView.loadUIIntResource("StatusHeight"));
		
		HBox myStatComponents = new HBox();
		myStatComponents.minWidthProperty().bind(myPane.widthProperty());
		myStatComponents.minHeightProperty().bind(myPane.heightProperty());
		myPane.getChildren().add(myStatComponents);
		
		myStatComponents.getChildren().addAll(buildControl("test1"), buildControl("test1"), buildControl("test1"), buildControl("test1"));
		
		return myPane;
	}
	
	private Node buildControl(String name){

		VBox myVBox = new VBox();
		
		Button button = new Button(name);
		Button button1 = new Button(name);
		
		button.setMaxHeight(Double.MAX_VALUE);
		button.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(button, Priority.ALWAYS);
		VBox.setVgrow(button, Priority.ALWAYS);
		
		button1.setMaxHeight(Double.MAX_VALUE);
		button1.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(button1, Priority.ALWAYS);
		VBox.setVgrow(button1, Priority.ALWAYS);
		HBox.setHgrow(myVBox, Priority.ALWAYS);
		
		myVBox.getChildren().addAll(button, button1);
		return myVBox;
	}
	
	private Node buildStatDisplay(String name){
		return null;
	}
}
