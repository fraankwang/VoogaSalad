package authoring_environment.frontend.display_elements.panels;

import authoring_environment.frontend.display_elements.panels.button_dashboards.SimpleButtonDashboard;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * The RulesEditorPanel contains two scrollable views of "if" and "then"
 * conditions, which together create a rule.
 * 
 * @author Frank
 *
 */

public class RulesEditorPanel extends Panel {

	private SimpleButtonDashboard mySimpleButtonDashboard;
	private Node myIfNode;
	private Node myThenNode;
	private Button myAddNewIfButton;
	private Button myAddNewThenButton;
	private ListView myIfStatements;
	private ListView myThenStatements;
	
	public RulesEditorPanel(int height, int width) {
		super(height, width);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void assembleComponents() {
		// TODO Auto-generated method stub

	}

}
