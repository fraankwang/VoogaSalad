package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
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
		myAttributes = (List<String>) Arrays.asList("Easy", "Medium", "Hard");
		super.assembleComponents();

	}

	@Override
	protected void assembleRows() {
		myInputMap = new HashMap<String, Control>();

		ComboBox<String> cb = new ComboBox<String>();
		cb.setEditable(true);

		for (int i = 0; i < myAttributes.size(); i++) {
			cb.getItems().add(myAttributes.get(i));

		}

		Text text = new Text("Mode");
		text.setFont(new Font(FONT_SIZE));
		myAttributesGridPane.add(text, 0, 0);
		myAttributesGridPane.add(cb, 1, 0);
		myInputMap.put("Mode", cb);
	}

	@Override // change to make refresh combo boxes instead
	protected void refreshAttributesGrid() {
		for (int i = 0; i < myAttributes.size(); i++) {
			TextField tf = (TextField) myInputMap.get(myAttributes.get(i));
			myInputMap.put(myAttributes.get(i), tf);
			myAttributesGridPane.add(tf, 1, i);
		}
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
