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
    this.world = handler.getWorld();
    this.sprite = image;
    this.iterator = 0;
    this.engine = new Physics(this.world);
    this.engine.setHeight();
    this.moving = false;
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
    if(handler.getGame().getMouseManager().isRightPressed()) {
      reset();
      State.setState(handler.getGame().menuState);
    }
    if(this.y>5*world.tileHeight && this.y<9*world.tileHeight
    && this.x>22*world.tileWidth &&  this.x<28*world.tileWidth  ||
    this.y>9*world.tileHeight && this.y<13*world.tileHeight
    && this.x>4*world.tileWidth &&  this.x<9*world.tileWidth)
        reset();

    if(this.y>13*world.tileHeight && this.y<14*world.tileHeight
        && this.x>27*world.tileWidth &&  this.x<28*world.tileWidth)
        gameOver();

    if(this.moving)
        move();
  }

  public void hit(int mouseX, int mouseY){
      double vel = Math.sqrt(Math.pow((mouseX - this.x), 2) + Math.pow((mouseY - this.y), 2));
      System.out.println("START");
      // double vel = 100;
      if(vel > 800) vel = 800;

      double angle = Math.atan((mouseY - this.y) / (mouseX - this.x));
      if (mouseY - this.y > 0 && mouseX - this.x < 0)
        angle = Math.PI + Math.atan((mouseY - this.y) / (mouseX - this.x));
      else if (mouseY - this.y < 0 && mouseX - this.x < 0)
        angle = Math.PI + Math.atan((mouseY - this.y) / (mouseX - this.x));

      this.engine.setInMotion(vel, angle, new double[]{this.x, this.y});
      //this.engine.setHeight();
      this.moving = true;
  }

  public void move(){

    this.engine.ballMotion();
    this.x = this.engine.ballPosition[0];
    this.y = this.engine.ballPosition[1];


    if(this.engine.ballVelocity <= 10 && Math.sqrt(this.engine.getGravity()[0]*this.engine.getGravity()[0]+
    this.engine.getGravity()[1]*this.engine.getGravity()[1])<150  ){

      this.moving = false;
      this.engine.reset(false);
    }
  }

  public void gameOver(){
    State.setState(handler.getGame().menuState);
    reset();
    //this.handler.getGame().stop();
  }

  public void reset(){
    this.x = 450;
    this.y = 450;
    this.moving = false;
    this.engine.reset(true);
  }



  public double getX(){
    return x;
  }
  public Physics getEngine(){return engine;}
}
