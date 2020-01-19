package name.panitz.game.framework;

public class ImageObject<I> extends AbstractGameObject<I>{

	String imageFileName;
	protected I img;
	protected boolean changed = true;
	public int ImageScaleFactor = 4;

	public ImageObject(String imageFileName) {
		super(0,0);
		this.imageFileName = imageFileName;
	}

	public ImageObject(String imageFileName, Vertex pos, Vertex motion) {
		super(0, 0, pos, motion);
		this.imageFileName = imageFileName;
	}
	public ImageObject(String imageFileName, Vertex position) {
		super(0, 0, position);
		this.imageFileName = imageFileName;
	}

	public ImageObject(String imageFileName, double width) {
		super(width);
		this.imageFileName = imageFileName;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
		changed  = true;
	}
	public void initializeImage(GraphicsTool<I> g){
		img = g.generateImage(imageFileName,this, ImageScaleFactor);
	}

	@Override
	public void paintTo(GraphicsTool<I> g) {
		if (changed) {
			img = g.generateImage(imageFileName,this, ImageScaleFactor);
			changed = false;
		}
		if (null!=img) g.drawImage(img, getPos().x, getPos().y);
	}

	public void animate(I image){
		img = image;
	}
}

