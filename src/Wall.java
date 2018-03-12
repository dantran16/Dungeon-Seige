import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * 
 * Name - Wall
 * 
 * Description - This class is similar to the platform class
 * and the ground class. However, it's more vertical.
 *
 */
public class Wall extends GameObject {
  GameHandler handler;
  final int WIDTH = 10;
  final int HEIGHT = 800;
  public Wall(double x, double y, Type type, GameHandler handler) {
    super(x, y, type);
    this.handler = handler;
  }

  @Override
  public void tick() {
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.GRAY);
    g.fillRect((int) this.x, (int) this.y,WIDTH, HEIGHT);
  }

  @Override
  public Rectangle hitbox() {
    return new Rectangle((int) this.x, (int) this.y,WIDTH,HEIGHT);
  }
}
