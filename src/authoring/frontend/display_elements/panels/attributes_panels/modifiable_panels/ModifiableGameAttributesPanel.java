package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;

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
	protected void initializeComponents() {
		super.initializeComponents();
		myAttributes = (List<String>) Arrays.asList("Game Type", "Number of Players", "Number of Starting Lives",
				"Number of Lives For Defeat", "Game Timer", "Starting Resources");
		assembleEmptyInputRows();
		
	}

	@Override
	protected void assembleComponents() {
		super.assembleComponents();

	}

	@Override
	public Map<String, String> saveAttributes() {
		myAttributesMap.put("Type", "Game");
		return myAttributesMap;
	}

	@Override
	protected void refreshAttributes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateImageComponent(String image) {
		//update
	}
}
