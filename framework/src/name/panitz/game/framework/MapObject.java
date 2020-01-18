package name.panitz.game.framework;

public class MapObject<I> extends ImageObject<I> {
	public MapObject(String imageFileName) {
		super(imageFileName);
	}

	public MapObject(String imageFileName, Vertex pos, Vertex motion) {
		super(imageFileName, pos, motion);
	}

	public MapObject(String imageFileName, Vertex position) {
		super(imageFileName, position);
	}

	public MapObject(String imageFileName, double width) {
		super(imageFileName, width);
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		if(changed){
			img = g.generateMap(super.imageFileName,this);
			changed = false;
		}
		if(null != img) g.drawImage(img, getPos().x,getPos().y);
	}
}
