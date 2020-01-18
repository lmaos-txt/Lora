package de.lmaos._code.lora;

import name.panitz.game.framework.*;


public class Player<I> extends ImageObject<I> {

	public Player(String imageFileName, Vertex corner) {
		super(imageFileName,corner,new Vertex(0,0));
	}

}
