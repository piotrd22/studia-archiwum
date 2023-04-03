import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCylinder {
    private Cylinder cylinder;

    @Before
    public void setup() {
        this.cylinder = new Cylinder(10, 20);
    }

    @Test
    public void baseSurfaceArea() {
        double result = Math.PI * Math.pow(cylinder.getRadius(), 2);
        assertEquals(result, cylinder.baseSurfaceArea(), 3);
    }

    @Test
    public void sideSurfaceArea() {
        double result = 2 * Math.PI * cylinder.getHeight() * cylinder.getRadius();
        assertEquals(result, cylinder.sideSurfaceArea(), 3);
    }

    @Test
    public void surfaceArea() {
        double result = 2 * cylinder.baseSurfaceArea() + cylinder.sideSurfaceArea();
        assertEquals(result, cylinder.surfaceArea(), 3);
    }

    @Test
    public void volume() {
        double result = cylinder.baseSurfaceArea() * cylinder.getHeight();
        assertEquals(result, cylinder.volume(), 3);
    }
}
