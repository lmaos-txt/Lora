package name.panitz.game.framework;

public class MapEntity {
	int x_pos;
	int y_pos;
	double layer;
	int spriteID;
	Rect collisionRect;

	public MapEntity(int x_cord, int y_cord, double layerNum, int spriteIDNum, Rect colR){
		x_pos = x_cord;
		y_pos = y_cord;
		layer = layerNum;
		spriteID = spriteIDNum;
		collisionRect = colR;
	}

	public int getX_pos() {
		return x_pos;
	}

	public int getY_pos() {
		return y_pos;
	}

	public double getLayer() {
		return layer;
	}

	public int getSpriteID() {
		return spriteID;
	}

	public Rect getCollisionRect() {
		return collisionRect;
	}

	@Override
	public String toString() {
		return "MapEntity{" +
				"\nx_pos = " + x_pos +
				"\ny_pos = " + y_pos +
				"\nlayer = " + layer +
				"\nspriteID = " + spriteID +
				"\ncollisionRect = " + collisionRect +
				'}';
	}
}
