package name.panitz.game.framework.fx;

import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.Rect;
import name.panitz.game.framework.SpriteProvider;
import name.panitz.game.framework.Vertex;
import javafx.scene.image.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Sprite extends SpriteProvider<Image> {


    public Sprite(String imageFileName, String tag, GraphicsTool<Image> g, Rect CollisionR, Rect ImgR) { //legacy Sprite constructor
        super(imageFileName);
        super.setTag(tag);
        super.initializeSubImage(g, ImgR);
//        super.setCollisionBound(CollisionR);
    }

    public Sprite(String imageFileName,  GraphicsTool<Image> g, List<String> tag, List<Rect> CollisionR, List<Rect> ImgR) { //AnimationSpriteConstructor
        super(imageFileName);
        super.setTagList(tag);
        super.initializeAnimatedSprite(g, ImgR);
        super.setAnimationCollisionBound(CollisionR);
    }

    public Sprite(String imageFileName, String tag, GraphicsTool<Image> g) {
        super(imageFileName);
        super.setTag(tag);
        super.initializeImage(g);
        //TODO Some List creation for std Collision
        //super.setCollisionBound(new Rect(new Vertex(0,0),new Vertex(64,64)));
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

    public List<Image> getAnimationFrames(){
        return super.animationFrames;
    }

}
