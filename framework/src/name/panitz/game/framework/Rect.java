package name.panitz.game.framework;

public class Rect {
    Vertex p1;
    Vertex p2;

    public Rect(Vertex vertex1, Vertex vertex2) {
        p1 = vertex1;
        p2 = vertex2;
    }

    public double getWidth(){
        return Math.abs((p1.x - p2.x));
    }
    public double getHeight(){
        return Math.abs((p1.y - p2.y));
    }
    public Vertex getP1() {
        return p1;
    }
    public Vertex getP2() {
        return p2;
    }
    public boolean touches(Rect that, double xPosThis, double yPosThis, double xPosThat, double yPosThat){
        if(isLeftOf(that,xPosThis,xPosThat)) return false;
        if(isRightOf(that,xPosThis,xPosThat)) return false;
        if(isBelow(that,yPosThis,yPosThat)) return false;
        if(isAbove(that,yPosThis,yPosThat)) return false;
        return true;
    }
    public boolean touches(Rect that){
        return (!isAbove(that) && !isBelow(that) || (!isLeftOf(that) && !isRightOf(that)));
    }
    public boolean isLeftOf (Rect that){
        return that.p1.x + that.getWidth() < p1.x + getWidth();
    }
    public boolean isRightOf (Rect that){
        return that.p1.x + that.getWidth() > p1.x + getWidth();
    }
    public boolean isAbove (Rect that){
        return that.p1.y + that.getHeight() < p1.y + getHeight();
    }
    public boolean isBelow (Rect that){
        return that.p1.y + that.getHeight() > p1.y + getHeight();
    }

    public boolean isLeftOf (Rect that, double xPosThis, double xPosThat){
        return that.p1.x+ xPosThat > p1.x + xPosThis + getWidth();
    }
    public boolean isRightOf (Rect that,double xPosThis, double xPosThat){
        return  that.p1.x+ xPosThat + that.getWidth() < p1.x + xPosThis ;
    }
    public boolean isAbove (Rect that, double yPosThis, double yPosThat){
        return that.p1.y+ yPosThat > p1.y+ yPosThis + getHeight() ;
    }
    public boolean isBelow (Rect that, double yPosThis, double yPosThat){
        return that.p1.y+ yPosThat + that.getHeight() < p1.y+ yPosThis ;
    }

    @Override
    public String toString() {
        return "Rect{" +
                " p1=" + p1 +
                " p2=" + p2 +
                '}';
    }
}
