package de.lmaos._code.lora;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import name.panitz.game.framework.*;

import java.awt.*;
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
		MapObject<I> map = new MapObject<>("C:\\Users\\Manuel\\Google Drive\\Subato Game (Name Pending)\\Lora\\src\\res\\maps\\map1\\map1.png"
			, new Vertex(0,0),new Vertex(0,0));

		List<MapObject<I>> mapObjects = new ArrayList<>();
		mapObjects.add(map);
		getGOss().add(mapObjects);

	}

	@Override
	public void doChecks() {
/*		ControllerState state = controllerManager.getState(0);
		emulateKeyPress(state);*/
	}

	private void emulateKeyPress(ControllerState state) {
		System.out.println(state);
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
