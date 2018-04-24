import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

public class Launcher {
    public static void main(String[] args){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Game game= new Game("Golf Game",screenSize.width,screenSize.height);

        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("../resources/music.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(999);
        }catch(Exception ex){ }


        game.start();

    }
}
