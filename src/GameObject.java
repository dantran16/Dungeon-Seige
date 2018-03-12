import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 
 * Name: GameObject
 * Description - This class will be used
 * to create any game object, like enemies,
 * the player, platforms, etc.
 * Sources - https://www.youtube.com/watch?v=0T1U0kbu1Sk&
 */
public abstract class GameObject {
  protected double x,y; // for positioning
  protected Type type;   // type of object
  protected int speedX, speedY; //speed of gameobject
  
  public GameObject(double x, double y, Type type) {
    this.x = x;
    this.y = y;
    this.type = type;
  }
  
  public abstract void tick();
  public abstract void render(Graphics g);
  public abstract Rectangle hitbox();
  
  
  public void setX(int x) {
    this.x = x;
  }
  public void setY(int y) {
    this.y = y;
  }
  public void setType(Type type) {
    this.type = type;
  }
  public void setSpeedX(int speedX) {
    this.speedX = speedX;
  }
  public void setSpeedY(int speedY) {
    this.speedY = speedY;
  }
  public double getX() {
    return this.x;
  }
  public double getY() {
    return this.y;
  }
  public Type getType() {
    return this.type;
  }
  public int getSpeedX() {
    return this.speedX;
  }
  public int getSpeedY() {
    return this.speedY;
  }

}
