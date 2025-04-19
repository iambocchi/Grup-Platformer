package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import input.KeyHandler;
import main.GamePanel;
import utils.LoadSave;

public class Player extends Entity {

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
        updatePlayerAnimation();
    }

    public void setDefaultValues() {
        groundY = gp.TILESIZE * 11;
        velocityY = 0;
        x = 100;
        y = groundY;
        speed = 2;
        action = "IDLE";

        // for animation
        aniTick = 0;
        aniIndex = 0;
        aniSpeed = 25;
    }

    public void update() {
        playerMovement();

        if (!action.equals(previousAction)) {
            updatePlayerAnimation();
            previousAction = action;
        }

        updateAnimationTick();
    }

    // stops player if go to other window
    public void resetMoveBoleans() {

        action = "IDLE";
        keyH.leftPressed = false;
        keyH.rightPressed = false;
        keyH.upPressed = false;
        keyH.downPressed = false;
    }

    public void updatePlayerAnimation() {
        img = switch (action) {
            case "IDLE" -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_IDLE);
            case "ATTACK1" -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATTACK1);
            case "ATTACK2" -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATTACK2);
            case "CLIMB" -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_CLIMB);
            case "DEATH" -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_DEATH);
            case "HURT" -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_HURT);
            case "JUMP" -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_JUMP);
            case "PUSH" -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_PUSH);
            case "RUN" -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_RUN);
            case "THROW" -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_THROW);
            case "WALK" -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_WALK);
            default -> LoadSave.GetSpriteAtlas(LoadSave.PLAYER_IDLE); // fallback
        };
        if (img != null) {
            int frameWidth = 32; // adjust if needed
            int totalFrames = img.getWidth() / frameWidth;
            playerAnimation = new BufferedImage[totalFrames];
            for (int i = 0; i < totalFrames; i++) {
                playerAnimation[i] = img.getSubimage(i * frameWidth, 0, frameWidth, frameWidth);
            }
        }
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
        if (keyH.leftPressed) {
            x -= speed;
            isMoving = true;
        }
        if (keyH.rightPressed) {
            x += speed;
            isMoving = true;
        }

        // Set run animation if on ground and moving
        if (isMoving && !isJumping && !action.equals("RUN")) {
            action = "RUN";
        }

        // Jumping
        if (keyH.upPressed && !isJumping) {
            isJumping = true;
            velocityY = JUMPSTRENGTH;
            if (!action.equals("JUMP")) {
                action = "JUMP";
            }
        }

        // Apply gravity
        if (isJumping) {
            y += velocityY;
            velocityY += GRAVITY;

            if (y >= groundY) {
                y = groundY;
                isJumping = false;
                velocityY = 0;
                if (isMoving && !action.equals("RUN")) {
                    action = "RUN";
                } else if (!isMoving && !action.equals("IDLE")) {
                    action = "IDLE";
                }
            }
        }

        // Idle state
        if (!isMoving && !keyH.upPressed && !isJumping && !action.equals("IDLE")) {
            action = "IDLE";
        }
    }

    public void draw(Graphics2D g2) {
        if (playerAnimation != null && aniIndex < playerAnimation.length) {
            g2.drawImage(playerAnimation[aniIndex], x, y, gp.TILESIZE, gp.TILESIZE, null);
        }
    }
}
