import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Boss1 extends GameObject {

  public Boss1(int x, int y, Type type) {
    super(x, y, type);
  }
  /**
  * Name - tick()
  * Description - This defines the boss movement
  */
  @Override
  public void tick() {
  }
    
  /**
  * Name - render(Graphics g)
  * Description - This method renders out
  * the first boss's image
  */
  @Override
  public void render(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect(this.x, this.y, 50, 50);
  }
  @Override
  public Rectangle hitbox() {
    return new Rectangle(this.x, this.y, 50, 50);    
  }

}
