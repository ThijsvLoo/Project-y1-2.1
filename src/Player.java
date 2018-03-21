import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Entity {
  private static BufferedImage[] sprite;
  private static int iterator;
  private static Physics engine;

  public Player(BufferedImage[] image, float x, float y, int width, int height) {
    super(x, y, width, height);
    this.sprite = image;
    this.iterator = 0;
    //this.engine = new Physics();
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
    this.x+=5;
    this.y+=5;
  }
}
