package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class CShapeFactory implements ShapeFactory{
    @Override
    public Object createRect(Position pos) {
    	Rectangle rectangle = new Rectangle(pos.getX(), pos.getY(), 30, 20);
        rectangle.setFill(Color.GREEN);
        return rectangle;
    }

    @Override
    public Object createPoly(Position pos, double side) {
    	Polygon polygon = new Polygon(new double[] {
    		pos.getX(),pos.getY(),
    		pos.getX() + side,pos.getY() + side,
    		pos.getX() + side/2,pos.getY() + 2*side,
    		pos.getX() - side/2,pos.getY() + 2*side,
    		pos.getX() - side,pos.getY() + side
        });
        polygon.setFill(Color.RED);
        return polygon;
    }
}