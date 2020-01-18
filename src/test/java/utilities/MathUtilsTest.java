package utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MathUtilsTest {

    private static MathUtils math = new MathUtils();

    @Test
    void euclideanDistance() {

        double result = MathUtils.euclideanDistance(0, 0, 3, 4);

        assertEquals(result, 5);
    }

    @Test
    void reflect() {
        double[] reflection = MathUtils.reflect(0, 0, 0, 1);

        assertEquals(0, reflection[0]);
        assertEquals(1, reflection[1]);

    }

    @Test
    void checkRadiusOverlapping() {
        boolean overLapping = MathUtils.checkRadius(0, 0, 10, 10, 10, 10);

        assertTrue(overLapping);
    }

    @Test
    void checkRadiusNoOverlapping() {
        boolean overLapping = MathUtils.checkRadius(0, 0, 10, 30, 30, 10);

        assertFalse(overLapping);
    }
}