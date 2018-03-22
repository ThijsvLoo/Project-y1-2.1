import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.Math;

public class Player extends Entity {
  private static BufferedImage[] sprite;
  private static int iterator;
  private static Physics engine;
  private static World world;
  private Handler handler;
  public static boolean moving;

  public Player(Handler handler, BufferedImage[] image, float x, float y, int width, int height) {
    super(x, y, width, height);
    this.handler=handler;
    this.world = new World(handler,"resources/world1.txt");
    this.sprite = image;
    this.iterator = 0;
    this.engine = new Physics(this.world.width, this.world.height);
    this.moving = false;
    this.handler = handler;
  }

  public void render(Graphics g){
    g.drawImage(this.sprite[this.iterator], (int) this.x, (int) this.y, this.width, this.height, null);
  }

  public void tick() {     //This will update everything
    this.iterator++;
    if(this.iterator >= 2) this.iterator = 0;
    if(handler.getGame().getMouseManager().isLeftPressed())
      if(this.moving == false)
          hit(handler.getGame().getMouseManager().getMouseX(), handler.getGame().getMouseManager().getMouseY());
    if(handler.getGame().getMouseManager().isRightPressed())
        reset();
    if(this.moving)
        move();
  }

  public void hit(int mouseX, int mouseY){
    double vel = Math.sqrt(Math.pow(mouseX, 2) + Math.pow(mouseY, 2));
    double angle = Math.atan(mouseY/mouseX);
    this.engine.setInMotion(vel, angle, new double[]{this.x, this.y});
    this.moving = true;
  }

  public void move(){
    this.engine.collision();
    this.engine.ballMotion();
    this.x = this.engine.ballPosition[0];
    this.y = this.engine.ballPosition[1];
    if(this.engine.ballVelocity == 0)
      this.moving = false;
  }

  public void reset(){
    this.x = 10;
    this.y = 10;
    this.engine.reset();
  }

  public double getX(){
    return x;
  }
}