package name.panitz.game.framework;

public class Player<I> extends SpriteProvider<I> {
	Vertex facing;
	Rect colR;
	double health;
	double maxHealth;
	int level;
	double layer;
	boolean moving;

	public Player(String imageFileName, Vertex corner) {
		super(imageFileName, corner, new Vertex(0, 0));
	}
	public void initPlayer(GraphicsTool<I> g){
		super.setOldDirection(new Vertex(0,1));
		super.initializeAnimatedSprite(g,RectVal.getImages("playerGrid"));
		super.setAnimationSpeed(RectVal.getAnimationSpeed(1));
	}

	public void setImage(I img) {
		super.img = img;
	}

	public Vertex getFacing() {
		return facing;
	}

	public void setFacing(Vertex facing) {
		this.facing = facing;
	}

	public Rect getColR() {
		return colR;
	}

	public double getHealth() {
		return health;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public int getLevel() {
		return level;
	}

	public double getLayer() {
		return layer;
	}

	public void setLayer(double layer) {
		this.layer = layer;
	}

	public void setColR(Rect colR) {
		this.colR = colR;
	}

	public void setMoving(boolean in){
		moving = in;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		if(moving && null!= facing)
		super.makeAnimation(facing);
		if(null != img) g.drawImage(animationFrames.get(currentFrameCount+animationLine),getPos().x,getPos().y);
		if(null!=super.tag)setColR(RectVal.getCollision(super.tag).get(currentFrameCount+animationLine));
	}
}
