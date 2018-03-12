import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * 
 * Name - Game
 * 
 * Description - This class is inspired by a youtube series called 
 * Java Programming: Let's Build A Game. We used this series to help create 
 * the framework of the game.
 * 
 * Source - https://www.youtube.com/watch?v=1gir2R7G9ws
 */
public class Game extends Canvas implements Runnable{
  private static final long serialVersionUID = -2602731302445623775L;
  
  public final static int WIDTH = 800; 
  public final static int HEIGHT = 600;
  public final static int SPAWN_YPOS = HEIGHT - 70;
  public final static int FLOOR_YPOS = HEIGHT - 38;
  public final static int rWall_XPOS = WIDTH - 15;
  public final String TITLE = "Dungeon Seige";
  
  private Thread thread; // used to execute program
  private boolean running = false; //used to run the pr8ogram
  
  private GameHandler handler;
  private HUD hud;
  
  private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
  
  
  public Game() {
	handler = new GameHandler();
	this.addKeyListener(new KeyboardInput(handler));
	
    new GameWindow(WIDTH, HEIGHT, TITLE, this);
    
    hud = new HUD();
    
    //Game characters
    handler.addObject(new Player(WIDTH/2,SPAWN_YPOS,Type.Player, handler));
    handler.addObject(new Boss1(WIDTH * 2/3, HEIGHT/2, Type.Boss, handler));
    
    //The room setup
    handler.addObject(new Ground(0, FLOOR_YPOS, Type.Ground, handler));
    handler.addObject(new Ground(0, 0, Type.Ground, handler));
    handler.addObject(new Wall(0,0,Type.Wall, handler));
    handler.addObject(new Wall(rWall_XPOS,0,Type.Wall, handler));
    
    //Platform setup
    handler.addObject(new Platform(0, HEIGHT*7/12, Type.Platform, handler));
    handler.addObject(new Platform(WIDTH*3/4, HEIGHT*7/12, Type.Platform, handler));
    handler.addObject(new Platform(0, HEIGHT*3/4, Type.Platform, handler));
    handler.addObject(new Platform(WIDTH*3/4, HEIGHT*3/4, Type.Platform, handler));
    handler.addObject(new Platform(WIDTH*4/10, HEIGHT*5/6, Type.Platform, handler));
    handler.addObject(new Platform(WIDTH*4/10, HEIGHT*4/6, Type.Platform, handler));
  }
  
  public synchronized void start() {
	if(running)
	  return;
    thread = new Thread(this);
    thread.start();
    running = true;
  }
  
  public synchronized void stop() {
    if(!running)
      return;
    try {
      thread.join();
      running = false;
    }catch(Exception e) {
    	e.printStackTrace();
    }
  }
  /**
   * Name - run()
   * Description - This includes the render and a fps counter
   * in the console. I used the source to create the game loop.
   * Sources - https://www.youtube.com/watch?v=TNVHWROwYuM
   */
  public void run() {
	this.requestFocus();
	long lastTime = System.nanoTime();
	final double ticks = 60.0;
	double ns = 1000000000 / ticks;
	double delta = 0;
	int frames = 0;
	long timer = System.currentTimeMillis();
    while(running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      if(delta >= 1) {
        tick();
        delta--;
      }
      render();
      frames++;
      if(System.currentTimeMillis() - timer > 1000) {
    	  timer += 1000;
    	  System.out.println("FPS: "+ frames);
    	  frames = 0;
      }
    }
    stop();
  }
  
  /**
   * Name - tick()
   * Description - It basically updates all the objects in
   * real time in the game loop.
   */
  private void tick() {
    handler.tick();
    hud.tick();
  }
  /*
   * Name - render()
   * Description-
   * Source: https://www.youtube.com/watch?v=Nn8LH6T3xuc
   * https://www.youtube.com/watch?v=1gir2R7G9ws
   */
  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null) {
      createBufferStrategy(3);
      return;
    }
    
    Graphics g = bs.getDrawGraphics();
    
    
    // THIS IS THE GRAPHICS SECTION
    if(HUD.P_HEALTH > 0 || HUD.B_HEALTH > 0) {
      g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
      handler.render(g);
      hud.render(g);
    }
    // GAME OVER GRAPHICS
    if(HUD.P_HEALTH == 0) {
      Font font = new Font("Arial", Font.BOLD, 50);
      g.setFont(font);
      g.drawString("GAME OVER", WIDTH/3, HEIGHT/2);
    }
    //BEAT THE GAME GRAPHICS
    if(HUD.B_HEALTH == 0){
      Font font = new Font("Arial", Font.BOLD, 40);
      g.setFont(font);
      g.drawString("YOU BEAT THE GAME", WIDTH/4, HEIGHT/2);
    }
    
    g.dispose();
    bs.show();
  }
  /**
   * Name - border(int pos, int min, int max)
   * 
   * Description - This is used to make all the objects have
   * a boundary.
   * 
   * Source - https://www.youtube.com/watch?v=5ufOPX8N1Rg
   */
  public static double border(double pos, double min, double max) {
    if(pos >= max)
      return pos = max;
    else if(pos <= min)
      return pos = min;
    else
      return pos;
  }
  public static void main(String args[]) {
    new Game();
  }
}
