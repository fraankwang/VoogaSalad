package authoring.frontend.display_elements.panels;

import java.util.ArrayList;
import java.util.List;

import authoring.frontend.display_elements.panels.panel_bars.GridPanelBar;
import authoring.frontend.display_elements.panels.panel_bars.PanelBar;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * The GridViewPanel is the primary display within many of the TabDisplays. The
 * GridView contains a scrollable GridPane that shows all the existing game
 * sprites (or levels/modes) created. The PanelBar allows
 * 
 * @author Frank
 *
 */

public class GridViewPanel extends Panel {

	private final static int DEFAULT_NUM_GRID_COLUMNS = 2;

	private GridPane myGridPane;
	private ScrollPane myScrollPane;
	private PanelBar myPanelBar;
	private Button myAddNewButton;
	private int numColumns;
	private List<ImageView> myImages;
	private ITabDisplay myTabDisplay;

	public GridViewPanel(double height, double width, ITabDisplay tab) {
		super(height, width);
		myTabDisplay = tab;
		numColumns = DEFAULT_NUM_GRID_COLUMNS;
	}

	@Override
	protected void initializeComponents() {
		myGridPane = new GridPane();
		myScrollPane = new ScrollPane();
		myPanelBar = new GridPanelBar(50, 50, this);
		myPanelBar.initialize();
		myImages = new ArrayList<ImageView>();
		myAddNewButton = new Button("Add New...");
	}

	private void resetGrid() {
		myGridPane.getChildren().clear();
		myGridPane.getColumnConstraints().clear();
		double gridCellSize = (myScrollPane.getViewportBounds().getWidth() - 20) / numColumns;
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
		myGridPane.setGridLinesVisible(true);
		myAddNewButton.setOnAction(e -> myTabDisplay.openEditorDisplay());
		myScrollPane.setContent(myGridPane);
		VBox.setVgrow(myGridPane, Priority.ALWAYS);
		vbox.getChildren().addAll(myPanelBar.getNode(), myScrollPane);
		myNode = vbox;
	    resetGrid();
	}
	
	public void addImage(Image image) {
		myGridPane.getChildren().remove(myAddNewButton);
		ImageView iv = new ImageView(image);
		iv.setOnMouseClicked(e -> iv.requestFocus());
		
		iv.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					iv.setOpacity(1);
					return;
				}
				iv.setOpacity(0.7);
				//update the UnmodifiableAttributesPanel
			}
		});
		myImages.add(iv);
		resetGrid();
	}

}
