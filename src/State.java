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
    public World world;
    public int end =0;

    public GameState(Handler handler){
        super(handler);
        this.world=new World(handler,"resources/world1.txt");
        handler.setWorld(this.world);
    }

    @Override
    public void tick() {
        isGameOver();
        if(end>0){

           // State.setState(handler.getGame().menuState);
            //handler.getPhysics().reset(false);
            //System.exit(0);
        }

        world.tick();
    }
    public int isGameOver(){
        if (handler.getPhysics().ballPosition[0] > 1740 && handler.getPhysics().ballPosition[0] < 1785
                && handler.getPhysics().ballPosition[1] > 843 && handler.getPhysics().ballPosition[1] < 884) {
            end++;
        }
        return end;
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}

class MenuState extends State {

    public MenuState(Handler handler){
        super(handler);
    }
    @Override
    public void tick() {
       /* if(handler.getMouseManager().isLeftPressed())
            System.out.println(handler.getMouseManager().getMouseX() + " " + handler.getMouseManager().getMouseY()); */
        if((handler.getMouseManager().getMouseY()>419 && handler.getMouseManager().getMouseY()<528)
            &&(handler.getMouseManager().getMouseX()>142&&handler.getMouseManager().getMouseX()<584)
             && handler.getMouseManager().isLeftPressed()){
            try{
                Thread.sleep(200);
            }catch(InterruptedException e){
            }
            State.setState(handler.getGame().gameState);
        }
        else if((handler.getMouseManager().getMouseY()>575 && handler.getMouseManager().getMouseY()<685)
                &&(handler.getMouseManager().getMouseX()>140&&handler.getMouseManager().getMouseX()<590)
                && handler.getMouseManager().isLeftPressed()){
            System.exit(0);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menu,0,0,1920,1080,null);
    }
}
