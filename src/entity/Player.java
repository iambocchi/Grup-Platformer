package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import input.KeyHandler;
import main.GamePanel;
import utils.LoadSave;

public class Player extends Entity {
    private int aniTick, aniIndex, aniSpeed = 15;
    private String previousAction = "";

    private GamePanel gp;
    private KeyHandler keyH;
    private BufferedImage[] playerAnimation;
    public BufferedImage img;

    // D akin
    private boolean isJumping = false;
    private boolean isMoving = false;
    private int groundY; // Adjust based on ground level in your game

    public Player(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        setDefaultValues();
        setPlayerAction(action);
    }

    public void setDefaultValues() {
        groundY = gp.TILESIZE * 8;
        velocityY = 0;
        x = 100;
        y = gp.TILESIZE * 8;
        speed = 4;
        action = "IDLE";
    }

    public void update() {
        updateAnimationTick();
        playerMovement();
    }

    // stops player if go to other window
    public void resetMoveBoleans() {

        action = "IDLE";
        keyH.leftPressed = false;
        keyH.rightPressed = false;
        keyH.upPressed = false;
        keyH.downPressed = false;
    }

    public void setPlayerAction(String action) {
        switch (action) {
            case "IDLE" -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_IDLE);
            case "ATTACK1" -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATTACK1);
            case "ATTACK2" -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATTACK2);
            case "CLIMB" -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_CLIMB);
            case "DEATH" -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_DEATH);
            case "HURT" -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_HURT);
            case "JUMP" -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_JUMP);
            case "PUSH" -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_PUSH);
            case "RUN" -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_RUN);
            case "THROW" -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_THROW);
            case "WALK" -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_WALK);
            default -> img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_IDLE); // fallback

        }
        if (img != null) {
            int frameWidth = 32; // adjust if needed
            int totalFrames = img.getWidth() / frameWidth;
            playerAnimation = new BufferedImage[totalFrames];
            for (int i = 0; i < totalFrames; i++) {
                playerAnimation[i] = img.getSubimage(i * frameWidth, 0, gp.TILESIZE, gp.TILESIZE);
            }
        }
        System.out.println("this is the width: " + img.getWidth());
    }

    public void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= playerAnimation.length) {
                aniIndex = 0;
            }
        }
    }

    public void playerMovement() {
        isMoving = false;
        // Horizontal movement
        if (keyH.leftPressed == true) {
            x -= speed;
            isMoving = true;
        }
        if (keyH.rightPressed == true) {
            x += speed;
            isMoving = true;
        }

        // Set run animation if on ground and moving
        if (isMoving && !isJumping) {
            action = "RUN";
        }

        // Jumping
        if (keyH.upPressed && !isJumping) {
            isJumping = true;
            velocityY = JUMPSTRENGTH;
            action = "JUMP";
        }

        // Apply gravity
        if (isJumping) {
            y += velocityY;
            velocityY += GRAVITY;

            if (y >= groundY) {
                y = groundY;
                isJumping = false;
                velocityY = 0;
                action = isMoving ? "RUN" : "IDLE";
            }
        }

        // Idle state
        if (!isMoving && !keyH.upPressed && !isJumping) {
            action = "IDLE";
        }
    }

    public void draw(Graphics2D g2) {
        if (!action.equals(previousAction)) {
            setPlayerAction(action);
            previousAction = action;
        }
        if (playerAnimation != null && aniIndex < playerAnimation.length) {
            g2.drawImage(playerAnimation[aniIndex], x, y, gp.TILESIZE, gp.TILESIZE, null);
        }
    }
}
