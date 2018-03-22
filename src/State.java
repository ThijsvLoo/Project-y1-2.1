import java.awt.*;

public abstract class State {

    private static	State currentState=null;

    public static void setState(State state) {
        currentState = state;
    }  //method that sets the current state of the game

    public static State getState() {
        return currentState;
    }  //returns the current state

    //CLASS
    protected Handler handler;

    public State(Handler handler){
        this.handler=handler;

    }

    public abstract void tick();

    public abstract void render(Graphics g);


}

class GameState extends State {
/*
That is our game state , which means we are currently not in menu ,but in the actual game ( so when the player will press PLAY in menu state , the game state will start)
here the rendering is called and the tick method + the path to our world is given . We can change that later when we add more levels
 */
    private World world;

    public GameState(Handler handler){
        super(handler);
        world=new World("resources/world1.txt");
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