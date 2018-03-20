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

    public State(){

    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
