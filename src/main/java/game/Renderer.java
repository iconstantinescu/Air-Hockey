package game;

/**
 * The interface for the specific game renderers, which have to contain
 * 2 methods, one that specifies what to render and one that specifies
 * what to dispose of once the application is terminated.
 */
public interface Renderer {

    void run();
    void dispose();

}
