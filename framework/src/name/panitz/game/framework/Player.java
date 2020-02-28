package name.panitz.game.framework;

public class Player<I> extends ImageObject<I> {
	Vertex facing;
	Rect colR;
	double health;
	double maxHealth;
	int level;
	double layer;

	public Player(String imageFileName, Vertex corner) {
		super(imageFileName, corner, new Vertex(0, 0));
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

}
