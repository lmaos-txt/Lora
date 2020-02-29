package name.panitz.game.framework;


public class Enemy<I> extends SpriteProvider<I> {
	int enemyType;// 0 = Blob;
	double health;
	double dmg;
	double armour;

//	public Enemy(int EnemyType, Vertex Pos){
//		super(EnemyVal);
//	}

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

	@Override
	public void paintTo(GraphicsTool<I> g) {
		if(changed)
			img = g.generateImage(imageFileName,this, ImageScaleFactor);
		if(null != img) g.drawImage(img,getPos().x,getPos().y);
	}

}
