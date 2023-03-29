public class Cylinder {
    private double radius;
    private double height;

    public Cylinder() {}

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public double baseSurfaceArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double sideSurfaceArea() {
        return 2 * Math.PI * radius * height;
    }

    public double surfaceArea() {
        return 2 * baseSurfaceArea() + sideSurfaceArea();
    }

    public double volume() {
        return baseSurfaceArea() * height;
    }
}
