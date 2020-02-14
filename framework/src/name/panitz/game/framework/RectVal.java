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
		return null;
	}
	public static List<Rect> getImages(String tag){
		if(tag.equals("spriteGrid1"))
			return  spriteGrid1I;
		if(tag.equals("playerGrid"))
			return  playerGridI;
		return null;
	}
	public static List<String> getText(String tag) {
		if(tag.equals("spriteGrid1"))
			return spriteGrid1T;
		if(tag.equals("Player"))
			return playerGridT;
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
				for (int i = 0; i < 6; i++) {
					add(new Rect(new Vertex(0,0),new Vertex((32 * scaleFactor),32 * scaleFactor)));
				}
			}}
	);

	public static final List<String> spriteGrid1T = Collections.unmodifiableList( //TODO Text Sprite Grid
			new ArrayList<>() {{
				add("path"); add("grass"); add("stone"); add("water"); add("mailbox"); add("cauldron"); // add(""); add("");
			}}
	);

	public static final List<Rect> playerGridI = Collections.unmodifiableList( //TODO Image Player Grid
			new ArrayList<>() {{
				for (int y = 0; y < 8; y++) {
					for (int x = 0; x < 8; x++) {
						add(new Rect(new Vertex(x*16,y*16), new Vertex((x+1)*16,(y+1)*16)));
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

}
