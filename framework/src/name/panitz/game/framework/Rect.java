package name.panitz.game.framework;

public class Rect {
    Vertex p1;
    Vertex p2;

    public Rect(Vertex vertex1, Vertex vertex2) {
        p1 = vertex1;
        p2 = vertex2;
    }

    double getWidth(){
        return Math.abs((p1.x - p2.x));
    }
    double getHeight(){
        return Math.abs((p1.x - p2.y));
    }
}
