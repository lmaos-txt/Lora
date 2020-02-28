package name.panitz.game.framework;

import java.util.ArrayList;
import java.util.List;

public class MapObject<I> extends ImageObject<I> {

    int mapWidth;
    int mapHeight;

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
            calcHeight();
            calcWidth();
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

    @Override
    public double getHeight() {
        return mapHeight;
    }

    @Override
    public double getWidth() {
        return mapWidth;
    }

    public void calcHeight() {
        int maxHeight = 0;
        for (int i = layerMap.size() - 1; i > 0 ; i--) {
            if(layerMap.get(i).getY_pos()> maxHeight) maxHeight = layerMap.get(i).getY_pos();
        }
        mapHeight =  maxHeight + 64;
        System.out.println(mapHeight);
    }

    public void calcWidth() {
        int maxWidth = 0;
        for (int i = layerMap.size() - 1; i > 0 ; i--) {
            if(layerMap.get(i).getX_pos() > maxWidth) maxWidth = layerMap.get(i).getY_pos();
        }
        mapWidth =  maxWidth + 64;
        System.out.println(mapWidth);
    }
}
