package model;

import javafx.scene.layout.VBox;

public class Toolbar {
	private VBox vBox;
	private GroupOfShapes gos;
	
	public Toolbar() {
		this.vBox = new VBox();
		this.gos = new GroupOfShapes();
	}
	
	public Toolbar(int a) {
		this.vBox = new VBox(a);
		this.gos = new GroupOfShapes();
	}

	public VBox getvBox() {
		return vBox;
	}

	public void setvBox(VBox vBox) {
		this.vBox = vBox;
	}

	public GroupOfShapes getGos() {
		return gos;
	}

	public void setGos(GroupOfShapes gos) {
		this.gos = gos;
	}
}
