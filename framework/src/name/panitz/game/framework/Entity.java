package name.panitz.game.framework;

public class Entity<I> extends ImageObject<I> {

	public Entity(String imageFileName) {
		super(imageFileName);
	}

	public Entity(String imageFileName, Vertex pos, Vertex motion) {
		super(imageFileName, pos, motion);
	}
}