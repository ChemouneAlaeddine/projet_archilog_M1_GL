package model;

import java.io.Serializable;

import javafx.beans.Observable;

public interface IShape extends Cloneable, Observable, Serializable{
	public IShape clone();
    public void draw(Object root);
    public void addChild(IShape shape);
  	public void removeChild(IShape shape);
    public IShape getChild(int i);
    //public boolean contains(IShape s);
    //public IShape whoContains(Object s);
}
