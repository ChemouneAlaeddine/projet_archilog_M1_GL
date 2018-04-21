package model;

import java.io.Serializable;

import javafx.scene.Group;

public class Board implements Serializable {
	private Group group;
	private GroupOfShapes gos;
	
	public Board() {
		this.group = new Group();
		this.gos = new GroupOfShapes();
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public GroupOfShapes getGos() {
		return gos;
	}

	public void setGos(GroupOfShapes gos) {
		this.gos = gos;
	}
}
