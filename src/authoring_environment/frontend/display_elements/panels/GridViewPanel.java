package authoring_environment.frontend.display_elements.panels;

import java.util.ArrayList;
import java.util.List;

import authoring_environment.frontend.display_elements.panels.panel_bars.GridPanelBar;
import authoring_environment.frontend.display_elements.panels.panel_bars.PanelBar;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.geometry.HPos;
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

	public GridViewPanel(int height, int width, ITabDisplay tab) {
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

	private void sizeGrid(int num) {
		myGridPane.getChildren().clear();
		myGridPane.getColumnConstraints().clear();
		double gridCellSize = (myScrollPane.getViewportBounds().getWidth() - 20) / num;
		for (int i = 0; i < num; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setMinWidth(gridCellSize);
			column.setMaxWidth(gridCellSize);
			column.setHalignment(HPos.CENTER);
			myGridPane.getColumnConstraints().add(column);
		}

		ImageView currImage;
		int colNum, rowNum;
		for (int j = 0; j < myImages.size(); j++) {
			colNum = j % num;
			rowNum = j / num;
			currImage = myImages.get(j);
			currImage.setPreserveRatio(true);
			currImage.fitHeightProperty().set(gridCellSize);
			myGridPane.add(currImage, colNum, rowNum);
		}
		myGridPane.add(myAddNewButton, myImages.size() % num, myImages.size() / num);
	}

	public void decreaseGridSize() {
		if (numColumns != 1) {
			numColumns--;
			sizeGrid(numColumns);
		}
	}

	public void increaseGridSize() {
		numColumns++;
		sizeGrid(numColumns);
	}

	@Override
	protected void assembleComponents() {
		VBox vbox = new VBox();
		myGridPane.setGridLinesVisible(true);
		myAddNewButton.setOnAction(e -> myTabDisplay.openEditorDisplay());
		myImages.add(new ImageView("DrumpfVader.png"));
		myImages.add(new ImageView("DrumpfVader.png"));
		myImages.add(new ImageView("DrumpfVader.png"));
		myImages.add(new ImageView("DrumpfVader.png"));
		myImages.add(new ImageView("DrumpfVader.png"));
		myImages.add(new ImageView("DrumpfVader.png"));

		myScrollPane.setContent(myGridPane);
		VBox.setVgrow(myGridPane, Priority.ALWAYS);
		sizeGrid(numColumns);
		vbox.getChildren().addAll(myPanelBar.getNode(), myScrollPane);
		myNode = vbox;
	}

}
