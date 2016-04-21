package engine.frontend.status;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

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
		myPane.minWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("StatusWidth")));
		myPane.minHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("StatusHeight")));
		myPane.maxWidthProperty().bind(myEngineView.getUsableWidth(myEngineView.loadDoubleResource("StatusWidth")));
		myPane.maxHeightProperty().bind(myEngineView.getUsableHeight(myEngineView.loadDoubleResource("StatusHeight")));
		myPane.getChildren().add(buildRecordControl());
		return myPane;
	}
	
	private VBox buildRecordControl(){
		VBox myVBox = new VBox();
		
		Button button = new Button("Record");
		Button button1 = new Button("End");
		
		button.setMaxHeight(Double.MAX_VALUE);
		button.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(button, Priority.ALWAYS);
		
		button1.setMaxHeight(Double.MAX_VALUE);
		button1.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(button1, Priority.ALWAYS);
		
		myVBox.minWidthProperty().bind(myPane.widthProperty().divide(4));
		myVBox.minHeightProperty().bind(myPane.heightProperty());
		myVBox.maxHeightProperty().bind(myPane.heightProperty());
		
		myVBox.getChildren().addAll(button, button1);
		return myVBox;
	}
	
	private Node buildStatDisplay(String name){
		return null;
	}
}
