#Screen Capture Utility README

##How to integrate ScreenCapture utility into your program.

To begin, you must store a GameCapture object in your Model View Controller.  This will be where your front end accesses the GameCapturing.

For example, here is our method to initialize the parameters of the Game Capture:

```

	private void setupGameCapture(){
			myGameCapture = new GameCapture( 
				myEngineView.loadIntResource("StartX"), 
				myEngineView.loadIntResource("StartY"),
				myEngineView.loadIntResource("StageMinWidth"),
				myEngineView.loadIntResource("StageMinHeight"));
		
		myStage.xProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureX(newValue.intValue());
			}
		});
		
		myStage.yProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureY(newValue.intValue());
			}
		});
		
		myStage.widthProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureWidth(newValue.intValue());
			}
		});
		
		myStage.heightProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myGameCapture.setCaptureHeight(newValue.intValue());
			}
		});
	}
	
```

Once all of the data is initialized, you must also set up the buttons for file saving location, image file type, customized file name in a menu bar at the top, as well as buttons for capturing functions at the bottom.



## Please refer to the IGameCapture API for all necessary methods to utilize the functionality of the utility

Also note that current design maxes out at 10-12 fps.  This is due to the limitations of screenCapture speed using java robot using normal machines.  

##Jar Dependencies:

* commons-cli-1.3.1.jar
* commons-lang3-3.4.jar
* logback-classic-1.1.7.jar
* logback-core-1.1.7.jar
* slf4j-api-1.7.21.jar
* xuggle-xuggler-5.4.jar

###How to add jars into your workspace:
1. Go to your project explorer on the left
2.  Scroll down to a folder that says "dependencies"
3.  Expand Folder, select all the imported jars
4.  Right click and hit "add to build path"

