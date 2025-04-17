package assets;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Images {
    public BufferedImage ATTACK1,
            ATTACK2,
            CLIMB,
            DEATH,
            HURT,
            IDLE,
            JUMP,
            PUSH,
            RUN,
            THROW,
            WALK;

    public Images() {
        try {
            // player
            ATTACK1 = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/Owlet_Monster_Attack1_4.png"));
            ATTACK2 = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/Owlet_Monster_Attack2_6.png"));
            CLIMB = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/Owlet_Monster_Climb_4.png"));
            DEATH = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/Owlet_Monster_Death_8.png"));
            IDLE = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/Owlet_Monster_Idle_4.png"));
            JUMP = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/Owlet_Monster_Jump_8.png"));
            HURT = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/Owlet_Monster_Hurt_4.png"));
            PUSH = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/Owlet_Monster_Push_6.png"));
            RUN = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/Owlet_Monster_Run_6.png"));
            THROW = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/Owlet_Monster_Throw_4.png"));
            WALK = ImageIO.read(getClass().getResourceAsStream("/pixel/owlet monster/Owlet_Monster_Walk_6.png"));

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
