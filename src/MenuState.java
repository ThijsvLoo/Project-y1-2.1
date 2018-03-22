import java.awt.*;

public class MenuState extends State {

    public MouseManager mouseManager;
    public Game game;

    public MenuState(Handler handler){
        super(handler);
        game= new Game();
       mouseManager = new MouseManager();
    }
    @Override
    public void tick() {
        if(handler.getMouseManager().isLeftPressed()){
            System.out.println(handler.getMouseManager().getMouseX() + " " + handler.getMouseManager().getMouseY());
        }
        if((handler.getMouseManager().getMouseY()>424 && handler.getMouseManager().getMouseY()<486)
            &&(handler.getMouseManager().getMouseX()>661&&handler.getMouseManager().getMouseX()<1260)
             && handler.getMouseManager().isLeftPressed()){
            State.setState(handler.getGame().gameState);
        }


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menu,0,0,1920,1080,null);
    }
}
