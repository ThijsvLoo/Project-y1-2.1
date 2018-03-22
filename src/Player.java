import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Entity {
  private static BufferedImage[] sprite;
  private static int iterator;
  private static Physics engine;
  private static World world;

  public Player(World world, BufferedImage[] image, float x, float y, int width, int height) {
    super(x, y, width, height);
    this.world = world;
    this.sprite = image;
    this.iterator = 0;
    this.engine = new Physics(world.width, world.height);
    this.engine.setInMotion(10,Math.PI/4,new double[]{x, y});
  }

  public void render(Graphics g){
    g.drawImage(this.sprite[this.iterator], (int) this.x, (int) this.y, this.width, this.height, null);
    tick();
  }

  public void tick() {     //This will update everything
    this.iterator++;
    if(this.iterator >= 2) this.iterator = 0;
  }

  public void move() {
    this.engine.ballMotion();
    this.x = this.engine.ballPosition[0];
    this.y = this.engine.ballPosition[1];
  }
}