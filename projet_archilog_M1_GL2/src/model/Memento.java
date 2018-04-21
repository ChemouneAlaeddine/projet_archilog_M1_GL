package model;

import javafx.scene.Group;
import javafx.scene.layout.VBox;

public class Memento {
	private Group group;
	private VBox vBox;
	
	public Memento(Group g, VBox v){
		this.group = g;
		this.vBox = v;
	}

	public Group getGroupState(){
		return this.group;
	}
	
	public VBox getVboxState(){
		return this.vBox;
	}
	
}
