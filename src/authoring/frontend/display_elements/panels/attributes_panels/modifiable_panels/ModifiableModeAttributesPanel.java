package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableModeAttributesPanel extends ModifiableAttributesPanel {

	public ModifiableModeAttributesPanel(int height, int width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();

	}

	@Override
	protected void assembleComponents() {
		super.assembleComponents();

		List<String> modeNames = (List<String>) Arrays.asList("Easy", "Medium", "Hard");
<<<<<<< HEAD
		
		//assembleRows(myAttributesGridPane, modeNames);
		
=======

		assembleRows(myAttributesGridPane, modeNames);

>>>>>>> authoring_frontend_buttondashboards_fxw
	}

	@Override
	protected void assembleRows(GridPane gridPane, List<String> modeNames) {
		myInputMap = new HashMap<String, Control>();

		ComboBox<String> cb = new ComboBox<String>();
		cb.setEditable(true);

		for (int i = 0; i < modeNames.size(); i++) {
			cb.getItems().add(modeNames.get(i));

		}

		Text text = new Text("Mode");
		text.setFont(new Font(FONT_SIZE));
		gridPane.add(text, 0, 0);
		gridPane.add(cb, 1, 0);
		myInputMap.put("Mode", cb);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> saveAttributes() {
		myAttributesMap = new HashMap<String, String>();
		myAttributesMap.put("Mode", ((ComboBox<String>) myInputMap.get("Mode")).getSelectionModel().getSelectedItem());
		myAttributesMap.put("Type", "Mode");
		return myAttributesMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void resetAttributes() {
		for (String s : myInputMap.keySet()) {
			ComboBox<String> inputArea = (ComboBox<String>) myInputMap.get(s);
			inputArea.getSelectionModel().clearSelection();
		}

	}

}
