package name.panitz.game.framework.fx;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Transform;
import name.panitz.game.framework.GraphicsTool;
import name.panitz.game.framework.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FXContextTool implements GraphicsTool<Image> {
    GraphicsContext gc;
    List<Sprite> spriteList;
    Boolean spritesLoaded = false;

    public FXContextTool(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void drawImage(Image img, double x, double y) {
        gc.drawImage(img, x, y);
    }

    @Override
    public void drawRect(double x, double y, double w, double h) {
        gc.setLineWidth(1);
        gc.strokeRect(x, y, w, h);
    }

    @Override
    public void fillRect(double x, double y, double w, double h) {
        gc.fillRect(x, y, w, h);
    }

    @Override
    public void drawOval(double x, double y, double w, double h) {
        gc.setLineWidth(2);
        gc.strokeOval(x, y, w, h);
    }

    @Override
    public void fillOval(double x, double y, double w, double h) {
        gc.fillOval(x, y, w, h);
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        gc.strokeLine(x1, y1, x2, y2);
    }


    @Override
    public void drawString(double x, double y, int fontSize, String fontName, String text) {
        gc.setFont(new Font(fontName, fontSize));
        gc.fillText(text, x, y);
    }

    @Override
    public Image generateImage(String name, GameObject<Image> go, int scaleFactor) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(name), 16 * scaleFactor,
                16 * scaleFactor, true, true);
        go.setWidth(image.getWidth());
        go.setHeight(image.getHeight());
        return image;
    }

    @Override
    public Image generateMap(String URL, GameObject<Image> go) {
        try {
            File resFolder = new File(URL);
            File[] listOfFiles = resFolder.listFiles();
            List<File> fileArray = new ArrayList<>();
            BufferedImage buff = ImageIO.read(new File(String.valueOf(listOfFiles[0])));
            Canvas c = new Canvas();
            GraphicsContext gc = c.getGraphicsContext2D();
            c.setWidth(buff.getWidth() * 64);//TBD
            c.setHeight(buff.getHeight() * 64);
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].toString().contains("layer"))
                    fileArray.add(new File(listOfFiles[i].toString()));
            }

            for (int i = 0; i < fileArray.size(); i++) {
                buff = ImageIO.read(fileArray.get(i));
                for (int y = 0; y < buff.getHeight(); y++) {
                    for (int x = 0; x < buff.getWidth(); x++) {
                        Color tmpC = new Color(buff.getRGB(x, y));
                        gc.drawImage(getImage(tmpC), x * 64, y * 64);
                    }
                }
            }
            Image tmp = pixelScaleAwareCanvasSnapshot(c, 1.0);
            go.setWidth(tmp.getWidth());
            go.setHeight(tmp.getHeight());
            return tmp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadSprites(GraphicsTool<Image> g) {
        spritesLoaded = true;
        spriteList = new ArrayList<>();
        File resFolder = new File("src/res/sprites");
        File[] listOfFiles = resFolder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            spriteList.add(new Sprite(listOfFiles[i].toString().substring(4),
                    listOfFiles[i].toString().substring(16).substring(0, listOfFiles[i].toString().substring(16).length() - 4)
                    , g));
        }

    }

    private Image getSprite(List<Sprite> list, String tag) {
        if (spritesLoaded) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTag().equals(tag))
                    return list.get(i).getImage();
            }
        }
        return null;
    }

    private Image getImage(Color c) {
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

    public static WritableImage pixelScaleAwareCanvasSnapshot(Canvas canvas, double pixelScale) {
        WritableImage writableImage = new WritableImage((int) Math.rint(pixelScale * canvas.getWidth()), (int) Math.rint(pixelScale * canvas.getHeight()));
        SnapshotParameters spa = new SnapshotParameters();
        spa.setTransform(Transform.scale(pixelScale, pixelScale));
        return canvas.snapshot(spa, writableImage);
    }

    @Override
    public void setColor(double r, double g, double b) {
        gc.setFill(new javafx.scene.paint.Color(r, g, b, 1));
        gc.setStroke(new javafx.scene.paint.Color(r, g, b, 1));
    }
}
