package de.lmaos._code.lora;


import name.panitz.game.framework.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lora<I,S> extends AbstractGame<I, S> {

	Player<I> lora;
	MapObject<I> map;


	public Lora() {
		super(new Player<>("res/sprites/lora standing front.png", new Vertex(0,0)),
				640,640);
		lora = (Player<I>) getPlayer();
		map = new MapObject<>("src\\res\\maps\\map4", new Vertex(0,0),
				new Vertex(0,0));
		List<MapObject<I>> mapObjects = new ArrayList<>();
		mapObjects.add(map);
		getGOss().add(mapObjects);
	}

	@Override
	public void doChecks() {
		getPlayer().move();
		PlayerColCheck();
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
					if(getPlayer().getVelocity().y <=0){
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x,getPlayer().getVelocity().y + 3));
					}
					break;
				case VK_A:
					if(getPlayer().getVelocity().x <=0){
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x + 3,getPlayer().getVelocity().y));
						System.out.println("A released");
					}
					break;
				case VK_S:
					if(getPlayer().getVelocity().y >=0){
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x,getPlayer().getVelocity().y - 3));
					}
					break;

				case VK_D:
					if(getPlayer().getVelocity().x >=0){
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x - 3,getPlayer().getVelocity().y));
					}
					break;
				case VK_E:
					System.out.println("Lora's Position: "+lora.getPos());
					System.out.println("Lora's Modulo Position: ("+(lora.getPos().x- (lora.getPos().x%64))+"."+(lora.getPos().y- (lora.getPos().y %64))+")");
					System.out.println("Lora's Movement: "+lora.getVelocity());
					System.out.println("Lora's Layer: " + lora.getLayer());
					System.out.println(lora.getColR());
					System.out.println(map.getEntityAt(lora.getPos())+"\n----------------------");
					lora.setVelocity(new Vertex(0,0));
					break;
				case VK_R:
					lora.setPos(new Vertex(0,0));
					break;
				case LEFT_ARROW:
					lora.setPos(new Vertex(lora.getPos().x-1,lora.getPos().y));
			}
		}
	}

	@Override
	public void setupPlayer() {
		lora.setLayer(map.getEntityAt(lora.getPos()).getLayer() + 1);
		lora.setColR(new Rect(new Vertex(8,4),new Vertex(32,33)));
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

	private void PlayerColCheck() {
		for (int i = 0; i < map.getLayerMap().size(); i++) {
			if(map.getLayerMap().get(i).getLayer() < lora.getLayer())
				continue;
			if(map.getLayerMap().get(i).getLayer() >= lora.getLayer()){
				if( map.getLayerMap().get(i).getX_pos() > lora.getPos().x - 192 &&
						map.getLayerMap().get(i).getX_pos() < lora.getPos().x + 192 &&
						map.getLayerMap().get(i).getY_pos() < lora.getPos().y + 192 &&
						map.getLayerMap().get(i).getY_pos() > lora.getPos().y - 192){
					//could collide
					if(lora.getColR().touches(map.getLayerMap().get(i).getCollisionRect(),(int)lora.getPos().x,(int)lora.getPos().y,map.getLayerMap().get(i).getX_pos(),map.getLayerMap().get(i).getY_pos())){
						System.out.println("Lora Touches:"+ map.getLayerMap().get(i)+"\n and is at: " + lora.getPos() + "\n"+lora.getColR()+"-----------------\n");
						if(lora.getColR().isAbove(map.getLayerMap().get(i).getCollisionRect(),(int)lora.getPos().y,map.getLayerMap().get(i).getY_pos())){
							System.out.println("__________________________Above_________________________");
						}
					}
//					System.out.println("Position: " + map.getLayerMap().get(i).getX_pos() +"\n  "+ (lora.getPos().x - 195));
//					lora.setPos(new Vertex(0,0));
//					System.out.println("Lora At" + lora.getPos());
				}

			}

		}
	}
}
