package utility.hud;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import engine.frontend.status.DrumpfHUDScreen;

/**
 * Main Controller class for the HUD package.
 * Contains a HUDModel, an implementation of an
 * AbstractHUDSCreen, and a dataSource
 * @author bobby
 *
 */



public class HUDController implements Observer{
	
	private HUDModel model;
	private AbstractHUDScreen view;
	
	
	/**
	 * Initializes the HUD by setting up the model and view.
	 * @param filename - file where you saved your preferences from authoring environment
	 * @param dataSource - object that holds all of your relevant game data, or references to other classes
	 * that contain it
	 * @param valueFinder - you need to write a custom ValueFinder class for your own project
	 */
	
	
	public void init(Object dataSource, IValueFinder valueFinder) {
		setModel(new HUDModel());
		valueFinder.setController(this);
		valueFinder.setDataSource(dataSource);
		List<String> fieldsToObserve = getFieldsToFollow();
		Map<Integer, String> rowToValueMap = new HashMap<>();
		for (int i = 0; i<fieldsToObserve.size(); i++) {
			Property myProperty = valueFinder.find(fieldsToObserve.get(i));
			model.getData().put(myProperty.getFieldName(), myProperty);
			rowToValueMap.put(i, myProperty.getFieldName());
		}
		setView(new DrumpfHUDScreen(model.getData(), rowToValueMap));
	}
	
	
	/**
	 * Very simple file reader that returns the fields to follow based
	 * on the input from a text file
	 * @param filename
	 * @return
	 */
	
	private List<String> getFieldsToFollow() {
		return Arrays.asList("lives", "mode", "level", "resources");
	}
	
	/**
	 * Sets the model
	 * @param model
	 */
	
	private void setModel(HUDModel model) {
		this.model = model;
	}
	
	/**
	 * Sets the view
	 * @param view
	 */
	
	private void setView(AbstractHUDScreen view) {
		this.view = view;
	}
	
	
	
	/**
	 * Handles the change whenever any object (most likely Property)
	 * that this is observing has changed. It does so by telling both the
	 * model and view to update themselves.
	 */
	
	@Override
	public void update(Observable o, Object arg) {
		ValueChange change = (ValueChange) arg;
		model.handleChange(change);
		view.handleChange(change);
	}
	
	/**
	 * Returns the view
	 * @return AbstractHUDScreen
	 */
	public AbstractHUDScreen getView() {
		return view;
	}

}
