package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.*;

import authoring.frontend.display_elements.panels.Panel;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

/**
 * All attribute panels have a map of attribute names to values, as well as a
 * list of attributes names.
 * 
 * @author Frank
 *
 */

public abstract class AttributesPanel extends Panel {

	protected static final int FONT_SIZE = 13;
	protected static final double ATTRIBUTES_PANEL_WIDTH = 800 * 0.4;

	protected List<String> myAttributes;
	protected Map<String, String> myAttributesMap;

	public AttributesPanel(int height, int width) {
		super(height, width);
	}

	/**
	 * Helper method for creating and repopulating Grids
	 * 
	 * @param names
	 * @param gridPane
	 */
	protected void addColumnNames(List<String> columnNames, GridPane gridPane) {
		for (int i = 0; i < columnNames.size(); i++) {
			Label text = new Label(columnNames.get(i));
			text.setFont(new Font(FONT_SIZE));
			gridPane.add(text, i, 0);
		}
	}

}
