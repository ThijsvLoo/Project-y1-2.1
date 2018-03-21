import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Entity {
  private static BufferedImage[] sprite;
  private static int iterator;

  public Player(float x, float y, int width, int height) {
    super(x, y, width, height);
    this.sprite = Assets.ball;
    this.iterator = 0;
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
    this.x++;
    this.y++;
  }
}