package name.panitz.game.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapObject<I> extends ImageObject<I> {

    private List<MapEntity> layerMap = new ArrayList<>();

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

    public List<MapEntity> getLayerMap() {
        return layerMap;
    }
    public MapEntity getEntityAt(Vertex Position){
        MapEntity tmp = layerMap.get(0);
        for (int i = 0; i < layerMap.size(); i++) {
            if(Position.x - (Position.x % 64) == layerMap.get(i).getX_pos() &&
                    Position.y - (Position.y % 64) == layerMap.get(i).getY_pos() &&
                    tmp.layer < layerMap.get(i).layer) tmp = layerMap.get(i);
        }
        return tmp;
    }
}
