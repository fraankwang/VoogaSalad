package engine.frontend.status;

import engine.frontend.overall.EngineView;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
		myPane.minWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("StatusWidth")));
		myPane.minHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("StatusHeight")));
		myPane.maxWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("StatusWidth")));
		myPane.maxHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("StatusHeight")));
		myPane.getChildren().add(buildRecordControl());
		return myPane;
	}
	
	private VBox buildRecordControl(){
		VBox myVBox = new VBox();
		
		Button record = new Button("Record");
		Button stop = new Button("Stop");
		Button picture = new Button("Picture");
		
		record.setMaxHeight(Double.MAX_VALUE);
		record.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(record, Priority.ALWAYS);
		record.setDisable(false);
		record.setOnAction(e -> {
			myEngineView.getGameCapture().startCapture();
			record.setDisable(true);
			stop.setDisable(false);
		});
		
		stop.setMaxHeight(Double.MAX_VALUE);
		stop.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(stop, Priority.ALWAYS);
		stop.setDisable(true);
		stop.setOnAction(e -> {
			myEngineView.getGameCapture().endCapture();
			record.setDisable(false);
			stop.setDisable(true);
		});
		
		picture.setMaxHeight(Double.MAX_VALUE);
		picture.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(picture, Priority.ALWAYS);
		picture.setOnAction(e -> myEngineView.getGameCapture().takeScreenshot());
		
		myVBox.minWidthProperty().bind(myPane.widthProperty().divide(4));
		myVBox.minHeightProperty().bind(myPane.heightProperty());
		myVBox.maxHeightProperty().bind(myPane.heightProperty());
		
		myVBox.getChildren().addAll(record, stop, picture);
		return myVBox;
	}
	
	private Node buildStatDisplay(String name){
		return null;
	}
}
