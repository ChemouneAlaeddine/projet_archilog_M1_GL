package model;

import controller.ControllerFacade;
import javafx.beans.InvalidationListener;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class CPolygon implements IShape {

	private Polygon polygon;
	private double side;
	private Position pos;
	
	public CPolygon(Position pos, double side){
	       Polygon poly = new Polygon(new double[] {
	    		   pos.getX(),pos.getY(),
	    		   pos.getX() + side,pos.getY() + side,
	    		   pos.getX() + side/2,pos.getY() + 2*side,
	    		   pos.getX() - side/2,pos.getY() + 2*side,
	    		   pos.getX() - side,pos.getY() + side
	       });
	       poly.setFill(Color.RED);
	       this.polygon = poly;
	       this.side = side;
	       this.pos = pos;
	    }
	
	@Override
	public IShape clone() {
		CPolygon p = null;
        try {
        	p = (CPolygon) super.clone();
        	p.polygon = (Polygon) ControllerFacade.getInstance().getcShapeFactory().createPoly(this.pos, this.side);
        }
        catch(CloneNotSupportedException error) {
        	error.printStackTrace(System.err);
        }
        return p;
	}

	@Override
	public void draw(Object group) {
		if(group instanceof Group) {
    		((Group) group).getChildren().addAll(this.polygon);
		}
	}

	@Override
	public void addChild(IShape shape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeChild(IShape shape) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IShape getChild(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public Position get_position() {
		return pos;
	}

	public void set_position(Position _position) {
		this.pos = _position;
		//this.notifyAll();
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
		//this.notifyAll();
	}

	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
		//this.notifyAll();
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
		//this.notifyAll();
	}

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}
}
