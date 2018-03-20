import java.awt.*;

public class GameState extends State {
/*
That is our game state , which means we are currently not in menu ,but in the actual game ( so when the player will press PLAY in menu state , the game state will start)
here the rendering is called and the tick method + the path to our world is given . We can change that later when we add more levels
 */
    private World world;

    public GameState(){
        super();
        world=new World("res/lvl/world1.txt");
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);

    }
}
