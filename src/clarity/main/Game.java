package clarity.main;

import clarity.graphics.entity.SpriteSheet;
import clarity.state.StateManager;
import clarity.utilities.Timer;
import clarity.utilities.input.Keyboard;
import clarity.utilities.input.Mouse;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

  /**
   * Default serial ID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Width of the window.
   */
  public static final int WIDTH = 480;
  /**
   * Height of the window.
   */
  public static final int HEIGHT = WIDTH / 16 * 9;
  /**
   * Scale of the window.
   */
  public static final int SCALE = 3;
  /**
   * Title of the game.
   */
  public static final String TITLE = "Clarity";

  /**
   * Set to true to enable full screen mode.
   */
  public static final boolean FULL_SCREEN_MODE = false;
  public static int monitorWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  public static int monitorHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  public static double monitorScale =
      Toolkit.getDefaultToolkit().getScreenSize().getWidth() / WIDTH;

  private JFrame frame;
  private Graphics2D graphics;
  private BufferedImage image;
  private StateManager manager;

  private Keyboard keyboard;
  private Mouse mouse;

  /**
   * Game constructor.
   */
  public Game() {
    setPreferredSize(new Dimension((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE)));
    setFocusable(true);
    requestFocus();
    keyboard = new Keyboard();
    mouse = new Mouse();
    addKeyListener(keyboard);
    addMouseListener(mouse);
    addMouseMotionListener(mouse);

    image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    graphics = (Graphics2D) image.getGraphics();

    frame = new JFrame();
    frame.setResizable(false);
    frame.setTitle(TITLE);
    frame.add(this);
    if (FULL_SCREEN_MODE) {
      frame.setUndecorated(true); // no boarders
    }
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    if (FULL_SCREEN_MODE) {
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen mode
    }
    frame.setVisible(true);
    createSplashScreen();
    run();
  }

  /**
   * Creates the splash screen on startup.
   */
  private void createSplashScreen() {
    BufferedImage splashScreen;
    try {
      splashScreen = ImageIO
          .read(SpriteSheet.class.getResourceAsStream("/textures/backgrounds/splashScreen.png"));
      Graphics g2 = (Graphics2D) getGraphics();
      g2.drawImage(splashScreen, (int) (monitorWidth - WIDTH * SCALE) / 2,
          (int) (monitorHeight - HEIGHT * SCALE) / 2, (int) (WIDTH * SCALE), (int) (HEIGHT * SCALE),
          null);
      g2.dispose();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  private void run() {
    Timer gameLoopTimer = new Timer();
    Timer titleTimer = new Timer();
    int frames = 0;
    int updates = 0;
    requestFocus();
    manager = new StateManager();

    while (true) {
      if (gameLoopTimer.hasElapsed(1000 / 60)) { // update 60 times per second
        update();
        updates++;
        gameLoopTimer.reset();
        render(); // render as fast as possible
        frames++;
      }
      if (titleTimer.hasElapsed(1000)) {
        frame.setTitle(TITLE + "  |  " + updates + " UPS, " + frames + " FPS");
        // System.out.println(TITLE + " | " + updates + " UPS, " + frames + " FPS");
        // System.out.println("Entities: " + Level.getEntities().size());
        // System.out.println("Particles: " + Level.getParticles().size());
        // System.out.println("Projectiles: " + Level.getProjectiles().size());
        updates = 0;
        frames = 0;
        titleTimer.reset();
      }
    }
  }

  private void update() {
    keyboard.update();
    manager.update();
  }

  private void render() {
    // StateManager.loadingScreen.render(graphics);
    manager.render(graphics);

    //////// add above here ////////
    Graphics graphics2 = (Graphics2D) getGraphics(); // gets image to render
    if (FULL_SCREEN_MODE) {
      // render image to screen
      graphics2.drawImage(image, (int) (monitorWidth - WIDTH * monitorScale) / 2,
          (int) (monitorHeight - HEIGHT * monitorScale) / 2, (int) (WIDTH * monitorScale),
          (int) (HEIGHT * monitorScale), null);
    } else {
      // render image to screen
      graphics2.drawImage(image, 0, 0, (int) (WIDTH * SCALE), (int) (HEIGHT * SCALE), null);
    }
    graphics2.dispose(); // dispose of graphics from memory
  }

}
