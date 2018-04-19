package controller;

import model.*;
import view.*;

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
	private static GroupOfShapes groupOfShapes;
	
	public static ControllerFacade getInstance() {
		if(controller == null)
			controller = new ControllerFacade();
		return controller;
	}
	
	private ControllerFacade() {
		view = View.getInstance();
		cShapeFactory = new CShapeFactory();
		groupOfShapes = new GroupOfShapes();
	}

	public CShapeFactory getcShapeFactory() {
		return cShapeFactory;
	}

	public void setcShapeFactory(CShapeFactory cShapeFactory) {
		this.cShapeFactory = cShapeFactory;
	}

	public View getView() {
		return view;
	}
	
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
	
	public static EventHandler<MouseEvent> DragReleased = new EventHandler<MouseEvent>() {
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
	                        ((Node) myNewShape.get_rectangle()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.Moving);
	                        myNewShape.draw(View.getInstance().getGroup());
                    	}else {
                    		System.out.println("sur buton1");
                    		if((event.getPickResult().getIntersectedNode() == view.getDelete())){
                    			System.out.println("sur buton2");
                    			getGroupOfShapes().removeChild(myShapeSource);
	                			view.getInstance().getvBox().getChildren().remove((Rectangle) myShapeSource.get_rectangle());
                    		}
                    	}
                        
                    }else {
                    	if (event.getSource() instanceof Polygon) {
                            CPolygon myShapeSource = new CPolygon(new Position(((Polygon)event.getSource()).getPoints().get(0),((Polygon)event.getSource()).getPoints().get(1)),10);
                            myShapeSource.setPolygon((Polygon)event.getSource());
                        	System.out.println("released");
                        	if(!(event.getPickResult().getIntersectedNode() instanceof ImageView)) {
	                            CPolygon myNewShape = (CPolygon) myShapeSource.clone();
	                            Polygon pol = (Polygon) cShapeFactory.createPoly(new Position(event.getSceneX(),event.getSceneY()), myNewShape.getSide());
	                            myNewShape.setPolygon(pol);
	                            ((Node) myNewShape.getPolygon()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.Moving);
	                            myNewShape.draw(View.getInstance().getGroup());
                        	}else {
                        		System.out.println("sur buton1");
                        		if((event.getPickResult().getIntersectedNode() == view.getDelete())){
                        			System.out.println("sur buton2");
                        			getGroupOfShapes().removeChild(myShapeSource);
    	                			view.getInstance().getvBox().getChildren().remove((Polygon) myShapeSource.getPolygon());
                        		}
                        	}
                    	}
                    }
                }
            }
		}
	};
	
	public static EventHandler<MouseEvent> Moving = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
		    if (event.getButton() == MouseButton.PRIMARY) {
                if (!(event.getPickResult().getIntersectedNode() instanceof HBox) &&
                		!(event.getPickResult().getIntersectedNode() instanceof Button) &&
                		!(event.getPickResult().getIntersectedNode() instanceof Rectangle) &&
                		!(event.getPickResult().getIntersectedNode() instanceof Polygon)) {
                    if(event.getSource() instanceof Shape) {
	                	if (event.getSource() instanceof Rectangle) {
	                		System.out.println("here");
	                        CRectangle myShapeSource = new CRectangle(new Position(((Rectangle)event.getSource()).getX(),((Rectangle)event.getSource()).getY()));
	                        myShapeSource.set_rectangle((Rectangle)event.getSource());
	                    	System.out.println("moved");
	                    	if(!(event.getPickResult().getIntersectedNode() instanceof VBox) &&
	                    			!(event.getPickResult().getIntersectedNode() instanceof ImageView)) {
		                    	((Rectangle) myShapeSource.get_rectangle()).setX(event.getSceneX());
		                        ((Rectangle) myShapeSource.get_rectangle()).setY(event.getSceneY());
	                		}else {
	                			if((event.getPickResult().getIntersectedNode() instanceof ImageView)) {
	                				System.out.println("sur buton1");
	                        		if((event.getPickResult().getIntersectedNode() == view.getDelete())){
	                        			System.out.println("supprimer le shape");
	                        			View.getInstance().getGroup().getChildren().remove(myShapeSource.get_rectangle());
	                        		}
	                			}else {
		                			groupOfShapes.addChild(myShapeSource);
		                			myShapeSource.set_position(new Position(0,groupOfShapes.get_shapes().size()*20));
		                			getGroupOfShapes().addChild(myShapeSource);
		                			view.getInstance().getvBox().getChildren().add((Rectangle) myShapeSource.get_rectangle());
		                			((Node) myShapeSource.get_rectangle()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.DragReleased);
		                			System.out.println("add to toolbar");
	                			}
	                		}
	                    }else {
	                    	if (event.getSource() instanceof Polygon) {
	                            CPolygon myShapeSource = new CPolygon(new Position(((Polygon)event.getSource()).getPoints().get(0),((Polygon)event.getSource()).getPoints().get(1)),10);
	                            myShapeSource.setPolygon((Polygon)event.getSource());
	                        	System.out.println("moved");
	                        	if(!(event.getPickResult().getIntersectedNode() instanceof VBox) &&
	                        			!(event.getPickResult().getIntersectedNode() instanceof ImageView)) {
		                            Polygon pol = (Polygon) cShapeFactory.createPoly(new Position(event.getSceneX(),event.getSceneY()), myShapeSource.getSide());
		                            myShapeSource.getPolygon().getPoints().clear();
		                            myShapeSource.getPolygon().getPoints().addAll(pol.getPoints());
	                        	}else {
	                        		if((event.getPickResult().getIntersectedNode() instanceof ImageView)) {
	                        			System.out.println("sur buton1");
		                        		if((event.getPickResult().getIntersectedNode() == view.getDelete())){
		                        			System.out.println("supprimer le shape");
		                        			View.getInstance().getGroup().getChildren().remove(myShapeSource.getPolygon());
		                        		}
	                        		}else {
		                        		groupOfShapes.addChild(myShapeSource);
		                        		myShapeSource.set_position(new Position(0,groupOfShapes.get_shapes().size()*20));
			                			getGroupOfShapes().addChild(myShapeSource);
			                			view.getInstance().getvBox().getChildren().add((Polygon) myShapeSource.getPolygon());
			                			((Node) myShapeSource.getPolygon()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.DragReleased);
		                        		System.out.println("add to toolbar");
	                        		}
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

	public static GroupOfShapes getGroupOfShapes() {
		return groupOfShapes;
	}
}
