public class LineSub extends Point {

    private Point end;

    public LineSub(int beginX, int beginY, int endX, int endY) {
        super(beginX, beginY);
        this.end = new Point(endX, endY);
    }

    public LineSub(Point begin, Point end) {
        super(begin.getX(), begin.getY());
        this.end = end;
    }

    @Override
    public String toString() {
        return "LineSub: " + super.toString() + " to " + end.toString();
    }

    public Point getBegin() {
        return this;
    }

    public Point getEnd() {
        return end;
    }

    public void setBegin(int x, int y) {
        setXY(x, y);
    }

    public void setEnd(int x, int y) {
        end.setXY(x, y);
    }

    public int getBeginX() {
        return getX();
    }

    public int getBeginY() {
        return getY();
    }

    public int getEndX() {
        return end.getX();
    }

    public int getEndY() {
        return end.getY();
    }

    public void setBeginX(int x) {
        setX(x);
    }

    public void setBeginY(int y) {
        setY(y);
    }

    public void setBeginXY(int x, int y) {
        setXY(x, y);
    }

    public void setEndX(int x) {
        end.setX(x);
    }

    public void setEndY(int y) {
        end.setY(y);
    }

    public void setEndXY(int x, int y) {
        end.setXY(x, y);
    }

    public int getLength() {
        return (int)Math.round(Math.sqrt(squareInt(end.getX() - getX()) + squareInt(end.getY() - getY())));
    }

    public double getGradient() {
        return Math.atan2(end.getY() - getY(), end.getX() - getX());
    }

    private int squareInt(int i) {
        return i * i;
    }
}
