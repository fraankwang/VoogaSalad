package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.*;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.LevelGridViewPanel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.parser.GlobalParser;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableModeAttributesPanel extends ModifiableAttributesPanel {

	private static final int MODE_DESCRIPTION_HEIGHT = 30;
	private static final List<String> DEFAULT_MODE_ATTRIBUTES = Arrays.asList("Name", "InitialLives", "InitialResources");
	
	private LevelGridViewPanel myLevelSelector;
	private Map<String, String> myPossibleLevels;
	private Map<Integer, String> mySelectedLevels;
	
	public ModifiableModeAttributesPanel(int height, int width, IAuthoringView controller) {
		super(height, width, controller);
		myPossibleLevels = new HashMap<String, String>();
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		rowConstraints.add(MODE_DESCRIPTION_HEIGHT);
		rowConstraints.add(100 - MODE_DESCRIPTION_HEIGHT);

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);

		myAttributesGridPane = createAttributesGridPane();
		myAttributesGridPane.setPrefWidth(ATTRIBUTES_PANEL_WIDTH);
		myLevelSelector = new LevelGridViewPanel(myHeight, myWidth, null, myController);
		myLevelSelector.initialize();
		mySelectedLevels = new HashMap<Integer, String>();
		
		myAttributesMap = new TreeMap<String, String>();
		myInputMap = new TreeMap<String, Control>();
		myAttributes = DEFAULT_MODE_ATTRIBUTES;
		assembleEmptyInputRows();

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myAttributesGridPane, 0, 0);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;
	}
	

	@Override
	public void updateAttributes(Map<String, String> info) {
		myAttributesMap = info;
		myInputMap.clear();
		
		for (String attribute : DEFAULT_MODE_ATTRIBUTES) {
			TextField tf = new TextField();
			myInputMap.put(attribute, tf);
		}
		
		if (info.get("Levels") != null) {
			List<String> selectedLevels = GlobalParser.parseLevels(info.get("Levels"));
			updateLevels(selectedLevels);
			
		}
		
		System.out.println(
				"*****3. ModifiableModeAttrPanel: updated myAttributesMap and myAttributes set with given unmodifiableattributespanel outputs:");
		System.out.println(myAttributesMap);
		refreshAttributes();
	}

	private void updateLevels(List<String> selectedLevels) {
		setMyPossibleLevels(myController.getLevels());
		myLevelSelector.updatePossibleLevels(myPossibleLevels);
		myLevelSelector.updateSelectedLevels(selectedLevels);
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

	@SuppressWarnings("unchecked")
	public Map<String, String> saveAttributes() {
		myAttributesMap.put("Type", "Mode");

		for (String s : myInputMap.keySet()) {
			if (myInputMap.get(s) instanceof TextField) {
				myAttributesMap.replace(s, ((TextField) myInputMap.get(s)).getText());
			} else if (myInputMap.get(s) instanceof ComboBox<?>) {
				myAttributesMap.replace(s, ((ComboBox<String>) myInputMap.get(s)).getValue());
			}

		}

		mySelectedLevels = myLevelSelector.getSelectedLevels();
		String levelsCompressed = GlobalParser.compressLevels(mySelectedLevels);
		
		if (!myAttributesMap.containsKey("Levels")) {
			myAttributesMap.put("Levels", levelsCompressed);
		} else {
			myAttributesMap.replace("Levels", levelsCompressed);
		}
		
		System.out.println("*****4. ModifiableEntityAttrPanel: myAttributesMap saved by user:");
		System.out.println(myAttributesMap);

		return myAttributesMap;
	}
	
	public void setMyPossibleLevels(Map<String, String> levels) {
		myPossibleLevels = levels;
	}

	public LevelGridViewPanel getLevelSelector() {
		return myLevelSelector;
	}

}
