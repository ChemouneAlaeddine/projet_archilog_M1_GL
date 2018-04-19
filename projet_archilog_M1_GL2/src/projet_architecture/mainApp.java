package projet_architecture;

import controller.ControllerFacade;
import javafx.application.*;
import javafx.stage.Stage;

public class mainApp extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ControllerFacade.getInstance().start(primaryStage);
	}
}
