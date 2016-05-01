package authoring.frontend.display_elements.panels;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.configuration.Constants;
import authoring.frontend.display_elements.panels.panel_bars.GridPanelBar;
import authoring.frontend.display_elements.panels.panel_bars.PanelBar;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * The GridViewPanel is the primary display within many of the TabDisplays. The
 * GridView contains a scrollable GridPane that shows all the existing game
 * sprites (or levels/modes) created. The PanelBar allows
 * 
 * @author Ben
 *
 */

public class GridViewPanel extends Panel {

	private final static int DEFAULT_NUM_GRID_COLUMNS = Constants.getInt("GRID_VIEW_PANEL_DEFAULT_NUM_COLUMNS");
	private final static int ADD_NEW_BUTTON_SIZE = Constants.getInt("GRID_VIEW_ADD_NEW_SIZE");
	protected GridPane myGridPane;
	protected ScrollPane myScrollPane;
	protected PanelBar myPanelBar;
	protected Button myAddNewButton;
	private int numColumns;
	protected List<ImageView> myImages;
	protected ImageView myCurrImage;
	protected ITabDisplay myTabDisplay;
	protected IAuthoringView myController;

	public GridViewPanel(double height, double width, ITabDisplay tab, IAuthoringView controller) {
		super(height, width);
		myTabDisplay = tab;
		numColumns = DEFAULT_NUM_GRID_COLUMNS;
		myController = controller;
	}

	@Override
	protected void initializeComponents() {
		myGridPane = new GridPane();
		myScrollPane = new ScrollPane();
		myPanelBar = new GridPanelBar(MAX_SIZE, MAX_SIZE, this);
		myPanelBar.initialize();
		
		myImages = new ArrayList<ImageView>();
		myAddNewButton = new Button("Add New");
		myAddNewButton.setStyle(
				"-fx-wrap-text: true; -fx-background-insets: 0,1,2,3; -fx-background-radius: 3,2,2,2;-fx-padding: 12 30 12 30;-fx-text-fill: white;-fx-font-size: 30px;-fx-background-color:#515D7B,linear-gradient(#7ebcea, #2f4b8f),linear-gradient(#426ab7, #263e75),linear-gradient(#395cab, #223768);");

		myAddNewButton.setPrefSize(ADD_NEW_BUTTON_SIZE, ADD_NEW_BUTTON_SIZE);
		
	}

	public void resetGrid() {
		myGridPane.getChildren().clear();
		myGridPane.getColumnConstraints().clear();
		double gridCellSize = (myScrollPane.getViewportBounds().getWidth() - Constants.getInt("SCROLLPANE_OFFSET")) / numColumns;
		for (int i = 0; i < numColumns; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setMinWidth(gridCellSize);
			column.setMaxWidth(gridCellSize);
			column.setHalignment(HPos.CENTER);
			myGridPane.getColumnConstraints().add(column);
		}

		ImageView currImage;
		int colNum, rowNum;
		for (int j = 0; j < myImages.size(); j++) {
			colNum = j % numColumns;
			rowNum = j / numColumns;
			currImage = myImages.get(j);
			currImage.setPreserveRatio(true);
			currImage.fitHeightProperty().set(gridCellSize);
			currImage.fitWidthProperty().set(gridCellSize);
			myGridPane.add(currImage, colNum, rowNum);
		}
		myGridPane.add(myAddNewButton, myImages.size() % numColumns, myImages.size() / numColumns);
	}

	public void decreaseGridSize() {
		if (numColumns != 1) {
			numColumns--;
		}
		resetGrid();
	}

	public void increaseGridSize() {
		numColumns++;
		resetGrid();
	}

	@Override
	protected void assembleComponents() {
		VBox vbox = new VBox();
		myAddNewButton.setOnAction(e -> {
			Map<String, String> defaultAttributesMap = ((TabDisplay) myTabDisplay).getDefaultAttributesMap();
			myTabDisplay.openEditorDisplay(defaultAttributesMap);
		});

		myScrollPane.setContent(myGridPane);
		VBox.setVgrow(myGridPane, Priority.ALWAYS);
		vbox.getChildren().addAll(myPanelBar.getNode(), myScrollPane);
		myNode = vbox;
		resetGrid();
	}

	public void addImage(ImageView iv) {
		myGridPane.getChildren().remove(myAddNewButton);
		iv.setOnMouseClicked(e -> iv.requestFocus());

		iv.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
					Boolean newValue) {
				if (newValue) {
					iv.setOpacity(1);
					myCurrImage = iv;
					return;
				}
				iv.setOpacity(0.2);
			}
		});
		myImages.add(iv);
		resetGrid();
	}

	public ImageView getCurrentImage() {
		return myCurrImage;
	}

	public void clearImages() {
		myImages.clear();
	}

	public Button getMyAddNewButton() {
		return myAddNewButton;
	}
	
	public void setPanelBarDescription(String description) { 
		((GridPanelBar) myPanelBar).setDescription(description);
	}
	
	public Node getMyGridPane() {
		return myGridPane;
	}
}
