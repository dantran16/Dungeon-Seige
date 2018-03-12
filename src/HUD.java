import java.awt.Color;
import java.awt.Graphics;

/*
 * Name - HUD
 * Description - this contains all the
 * heads up display elements, such as
 * player's health bar and boss's health
 * 
 * Source - https://www.youtube.com/watch?v=5ufOPX8N1Rg
 */
public class HUD {
  public static double P_HEALTH = 100;
  public static double B_HEALTH = 100;
  
  public void tick() {
	final int MAX_HEALTH = 100;
    P_HEALTH = Game.border(P_HEALTH,0, MAX_HEALTH);
    B_HEALTH = Game.border(B_HEALTH,0, MAX_HEALTH);
  }
  public void render(Graphics g) {
	final int P_XPOS = 15;
	final int P_YPOS = 15;
	final int P_WIDTH = 200;
	final int P_HEIGHT = 25;
	
	final int B_XPOS = Game.WIDTH - 225;
	final int B_YPOS = 15;
	final int B_WIDTH = 200;
	final int B_HEIGHT= 25;
	
	//Player's health bar
    g.setColor(Color.GRAY);
    g.fillRect(P_XPOS, P_YPOS, P_WIDTH, P_HEIGHT);
    g.setColor(Color.GREEN);
    g.fillRect(P_XPOS, P_YPOS, (int) (P_HEALTH * 2), P_HEIGHT);
    g.setColor(Color.WHITE);
    g.drawRect(P_XPOS, P_YPOS, P_WIDTH, P_HEIGHT);
    
    //Boss's health bar
    //TODO: CHANGE THE BOSS HEALTH TO TRACK ENEMY +
    // MAKE IT SMALLER IF WE DO
    g.setColor(Color.GRAY);
    g.fillRect(B_XPOS, B_YPOS, B_WIDTH, B_HEIGHT);
    g.setColor(Color.YELLOW);
    g.fillRect(B_XPOS, B_YPOS, (int) (B_HEALTH * 2), B_HEIGHT);
    g.setColor(Color.WHITE);
    g.drawRect(B_XPOS, B_YPOS, B_WIDTH, B_HEIGHT);
    		
  }
}
