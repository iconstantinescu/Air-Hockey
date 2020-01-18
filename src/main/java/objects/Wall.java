package objects;

public class Wall extends GameObject{

    private int width;

    private int height;

    public Wall(float x, float y, int width, int height) {
        super(x, y);

        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
