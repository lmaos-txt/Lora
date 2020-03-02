package name.panitz.game.framework;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RectVal {
	final static  int  scaleFactor = 2;
	public static List<Rect> getCollision(String tag) {
		if(tag.equals("spriteGrid1"))
			return spriteGrid1C;
		if(tag.equals("Player"))
			return playerGridC;
		if(tag.equals("blobEnemyGrid"))
			return blobGridC;
		return null;
	}
	public static List<Rect> getImages(String tag){
		if(tag.equals("spriteGrid1"))
			return  spriteGrid1I;
		if(tag.equals("playerGrid"))
			return  playerGridI;
		if(tag.equals("blobEnemyGrid"))
			return blobGridI;
		return null;
	}
	public static List<String> getText(String tag) {
		if(tag.equals("spriteGrid1"))
			return spriteGrid1T;
		if(tag.equals("Player"))
			return playerGridT;
		if(tag.equals("blobEnemyGrid"))
			return blobGridT;
		return null;
	}

	public static int getSpriteID(Color c) {
		if (c.equals(new Color(0, 255, 0)))
			return 0;
		if (c.equals(new Color(0, 0, 255)))
			return 1;
		if (c.equals(new Color(255, 0, 255)))
			return 2;
		if (c.equals(new Color(255, 0, 0)))
			return 3;
		if (c.equals(new Color(46, 46, 46)))
			return 4;
		if (c.equals(new Color(255, 255, 255)))
			return -1;
		return -1;
	}

	public static final List<Rect> spriteGrid1I = Collections.unmodifiableList( //TODO Image Sprite Grid
			new ArrayList<>() {{
				for (int y = 0; y < 8; y++) {
					for (int x = 0; x < 6; x++) {
						add(new Rect(new Vertex(x*32,0),new Vertex((x+1)*32,32)));
					}
				}
			}}
	);

	public static final List<Rect> spriteGrid1C = Collections.unmodifiableList(//TODO Collision Sprite Grid
			new ArrayList<>() {{
				for (int i = 0; i < 5; i++) {
					add(new Rect(new Vertex(0,0),new Vertex((32 * scaleFactor),32 * scaleFactor)));
				}
				add(new Rect(new Vertex(6 * scaleFactor,5 * scaleFactor), new Vertex(26 * scaleFactor,29 *scaleFactor)));
//				add(new Rect(new Vertex(0,0),new Vertex((32 * scaleFactor),32 * scaleFactor)));

			}}
	);

	public static final List<String> spriteGrid1T = Collections.unmodifiableList( //TODO Text Sprite Grid
			new ArrayList<>() {{
				add("path"); add("grass"); add("stone"); add("water"); add("mailbox"); add("cauldron"); // add(""); add("");
			}}
	);

	public static final List<Rect> playerGridI = Collections.unmodifiableList( //TODO Image Player Grid
			new ArrayList<>() {{
				for (int y = 0; y < 4; y++) {
					for (int x = 0; x < 3; x++) {
						add(new Rect(new Vertex(x*32,y*32), new Vertex((x+1)*32,(y+1)*32)));
					}
				}
			}}
	);
	public static final List<Rect> playerGridC = Collections.unmodifiableList( //TODO Collision Player Grid
			new ArrayList<>() {{
				for (int y = 0; y < 8; y++) {
					for (int x = 0; x < 8; x++) {
						add(new Rect(new Vertex(x*16,y*16), new Vertex((x+1)*16,(y+1)*16)));
					}
				}
			}}
	);
	public static final List<String> playerGridT = Collections.unmodifiableList( //TODO Collision Player Grid
			new ArrayList<>() {{
//	TODO			add("Path"); add("Grass"); add("Stone"); add("Water"); add("Mailbox"); add("Couldron"); // add(""); add("");
			}}
	);

	public static final List<Rect> blobGridC = Collections.unmodifiableList( //TODO Collision Blob Grid
			new ArrayList<>() {{
				add(new Rect(new Vertex(10,10),new Vertex(22*2,21*2)));
				add(new Rect(new Vertex(10,7),new Vertex(21*2,19*2)));
				add(new Rect(new Vertex(11,9),new Vertex(22*2,21*2)));
				add(new Rect(new Vertex(10,10),new Vertex(22*2,21*2)));
				add(new Rect(new Vertex(10,10),new Vertex(22*2,21*2)));
				add(new Rect(new Vertex(10,7),new Vertex(21*2,19*2)));
				add(new Rect(new Vertex(11,9),new Vertex(22*2,21*2)));
				add(new Rect(new Vertex(10,10),new Vertex(22*2,21*2)));
			}}
	);
	public static final List<String> blobGridT = Collections.unmodifiableList( //TODO Text Blob Grid
			new ArrayList<>() {{
				for (int x = 0; x < 8; x++) {
					add("blob"+ x);
				}
			}}
	);
	public static final List<Rect> blobGridI = Collections.unmodifiableList( //TODO Image Blob Grid
			new ArrayList<>() {{
				for (int y = 0; y < 2; y++) {
					for (int x = 0; x < 4; x++) {
						if(!(y==1 && x==3))
						add(new Rect(new Vertex(x * 32, y*32), new Vertex((x + 1) * 32, 32*(y + 1))));
					}
				}
			}}
	);

	public static String getEnemyTypeResources(int i) {
		if(i == 0) return "res/sprites/blobEnemyGrid.png";
		return "";
	}

	public static double[] getEnemyTypeStats(int i) { //Health | DMG | Armour
		if(i == 0) return new double[]{1, 1, 1};
		return new double[]{};
	}
	public static boolean getAnimationStatus(int i){
		return i == 0;
	}
	public static int getAnimationSpeed(int i){
		if(i == 0) return 3;
		if(i == 1) return 3;
		return 0;
	}

	public static int getEntityAnimationsLines(Vertex direction) {
		if(direction.x == 0 && direction.y == 1) return 0;
		if(direction.x == 1 && direction.y == 0) return 3;
		if(direction.x == 0 && direction.y == -1) return 6;
		if(direction.x == -1 && direction.y== 0) return 9;
		return 0;
	}
}
