package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class CShapeFactory implements ShapeFactory{
    @Override
    public Object createRect(Position pos) {
    	Rectangle rectangle = new Rectangle(pos.x, pos.y, 30, 20);
        rectangle.setFill(Color.GREEN);
        return rectangle;
    }

    @Override
    public Object createPoly(Position pos, double side) {
    	Polygon polygon = new Polygon(new double[] {
    		pos.x,pos.y,
    		pos.x + side,pos.y + side,
    		pos.x + side/2,pos.y + 2*side,
    		pos.x - side/2,pos.y + 2*side,
    		pos.x - side,pos.y + side
        });
        polygon.setFill(Color.RED);
        return polygon;
    }
}