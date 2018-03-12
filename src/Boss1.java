import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * 
 * Name - Boss1
 * Description - This is an object class for the boss.
 * He extends gameobject and has a set pattern for attack
 * and movement (depending on his health).
 *
 */
public class Boss1 extends GameObject {
  private GameHandler handler;
  private double gravity; 
  private boolean falling;
  private boolean moveRight;
  private int count;
  private int firerate;
  
  public Boss1(int x, int y, Type type, GameHandler handler) {
    super(x, y, type);
    this.handler = handler;
    this.falling = true;
    this.gravity = 1;
    this.moveRight = true;
    this.count = 0;
    this.firerate = 10;
  }
  /**
  * Name - tick()
  * Description - This defines the boss movement
  */
  @Override
  public void tick() {
	if(HUD.P_HEALTH > 0 && HUD.B_HEALTH > 0) {
	  if(count > 10)
	    move();
	  fall();
	  fire();
      damaged();
	}
  }
  /**
   * Name - fall()
   * Description - This method makes the boss
   * have gravity on him
   */
  private void fall() {
    if(falling) 
      y += gravity;
  }
  /**
   * Name - fire()
   * Description - This method makes the boss
   * shoot his projectiles
   */
  private void fire() {
	count++;
	Random random = new Random();
	int randomnumber = random.nextInt(2);
	boolean shootright = true;
	if(randomnumber == 0) shootright = false;
	if(count%firerate==0) {
	  handler.addObject(new Projectile(getX(),getY() +10, Type.EnemyProjectile, shootright));
	}
  }
  
  /**
   * Name - move()
   * Description - This is how the boss moves
   * If the boss has more than 50 health, he will move diagonally from the left side or
   * the right side.
   * If the boss has less than 50 health, he will move vertically randomly, but with
   * a faster fire rate.
   */
  private void move() {
	final int MOVESPEED = 5;
    x+=speedX;
    boolean ground = false;
    boolean right = moveRight;
    for(int i = 0; i < handler.object.size(); i++) {
      GameObject temp = handler.object.get(i);
      if(temp.getType() == Type.Platform || temp.getType() == Type.Ground || temp.getType() == Type.Wall) {
        if(temp.hitbox().intersects(this.hitbox())) {
          ground = true;   
        }
      }
    }
    //He goes in a straight line then teleports
    if(HUD.B_HEALTH > 50 && ground == true) {
      Random random = new Random();
  	  int randomnumber = random.nextInt(Game.WIDTH/2);
  	  if(right == true) {
  		this.setX(Game.WIDTH-100);
  	    this.setSpeedX(MOVESPEED * -1);
  	    this.moveRight = false;
  	  }
  	  else {
  		this.setX(100);
  	    this.setSpeedX(MOVESPEED);
  	    this.moveRight = true;
  	  }
  	  randomnumber = random.nextInt(Game.HEIGHT/2);
  	  this.setY(randomnumber);
    }
    // He teleports when he has less than 50% health
    else if(HUD.B_HEALTH < 50 && ground == true) {
      this.firerate = 3;
      this.setSpeedX(0);
      Random random = new Random();
	  int randomnumber = random.nextInt(Game.WIDTH/2);
	  this.setX(randomnumber);
	  randomnumber = random.nextInt(Game.HEIGHT/2);
	  this.setY(randomnumber);
	  ground = false;
	}
  }
  /*
   * Name - damaged()
   * Description - This method ticks his health down
   * if he receives any of the player projectiles.
   */
  private void damaged() {
    final double DAMAGE = .5;
    for(int i = 0; i < handler.object.size(); i++) {
      GameObject temp = handler.object.get(i);
      if(temp.getType() == Type.PlayerProjectile) {
        if(temp.hitbox().intersects(this.hitbox())) {
          HUD.B_HEALTH -= DAMAGE;
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
