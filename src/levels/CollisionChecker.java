package levels;

import entity.Entity;
import main.GamePanel;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.TILESIZE;
        int entityRightCol = entityRightWorldX / gp.TILESIZE;
        int entityTopRow = entityTopWorldY / gp.TILESIZE;
        int entityBottomRow = entityBottomWorldY / gp.TILESIZE;

        int tileNum1, tileNum2;
        if (entityLeftCol >= 0 && entityRightCol < gp.MAXSCREENCOL &&
                entityTopRow >= 0 && entityBottomRow < gp.MAXSCREENROW) {
            switch (entity.actionMovement) {
                case "JUMP":
                    entityTopRow = (entityTopWorldY - entity.speed) / gp.TILESIZE;
                    tileNum1 = gp.levelM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.levelM.mapTileNum[entityRightCol][entityTopRow];
                    if (gp.levelM.tile[tileNum1].collision == true || gp.levelM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                    break;
                case "DOWN":
                    entityBottomRow = (entityBottomWorldY + entity.speed) / gp.TILESIZE;
                    tileNum1 = gp.levelM.mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = gp.levelM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.levelM.tile[tileNum1].collision == true || gp.levelM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                    break;
                case "LEFT":
                    entityLeftCol = (entityLeftWorldX - entity.speed) / gp.TILESIZE;
                    tileNum1 = gp.levelM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.levelM.mapTileNum[entityLeftCol][entityBottomRow];
                    if (gp.levelM.tile[tileNum1].collision == true || gp.levelM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                    break;
                case "RIGHT":
                    entityRightCol = (entityRightWorldX + entity.speed) / gp.TILESIZE;
                    tileNum1 = gp.levelM.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gp.levelM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.levelM.tile[tileNum1].collision == true || gp.levelM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
