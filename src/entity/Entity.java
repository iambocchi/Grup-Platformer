package entity;

import java.awt.image.BufferedImage;

public abstract class Entity {
    protected int speed;
    protected BufferedImage myImage;
    protected int defaultStance;
    protected String action;
    protected int x, y;

    public Entity() {

    }

}
