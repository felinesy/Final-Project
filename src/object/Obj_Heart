package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Heart extends Entity {

    public Obj_Heart(GamePanel gp){
        super(gp);
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
