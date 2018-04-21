package view;

import java.util.ArrayList;

import controller.ControllerFacade;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
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
	
	public void drawShapes(Toolbar toolbar, Stage primaryStage) {
		
		CRectangle rect = new CRectangle(new Position(0,0));
		CPolygon poly = new CPolygon(new Position(0,20),10);
		
		toolbar.getGos().addChild(rect);
		toolbar.getGos().addChild(poly);
		
		//((Node) rect.get_rectangle()).addEventHandler(MouseEvent.MOUSE_PRESSED, ControllerFacade.DragPressed);
		((Node) rect.getRectangle()).addEventHandler(MouseEvent.MOUSE_PRESSED, ControllerFacade.DragPressed);
		((Node) poly.getPolygon()).addEventHandler(MouseEvent.MOUSE_PRESSED, ControllerFacade.DragPressed);
        ((Node) rect.getRectangle()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.RectangleDragReleased);
        ((Node) poly.getPolygon()).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.PolygonDragReleased);
        
        for(IShape each : View.getInstance().getToolbar().getGos().get_shapes()) {
        	if(each instanceof CRectangle)
        		toolbar.getvBox().getChildren().add(((CRectangle) each).get_rectangle());
        	if(each instanceof CPolygon)
        		toolbar.getvBox().getChildren().add(((CPolygon) each).getPolygon());
        }
	}
	
	public void SelectionShapes(Group group, Stage primaryStage) {
		((Node) group).addEventHandler(MouseEvent.MOUSE_RELEASED, ControllerFacade.SelectShapes);
	}
}
