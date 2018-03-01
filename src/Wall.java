import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject {
  GameHandler handler;
  final int WIDTH = 10;
  final int HEIGHT = 800;
  public Wall(int x, int y, Type type, GameHandler handler) {
    super(x, y, type);
    this.handler = handler;
  }

  @Override
  public void tick() {
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.GRAY);
    g.fillRect(this.x,this.y,WIDTH, HEIGHT);
  }

  @Override
  public Rectangle hitbox() {
    return new Rectangle(this.x,this.y,HEIGHT,WIDTH);
  }
}
