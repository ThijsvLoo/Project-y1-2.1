import java.awt.*;

public class Launcher {
    public static void main(String[] args){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Game game= new Game("Golf Game",screenSize.width,screenSize.height);
        game.start();

    }
}
