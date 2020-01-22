package utilities;

/**
 * Class containing some helper methods for
 * Linear Algebra and Geometry calculations.
 */
public class MathUtils {

    /**
     * Function returning the euclidean distance between two points.
     * @param x1 x coordinate of the first object
     * @param y1 y coordinate of the first object
     * @param x2 x coordinate of the second object
     * @param y2 y coordinate of the second object
     * @return the euclidean distance as a double
     */
    public static double euclideanDistance(float x1, float y1, float x2, float y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Function that checks if the radius between Pusher and Puck is overlapping.
     * @param x1 x coordinate of the first object
     * @param y1 y coordinate of the first object
     * @param radius1 radius of the first object
     * @param x2 x coordinate of the second object
     * @param y2 y coordinate of the second object
     * @param radius2 radius of the second object
     * @return Whether the 2 objects overlap
     */
    public static boolean checkRadius(float x1, float y1, float radius1,
                                      float x2, float y2, float radius2) {
        return MathUtils.euclideanDistance(x1, y1, x2, y2) <= radius2 + radius1;
    }

    /**
     * Computes the direction of the reflection and returns and array containing the
     * deltaX and deltaY following the collision.
     * NOTE: x1 and y1 are the coordinates of the pusher
     *
     * @param x1 x coordinate of the first element
     * @param y1 y coordinate of the first element
     * @param x2 x coordinate of the second element
     * @param y2 y coordinate of the second element
     * @return vector direction of the reflection
     */
    public static double[] reflect(float x1, float y1, float x2, float y2) {
        double norm = euclideanDistance(x1, y1, x2, y2);

        double[] deltas = {(x2 - x1) / norm, (y2 - y1) / norm};

        return deltas;

    }
}