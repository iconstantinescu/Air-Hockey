import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PuckTest {

    @Test
    void translate() {
        Puck puck = new Puck(10, 10, 5, 2, 2);
        puck.translate();
        assertEquals(puck.getposX(), 12);
        assertEquals(puck.getposY(), 12);

    }
}