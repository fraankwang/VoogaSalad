/**
 * @author austinwu
 */
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

public class StatusPane extends AbstractPane {

	public static final String DEFAULT_RESOURCE = "status";
	private ControlManager myControlManager;
	private HBox myHBox;

	/**
	 * Constructor method for status pane
	 * 
	 * @param ev
	 *            EngineView - used to access various engine view methods and
	 *            base node of entire front end
	 */
	public StatusPane(EngineView ev) {
		super(ev, DEFAULT_RESOURCE);
		myControlManager = new ControlManager(this);
	}

	/**
	 * Instantiates the node for the status pane - and attaches an HBox to this
	 * node
	 * 
	 * @return returns the StatusPane's node
	 */
	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding) {
		super.buildNode(widthBinding, heightBinding);
		myHBox = new HBox();
		myHBox.getChildren().add(buildRecordControls());
		myHBox.getChildren().add(myControlManager.buildGameControls());
		myHBox.getChildren().add(buildStatDisplay());
		getPane().getChildren().add(myHBox);
		return getPane();
	}

	/**
	 * Private method to create a VBox to hold controls for recording gameplay
	 * 
	 * @return returns a vbox to be added to the HBox contained in the
	 *         StatusPane
	 */
	private VBox buildRecordControls() {
		VBox vbox = new VBox();
		Button record = createButton(loadStringResource("RecordLabel"), vbox.heightProperty().divide(3),
				vbox.widthProperty());
		Button stop = createButton(loadStringResource("StopRecordLabel"), vbox.heightProperty().divide(3),
				vbox.widthProperty());
		Button picture = createButton(loadStringResource("PictureLabel"), vbox.heightProperty().divide(3),
				vbox.widthProperty());

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

	/**
	 * Creates a button
	 * 
	 * @param s
	 *            - string to be displayed on button
	 * @param heightBinding
	 *            - binding property dictating how the button's height will
	 *            relate to another node's
	 * @param widthBinding
	 *            - binding property dictating how the button's width will
	 *            relate to another node's
	 * @return - returns a customized button
	 */
	public Button createButton(String s, DoubleExpression heightBinding, DoubleExpression widthBinding) {
		Button button = new Button(s);
		bindHeight(button, heightBinding);
		bindWidth(button, widthBinding);
		button.setOnKeyPressed(null);
		return button;
	}

	/**
	 * Creates a region that implements HUD Utility's GUI for an ongoing game
	 * 
	 * @return HUD Region to populate StatusPane's HBox
	 */
	private Region buildStatDisplay() {
		Region region = myEngineView.getEngineController().setupHUD();
		bindHeight(region, myPane.heightProperty());
		bindWidth(region, myPane.widthProperty().divide(2));
		return region;
	}

	/**
	 * Returns StatusPane's base pane
	 */
	public Pane getPane() {
		return myPane;
	}

	/**
	 * Returns StatusPane's internal control manager - separate class used to
	 * help set up recording controls
	 * 
	 * @return ControlManager - separate class used to help set up recording
	 *         controls.
	 */
	public ControlManager getControlManager() {
		return myControlManager;
	}
}
