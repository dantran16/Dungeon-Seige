import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
/*
 * Name - GameWindow
 * This is a class that creates the window and it uses
 * JFrame as the window. The sources below helped me
 * make a framework for the game.
 * Derived from: https://www.youtube.com/watch?v=gHh_96Ss1AI 
 * https://www.youtube.com/watch?v=1gir2R7G9ws
 * https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
 */
public class GameWindow extends Canvas{
  private static final long serialVersionUID = -2451971591139340573L;

  public GameWindow(int width, int height, String title, Game game) {
    JFrame window = new JFrame(title);
    
    window.setPreferredSize(new Dimension(width, height));
    window.setMaximumSize(new Dimension(width, height));
    window.setMinimumSize(new Dimension(width, height));
    window.setResizable(false);
    
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.add(game);
    window.pack();
    window.setVisible(true);
    game.start();
  }
}
