package Game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public abstract class GameEntity {
    protected Image image = null;
    protected static final Logger logger = LogManager.getRootLogger();

    protected void setImage(String file, Color transparentColor) {
        URL imageURL = GameEntity.class.getClassLoader().getResource(file);
        assert imageURL != null;
        ImageIcon ii = new ImageIcon(imageURL);
        image = ii.getImage();
        if(transparentColor != null) {
            image = ImageTransparency.makeColorTransparent(image, transparentColor);
        }
    }

    public Image getImage() {
        return image;
    }
}
