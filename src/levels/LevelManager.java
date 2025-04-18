package levels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import utils.LoadSave;
import main.GamePanel;

public class LevelManager {

    private GamePanel gp;
    public Tile[] tile;
    BufferedImage[][] levelSprite;

    public LevelManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[48];
        getTileSubImages();
        setTileImage();
    }

    private void setTileImage() {
        int tileIndex = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                if (tileIndex >= 48) {
                    break;
                }
                tile[tileIndex] = new Tile();
                tile[tileIndex].image = levelSprite[i][j];
                tileIndex++;
            }
        }
    }

    private void getTileSubImages() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.TILESET_FOREST);
        System.out.println(img.getWidth());
        if (img != null) {
            int frameWidth = 32;
            int frameHeight = 32;
            int totalRows = img.getHeight() / frameHeight;
            int totalCols = img.getWidth() / frameWidth;
            levelSprite = new BufferedImage[totalRows][totalCols];
            for (int row = 0; row < totalRows; row++) {
                for (int col = 0; col < totalCols; col++) {
                    levelSprite[row][col] = img.getSubimage(col * frameWidth, row * frameHeight, gp.TILESIZE,
                            gp.TILESIZE);
                }
            }
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < gp.MAXSCREENCOL && row < gp.MAXSCREENROW - 2) {
            g2.drawImage(tile[47].image, x, y, gp.TILESIZE, gp.TILESIZE, null);
            col++;
            x += gp.TILESIZE;
            if (col == gp.MAXSCREENCOL) {
                col = 0;
                x = 0;
                row++;
                y += gp.TILESIZE;
            }
        }
    }
}
