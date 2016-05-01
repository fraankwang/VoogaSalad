package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Map;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import javafx.scene.control.TextField;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableGameAttributesPanel extends ModifiableAttributesPanel {

	public ModifiableGameAttributesPanel(int height, int width, IAuthoringView controller) {
		super(height, width, controller);
	}

	@Override
	public Map<String, String> saveAttributes() {
		myAttributesMap.put("Type", "Game");
		return myAttributesMap;
	}

	@Override
	protected void refreshAttributes() {
		if (myInputMap != null) {
			for (int i = 0; i < myAttributes.size(); i++) {
				TextField tf = (TextField) myInputMap.get(myAttributes.get(i));
				tf.setText(myAttributesMap.get(myAttributes.get(i)));
				tf.setEditable(true);
				myInputMap.replace(myAttributes.get(i), tf);

			}

		}
		refreshAttributeInputRows();
	}


}
