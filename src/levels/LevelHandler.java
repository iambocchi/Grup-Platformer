package levels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import utils.LoadSave;
import main.GamePanel;

public class LevelHandler {

    private GamePanel gp;
    private BufferedImage[][] levelSprite;

    public LevelHandler(GamePanel gp) {
        this.gp = gp;
        importOutsiteSprites();
    }

    private void importOutsiteSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.TILESET_FOREST);
        System.out.println(img.getWidth());
        if (img != null) {
            // 32 or 16
            int frameWidth = 32;
            int frameHeight = 32;

            int totalRows = img.getHeight() / frameHeight;
            int totalCols = img.getWidth() / frameWidth;

            levelSprite = new BufferedImage[totalRows][totalCols];

            for (int row = 0; row < totalRows; row++) {
                for (int col = 0; col < totalCols; col++) {
                    levelSprite[row][col] = img.getSubimage(col * frameWidth, row * frameHeight, frameWidth,
                            frameHeight);
                }
            }
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(levelSprite[1][8], 100, 100, null);
    }
}
