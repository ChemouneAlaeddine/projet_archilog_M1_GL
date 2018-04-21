package controller;

import model.*;
import view.*;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.JFileChooser;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class ControllerFacade {

	private static ControllerFacade controller;
	private static View view;
	private static Stage stage;
	private static CShapeFactory cShapeFactory;
	//private static GroupOfShapes groupOfShapes;
	
	//private static transient Stack<Memento> undoStack;
	//private static transient Stack<Memento> redoStack;
	
	//private static double orgSceneX, orgSceneY;
	//private static double newTranslateX, newTranslateY;

	/*public static void majOrgScene(MouseEvent event){
		orgSceneX = event.getSceneX();
		orgSceneY = event.getSceneY();
	}*/
	
	public static ControllerFacade getInstance() {
		if(controller == null)
			controller = new ControllerFacade();
		return controller;
	}
	
	private ControllerFacade() {
		view = View.getInstance();
		cShapeFactory = new CShapeFactory();
		//groupOfShapes = new GroupOfShapes();
		//undoStack = new Stack<Memento>();
		//redoStack = new Stack<Memento>();
	}
	
	/*public void addMemento(Memento state){
		this.undoStack.push(state);
		this.redoStack.clear();
	}*/
	
	/*public Memento getMementoUndo(){
		try{
			return this.redoStack.push(this.undoStack.pop());
		}
		catch(EmptyStackException e){}
		return null;
	}
	
	public Memento getMementoRedo(){
		try{
			return this.undoStack.push(this.redoStack.pop());
		}
		catch(EmptyStackException e){}
		return null;
	}*/
	
	/*public static void saveMemento(){
		Memento memento = new Memento(view.getGroup(),view.getVbox());
		undoStack.add(memento);
		System.out.println("memento saved");
	}*/

	public CShapeFactory getcShapeFactory() {
		return cShapeFactory;
	}

	public void setcShapeFactory(CShapeFactory cShapeFactory) {
		this.cShapeFactory = cShapeFactory;
	}

	public View getView() {
		return view;
	}
	
	/*public static EventHandler<MouseEvent> OnMousePressedUndo = 
			new EventHandler<MouseEvent>(){
		public void handle(MouseEvent event){
			view.setGroup(((Memento) redoStack.push(undoStack.pop())).getGroupState());
			view.setvBox(((Memento) redoStack.push(undoStack.pop())).getVboxState());
		}
	};

	
	public static EventHandler<MouseEvent> OnMousePressedRedo =
			new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event){
			view.setGroup(((Memento) undoStack.push(redoStack.pop())).getGroupState());
			view.setvBox(((Memento) undoStack.push(redoStack.pop())).getVboxState());
		}
	};*/
	
	public static EventHandler<MouseEvent> DragPressed = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event)
		{
		    if (event.getButton() == MouseButton.PRIMARY) {
                System.out.println("pressed");
                event.setDragDetect(true);
            }
		}
	};
	
	/*public static EventHandler<MouseEvent> OnMouseDraggedEventHandler =
			new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			if( event.getButton() == MouseButton.SECONDARY){
				//do nothing
			}
			else if (event.getButton() == MouseButton.PRIMARY){
				IShape shapeRect = (IShape) ((Shape) event.getSource()).getUserData();
				double offsetX = event.getSceneX() - orgSceneX;
				double offsetY = event.getSceneY() - orgSceneY;
				newTranslateX = offsetX;
				newTranslateY = offsetY;
				
				shapeRect.
				//shapeRect.setTranslation(newTranslateX, newTranslateY);
				//Main.m.notifyChangeShape(shapeRect, false);
			}
		}
	};*/
	
	public static EventHandler<MouseEvent> buttonSave = new EventHandler<MouseEvent>(){
		@Override
		public void handle(MouseEvent event){
			if (event.getButton() == MouseButton.PRIMARY) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(file));
						ArrayList<IShape> list = new ArrayList<IShape>();
						list = View.getInstance().getBoard().getGos().get_shapes();
						oos.writeObject(list);
						oos.flush();
						oos.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	};
	
	public static EventHandler<MouseEvent> buttonLoading = new EventHandler<MouseEvent>(){
		@Override 
		public void handle(MouseEvent event){
			if (event.getButton() == MouseButton.PRIMARY) {
				JFileChooser fileChooser = new JFileChooser();
				if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						@SuppressWarnings("resource")
						ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(file)) ;
						@SuppressWarnings("unchecked")
						//ArrayList<IShape> list = (ArrayList<IShape>) ois.readObject();
						ArrayList<Position> list = (ArrayList<Position>) ois.readObject();
						
						//Main.m.returnWhiteboard().getListShapes().clear();
						for(Node each : View.getInstance().getBoard().getGroup().getChildren()) {
							System.out.println("load");
							View.getInstance().getBoard().getGroup().getChildren().remove(each);
						}
						
						CRectangle cr;
						for(Position i : list){
							cr = new CRectangle(i);
							View.getInstance().getBoard().getGos().get_shapes().add(cr);
							cr.draw(View.getInstance().getBoard().getGroup());
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
		}
		}
	};
	
	// Drag rectangle from toolbar
		public static EventHandler<MouseEvent> RectangleDragReleased = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					if (!(event.getPickResult().getIntersectedNode() instanceof HBox) &&
	                		!(event.getPickResult().getIntersectedNode() instanceof VBox) &&
	                		!(event.getPickResult().getIntersectedNode() instanceof Button) &&
	                		!(event.getPickResult().getIntersectedNode() instanceof Rectangle) &&
	                		!(event.getPickResult().getIntersectedNode() instanceof Polygon)) {
						if (event.getSource() instanceof Rectangle) {
							CRectangle myShapeSource = new CRectangle(new Position(((Rectangle)event.getSource()).getX(),((Rectangle)event.getSource()).getY()));
	                        myShapeSource.set_rectangle((Rectangle)event.getSource());
	                    	System.out.println("released");
		                    if(!(event.getPickResult().getIntersectedNode() instanceof ImageView)) {
		                    	System.out.println("pas sur buton");
		                    	CRectangle myNewShape = (CRectangle) myShapeSource.clone();
		                        ((Rectangle) myNewShape.get_rectangle()).setX(event.getSceneX());
		                        ((Rectangle) myNewShape.get_rectangle()).setY(event.getSceneY());
		                        ((Node) myNewShape.get_rectangle()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.RectangleMoving);
		                        myNewShape.draw(View.getInstance().getBoard().getGroup());
	                    	}else {
	                    		System.out.println("sur buton1");
	                    		if((event.getPickResult().getIntersectedNode() == view.getDelete())){
	                    			System.out.println("sur buton2");
	                    			View.getInstance().getBoard().getGos().removeChild(myShapeSource);
		                			View.getInstance().getToolbar().getvBox().getChildren().remove((Rectangle) myShapeSource.get_rectangle());
	                    		}
	                    	}
						}
					}
				}
			}
		};
	
	
	// Drag polygon from toolbar
		public static EventHandler<MouseEvent> PolygonDragReleased = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					if (!(event.getPickResult().getIntersectedNode() instanceof HBox) &&
	                		!(event.getPickResult().getIntersectedNode() instanceof VBox) &&
	                		!(event.getPickResult().getIntersectedNode() instanceof Button) &&
	                		!(event.getPickResult().getIntersectedNode() instanceof Rectangle) &&
	                		!(event.getPickResult().getIntersectedNode() instanceof Polygon)) {
						if (event.getSource() instanceof Polygon) {						
							CPolygon myShapeSource = new CPolygon(new Position(((Polygon)event.getSource()).getPoints().get(0),((Polygon)event.getSource()).getPoints().get(1)),10);
							myShapeSource.setPolygon((Polygon)event.getSource());
		                    if(!(event.getPickResult().getIntersectedNode() instanceof ImageView)) {
		                    	CPolygon myNewShape = (CPolygon) myShapeSource.clone();
		                    	Polygon pol = (Polygon) cShapeFactory.createPoly(new Position(event.getSceneX(),event.getSceneY()), myNewShape.getSide());
	                            myNewShape.setPolygon(pol);
		                        ((Node) myNewShape.getPolygon()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.PolygonMoving);
		                        myNewShape.draw(View.getInstance().getBoard().getGroup());
		                        View.getInstance().getBoard().getGos().addChild(myNewShape);
		                        //saveMemento();
	                    	}else {
	                    		if((event.getPickResult().getIntersectedNode() == view.getDelete())){
	                    			View.getInstance().getToolbar().getGos().removeChild(myShapeSource);
		                			View.getInstance().getToolbar().getvBox().getChildren().remove((Polygon) myShapeSource.getPolygon());
		                			//saveMemento();
	                    		}
	                    	}
						}
					}
				}
			}
		};

		// Drag Rectangle from board
		public static EventHandler<MouseEvent> RectangleMoving = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					if (!(event.getPickResult().getIntersectedNode() instanceof HBox) &&
	                		!(event.getPickResult().getIntersectedNode() instanceof Button) &&
	                		!(event.getPickResult().getIntersectedNode() instanceof Rectangle) &&
	                		!(event.getPickResult().getIntersectedNode() instanceof Polygon)) {
						if(event.getSource() instanceof Shape) {
							if (event.getSource() instanceof Rectangle) {
		                        CRectangle myShapeSource = new CRectangle(new Position(((Rectangle)event.getSource()).getX(),((Rectangle)event.getSource()).getY()));
		                        myShapeSource.set_rectangle((Rectangle)event.getSource());
		                    	if(!(event.getPickResult().getIntersectedNode() instanceof VBox) &&
		                    			!(event.getPickResult().getIntersectedNode() instanceof ImageView)) {
			                    	((Rectangle) myShapeSource.get_rectangle()).setX(event.getSceneX());
			                        ((Rectangle) myShapeSource.get_rectangle()).setY(event.getSceneY());
		                		}else {
		                			if((event.getPickResult().getIntersectedNode() instanceof ImageView)) {
		                        		if((event.getPickResult().getIntersectedNode() == view.getDelete())){
		                        			View.getInstance().getBoard().getGroup().getChildren().remove(myShapeSource.get_rectangle());
		                        		}
		                			}else {
			                			View.getInstance().getToolbar().getGos().addChild(myShapeSource);
			                			myShapeSource.set_position(new Position(0,View.getInstance().getToolbar().getGos().get_shapes().size()*20));
			                			View.getInstance().getToolbar().getGos().addChild(myShapeSource);
			                			View.getInstance().getToolbar().getvBox().getChildren().add((Rectangle) myShapeSource.get_rectangle());
			                			((Node) myShapeSource.get_rectangle()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.RectangleDragReleased);
		                			}
		                		}
							}
						}
					}
				}
			}
		};
	
	// Drag Polygon from board
	public static EventHandler<MouseEvent> PolygonMoving = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if (event.getButton() == MouseButton.PRIMARY) {
				if (!(event.getPickResult().getIntersectedNode() instanceof HBox) &&
                		!(event.getPickResult().getIntersectedNode() instanceof Button) &&
                		!(event.getPickResult().getIntersectedNode() instanceof Rectangle) &&
                		!(event.getPickResult().getIntersectedNode() instanceof Polygon)) {
					if(event.getSource() instanceof Shape) {
						if (event.getSource() instanceof Polygon) {
							CPolygon myShapeSource = new CPolygon(new Position(((Polygon)event.getSource()).getPoints().get(0),((Polygon)event.getSource()).getPoints().get(1)),10);
                            myShapeSource.setPolygon((Polygon)event.getSource());
                        	if(!(event.getPickResult().getIntersectedNode() instanceof VBox) &&
                        			!(event.getPickResult().getIntersectedNode() instanceof ImageView)) {
	                            Polygon pol = (Polygon) cShapeFactory.createPoly(new Position(event.getSceneX(),event.getSceneY()), myShapeSource.getSide());
	                            myShapeSource.getPolygon().getPoints().clear();
	                            myShapeSource.getPolygon().getPoints().addAll(pol.getPoints());
	                            //saveMemento();
                        	}else {
                        		if((event.getPickResult().getIntersectedNode() instanceof ImageView)) {
	                        		if((event.getPickResult().getIntersectedNode() == view.getDelete())){
	                        			View.getInstance().getBoard().getGroup().getChildren().remove(myShapeSource.getPolygon());
	                        			//saveMemento();
	                        		}
                        		}else {
	                        		myShapeSource.set_position(new Position(0,view.getToolbar().getGos().get_shapes().size()*20));
	                        		View.getInstance().getToolbar().getGos().addChild(myShapeSource);
		                			View.getInstance().getToolbar().getvBox().getChildren().add((Polygon) myShapeSource.getPolygon());
		                			((Node) myShapeSource.getPolygon()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.PolygonDragReleased);
		                			//saveMemento();
                        		}
                        	}
						}
					}
				}
			}
		}
	};

	
	public static EventHandler<MouseEvent> SelectShapes = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event)
		{
			System.out.println("one");
		    if (event.getButton() == MouseButton.PRIMARY) {
		    	System.out.println("two");
		    	if (!(event.getSource() instanceof Polygon) && !(event.getSource() instanceof Rectangle)) {
		    		System.out.println("object : "+event.getSource().toString());
		    		System.out.println("three");
			    	if (event.getPickResult().getIntersectedNode() instanceof Group) {
			    		System.out.println("selection");
			    	}
			    	System.out.println("object 2 : "+event.getPickResult().getIntersectedNode().toString());
		    	}
            }
		}
	};
	
	public void start(Stage primaryStage) throws Exception {
		ControllerFacade.stage = primaryStage;
		view.init(stage);
	}
}
