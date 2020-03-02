package name.panitz.game.framework;


import java.util.Objects;

public class Enemy<I> extends SpriteProvider<I> {
	int enemyType;// 0 = Blob;
	double health;
	double dmg;
	double armour;
	Vertex facing;
	boolean hasFacing;
	double layer;
	Rect colR;

	public Enemy(int EnemyType, Vertex Pos,GraphicsTool<I> g){
		super(RectVal.getEnemyTypeResources(EnemyType));
		String tag = RectVal.getEnemyTypeResources(EnemyType).substring(12).substring(0,
				RectVal.getEnemyTypeResources(EnemyType).substring(12).length() - 4);
		super.initializeAnimatedSprite(g, RectVal.getImages(tag));
		super.setTagList(RectVal.getText(tag));
		super.setAnimationCollisionBound(RectVal.getCollision(tag));
		super.setPos(Pos);
		super.setAnimationType(RectVal.getAnimationStatus(enemyType));
		super.setAnimationSpeed(RectVal.getAnimationSpeed(EnemyType));
		enemyType = EnemyType;
		double[] tmp = RectVal.getEnemyTypeStats(0);
		health = tmp[0];
		dmg = tmp[1];
		armour = tmp[2];
	}

	public Enemy(String imageFileName) {
		super(imageFileName);
	}

	public Enemy(String imageFileName, Vertex pos, Vertex motion) {
		super(imageFileName, pos, motion);
	}


	public Enemy(String imageFileName, Vertex position) {
		super(imageFileName, position);
	}

	public Enemy(String imageFileName, double width) {
		super(imageFileName, width);
	}


	public double getLayer() {
		return layer;
	}

	public void setLayer(double layer) {
		this.layer = layer;
	}

	public Rect getColR() {
		return colR;
	}

	public void setColR(Rect colR) {
		this.colR = colR;
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		if(hasFacing){
			super.makeAnimation(facing);
		}else{
			super.makeAnimation(null);
		}
		if(null!= img) g.drawImage(animationFrames.get(currentFrameCount), getPos().x,getPos().y);
		if(null!= img) setColR(collisionBoundRects.get(currentFrameCount));
	}
}
