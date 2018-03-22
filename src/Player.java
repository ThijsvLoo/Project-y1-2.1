import java.awt.Graphics;
import java.awt.Color;
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
    this.world = new World(handler,"../resources/world1.txt");
    this.sprite = image;
    this.iterator = 0;
    this.engine = new Physics(world);
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
    double vel = Math.sqrt(Math.pow((mouseX - this.x), 2) + Math.pow((mouseY - this.y), 2));

    double angle = Math.atan((mouseY - this.y)/(mouseX - this.x));
    if(mouseY-this.y>0 && mouseX-this.x<0)
        angle = Math.PI+Math.atan((mouseY - this.y)/(mouseX - this.x));
    else if(mouseY-this.y<0 && mouseX-this.x<0)
        angle = Math.PI+Math.atan((mouseY - this.y)/(mouseX - this.x));

    this.engine.setInMotion(vel, angle, new double[]{this.x, this.y});
    this.moving = true;
  }

  public void move(){
    this.engine.ballMotion();
    this.x = this.engine.ballPosition[0];
    this.y = this.engine.ballPosition[1];
    if(this.engine.ballVelocity <= 1){
      this.moving = false;
      this.engine.reset(false);
    }
  }

  public void reset(){
    this.x = 1740;
    this.y = 1000;
    this.moving = false;
    this.engine.reset(true);
  }

  public double getX(){
    return x;
  }
  public Physics getEngine(){return engine;}
}
