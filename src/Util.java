import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Util {
    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));    //this method is used to read from the files
            String line;
            while((line =br.readLine()) != null)
                builder.append(line + "\n");
            br.close();
        }
        catch(IOException e){ e.printStackTrace(); }
        return builder.toString();
    }

    public static int parseInt(String number) {
        try{ return Integer.parseInt(number); }
        catch(NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    static class ImageLoader {
        public static BufferedImage loadImage(String path){
            try {
                return ImageIO.read(new File(path));
                //return ImageIO.read(ImageLoader.class.getResource(path));   //Return a BufferedImage object of our Image
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);     //In case error was found we close the program (no image - no game)
            }
            return null;
        }
    }

    static class SpriteSheet {
        private BufferedImage sheet;

        public SpriteSheet(BufferedImage sheet){ this.sheet=sheet; }

        public BufferedImage crop(int x,int y,int width,int height){    //The cropping method
            return sheet.getSubimage(x,y,width,height);
        }
    }
}
