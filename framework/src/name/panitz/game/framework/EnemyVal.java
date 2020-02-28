package name.panitz.game.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnemyVal {

	public static List<Rect> getCollision(String tag) {
		if(tag.equals("spriteGrid1"))
			return null;
		if(tag.equals("Player"))
			return null;
		return null;
	}

	public static final List<Rect> blobImageGrid = Collections.unmodifiableList( //TODO Image Sprite Grid
			new ArrayList<>() {{
					for (int x = 0; x < 4; x++) {
						add(new Rect(new Vertex(x*32,0),new Vertex((x+1)*32,32)));
					}
				}}
	);
	public static final List<Rect> blobColGrid = Collections.unmodifiableList( //TODO Collision Sprite Grid
			new ArrayList<>() {{
				for (int x = 0; x < 4; x++) {
					add(new Rect(new Vertex(x*32,0),new Vertex((x+1)*32,32)));
				}
			}}
	);
}
