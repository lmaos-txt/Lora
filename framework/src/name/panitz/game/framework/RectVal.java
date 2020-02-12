package name.panitz.game.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RectVal {

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
					add(new Rect(new Vertex(i*32,0),new Vertex((i+1)*32,32)));
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
