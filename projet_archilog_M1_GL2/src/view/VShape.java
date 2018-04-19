package view;

import java.util.ArrayList;

import controller.ControllerFacade;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.*;

public class VShape {
	
	private static VShape vShape;
	//private ArrayList<IShape> toolbarShapes;
	
	private VShape() {}
	
	public static VShape getInstance() {
        if (vShape == null)
            vShape = new VShape();
        return vShape;
    }
	
	public void drawShapes(VBox vBox, Stage primaryStage) {
		
		CRectangle rect = new CRectangle(new Position(0,0));
		CPolygon poly = new CPolygon(new Position(0,20),10);
		
		ControllerFacade.getGroupOfShapes().addChild(rect);
		ControllerFacade.getGroupOfShapes().addChild(poly);
		
		((Node) rect.get_rectangle()).addEventHandler(MouseEvent.MOUSE_PRESSED, ControllerFacade.DragPressed);
        ((Node) poly.getPolygon()).addEventHandler(MouseEvent.MOUSE_PRESSED, ControllerFacade.DragPressed);
        ((Node) rect.get_rectangle()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.RectangleDragReleased);
        ((Node) poly.getPolygon()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.PolygonDragReleased);
        
        for(IShape each : ControllerFacade.getGroupOfShapes().get_shapes()) {
        	if(each instanceof CRectangle)
        		vBox.getChildren().add(((CRectangle) each).get_rectangle());
        	if(each instanceof CPolygon)
        		vBox.getChildren().add(((CPolygon) each).getPolygon());
        }
	}
	
	public void SelectionShapes(Group group, Stage primaryStage) {
		((Node) group).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.SelectShapes);
	}
}
