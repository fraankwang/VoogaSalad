package authoring.frontend.editor_features;

import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;

public class CheckComboBox extends MenuButton {

	private ListView<String> selectedItems;
	
	public CheckComboBox(String title, Collection<String> options) {
		selectedItems = new ListView<String>();
		initializeOptions(options);
	}
	
	private void initializeOptions(Collection<String> options) {
		for (String option: options) {
			add(option);
		}
	} 

	public void add(String option) {
		CheckBox cb = new CheckBox(option);
		CustomMenuItem item = new CustomMenuItem(cb);
		cb.selectedProperty().addListener(new ChangeListener<Boolean>() {  
            @Override  
            public void changed(ObservableValue<? extends Boolean> obs,  
                    Boolean wasPreviouslySelected, Boolean isNowSelected) {  
                if (isNowSelected) {  
                    selectedItems.getItems().add(cb.getText());  
                } else {  
                    selectedItems.getItems().remove(cb.getText());  
                }  
            }  
        });
		item.setHideOnClick(false);
		getItems().add(item);
	}
	
	public void select(String option) {
		selectedItems.getItems().add(option);
	}
	
	public void selectAll(Collection<String> options) {
		for (String option: options) {
			select(option);
		}
	}
	
	public void clearSelections() {
		selectedItems.getItems().clear();
	}
	
	public String getSelections() {
		StringBuilder sb = new StringBuilder();
		for (String item: selectedItems.getItems()) {
			sb.append(item + "_");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
}
