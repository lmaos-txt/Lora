package de.lmaos._code.lora;


import name.panitz.game.framework.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lora<I,S> extends AbstractGame<I, S> {

	Player<I> lora;

	public Lora() {
		super(new Player<>("res/sprites/lora standing front.png", new Vertex(0,0)),
			400,400);

		lora = (Player<I>) getPlayer();
		File resFolder = new File("src/res");
		List<ImageObject<I>> tmp = new ArrayList<>();
		getGOss().add(tmp);
		MapObject<I> map = new MapObject<>("src\\res\\maps\\map1\\map1.png"
			, new Vertex(0,0),new Vertex(0,0));

		List<MapObject<I>> mapObjects = new ArrayList<>();
		mapObjects.add(map);
		getGOss().add(mapObjects);

	}

	@Override
	public void doChecks() {
		getPlayer().move();
	}

	@Override
	public void keyPressedReaction(KeyCode keyCode) {
		if(null != keyCode){
			switch (keyCode){
				case VK_W:
					if(getPlayer().getVelocity().y != -3)
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x,getPlayer().getVelocity().y - 3));
					break;
				case VK_A:
					if(getPlayer().getVelocity().x != -3)
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x - 3,getPlayer().getVelocity().y));
					break;
				case VK_S:
					if(getPlayer().getVelocity().y != 3)
					getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x,getPlayer().getVelocity().y +3));
					break;
				case VK_D:
					if(getPlayer().getVelocity().x != 3)
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x + 3,getPlayer().getVelocity().y));
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
}
