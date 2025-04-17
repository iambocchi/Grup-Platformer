package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import input.KeyHandler;
import main.GamePanel;
import assets.Images;

public class Player extends Entity {
    private int aniTick, aniIndex, aniSpeed = 15;
    private GamePanel gp;
    private KeyHandler keyH;
    private Images asset = new Images();
    private BufferedImage[][] playerAnimation;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        setDefaultValues();
        playerAnimations();

    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void playerAnimations() {
        playerAnimation = new BufferedImage[16][8];
        for (int i = 0; i < playerAnimation.length; i++) {
            for (int j = 0; j < playerAnimation[i].length; j++) {
                playerAnimation[i][j] = asset.playerAni.getSubimage(i * 12, j * 32, 32, 32);
            }
        }
    }

    public void updateAnimationTick() {
        // aniTick++;
        // if (aniTick >= aniSpeed) {
        // aniTick = 0;
        // aniIndex++;
        // if (aniIndex >= playerAnimation.length) {
        // aniIndex = 0;
        // }
        // }
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

        updateAnimationTick();
        g2.drawImage(playerAnimation[8][1], x, y, gp.TILESIZE, gp.TILESIZE, null);
    }
}
