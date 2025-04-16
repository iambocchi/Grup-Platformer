package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import input.KeyHandler;
import main.GamePanel;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void getPlayerImage() {

        try {
            System.out.println(getClass().getResource("/Pink_Monster.png"));

            myImage = ImageIO.read(getClass().getResourceAsStream("/Pink_Monster.png"));
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public void update() {

        if (keyH.upPressed) {
            y -= speed;

        } else if (keyH.downPressed) {
            y += speed;

        } else if (keyH.leftPressed) {
            x -= speed;

        } else if (keyH.rightPressed) {
            x += speed;
        }

    }

    public void draw(Graphics2D g2) {
        try {
            BufferedImage img = myImage;
            g2.drawImage(img, x, y, gp.TILESIZE, gp.TILESIZE, null);
        } catch (Exception e) {

        }
    }
}
