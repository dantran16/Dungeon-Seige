import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Boss1 extends GameObject {
  private GameHandler handler;
  public Boss1(int x, int y, Type type, GameHandler handler) {
    super(x, y, type);
    this.handler = handler;
  }
  /**
  * Name - tick()
  * Description - This defines the boss movement
  */
  @Override
  public void tick() {
    damaged();
  }
    
  private void damaged() {
    for(int i = 0; i < handler.object.size(); i++) {
      GameObject temp = handler.object.get(i);
      if(temp.getType() == Type.Projectile) {
        if(temp.hitbox().intersects(this.hitbox())) {
          HUD.B_HEALTH -= .5;
        }
      }
    }
  }
  /**
  * Name - render(Graphics g)
  * Description - This method renders out
  * the first boss's image
  */
  @Override
  public void render(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect((int)this.x, (int)this.y, 50, 50);
  }
  @Override
  public Rectangle hitbox() {
    return new Rectangle((int)this.x, (int)this.y, 50, 50);    
  }

}
