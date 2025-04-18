package entity;

import java.awt.image.BufferedImage;

public class Entity {
    protected int speed;
    protected BufferedImage myImage;
    protected int defaultStance;
    protected String action;
    protected int x, y;

    protected double velocityY = 0;
    protected final double GRAVITY = 0.3;
    protected final double JUMPSTRENGTH = -10;

    public Entity() {

    }

}
