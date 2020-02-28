package name.panitz.game.framework;

import java.util.List;

public class Enemy<I> extends ImageObject<I> {
	int enemyType;// 0 = Blob;
	double health;
	double dmg;
	double armour;
	List<Rect> imageRects;

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
}
