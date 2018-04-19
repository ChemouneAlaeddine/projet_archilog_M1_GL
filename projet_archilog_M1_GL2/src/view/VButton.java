package view;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class VButton {

	private static VButton button;

    public static VButton getInstance() {
        if (button == null)
            button = new VButton();
        return button;
    }

    public void drawButtonView(HBox hbox, Stage primaryStage){
    	
    	ImageView imgSave = new ImageView("img/save.png");
		imgSave.setFitHeight(20);
		imgSave.setFitWidth(20);
		Button save = new Button();
		save.setGraphic(imgSave);
		save.setMaxSize(20, 20);
		
		ImageView imgLoad = new ImageView("img/computer-from-floppy-load-512.png");
		imgLoad.setFitHeight(20);
		imgLoad.setFitWidth(20);
		Button load = new Button();
		load.setGraphic(imgLoad);
		load.setMaxSize(20, 20);
		
		ImageView imgUndo = new ImageView("img/undo.png");
		imgUndo.setFitHeight(20);
		imgUndo.setFitWidth(20);
		Button undo = new Button();
		undo.setGraphic(imgUndo);
		undo.setMaxSize(20, 20);
		
		ImageView imgRedo = new ImageView("img/redo.png");
		imgRedo.setFitHeight(20);
		imgRedo.setFitWidth(20);
		Button redo = new Button();
		redo.setGraphic(imgRedo);
		redo.setMaxSize(20, 20);
		
		/*ImageView imgDelete = new ImageView("img/trash.png");
		imgDelete.setFitHeight(20);
		imgDelete.setFitWidth(20);
		Button delete = new Button("delete");
		delete.setGraphic(imgDelete);
		delete.setMaxSize(20, 20);*/
		
        /*Controller.getInstance();
		buttonSave.setOnAction(Controller.eventHandlerButtonSave);
        buttonOpen.setOnAction(Controller.eventHandlerButtonOpen);
        buttonEdit.setOnAction(Controller.eventHandlerButtonEdit);*/


        //hbox.setSpacing(10);
        hbox.getChildren().addAll(save, load, undo, redo);
    }
}
