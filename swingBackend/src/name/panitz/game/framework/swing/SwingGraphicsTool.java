package name.panitz.game.framework.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Transform;
import name.panitz.game.framework.*;


public class SwingGraphicsTool implements GraphicsTool<Image> {
    Graphics g;
    List<Sprite> spriteList;
    Boolean spritesLoaded = false;

    public SwingGraphicsTool(Graphics g) {
        this.g = g;
    }

    @Override
    public void drawImage(Image img, double x, double y) {
        g.drawImage(img, (int) x, (int) y, null);
    }

    @Override
    public void drawRect(double x, double y, double w, double h) {
        g.drawRect((int) x, (int) y, (int) w, (int) h);
    }

    @Override
    public void fillRect(double x, double y, double w, double h) {
        g.fillRect((int) x, (int) y, (int) w, (int) h);
    }

    @Override
    public void drawOval(double x, double y, double w, double h) {
        g.drawOval((int) x, (int) y, (int) w, (int) h);
    }

    @Override
    public void fillOval(double x, double y, double w, double h) {
        g.fillOval((int) x, (int) y, (int) w, (int) h);
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    @Override
    public void drawString(double x, double y, int fontSize, String fontName, String text) {
        g.setFont(new Font(fontName, Font.PLAIN, fontSize));
        g.drawString(text, (int) x, (int) y);
    }

    @Override
    public void loadSprites(GraphicsTool<Image> gt) {
        //TODO load Sprites for Swing
        spritesLoaded = true;
        spriteList = new ArrayList<Sprite>();
        File resFolder = new File("src/res/sprites");
        File[] listOfFiles = resFolder.listFiles();
        List<File> fileArray = new ArrayList<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if(listOfFiles[i].toString().contains("Grid")&&listOfFiles[i].toString().contains(".png")){
                fileArray.add(listOfFiles[i]);
            }
        }
        for (int i = 0; i < fileArray.size(); i++) {
            String tag = fileArray.get(i).toString().substring(16).substring(0,
                    fileArray.get(i).toString().substring(16).length() - 4);
            List<Rect> curIGrid = RectVal.getImages(tag);
            List<Rect> curCGrid = RectVal.getCollision(tag);
            List<String> curTGrid = RectVal.getText(tag);

            spriteList.add(new Sprite("res/sprites/" + tag + ".png",  g, curTGrid, curCGrid, curIGrid));
            }
        }

