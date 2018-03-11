import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ground extends GameObject{
	GameHandler handler;
	final int HEIGHT = 10;
	final int WIDTH = 800;
	public Ground(int x, int y, Type type, GameHandler handler) {
      super(x, y, type);
      this.handler = handler;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
      g.setColor(Color.GRAY);
      g.fillRect((int)this.x,(int)this.y,WIDTH, HEIGHT);
	}

	@Override
	public Rectangle hitbox() {
      return new Rectangle((int)this.x,(int)this.y,WIDTH,HEIGHT);
	}

}
