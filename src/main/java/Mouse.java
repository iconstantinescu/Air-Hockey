//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.image.BufferedImage;
//import java.awt.image.WritableRaster;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;

public class Mouse {
//    private State currentState = State.RELEASED;
//    private Rectangle clickBox;
//    private ArrayList<ActionListener> actionListeners;
//    private String text = "some text";
//
//    private Color released;
//    private Color hover;
//    private Color pressed;
//    public static final Font font = new Font("Font", Font.PLAIN, 28);
//
////    private AudioHandler audio;
//
//    public Mouse() {
////        clickBox = new Rectangle(x, y, width, height);
//        actionListeners = new ArrayList<ActionListener>();
//        released = new Color(173, 177, 179);
//        hover = new Color(150, 156, 158);
//        pressed = new Color(111, 116, 117);
//
////        audio = AutoHandler.getInstance();
////        audio.load("press.wav", "press");
//    }
//
//    public void update() {
//    }
//
//    public void render(Graphics2D g) {
//        if (currentState == State.RELEASED) {
//            g.setColor(released);
//            g.fill(clickBox);
//        } else if (currentState == State.HOVER) {
//            g.setColor(hover);
//            g.fill(clickBox);
//        } else {
//            g.setColor(pressed);
//            g.fill(clickBox);
//        }
//        g.setColor(Color.WHITE);
//        g.setFont(font);
////        g.drawString(text, clickBox.x + clickBox.width / 2 - DrawUtils.getMessageWidth(text, font, g) / 2,
////                clickBox.y + clickBox.height / 2 + DrawUtils.getMessageHeight(text, font, g) / 2);
//    }
//
//    public void addActionListener(ActionListener listener) {
//        actionListeners.add(listener);
//    }
//
//    public void mousePressed(MouseEvent e) {
//        if (clickBox.contains(e.getPoint())) {
//            currentState = State.PRESSED;
//        }
//    }
//
//    public void mouseReleased(MouseEvent e) {
//        if (clickBox.contains(e.getPoint())) {
//            for (ActionListener al : actionListeners) {
//                al.actionPerformed(null);
//            }
//        }
//        currentState = State.RELEASED;
//    }
//
//    public void mouseDragged(MouseEvent e) {
//        if (clickBox.contains(e.getPoint())) {
//            currentState = State.PRESSED;
//        } else {
//            currentState = State.RELEASED;
//        }
//    }
//
//    public void mouseMoved(MouseEvent e) {
//        if ("play.png".contains((CharSequence) e.getPoint())) {
//            BufferedImage img = null;
//            try {
//                img = colorImage(ImageIO.read(new File("play.png")));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            try {
//                ImageIO.write(img, "png", new File("test.png"));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//
//        }
//        if (clickBox.contains(e.getPoint())) {
//            currentState = State.HOVER;
//        } else {
//            currentState = State.RELEASED;
//        }
//    }
//
//    public int getX() {
//        return clickBox.x;
//    }
//
//    public int getY() {
//        return clickBox.y;
//    }
//
//    public int getWidth() {
//        return clickBox.width;
//    }
//
//    public int getHeight() {
//        return clickBox.height;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    private enum State {
//        RELEASED,
//        HOVER,
//        PRESSED
//    }
//
//    private static BufferedImage colorImage(BufferedImage image) {
//        int width = image.getWidth();
//        int height = image.getHeight();
//        WritableRaster raster = image.getRaster();
//
//        for (int xx = 0; xx < width; xx++) {
//            for (int yy = 0; yy < height; yy++) {
//                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
//                pixels[0] = 0;
//                pixels[1] = 255;
//                pixels[2] = 255;
//                raster.setPixel(xx, yy, pixels);
//            }
//        }
//        return image;
//    }
}
