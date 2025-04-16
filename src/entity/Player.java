package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import input.KeyHandler;
import main.GamePanel;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    BufferedImage img;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        setDefaultValues();
        importImg();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        // direction = "down";
    }

    public void importImg() {
        InputStream is = getClass().getResourceAsStream("/sprites/Pink_Monster.png");
        try {
            img = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPlayerImage() {

        try {
            myImage = ImageIO.read(getClass().getResourceAsStream("/res/Pink_Monster.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    //
    //
    //
    //
    //
    //
    //
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
            g2.drawImage(img, x, y, gp.TILESIZE, gp.TILESIZE, null);
        } catch (Exception e) {

        }
    }
}
