package view;

import model.*;

import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

//import java.awt.event.KeyEvent;

import controller.ControllerFacade;
import img.*;

public class View implements Observer {
	private static View view;
	private VShape vShape;
	private HBox hBox;
	private BorderPane borderPane;
	private Board board;
	//private Group group;
	private Scene scene;
	//private Button delete;
	private ImageView delete;
	private Toolbar toolbar;
	//private VBox vBox;
	
	public static View getInstance() {
		if(view == null)
			view = new View();
		return view;
	}
	
	public void init(Stage primaryStage) {
		primaryStage.setTitle("Editeur de figures");
		borderPane = new BorderPane();
		board = new Board();
		board.getGroup().setManaged(false);
		scene = new Scene(borderPane,600,500);
		primaryStage.setScene(scene);
		
		hBox = new HBox(8);
		hBox.setStyle("-fx-border-color: black;-fx-background-color: #d3d3d3; -fx-padding: 5;-fx-spacing: 10;");
		hBox.setPickOnBounds(true);
		hBox.setPadding(new Insets(10));
		hBox.setSpacing(10);
		
		toolbar = new Toolbar(8);
		toolbar.getvBox().setStyle("-fx-border-color: black;-fx-background-color: #d3d3d3; -fx-padding: 17;-fx-spacing: 15;");
		toolbar.getvBox().setAlignment(Pos.TOP_CENTER);
		toolbar.getvBox().setPickOnBounds(true);
		toolbar.getvBox().setPadding(new Insets(10));
		
		vShape = VShape.getInstance();
		vShape.drawShapes(toolbar , primaryStage);
		//vShape.SelectionShapes(group, primaryStage);
		
		/**
		 * HBox Buttons
		 */
		VButton vButton = VButton.getInstance();
		vButton.drawButtonView(hBox, primaryStage);
		
		/**
		 * Delete Button
		 */
		delete = new ImageView("img/delete.png");
		delete.setFitHeight(55);
		delete.setFitWidth(65);
		
		toolbar.getvBox().getChildren().addAll(delete);
		
		borderPane.setLeft(toolbar.getvBox());
		borderPane.setTop(hBox);
		borderPane.setCenter(board.getGroup());
		borderPane.setBottom(delete);
		primaryStage.show();
	}

	public ImageView getDelete() {
		return delete;
	}

	public HBox getHbox() {
		return hBox;
	}

	public static View getView() {
		return view;
	}

	public VShape getvShape() {
		return vShape;
	}

	public HBox gethBox() {
		return hBox;
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}

	public Scene getScene() {
		return scene;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Toolbar getToolbar() {
		return toolbar;
	}

	public void setToolbar(Toolbar toolbar) {
		this.toolbar = toolbar;
	}
}
