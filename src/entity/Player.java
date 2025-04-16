package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import input.KeyHandler;
import main.GamePanel;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    // private BufferedImage img;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        setDefaultValues();
        // getPlayerImage();
    }

    public void setDefaultValues() {
        x = 120;
        y = 120;
        speed = 12;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            myImage = ImageIO.read(getClass().getResourceAsStream("res\\player\\Pink_Monster.png"));
        } catch (Exception e) {

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

            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("\\res\\player\\Pink_Monster.png"));
            g2.drawImage(image, x, y, gp.TILESIZE, gp.TILESIZE, null);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
