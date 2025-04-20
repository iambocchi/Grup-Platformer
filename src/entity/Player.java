package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import input.KeyHandler;
import main.GamePanel;
import utils.LoadSave;

public class Player extends Entity {
    private GamePanel gp;
    private KeyHandler keyH;
    private BufferedImage[] playerAnimation;
    public BufferedImage img;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        solidArea = new Rectangle();
        solidArea.x = 4;
        solidArea.y = 0;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        updatePlayerAnimation();
    }

    public void setDefaultValues() {
        worldX = 100;
        worldY = 100;
        speed = 2;
        actionSprite = "IDLE";
        actionMovement = "";

        // for animation
        aniTick = 0;
        aniIndex = 0;
        aniSpeed = 25;
    }

    public void update() {

        playerMovement();
        updateAnimationTick();
    }

    // stops player if go to other window
    public void resetMoveBoleans() {

        actionSprite = "IDLE";
        keyH.leftPressed = false;
        keyH.rightPressed = false;
        keyH.upPressed = false;
        keyH.downPressed = false;
    }

    public void updatePlayerAnimation() {
        img = switch (actionSprite) {
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
        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                actionSprite = "JUMP";
                actionMovement = "JUMP";

            } else if (keyH.downPressed == true) {
                actionSprite = "IDLE";
                actionMovement = "DOWN";

            } else if (keyH.leftPressed == true) {
                actionSprite = "RUN";
                actionMovement = "LEFT";

            } else if (keyH.rightPressed == true) {
                actionSprite = "RIGHT";
                actionMovement = "RIGHT";
            }
        }
        // check tile collision
        collisionOn = false;
        gp.cChecker.checkTile(this);
        // IF collision is false, player can move
        if (collisionOn == false) {
            switch (actionMovement) {
                case "JUMP":
                    worldY -= speed;
                    break;
                case "DOWN":
                    worldY += speed;
                    break;
                case "LEFT":
                    worldX -= speed;
                    break;
                case "RIGHT":
                    worldX += speed;
                    break;
            }
        }

        // PLAYER MOVEMENT
        // isMoving = false;

        // // Horizontal movement
        // if (keyH.leftPressed) {
        // worldX -= speed;
        // actionMovement = "LEFT";
        // isMoving = true;
        // }
        // if (keyH.rightPressed) {
        // worldX += speed;
        // actionMovement = "RIGHT";
        // isMoving = true;
        // }

        // // Set run animation if on ground and moving
        // if (isMoving && !isJumping && !actionSprite.equals("RUN")) {
        // actionSprite = "RUN";
        // }

        // // Jumping
        // if (keyH.upPressed && !isJumping) {
        // isJumping = true;
        // velocityY = JUMPSTRENGTH;
        // if (!actionSprite.equals("JUMP")) {
        // actionSprite = "JUMP";
        // }
        // }

        // // Apply gravity
        // if (isJumping) {
        // worldY += velocityY;
        // velocityY += GRAVITY;

        // if (worldY >= groundY) {
        // worldY = groundY;
        // isJumping = false;
        // velocityY = 0;
        // if (isMoving && !actionSprite.equals("RUN")) {
        // actionSprite = "RUN";
        // } else if (!isMoving && !actionSprite.equals("IDLE")) {
        // actionSprite = "IDLE";
        // }
        // }
        // }

        // // Idle state
        // if (!isMoving && !keyH.upPressed && !isJumping &&
        // !actionSprite.equals("IDLE")) {
        // actionMovement = "DOWN";
        // actionSprite = "IDLE";
        // }
    }

    public void draw(Graphics2D g2) {
        if (playerAnimation != null && aniIndex < playerAnimation.length) {
            g2.drawImage(playerAnimation[aniIndex], worldX, worldY, gp.TILESIZE, gp.TILESIZE, null);
        }
    }
}
