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
}
