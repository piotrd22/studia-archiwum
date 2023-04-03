public class Cylinder {
    private double radius;
    private double height;

    public Cylinder() {}

    public Cylinder(double radius, double height) {
        if (radius <= 0 || height <= 0) {
            throw new IllegalArgumentException("Values must be positive");
        }
        this.radius = radius;
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.height = height;
    }

    public double baseSurfaceArea() {
        if (radius <= 0 || height <= 0) {
            throw new IllegalArgumentException("Values must be positive");
        }
        return Math.PI * Math.pow(radius, 2);
    }

    public double sideSurfaceArea() {
        if (radius <= 0 || height <= 0) {
            throw new IllegalArgumentException("Values must be positive");
        }
        return 2 * Math.PI * radius * height;
    }

    public double surfaceArea() { return 2 * baseSurfaceArea() + sideSurfaceArea(); }

    public double volume() {
        return baseSurfaceArea() * height;
    }
}
