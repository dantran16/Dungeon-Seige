import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Platform extends GameObject{
  GameHandler handler;
  final int HEIGHT = 10;
  final int WIDTH = 200;
  public Platform(int x, int y, Type type, GameHandler handler) {
    super(x, y, type);
    this.handler = handler;
	}

  @Override
  public void tick() {
    //TODO ADD A COLLISION
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.GRAY);
    g.fillRect((int)this.x,(int)this.y,WIDTH, HEIGHT);
    //below is to see hitbox
  }

  @Override
  public Rectangle hitbox() {
    return new Rectangle((int)x,(int)y,WIDTH,HEIGHT);
  }
  public Rectangle tophitbox() {
    return new Rectangle((int)x,(int)y,WIDTH,4);
  }
  public Rectangle bottomhitbox() {
    return new Rectangle((int)x,(int) y + HEIGHT - 4,WIDTH,4);
  }
  public Rectangle righthitbox() {
    return new Rectangle(WIDTH-4,(int)y,4,HEIGHT);
  }
  public Rectangle lefthitbox() {
    return new Rectangle((int)x,(int)y,4,HEIGHT);
  }
}
