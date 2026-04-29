package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Key extends Entity {

    public Obj_Key(GamePanel gp){
        super(gp);

        name = "Key";
        stackable = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
        description = "Normal key.";
    }
}
