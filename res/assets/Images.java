package assets;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Images {
    public BufferedImage bat, playerAni;

    public Images() {
        try {
            playerAni = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/owletM.png"));
            bat = ImageIO.read(getClass().getResourceAsStream("/rockyroads/Enemies/cactus.png"));

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
