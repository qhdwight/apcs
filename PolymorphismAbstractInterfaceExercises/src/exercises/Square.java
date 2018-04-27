package exercises;

public class Square extends Rectangle {

    public Square() {
        this(1.0);
    }

    public Square(double side) {
        super(side, side);
    }

    public Square(double side, String color, boolean filled) {
        super(side, side, color, filled);
    }

    @Override
    public String toString() {
        return String.format("A square with side=%f, which is a subclass of %s", width, super.toString());
    }

    @Override
    public void setLength(double length) {
        super.setLength(length);
        width = length;
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        length = width;
    }

    public double getSide() {
        return width;
    }

    public void setSide(double side) {
        width = side;
        length = side;
    }
}
