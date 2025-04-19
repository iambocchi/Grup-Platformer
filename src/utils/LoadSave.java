package utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class LoadSave {
    // PLAYER SPRITE / ATLAS
    public static final String PLAYER_ATTACK1 = "pixel/owlet monster/Owlet_Monster_Attack1_4.png";
    public static final String PLAYER_ATTACK2 = "pixel/owlet monster/Owlet_Monster_Attack2_6.png";
    public static final String PLAYER_CLIMB = "pixel/owlet monster/Owlet_Monster_Climb_4.png";
    public static final String PLAYER_DEATH = "pixel/owlet monster/Owlet_Monster_Death_8.png";
    public static final String PLAYER_IDLE = "pixel/owlet monster/Owlet_Monster_Idle_4.png";
    public static final String PLAYER_JUMP = "pixel/owlet monster/Owlet_Monster_Jump_8.png";
    public static final String PLAYER_HURT = "pixel/owlet monster/Owlet_Monster_Hurt_4.png";
    public static final String PLAYER_PUSH = "pixel/owlet monster/Owlet_Monster_Push_6.png";
    public static final String PLAYER_RUN = "pixel/owlet monster/Owlet_Monster_Run_6.png";
    public static final String PLAYER_THROW = "pixel/owlet monster/Owlet_Monster_Throw_4.png";
    public static final String PLAYER_WALK = "pixel/owlet monster/Owlet_Monster_Walk_6.png";
    public static final String TILESET_FOREST = "outside_sprites.png";

    // MAPS
    public static final String MAP_01 = "maps/map01.txt";

    // GET MAP
    public static BufferedReader GetMap(String filename) {
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = LoadSave.class.getResourceAsStream("/" + filename);
            br = new BufferedReader(new InputStreamReader(is));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return br;
    }

    public static BufferedImage GetSpriteAtlas(String filename) {
        BufferedImage img = null;
        try {
            // getting image
            img = ImageIO.read(LoadSave.class.getResourceAsStream("/" + filename));
        } catch (Exception e) {
            System.out.println("IMAGE NOT FOUND");
        }
        return img;
    }
}
