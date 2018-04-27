package exercises;

public class Circle extends Shape {

    private double radius;

    public Circle() {
        this(1.0);
    }

    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * 2.0 * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return Math.PI * 2.0 * radius;
    }

    @Override
    public String toString() {
        return String.format("A exercises.Circle with radius=%f, which is a subclass of %s", radius, super.toString());
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
