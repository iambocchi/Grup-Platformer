package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import input.KeyHandler;
import main.GamePanel;

import assets.Images;

public class Player extends Entity {
    private int aniTick, aniIndex, aniSpeed = 15;
    private String previousAction = "";
    private String player_action;

    private GamePanel gp;
    private KeyHandler keyH;
    private Images asset = new Images();
    private BufferedImage[] playerAnimation;
    public BufferedImage img;

    // AI
    private boolean isJumping = false;
    private double velocityY = 0;
    private final double gravity = 0.3;
    private final double jumpStrength = -10;
    private final int groundY = 500; // Adjust based on ground level in your game

    public Player(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        setDefaultValues();
        setPlayerAction(player_action);
    }

    public void setDefaultValues() {
        x = 100;
        y = 500;
        speed = 4;
        defaultStance = 1;
        player_action = "ATTACK1";
    }

    // public void playerAnimations() {
    // playerAnimation = new BufferedImage[4];
    // for (int i = 0; i < playerAnimation.length; i++) {
    // playerAnimation[i] = img.getSubimage(i * 32, 0, 32, 32);

    // }
    // }

    public void setPlayerAction(String player_action) {
        switch (player_action) {
            case "IDLE" -> img = asset.IDLE;
            case "ATTACK1" -> img = asset.ATTACK1;
            case "ATTACK2" -> img = asset.ATTACK2;
            case "CLIMB" -> img = asset.CLIMB;
            case "DEATH" -> img = asset.DEATH;
            case "HURT" -> img = asset.HURT;
            case "JUMP" -> img = asset.JUMP;
            case "PUSH" -> img = asset.PUSH;
            case "RUN" -> img = asset.RUN;
            case "THROW" -> img = asset.THROW;
            case "WALK" -> img = asset.WALK;
            default -> img = asset.IDLE; // fallback

        }
        if (img != null) {
            int frameWidth = 32; // adjust if needed
            int totalFrames = img.getWidth() / frameWidth;
            playerAnimation = new BufferedImage[totalFrames];
            for (int i = 0; i < totalFrames; i++) {
                playerAnimation[i] = img.getSubimage(i * frameWidth, 0, frameWidth, 32);
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

    public void update() {
        updateAnimationTick();
        // Horizontal movement
        if (keyH.leftPressed) {
            x -= speed;
            if (!isJumping)
                player_action = "RUN";
        }
        if (keyH.rightPressed) {
            x += speed;
            if (!isJumping)
                player_action = "RUN";
        }
        // Jumping
        if (keyH.upPressed && !isJumping) {
            isJumping = true;
            velocityY = jumpStrength;
            player_action = "JUMP";
        }
        // Apply gravity
        if (isJumping) {
            y += velocityY;
            velocityY += gravity;
            if (y >= groundY) {
                y = groundY;
                isJumping = false;
                velocityY = 0;
                player_action = "IDLE";
            }
        }
        // Idle if not moving
        if (!keyH.leftPressed && !keyH.rightPressed && !keyH.upPressed && !isJumping) {
            player_action = "IDLE";
        }
    }

    public void draw(Graphics2D g2) {
        if (!player_action.equals(previousAction)) {
            setPlayerAction(player_action);
            previousAction = player_action;
        }
        if (playerAnimation != null && aniIndex < playerAnimation.length) {
            g2.drawImage(playerAnimation[aniIndex], x, y, gp.TILESIZE, gp.TILESIZE, null);
        }
    }
}
