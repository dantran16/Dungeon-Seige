import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {
  final int XDISPLACEMENT = 45;
  final int YDISPLACEMENT = 68;
  final int playerSize = 32;
  GameHandler handler;
  private double gravity;
  private boolean falling;
  private boolean canJump;
  private int jumpHeight;
  private boolean shootRight;
  
  public Player(int x, int y, Type type, GameHandler handler) {
    super(x, y, type);
    this.handler = handler;
    this.falling = true;
    this.canJump = false;
    this.gravity = 1;
    this.jumpHeight = 30;
    this.shootRight = true;
  }
  
  @Override
  public Rectangle hitbox() {
    return new Rectangle((int)x,(int)y,playerSize,playerSize);
  }
  /**
   * Name: tick()
   * Description - This controls anything that relates
   * to time, such as attacking, moving, jumping, etc.
   */
  @Override
  public void tick() {
	if(HUD.P_HEALTH > 0 && HUD.B_HEALTH > 0) {
      move();
      fall();
      if(falling || canJump) {
        speedY += gravity;
        if(speedY > jumpHeight) {
          speedY = jumpHeight;
        }
      }
	  collision();
	  x = Game.border(x, 10, Game.WIDTH - XDISPLACEMENT);
      y = Game.border(y, 10, Game.HEIGHT - YDISPLACEMENT);
	}
  }
  
  /**
   * Name - move()
   * 
   * Description - This method just puts all the movement inside this.
   */
  private void move() {
    x+=speedX;
    y+=speedY;
  }

/**
   * Name - collision()
   * 
   * Description - This method makes the player lose health
   * if they touch an enemy's hitbox
   * 
   * Source - https://www.youtube.com/watch?v=HblfPi4v128
   */
  private void collision() {
	final double DAMAGE = .5;
    for(int i = 0; i < handler.object.size(); i++) {
      GameObject temp = handler.object.get(i);
      if(temp.getType() == Type.Boss || temp.getType() == Type.EnemyProjectile) {
    	//when any of the hitbox hits an enemy, lose health
        if(hitbox().intersects(temp.hitbox())) {
          HUD.P_HEALTH -= DAMAGE;
          //TODO: Add a knockback method/thingy
        }
      }
      if(temp.getType() == Type.Platform || temp.getType()== Type.Ground) {
        //top hitbox
    	if(tophitbox().intersects(temp.hitbox())) {
            y = temp.getY() + this.playerSize;
    	}
    	
    	if(hitbox().intersects(temp.hitbox())) {
    	  y = temp.getY() - this.playerSize;
          speedY = 0;
          falling = false;
          canJump = false;
    	}
    	else
    	  falling = true;
    	//right hitbox
    	if(righthitbox().intersects(temp.hitbox())) {
      	  x = temp.getX() - playerSize;
      	}
    	//Left hitbox
    	if(lefthitbox().intersects(temp.hitbox())){
    	  x = temp.getX() + 35;
    	}
      }
    }
  }
  public Rectangle tophitbox() {
    return new Rectangle((int)x,(int)y,playerSize,4);
  }
  public Rectangle bottomhitbox() {
    return new Rectangle((int)x,(int) y + playerSize - 4,playerSize,4);
  }
  public Rectangle righthitbox() {
    return new Rectangle((int)x+playerSize-4,(int)y,4,playerSize);
  }
  public Rectangle lefthitbox() {
    return new Rectangle((int)x,(int)y,4,playerSize);
  }
  
  /**
   * Name: render(Graphics g)
   * 
   * Description - This is the player model
   */
  @Override
  public void render(Graphics g) {
    g.setColor(Color.YELLOW);
    g.fillRect((int) this.x, (int)this.y,playerSize,playerSize);
    g.setColor(Color.RED);
    ((Graphics2D) g).draw(bottomhitbox());
    g.setColor(Color.BLUE);
    ((Graphics2D) g).draw(tophitbox());
    g.setColor(Color.YELLOW);
    ((Graphics2D) g).draw(righthitbox());
    g.setColor(Color.GREEN);
    ((Graphics2D) g).draw(lefthitbox());
  }
  
  private void fall() {
    if(falling) {
      y += gravity;
      if(speedY > jumpHeight) {
        speedY = jumpHeight;
      }
    }
  }
  public void jump(double jumpHeight) {
    if(canJump) {
      speedY -= jumpHeight;
      canJump = false;
    }
  }
  public double getJumpHeight() {
    return this.jumpHeight;
  }
  public boolean canJump() {
    return this.canJump;
  }
  public void setJump(boolean a) {
    this.canJump = a;
  }
  public void setShootRight(boolean a) {
    this.shootRight = a;
  }
  public boolean getShootRight() {
    return this.shootRight;
  }
}

