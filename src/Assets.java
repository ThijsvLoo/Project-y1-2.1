import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assets {

    public static final int width=16,height=16;   // The size of one tile

    public static BufferedImage grass,sand,water,hill;     //Tiles that are in game
    public static BufferedImage menu;
    public static BufferedImage[] ball;

    public static void init(){
        Util.SpriteSheet sheet = new Util.SpriteSheet(Util.ImageLoader.loadImage("../resources/sprite.png")); //Link to the spriteSheet
        Util.SpriteSheet golfsheet = new Util.SpriteSheet(Util.ImageLoader.loadImage("../resources/golfball.png")); //Link to golf ball sheet

        //Cropping every unit from the sprite sheet
        sand=sheet.crop(0,0,width,height);
        grass=sheet.crop(width,0,width,height);
        water=sheet.crop(width*2,0,width,height);
        ball=new BufferedImage[]{golfsheet.crop(0, 0, width/2, height/2), golfsheet.crop(width/2, 0, width/2, height/2)};
        try {
            menu=ImageIO.read(new File("../resources/menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
