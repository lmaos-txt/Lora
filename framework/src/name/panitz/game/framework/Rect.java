package name.panitz.game.framework;

public class Rect {
    Vertex p1;
    Vertex p2;

    public Rect(Vertex vertex) {
        p1 = vertex;
        p2 = vertex;
    }

    double getWidth(){
        return Math.abs((p1.x - p2.x));
    }
    double getHeight(){
        return Math.abs((p1.x - p2.y));
    }
}
