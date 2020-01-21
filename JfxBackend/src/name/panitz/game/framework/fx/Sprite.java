package name.panitz.game.framework.fx;

import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.SpriteProvider;
import name.panitz.game.framework.Vertex;
import javafx.scene.image.*;

public class Sprite extends SpriteProvider<Image> {
    public Sprite(String imageFileName, String tag, GraphicsTool<Image> g) {
        super(imageFileName);
        super.setTag(tag);
        super.initializeImage(g);
    }

    public Sprite(String imageFileName, Vertex pos, Vertex motion) {
        super(imageFileName, pos, motion);
    }

    public Sprite(String imageFileName, Vertex position) {
        super(imageFileName, position);
    }

    public Sprite(String imageFileName, double width) {
        super(imageFileName, width);
    }
}
