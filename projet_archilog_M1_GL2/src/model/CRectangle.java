package model;

import controller.*;
import javafx.beans.InvalidationListener;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class CRectangle implements IShape {
	
	private Rectangle rectangle;
	private Position position;
	private int width = 30;
	private int height = 20;
	
	public CRectangle(Position position) {
		Rectangle rect = new Rectangle();
		rect.setX(position.getX());
		rect.setY(position.getY());
		rect.setWidth(this.width);
		rect.setHeight(this.height);
		rect.setFill(Color.GREEN);
		this.rectangle = rect;
		this.position = position;
	}

	
	@Override
	public IShape clone() {
		CRectangle s = null;
        try {
        	s = (CRectangle) super.clone();
        	s.rectangle = (Rectangle) ControllerFacade.getInstance().getcShapeFactory().createRect(this.position);
        }
        catch(CloneNotSupportedException error) {
        	error.printStackTrace(System.err);
        }
        return s;
	}

	@Override
	public void draw(Object group) {
		if(group instanceof Group) {
    		((Group) group).getChildren().addAll(this.rectangle);
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
		return position;
	}


	public void set_position(Position _position) {
		this.position = _position;
		//this.notifyAll();
	}


	public Rectangle get_rectangle() {
		return rectangle;
	}


	public void set_rectangle(Rectangle _rectangle) {
		this.rectangle = _rectangle;
		//this.notifyAll();
	}


	public Rectangle getRectangle() {
		return rectangle;
	}


	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
		//this.notifyAll();
	}


	@Override
	public void addListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
		
	}

}