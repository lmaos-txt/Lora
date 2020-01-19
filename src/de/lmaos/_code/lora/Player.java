package de.lmaos._code.lora;

import name.panitz.game.framework.*;


public class Player<I> extends ImageObject<I> {
	Vertex facing;
	double health;
	double maxHealth;
	int level;

	public Player(String imageFileName, Vertex corner) {
		super(imageFileName,corner,new Vertex(0,0));
	}

	public void setImage(I img){
		super.img = img;
	}

	public Vertex getFacing() {
		return facing;
	}

	public void setFacing(Vertex facing) {
		this.facing = facing;
	}


}
