package entity;

import static main.GamePanel.TILESIZE;
import static main.GamePanel.SCREENWIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import input.KeyHandler;

public class Player extends Entity {
    int playerY = y;
    int playerX = x;
    int playerSpeed = speed;

    KeyHandler keyH;

    public Player(KeyHandler keyH) {
        this.keyH = keyH;
    }

    public void update() {
        if (keyH.upPressed) {
            playerY -= playerSpeed;

        } else if (keyH.downPressed) {
            playerY += playerSpeed;

        } else if (keyH.leftPressed && playerX > 0) {
            playerX -= playerSpeed;

        } else if (keyH.rightPressed && playerX < SCREENWIDTH - TILESIZE) {
            playerX += playerSpeed;
        }

    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(playerX, playerY, TILESIZE, TILESIZE);
    }
}