    @Override
    public Image generateImage(String name, GameObject<Image> go, int scaleFactor) {
        ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource(name));
        go.setWidth(image.getIconWidth());
        go.setHeight(image.getIconHeight());
        return image.getImage();
    }

    @Override
    public Image generateImage(String name, GameObject<Image> go, int ScaleFactor, Rect imgRect) {
        javafx.scene.image.Image fullImage = new javafx.scene.image.Image(getClass().getClassLoader().getResourceAsStream(name));
        PixelReader pr = fullImage.getPixelReader();
        WritableImage wImg = new WritableImage((int) imgRect.getWidth(),(int) imgRect.getHeight());
        PixelWriter pw = wImg.getPixelWriter();
        for (int y = 0; y < wImg.getHeight(); y++) {
            for (int x = 0; x < wImg.getWidth(); x++) {
                pw.setColor(x, y, pr.getColor((int) imgRect.getP1().x + x,(int) imgRect.getP1().y + y ));
            }
        }
        //Scaling
        BufferedImage before = SwingFXUtils.fromFXImage(wImg,null);
        int w = before.getWidth();
        int h = before.getHeight();
        BufferedImage after = new BufferedImage(w*2, h*2, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(2.0, 2.0);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return scaleOp.filter(before, after);
    }

    @Override
    public Image generateMap(String URL, GameObject<Image> go, List<MapEntity> layerMap) {
        try {
            File resFolder = new File(URL);
            File[] listOfFiles = resFolder.listFiles();
            List<File> fileArray = new ArrayList<>();
            BufferedImage buff = ImageIO.read(new File(String.valueOf(listOfFiles[0])));
            Canvas c = new Canvas();
            GraphicsContext gc = c.getGraphicsContext2D();
            c.setWidth(buff.getWidth() * 64);//TBD
            c.setHeight(buff.getHeight() * 64);
            Pattern ptrn = Pattern.compile("(?<=layer)\\d\\.\\d|(?<=layer)\\d");
            Matcher match;
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].toString().contains("layer"))
                    fileArray.add(new File(listOfFiles[i].toString()));
            }

            for (int i = 0; i < fileArray.size(); i++) {
                buff = ImageIO.read(fileArray.get(i));
                for (int y = 0; y < buff.getHeight(); y++) {
                    for (int x = 0; x < buff.getWidth(); x++) {
                        Color tmpC = new Color(buff.getRGB(x, y)); // Farbe in Image holen um zu entscheiden welcher Sprite gesetzt werden muss
                        gc.drawImage(getImage(tmpC), x * 64, y * 64);
                        match = ptrn.matcher(fileArray.get(i).toString());
                        if(match.find()&& ! tmpC.equals(new Color(255,255,255))) {
                            layerMap.add(new MapEntity(x * 64, y * 64, Double.parseDouble(match.group(0)),
                                    RectVal.getSpriteID(tmpC), getImageColR(tmpC)));
                        }
                    }
                }
            }
            javafx.scene.image.Image tmp = pixelScaleAwareCanvasSnapshot(c, 1.0);
            go.setWidth(tmp.getWidth());
            go.setHeight(tmp.getHeight());
            return SwingFXUtils.fromFXImage(tmp,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setColor(double r, double gr, double b) {
        g.setColor(new Color((float) r, (float) gr, (float) b));
    }

    private javafx.scene.image.Image getImage(Color c) {
        if (c.equals(new Color(0, 255, 0)))
            return getSprite(spriteList, "grass");
        if (c.equals(new Color(0, 0, 255)))
            return getSprite(spriteList, "water");
        if (c.equals(new Color(255, 0, 255)))
            return getSprite(spriteList, "stone");
        if (c.equals(new Color(255, 0, 0)))
            return getSprite(spriteList, "path");
        if (c.equals(new Color(46, 46, 46)))
            return getSprite(spriteList, "cauldron");
        if (c.equals(new Color(255, 255, 255)))
            return null;
        return null;
    }
    private javafx.scene.image.Image getSprite(List<Sprite> list, String tag) {
        if (spritesLoaded) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).getAnimationFrames().size() ; j++) {
                    if(list.get(i).getTagList().get(j).equals(tag)) return list.get(i).getAnimationFrames().get(j);
                }
            }
        }
        return null;
    }

    public static WritableImage pixelScaleAwareCanvasSnapshot(Canvas canvas, double pixelScale) {
        WritableImage writableImage = new WritableImage((int) Math.rint(pixelScale * canvas.getWidth()),
                (int) Math.rint(pixelScale * canvas.getHeight()));
        SnapshotParameters spa = new SnapshotParameters();
        spa.setTransform(Transform.scale(pixelScale, pixelScale));
        return canvas.snapshot(spa, writableImage);
    }
    private Rect getImageColR(Color c) {
        String tag = "";
        if (c.equals(new Color(0, 255, 0)))
            tag = "grass";
        if (c.equals(new Color(0, 0, 255)))
            tag=  "water";
        if (c.equals(new Color(255, 0, 255)))
            tag = "stone";
        if (c.equals(new Color(255, 0, 0)))
            tag = "path";
        if (c.equals(new Color(46, 46, 46)))
            tag = "cauldron";
        if (c.equals(new Color(255, 255, 255)))
            return new Rect(new Vertex(0,0),new Vertex(0,0));

        List<String> tmpL= RectVal.getText("spriteGrid1");
        for (int i = 0; i < tmpL.size(); i++) {
            if(tag.equals(tmpL.get(i)))
                return RectVal.getCollision("spriteGrid1").get(i);
        }
        return new Rect(new Vertex(0,0),new Vertex(0,0));
    }

}


