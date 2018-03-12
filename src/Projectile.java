import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 
 * Name - Projectile
 * 
 * Description - This is used by the player
 * and the boss in order to attack each other.
 * These projectiles can damage its opponent.
 * This projectile can go in two directions.
 */
public class Projectile extends GameObject {
  private boolean right;
  public Projectile(double x, double y, Type type, boolean right) {
    super(x, y, type);
    this.right = right;
    this.speedX = 5;
  }

  @Override
  public void tick() {
	if(this.right == true)
      x+=speedX;
	else {
	  x-=speedX;
	}
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect((int) x, (int) y, 4, 4);
  }

  @Override
  public Rectangle hitbox() {
  // TODO Auto-generated method stub
    return new Rectangle((int) x, (int) y, 4, 4);
  }
  
}
