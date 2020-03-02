package name.panitz.game.framework.fx;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.transform.Transform;
import name.panitz.game.framework.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public Image generateImage(String name, GameObject<Image> go, int scaleFactor) {//Stock Image Generator
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(name), 16 * scaleFactor,
				16 * scaleFactor, true, true);
		go.setWidth(image.getWidth());
		go.setHeight(image.getHeight());
		return image;
	}

	@Override
	public Image generateImage(String name, GameObject<Image> go, int scaleFactor, Rect imgRect) {
		Image fullImage = new Image(getClass().getClassLoader().getResourceAsStream(name));
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
		return SwingFXUtils.toFXImage(scaleOp.filter(before, after),null);
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

			spriteList.add(new Sprite("res/sprites/"+tag+".png",g,curTGrid,curCGrid,curIGrid));
		}
	}

	int tripleMin(int one, int two, int three){
		int min = one ;
		if(two < min) min = two;
		if(three < min) min = three;
		return min;
	}
	private Image getSprite(List<Sprite> list, String tag) {
		if (spritesLoaded) {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.get(i).getAnimationFrames().size() ; j++) {
					if(list.get(i).getTagList().get(j).equals(tag)) return list.get(i).getAnimationFrames().get(j);
				}
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
	//Proudly sponsored By StackOverflow
	public static WritableImage pixelScaleAwareCanvasSnapshot(Canvas canvas, double pixelScale) {
		WritableImage writableImage = new WritableImage((int) Math.rint(pixelScale * canvas.getWidth()),
				(int) Math.rint(pixelScale * canvas.getHeight()));
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
