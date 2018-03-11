import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Name - KeyboardInput
 *
 * Description - This class lets us
 * control our character in the game
 * 
 * Source: 
 * https://www.youtube.com/watch?v=bWHYjLJZswQ 
 * ^ basic keyboard layout
 * https://www.youtube.com/watch?v=JrSjwQbTldg
 * ^ for smoothing out movement
 */
public class KeyboardInput extends KeyAdapter{
  
  private GameHandler handler;
  private boolean[] keyDown = new boolean[4];
  
  public KeyboardInput(GameHandler handler) {
    this.handler = handler;
    for(int i = 0; i < keyDown.length; i++) {
      keyDown[i] = false;
    }
  }
  /*
   * Name - keyPressed(KeyEvent e)
   * Description - This is the movement controls
   * for the game. When pressing a key, it will go
   * to that direction
   */
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    for(int i = 0; i < handler.object.size(); i++) {
      GameObject temp = handler.object.get(i);
      if(temp.getType() == Type.Player) {
    	Player player = (Player) temp;
        if(key==KeyEvent.VK_W || key == KeyEvent.VK_UP && !player.canJump()) {
          player.setJump(true);
          player.setSpeedY(-15);
          keyDown[0] = true;
        }
        if(key==KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
          temp.setSpeedY(5); 
          keyDown[1] = true;
        }
        if(key==KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
          temp.setSpeedX(-5);
          keyDown[2] = true;
        }
        if(key==KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
          temp.setSpeedX(5);
          keyDown[3] = true;
        }
      }
    }
    
  }
  /*
   * Name - keyReleased(KeyEvent e)
   * Description - This is the second part for the
   * movement controls. When releasing a key, it will stop moving in that
   * direction.
   */
  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    for(int i = 0; i < handler.object.size(); i++) {
      GameObject temp = handler.object.get(i);
      if(temp.getType() == Type.Player) {
        if(key==KeyEvent.VK_W || key == KeyEvent.VK_UP) keyDown[0] = false;
        if(key==KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyDown[1] = false;
        if(key==KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyDown[2] = false;
        if(key==KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyDown[3] = false;
        
        //vertical 
        if(!keyDown[0] && !keyDown[1]) temp.setSpeedY(0);
        //horizontal
        if(!keyDown[2] && !keyDown[3]) temp.setSpeedX(0);
      }
    }
  }
}
