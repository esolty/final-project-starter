package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import twitter4j.Status;
import twitter4j.User;
import util.ImageCache;
import util.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PrettyMarker extends MapMarkerSimple {

    private String imageURL;
    private User user;
    private BufferedImage image;
    private String text;
    private Status status;
    private Color color;

    public PrettyMarker(Layer layer, String name, Coordinate coord, Color color, Status status) {
        super(layer, name, coord);
        this.color = color;
        setBackColor(color);
        this.status = status;
        this.text = status.getText();
        this.user = status.getUser();
        this.imageURL = status.getUser().getProfileImageURL();
        this.image = ImageCache.getInstance().getImage(status.getUser().getProfileImageURL());
    }

    public User getUser() {
        return user;
    }
    public String getText() {
        return text;
    }
    public Status getStatus() {
        return status;
    }
    public BufferedImage getImage() { return image; }
    public String getImageURL() { return imageURL; }

    @Override
    public void paint(Graphics g, Point position, int radius) {
        int size = radius * 2;
        g.setColor(this.getColor());
        g.drawOval(position.x - radius, position.y - radius, size, size);

        if (g instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D g2 = (Graphics2D)g;
            Composite oldComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3));
            g2.setPaint(this.getBackColor());
            g.fillOval(position.x - radius, position.y - radius, size, size);
            g2.setComposite(oldComposite);

            if (image != null) {
                g.drawImage(image, position.x - 15, position.y - 15, 30, 30, Color.BLACK, null);
                g2.drawRect(position.x - 15, position.y - 15, 30, 30);
            }
        }

        if (this.getLayer() == null || this.getLayer().isVisibleTexts()) {
            this.paintText(g, position);
        }

    }
}
