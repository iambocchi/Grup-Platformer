package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
    private boolean isAttacking = false;
    private boolean isJumping = false;
    private boolean isMoving = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH;
        this.gp = gp;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 8;
        solidArea.width = 24;
        solidArea.height = 24;

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
        previousAction = actionSprite;
        playerMovement();
        if (!previousAction.equals(actionSprite)) {
            updatePlayerAnimation();
            aniTick = 0;
            aniIndex = 0;
        }
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
                isAttacking = false;
            }
        }

    }

    // I DID SOME CHANGE HERE
    public void playerMovement() {
        // PLAYER MOVEMENT
        isMoving = false;

        if (!keyH.leftPressed && !keyH.rightPressed && !keyH.upPressed && !keyH.downPressed)
            return;

        float xSpeed = 0, ySpeed = 0;

        if (keyH.leftPressed && !keyH.rightPressed) {
            worldX -= speed;
        } else if (keyH.rightPressed && !keyH.leftPressed) {
            worldX += speed;
        }

        if (keyH.upPressed && !keyH.downPressed) {
            worldY -= speed;
        } else if (keyH.downPressed && !keyH.upPressed) {
            worldY += speed;

        }

        // if (CanMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
        // this.x += xSpeed;
        // this.y += ySpeed;
        // moving = true;
        // }

        // if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width,
        // hitbox.height, lvlData)) {
        // hitbox.x += xSpeed;
        // hitbox.y += ySpeed;
        // moving = true;
        // }

    }

    public void draw(Graphics2D g2) {
        if (playerAnimation != null && aniIndex < playerAnimation.length) {
            g2.drawImage(playerAnimation[aniIndex], worldX, worldY, gp.TILESIZE, gp.TILESIZE, null);
        }
    }
}
