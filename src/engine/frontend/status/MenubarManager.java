/**
 * @author austinwu
 */
package engine.frontend.status;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import engine.frontend.overall.EngineView;
import engine.frontend.overall.ResourceUser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class MenubarManager extends ResourceUser {
	private EngineView myEngineView;
	public static final String RESOURCE_NAME = "menubar";
	private MenuBar menubar;

	public MenubarManager(EngineView ev) {
		super(RESOURCE_NAME);
		myEngineView = ev;
	}

	public MenuBar buildMenuBar() {
		menubar = new MenuBar();

		Menu filemenu = buildFileMenu();
		Menu capturemenu = buildCaptureMenu();
		Menu helpmenu = buildHelpMenu();
		menubar.getMenus().addAll(filemenu, capturemenu, helpmenu);

		return menubar;
	}

	private Menu buildFileMenu() {
		Menu menu = new Menu(loadStringResource("FilePrompt"));
		MenuItem save = new MenuItem(loadStringResource("SavePrompt"));
		save.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showSaveDialog(myEngineView.getStage());
			System.out.println(file);
		});

		MenuItem load = new MenuItem(loadStringResource("LoadPrompt"));
		load.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(myEngineView.getStage());
			System.out.println(file);
		});

		menu.getItems().addAll(load, save);
		return menu;
	}

	private Menu buildCaptureMenu() {
		Menu menu = new Menu(loadStringResource("MenuPrompt"));
		MenuItem setImageFileType = new MenuItem(loadStringResource("ImageFileTypePrompt"));
		setImageFileType.setOnAction(e -> {
			List<String> choices = Arrays.asList(ImageIO.getWriterFormatNames());
			ChoiceDialog<String> dialog = new ChoiceDialog<>(myEngineView.getGameCapture().getImageFileType(), choices);
			dialog.setTitle(loadStringResource("ImageDialogTitle"));
			dialog.setHeaderText(loadStringResource("ImageDialogHeader"));
			dialog.setContentText(loadStringResource("ImageDialogContent"));

			Optional<String> result = dialog.showAndWait();
			result.ifPresent(choice -> myEngineView.getGameCapture().setImageFileType(choice));
		});

		MenuItem setNumFramesPerSecond = new MenuItem(loadStringResource("NumFramesPerSecondPrompt"));
		setNumFramesPerSecond.setOnAction(e -> {
			List<Integer> choices = new ArrayList<Integer>();
			for (int i = 1; i <= 60; i++) {
				choices.add(i);
			}
			ChoiceDialog<Integer> dialog = new ChoiceDialog<>(myEngineView.getGameCapture().getFramesPerSecond(),
					choices);
			dialog.setTitle(loadStringResource("FPSDialogTitle"));
			dialog.setHeaderText(loadStringResource("FPSDialogHeader"));
			dialog.setContentText(loadStringResource("FPSDialogContent"));
			Optional<Integer> result = dialog.showAndWait();
			result.ifPresent(choice -> myEngineView.getGameCapture().setFramesPerSecond(choice));
		});

		MenuItem setSaveLocation = new MenuItem(loadStringResource("FileSaveLocationPrompt"));
		setSaveLocation.setOnAction(e -> {
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle(loadStringResource("SaveLocationTitle"));
			chooser.setInitialDirectory(myEngineView.getGameCapture().getSaveLocation());
			File selectedDirectory = chooser.showDialog(myEngineView.getStage());
			myEngineView.getGameCapture().setSaveLocation(selectedDirectory);
		});

		MenuItem setFileName = new MenuItem(loadStringResource("FileNamePrompt"));
		setFileName.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog(myEngineView.getGameCapture().getFileName());
			dialog.setTitle(loadStringResource("FileDialogTitle"));
			dialog.setHeaderText(loadStringResource("FileDialogHeader"));
			dialog.setContentText(loadStringResource("FileDialogContent"));
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(name -> myEngineView.getGameCapture().setFileName(name));
		});

		menu.getItems().addAll(setImageFileType, setNumFramesPerSecond, setSaveLocation, setFileName);
		return menu;
	}

	private Menu buildHelpMenu() {
		Menu menu = new Menu(loadStringResource("HelpPrompt"));
		CheckMenuItem debugModeItem = new CheckMenuItem(loadStringResource("DebugPrompt"));
		debugModeItem.setSelected(false);
		debugModeItem.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue ov, Boolean old_val, Boolean new_val) {
				myEngineView.getShopPane().getCurrentView().setDebug(new_val);
			}
		});
		menu.getItems().add(debugModeItem);
		return menu;
	}
}
