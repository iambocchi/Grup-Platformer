package levels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

import utils.LoadSave;
import main.GamePanel;

public class LevelManager {

    private GamePanel gp;
    public Tile[] tile;
    BufferedImage[][] levelSprite;
    public int mapTileNum[][];
    String map = "";

    public LevelManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[48];
        mapTileNum = new int[gp.MAXSCREENROW][gp.MAXSCREENCOL];
        // insert the tileset to get each subimage -> 32 x 32 pixel
        initTileset(LoadSave.GetSpriteAtlas(LoadSave.TILESET_FOREST), 11);

        // sets the map
        setMap("MAP_01");

        // loads the map
        loadMap();

    }

    public void setMap(String maplist) {
        switch (maplist) {
            case "MAP_01" -> map = LoadSave.MAP_01;
            default -> System.out.println("no map");
        }
    }

    public void loadMap() {
        try {
            // 26 width COL
            // 14 height ROW
            BufferedReader br = LoadSave.GetMap(map);
            int col = 0;
            int row = 0;
            while (col < gp.MAXSCREENCOL && row < gp.MAXSCREENROW) {
                String line = br.readLine();
                while (col < gp.MAXSCREENCOL) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                    col++;
                }
                if (col == gp.MAXSCREENCOL) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* for 32 x 32 pixel */
    private void initTileset(BufferedImage tileset, int indexNoCollision) {
        if (tileset == null)
            return;

        int tileIndex = 0;
        int frameWidth = 32;
        int frameHeight = 32;
        int totalRows = tileset.getHeight() / frameHeight;
        int totalCols = tileset.getWidth() / frameWidth;
        int totalIndex = totalRows * totalCols;

        if (tileset != null) {
            levelSprite = new BufferedImage[totalRows][totalCols];
            for (int row = 0; row < totalRows; row++) {
                for (int col = 0; col < totalCols; col++) {
                    levelSprite[row][col] = tileset.getSubimage(col * frameWidth, row * frameHeight, frameWidth,
                            frameHeight);
                }
            }
        }
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                if (tileIndex >= totalIndex) {
                    break;
                }
                tile[tileIndex] = new Tile();
                tile[tileIndex].image = levelSprite[i][j];
                // which tile has collision
                // still need to be modified to be universal
                if (tile[tileIndex] != tile[indexNoCollision]) {
                    tile[tileIndex].collision = true;
                }
                tileIndex++;
            }
        }
        System.out.println(tile.length);
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.MAXSCREENCOL && row < gp.MAXSCREENROW) {

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
