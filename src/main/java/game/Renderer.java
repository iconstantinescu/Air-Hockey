package game;

/**
 * The interface for the specific game renderers, which have to contain
 * 2 methods, one that specifies what to render and one that specifies
 * what to dispose of once the application is terminated.
 */
public interface Renderer {

    /**
     * The run method specifies the general behaviour of the renderer which will be
     * run in a loop.
     */
    void run();

    /**
     * Specify how to dispose of the initialized objects after the game the terminated.
     */
    void dispose();
}
