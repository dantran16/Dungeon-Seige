import java.awt.Color;
import java.awt.Graphics;
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
    g.fillRect(this.x,this.y,WIDTH, HEIGHT);
  }

  @Override
  public Rectangle hitbox() {
    // TODO NEED TO ADD A COLLISION
    return null;
  }

}
