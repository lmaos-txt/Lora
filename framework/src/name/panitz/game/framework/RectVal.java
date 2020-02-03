package name.panitz.game.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RectVal {

	public static List<Rect> getCollision(String tag) {
		if(tag.equals("Spritesheet"))
			return spritegrid;
		if(tag.equals("Player"))
			return playerGrid;
		return null;
	}
	public static final List<Rect> spritegrid = Collections.unmodifiableList(
			new ArrayList<>() {{
				for (int i = 0; i < 7; i++) {
					add(new Rect(new Vertex(i*16,0),new Vertex((i+1)*16,16)));
				}
			}}
	);

	public static final List<Rect> playerGrid = Collections.unmodifiableList(
			new ArrayList<>() {{
				for (int y = 0; y < 8; y++) {
					for (int x = 0; x < 8; x++) {
						add(new Rect(new Vertex(x*16,y*16), new Vertex((x+1)*16,(y+1)*16)));
					}
				}
			}}
	);
}
