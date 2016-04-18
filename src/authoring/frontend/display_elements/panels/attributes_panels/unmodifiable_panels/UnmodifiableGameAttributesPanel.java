package authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Frank
 *
 */

public class UnmodifiableGameAttributesPanel extends UnmodifiableAttributesPanel {

	private BorderPane myWrapper;
	private GridPane myGridPane;
	
	public UnmodifiableGameAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width, tabDisplay);
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		rowConstraints.add(BUTTON_HEIGHT_PERCENTAGE);
		rowConstraints.add(100 - BUTTON_HEIGHT_PERCENTAGE);
		List<Integer> columnConstraints = new ArrayList<Integer>();

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);

		myAttributesGridPane = createAttributesGridPane();
		myOpenEditorButton = createOpenEditorButton();

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myOpenEditorButton, 0, 0);
		myGridPane.add(myAttributesGridPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;

	}

	private GridPane createAttributesGridPane() {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(COLUMN_1_PERCENTAGE);
		columnConstraints.add(COLUMN_2_PERCENTAGE);

		myAttributesGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myAttributes = (List<String>) Arrays.asList("Game Type", "Number of Players", "Number of Starting Lives",
				"Number of Lives for Defeat", "Game Timer", "Starting Resources");

		assembleRows();
		
		myAttributesGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);
		return myAttributesGridPane;

	}

	@Override
	protected void refreshDisplay() {
		myAttributesGridPane = createAttributesGridPane();
		
//		for (int i = 0; i < myAttributes.size(); i++) {
//			String currentAttribute = myAttributes.get(i);
//
//			Text text = new Text(currentAttribute);
//			text.setFont(new Font(FONT_SIZE));
//			TextField tf = (TextField) myOutputMap.get(currentAttribute);
//			tf.setText(myAttributesMap.get(myAttributes.get(i)));
//			tf.setEditable(false);
//
//			myOutputMap.replace(currentAttribute, tf);
//
//			// for (Node node : myAttributesGridPane.getChildren()) {
//			// if (node instanceof TextField && GridPane.getColumnIndex(node) ==
//			// 1 && GridPane.getRowIndex(node) == i) {
//			// node.setVisible(false);
//			// }
//			// }
//		}
	}
}
