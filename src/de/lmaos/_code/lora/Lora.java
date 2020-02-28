package de.lmaos._code.lora;


import name.panitz.game.framework.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lora<I,S> extends AbstractGame<I, S> {

	Player<I> lora;
	MapObject<I> map;
	double viewportX;
	double viewportY;
	double oldOffsetX;
	double oldOffsetY;

	public Lora() {
		super(new Player<>("res/sprites/lora standing front.png", new Vertex(200,100)),
				600,600);
		lora = (Player<I>) getPlayer();
		map = new MapObject<>("src\\res\\maps\\map1", new Vertex(0,0),
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
	public double moveField(boolean xOrY) {
		double offsetMaxX = map.getWidth() - viewportX;
		double offsetMaxY = map.getHeight() - viewportY;
		double camX = lora.getPos().x + ((lora.getColR().getP1().x + lora.getColR().getP2().x) /2) - viewportX / 2;
		double camY = lora.getPos().y + ((lora.getColR().getP1().y + lora.getColR().getP2().y) /2) - viewportY / 2;
		double moveX = 0;
		double moveY = 0;

		moveX = oldOffsetX - camX;
		moveY = oldOffsetY - camY;

		if(viewportX > map.getWidth()) moveX = 0;
		if(viewportY > map.getHeight()) moveY = 0;

		if(camX < 0 && (Math.signum(camX) == -1)){
			moveX = 0;
		}
		if(camX > offsetMaxX && (Math.signum(camX) == 1)){
			moveX = 0;
		}
		if(camY < 0 && (Math.signum(camY) == -1)){
			moveY = 0;
		}
		if(camY > offsetMaxY && (Math.signum(camY) == 1)){
			moveY = 0;
		}

		oldOffsetX = camX;
		if(!xOrY)
		oldOffsetY = camY;

		return xOrY ? moveX : moveY ;
	}

	public void updateScreenSize(double width,double height){
		viewportX = width;
		viewportY = height;
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
					if(getPlayer().getVelocity().y <0){
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x,getPlayer().getVelocity().y + 3));
					}
					break;
				case VK_A:
					if(getPlayer().getVelocity().x <0){
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x + 3,getPlayer().getVelocity().y));
					}
					break;
				case VK_S:
					if(getPlayer().getVelocity().y >0){
						getPlayer().setVelocity(new Vertex(getPlayer().getVelocity().x,getPlayer().getVelocity().y - 3));
					}
					break;

				case VK_D:
					if(getPlayer().getVelocity().x >0){
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
					break;
				case RIGHT_ARROW:
					lora.setPos(new Vertex(lora.getPos().x+1,lora.getPos().y));
					break;
				case UP_ARROW:
					lora.setPos(new Vertex(lora.getPos().x,lora.getPos().y-1));
					break;
				case DOWN_ARROW:
					lora.setPos(new Vertex(lora.getPos().x,lora.getPos().y+1));
					break;
			}
		}
	}

	@Override
	public void setupPlayer() {
		lora.setLayer(map.getEntityAt(lora.getPos()).getLayer() + 1);
		lora.setColR(new Rect(new Vertex(16,8),new Vertex(48,48)));
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
		if(lora.getPos().x + lora.getColR().getP2().x > map.getWidth() - 3){
			lora.setVelocity(new Vertex(0, lora.getVelocity().y));
			lora.setPos(new Vertex(lora.getPos().x-3,lora.getPos().y));
		}
		if(lora.getPos().x + lora.getColR().getP1().x < 0){
			lora.setVelocity(new Vertex(0, lora.getVelocity().y));
			lora.setPos(new Vertex(lora.getPos().x+3,lora.getPos().y));
		}
		if(lora.getPos().y + lora.getColR().getP1().y < 0){
			lora.setVelocity(new Vertex(lora.getVelocity().x, 0));
			lora.setPos(new Vertex(lora.getPos().x,lora.getPos().y+3));
		}
		if(lora.getPos().y + lora.getColR().getP2().y > map.getHeight() - 3){
			lora.setVelocity(new Vertex(lora.getVelocity().x, 0));
			lora.setPos(new Vertex(lora.getPos().x,lora.getPos().y-3));
		}
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
						if(lora.getPos().y < map.getLayerMap().get(i).getY_pos() && lora.getVelocity().y > 0){
							lora.setVelocity(new Vertex(lora.getVelocity().x, 0));
							lora.setPos(new Vertex(lora.getPos().x,lora.getPos().y-3));
						}
						if(lora.getPos().y > map.getLayerMap().get(i).getY_pos() && lora.getVelocity().y < 0){
							lora.setVelocity(new Vertex(lora.getVelocity().x, 0));
							lora.setPos(new Vertex(lora.getPos().x,lora.getPos().y+3));
						}
						if(lora.getPos().x < map.getLayerMap().get(i).getX_pos() && lora.getVelocity().x > 0){
							lora.setVelocity(new Vertex(0, lora.getVelocity().y));
							lora.setPos(new Vertex(lora.getPos().x-3,lora.getPos().y));
						}
						if(lora.getPos().x > map.getLayerMap().get(i).getX_pos() && lora.getVelocity().x < 0){
							lora.setVelocity(new Vertex(0, lora.getVelocity().y));
							lora.setPos(new Vertex(lora.getPos().x+3,lora.getPos().y));
						}

					}
				}
			}
		}
	}
}
