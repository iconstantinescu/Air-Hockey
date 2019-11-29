import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import objects.Puck;
import objects.Pusher;
import org.junit.jupiter.api.Test;
import utilities.MathUtils;

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
        Pusher pusher  = new Pusher(0, 0, 10);
        Puck puck = new Puck(10, 10, 10, 0, 0);

        boolean overLapping = MathUtils.checkRadius(pusher, puck);

        assertTrue(overLapping);
    }

    @Test
    void checkRadiusNoOverlapping() {
        Pusher pusher  = new Pusher(0, 0, 10);
        Puck puck = new Puck(30, 30, 10, 0, 0);

        boolean overLapping = MathUtils.checkRadius(pusher, puck);

        assertFalse(overLapping);
    }
}