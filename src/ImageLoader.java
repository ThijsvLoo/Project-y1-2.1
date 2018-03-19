import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage loadImage(String path){

        try {
            return ImageIO.read(ImageLoader.class.getResource(path));                //Return a BufferedImage object of our Image
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);                                                    //In case error was found we close the program (no image - no game)
        }
        return null;
    }
}
