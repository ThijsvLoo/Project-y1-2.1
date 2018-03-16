import java.awt.image.BufferedImage;

public class Assets {

    private static final int width=16,height=16;   // The size of one tile

    public static BufferedImage ball,grass,sand,water,hill;     //Tiles that are in game

    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprite.png")); //Link to the spriteSheet
//Cropping every unit from the sprite sheet
        sand=sheet.crop(0,0,16,16);
        grass=sheet.crop(width,0,width,height);
        water=sheet.crop(width*2,0,width,height);

    }
}
