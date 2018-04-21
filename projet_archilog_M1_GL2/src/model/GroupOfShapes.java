package model;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Iterator;

import javafx.beans.InvalidationListener;

public class GroupOfShapes implements IShape, Serializable {

	private ArrayList<IShape> _shapes;
	
	public GroupOfShapes() {
		_shapes = new ArrayList<IShape>();
	}

	@Override
	public IShape clone() {
		IShape shape = null;
        try {
        	shape = (IShape) super.clone();
        }
        catch(CloneNotSupportedException error) {
        	error.printStackTrace(System.err);
        }
        return shape;
	}

	@Override
	public void draw(Object root) {
		for(IShape each : _shapes) {
    		each.draw(root);
    	}
	}

	@Override
	public void addChild(IShape shape) {
		_shapes.add(shape);
	}

	@Override
	public void removeChild(IShape shape) {
		_shapes.remove(shape);
	}

	@Override
	public IShape getChild(int i) {
		return _shapes.get(i);
	}

	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<IShape> get_shapes() {
		return _shapes;
	}

	public void set_shapes(ArrayList<IShape> _shapes) {
		this._shapes = _shapes;
	}

}
