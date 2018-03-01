import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
  final int playerSize = 32;
  GameHandler handler;
  public Player(int x, int y, Type type, GameHandler handler) {
    super(x, y, type);
    this.handler = handler;
  }
  
  @Override
  public Rectangle hitbox() {
    return new Rectangle(x,y,playerSize,playerSize);
  }
  /**
   * Name: tick()
   * Description - This controls anything that relates
   * to time, such as attacking, moving, jumping, etc.
   */
  @Override
  public void tick() {
    x+=speedX;
    y+=speedY;
    final int XDISPLACEMENT = 47;
    final int YDISPLACEMENT = 70;

    x = Game.border(x, 10, Game.WIDTH - XDISPLACEMENT);
    y = Game.border(y, 10, Game.HEIGHT - YDISPLACEMENT);
    collision();
  }
  /**
   * Name - collision()
   * 
   * Description - This method makes the player lose health
   * if they touch an enemy's hitbox
   */
  private void collision() {
    for(int i = 0; i < handler.object.size(); i++) {
      GameObject temp = handler.object.get(i);
      if(temp.getType() == Type.Boss) {
        if(hitbox().intersects(temp.hitbox())) {
          HUD.P_HEALTH -= 2;
          //TODO: Add a knockback method/thingy
        }
      }
    }
  }
  
  /**
   * Name: render(Graphics g)
   * 
   * Description - This is the player model
   */
  @Override
  public void render(Graphics g) {
    g.setColor(Color.YELLOW);
    g.fillRect(this.x,this.y,playerSize,playerSize);
  }

	
}

