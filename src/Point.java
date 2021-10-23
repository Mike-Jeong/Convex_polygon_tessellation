
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static double calcDistance(Point a, Point b) {

        double deltaX2 = (a.x - b.x) * (a.x - b.x);
        double deltaY2 = (a.y - b.y) * (a.y - b.y);

        return Math.sqrt(deltaX2 + deltaY2);
    }
    
}

