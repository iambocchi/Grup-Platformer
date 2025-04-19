package levels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import utils.LoadSave;
import main.GamePanel;

public class TileManager {

    private GamePanel gp;
    public Tile[] tile;
    BufferedImage[][] levelSprite;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[48];
        mapTileNum = new int[gp.MAXSCREENROW][gp.MAXSCREENCOL];
        getTileSubImages(LoadSave.GetSpriteAtlas(LoadSave.TILESET_FOREST));
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.MAXSCREENCOL && row < gp.MAXSCREENROW) {
                String line = br.readLine();
                while (col < gp.MAXSCREENCOL) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.MAXSCREENCOL) {
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {

        }
        br.close();
    }

    private void getTileSubImages(BufferedImage tileset/* for 32 x 32 pixel */) {

        BufferedImage img = tileset;
        int tileIndex = 0;
        int frameWidth = 32;
        int frameHeight = 32;
        int totalRows = img.getHeight() / frameHeight;
        int totalCols = img.getWidth() / frameWidth;
        if (img != null) {
            levelSprite = new BufferedImage[totalRows][totalCols];
            for (int row = 0; row < totalRows; row++) {
                for (int col = 0; col < totalCols; col++) {
                    levelSprite[row][col] = img.getSubimage(col * frameWidth, row * frameHeight, frameWidth,
                            frameHeight);
                }
            }
        }
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                if (tileIndex >= 48) {
                    break;
                }
                tile[tileIndex] = new Tile();
                tile[tileIndex].image = levelSprite[i][j];
                tileIndex++;
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

            int tileNum = mapTileNum[row][col];
            g2.drawImage(tile[tileNum].image, x, y, gp.TILESIZE, gp.TILESIZE, null);
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
