package name.panitz.game.framework;

import java.util.ArrayList;
import java.util.List;

public class ImageObject<I> extends AbstractGameObject<I> {

    public int ImageScaleFactor = 4;
    protected I img;
    protected boolean changed = true;
    String imageFileName;
//    Rect collisionBounds;
    int animationSpeed;
    public List<I> animationFrames;
    List<Rect> collisionBoundRects;
    int currentFrameCount;
    int skipCounter;
    boolean isLinearAnimation;
    Vertex oldDirection;
    int animationLine;

    public ImageObject(String imageFileName) {
        super(0, 0);
        this.imageFileName = imageFileName;
    }
    public ImageObject(I imgIN){
        super(0,0);
        img = imgIN;
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

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public boolean getAnimationType() {
        return isLinearAnimation;
    }

    public void setAnimationType(boolean linearAnimation) {
        isLinearAnimation = linearAnimation;
    }
    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
        changed = true;
    }

    public void setOldDirection(Vertex oldDirection) {
        this.oldDirection = oldDirection;
    }

    public void initializeImage(GraphicsTool<I> g) {
        img = g.generateImage(imageFileName, this, ImageScaleFactor);
    }
    public void initializeSubImage(GraphicsTool<I> g, Rect ImgR) {
        img = g.generateImage(imageFileName, this, ImageScaleFactor, ImgR);
    }

    public void initializeAnimatedSprite(GraphicsTool<I> g, List<Rect> ImgR) {
        img = g.generateImage(imageFileName, this, ImageScaleFactor);
        animationFrames = new ArrayList<>();
        for (int i = 0; i < ImgR.size(); i++) {
            animationFrames.add(makeSprite(g,ImgR.get(i)));
        }
    }

    private I makeSprite(GraphicsTool<I> g, Rect ImageR){
        return g.generateImage(imageFileName,this,ImageScaleFactor,ImageR);
    }

    public void setAnimationCollisionBound(List<Rect> inR){
        collisionBoundRects = inR;
    }

    @Override
    public void paintTo(GraphicsTool<I> g) {
        if (changed) {
            img = g.generateImage(imageFileName, this, ImageScaleFactor);
            changed = false;
        }
        if (null != img) g.drawImage(img, getPos().x, getPos().y);
    }
//    void setCollisionBounds(Rect toSet){
//        collisionBounds = toSet;
//    }

    public void makeAnimation(Vertex direction){
        //TODO Override On lower Level
        if(isLinearAnimation){
            if(skipCounter >= animationSpeed){
                img = animationFrames.get(currentFrameCount);
                currentFrameCount++;
                if(currentFrameCount >= animationFrames.size()) currentFrameCount = 0;
                skipCounter = 0;
            }
            skipCounter ++;
        }else{
            //TODO NON LINEAR ANIMATIONS
            if(null == oldDirection) oldDirection = direction;
//            if()
            if(oldDirection == direction){
                if(skipCounter >= animationSpeed){
                    img = animationFrames.get(currentFrameCount+RectVal.getEntityAnimationsLines(direction));
                    currentFrameCount++;
                    if(currentFrameCount >= animationFrames.size()) currentFrameCount = 0;
                    skipCounter = 0;
                }
                skipCounter ++;
            }else{
                animationLine = RectVal.getEntityAnimationsLines(direction);
                img = animationFrames.get(animationLine);
            }
        }
        changed = true;
    }

}

