package de.lmaos._code.lora;


import name.panitz.game.framework.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lora<I,S> extends AbstractGame<I, S> {

	Player<I> lora;

	public Lora() {
		super(new Player<>("res/sprites/lora standing front.png", new Vertex(0,0)),
				640,640);
		lora = (Player<I>) getPlayer();
		MapObject<I> map = new MapObject<>("src\\res\\maps\\map1", new Vertex(0,0),
				new Vertex(0,0));
		List<MapObject<I>> mapObjects = new ArrayList<>();
		mapObjects.add(map);
		getGOss().add(mapObjects);

	}

	@Override
	public void doChecks() {
		getPlayer().move();
		collisionCheck();
	}

	@Override
	public void keyPressedReaction(KeyCode keyCode) {
		if(null != keyCode){
			switch (keyCode){
				case VK_W:
					if(getPlayer().getVelocity().y != -3)
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x,getPlayer().getVelocity().y - 3));
					lora.setFacing(new Vertex(0,-1));
					break;
				case VK_A:
					if(getPlayer().getVelocity().x != -3)
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x - 3,getPlayer().getVelocity().y));
					lora.setFacing(new Vertex(-1,0));
					break;
				case VK_S:
					if(getPlayer().getVelocity().y != 3)
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x,getPlayer().getVelocity().y +3));
					lora.setFacing(new Vertex(0,1));
					break;
				case VK_D:
					if(getPlayer().getVelocity().x != 3)
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x + 3,getPlayer().getVelocity().y));
					lora.setFacing(new Vertex(0,-1));
					break;
			}
		}
	}

	@Override
	public void keyReleasedReaction(KeyCode keyCode) {
		if(null != keyCode){
			switch (keyCode){
				case VK_W:
					getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x,getPlayer().getVelocity().y + 3));
					break;
				case VK_A:
					getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x + 3,getPlayer().getVelocity().y));
					break;
				case VK_S:
					getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x,getPlayer().getVelocity().y - 3));
					break;
				case VK_D:
					getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x - 3,getPlayer().getVelocity().y));
					break;
			}
		}
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		super.paintTo(g);
	}


	@Override
	public void move() {

	}

	@Override
	public void playSounds(SoundTool<S> soundTool) {

	}

	private void collisionCheck() {
		/*gets array list of arrayLists*/getGOss();
	}
}
