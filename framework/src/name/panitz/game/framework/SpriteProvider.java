package name.panitz.game.framework;

public class SpriteProvider<I> extends ImageObject<I> {
	String tag;

	public SpriteProvider(String imageFileName) {
		super(imageFileName);
	}

	public SpriteProvider(String imageFileName, Vertex pos, Vertex motion) {
		super(imageFileName, pos, motion);
	}

	public SpriteProvider(String imageFileName, Vertex position) {
		super(imageFileName, position);
	}

	public SpriteProvider(String imageFileName, double width) {
		super(imageFileName, width);
	}

	public void setTag(String Tag) {
		tag = Tag;
	}

	public String getTag() {
		return tag;
	}
	public I getImage(){
		return super.img;
	}
}
