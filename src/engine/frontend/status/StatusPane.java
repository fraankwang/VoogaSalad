package engine.frontend.status;

import engine.frontend.overall.AbstractPane;
import engine.frontend.overall.EngineView;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class StatusPane extends AbstractPane{
	public static final String DEFAULT_RESOURCE = "status";
	
	private ControlManager myControlManager;
	private HBox myHBox;

	public StatusPane(EngineView ev){
		super(ev, DEFAULT_RESOURCE);
		myControlManager = new ControlManager(this);
	}
	
	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding){
		super.buildNode(widthBinding, heightBinding);
		myHBox = new HBox();
		myHBox.getChildren().add(buildRecordControls());
		myHBox.getChildren().add(myControlManager.buildGameControls());
		myHBox.getChildren().add(buildStatDisplay());
		getPane().getChildren().add(myHBox);
		return getPane();
	}
	
	private VBox buildRecordControls(){
		VBox vbox = new VBox();
		Button record = createButton(loadStringResource("RecordLabel"), vbox.heightProperty().divide(3), vbox.widthProperty());
		Button stop = createButton(loadStringResource("StopRecordLabel"), vbox.heightProperty().divide(3), vbox.widthProperty());
		Button picture = createButton(loadStringResource("PictureLabel"), vbox.heightProperty().divide(3), vbox.widthProperty());
		
		record.setOnAction(e -> {
			myEngineView.getGameCapture().startCapture();
			record.setDisable(true);
			stop.setDisable(false);
			myEngineView.getStage().setResizable(false);
		});
		
		stop.setDisable(true);
		stop.setOnAction(e -> {
			myEngineView.getGameCapture().endCapture();
			record.setDisable(false);
			stop.setDisable(true);
			myEngineView.getStage().setResizable(true);
		});
		
		picture.setOnAction(e -> myEngineView.getGameCapture().takeScreenshot());
		
		bindWidth(vbox, myPane.widthProperty().divide(4));
		bindHeight(vbox, myPane.heightProperty());
		
		vbox.getChildren().addAll(record, stop, picture);
		return vbox;
	}
	
	public Button createButton(String s, DoubleExpression heightBinding, DoubleExpression widthBinding){
		Button button = new Button(s);
		bindHeight(button, heightBinding);
		bindWidth(button, widthBinding);
		return button;
	}
	
	
	private Region buildStatDisplay(){
		Region region = myEngineView.getEngineController().setupHUD();
		bindHeight(region, myPane.heightProperty());
		bindWidth(region, myPane.widthProperty().divide(2));
		return region;
	}
	
	public Pane getPane(){
		return myPane;
	}
	
	public ControlManager getControlManager(){
		return myControlManager;
	}
}
