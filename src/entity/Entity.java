package entity;

public class Entity {
    protected int speed;
    protected int defaultStance;
    protected String action;
    protected int x, y;
    protected int width;
    protected int height;

    // for animation speed
    protected int aniTick;
    protected int aniIndex;
    protected int aniSpeed;

    // not permanet -> ill add collision then remove this
    // alter player movemnet
    protected double velocityY = 0;
    protected final double GRAVITY = 0.3;
    protected final double JUMPSTRENGTH = -8;

    public Entity() {

    }

}
