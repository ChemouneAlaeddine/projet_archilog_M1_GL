package model;

public interface ShapeFactory {
	Object createRect(Position pos);
	Object createPoly(Position pos, double side);
}