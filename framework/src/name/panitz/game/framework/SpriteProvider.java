package name.panitz.game.framework;

import java.util.List;

public class SpriteProvider<I> extends ImageObject<I> {

    String tag;
    List<String> tagList;
    boolean isAnimated;

    public SpriteProvider(String imageFileName) {
        super(imageFileName);
    }
    public SpriteProvider(I img) {
        super(img);
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

    public String getTag() {
        return tag;
    }

    public void setTag(String Tag) {
        tag = Tag;
    }
    public void setTagList(List<String> TagList){
        tagList = TagList;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public I getImage() {
        return super.img;
    }

    public void setCollisionBound(Rect set){
        super.setCollisionBounds(set);
    }

}
