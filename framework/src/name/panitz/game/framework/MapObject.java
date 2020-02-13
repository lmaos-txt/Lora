package name.panitz.game.framework;

import java.util.ArrayList;
import java.util.List;

public class MapObject<I> extends ImageObject<I> {

    List<MapEntity> layerMap = new ArrayList<>();

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
        if (changed) {
            img = g.generateMap(super.imageFileName, this, layerMap);
            changed = false;
        }
        if (null != img) g.drawImage(img, getPos().x, getPos().y);
    }
}
