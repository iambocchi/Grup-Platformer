package entity;

import java.awt.Rectangle;

public class Entity {
    public int speed;
    public int defaultStance;
    public String actionSprite;

    public int worldY;
    public int worldX;
    public int width;
    public int height;

    // actionMovement
    public String actionMovement;

    // for animation speed
    public int aniTick;
    public int aniIndex;
    public int aniSpeed;

    // collision
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public Entity() {

    }

}
