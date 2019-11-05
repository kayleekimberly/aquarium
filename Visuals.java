import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Visuals extends JPanel {


  static final Boolean DOWN = Boolean.valueOf(true);
  static final Boolean UP = Boolean.valueOf(false);
  static final Boolean RIGHT = Boolean.valueOf(true);
  static final Boolean LEFT = Boolean.valueOf(false);
  private static int frameHeight = 720;
  private static int frameWidth = 1280;
  private static JFrame mainFrame;
  private int fishWidth = 50;
  private int fishHeight = 37;
  private static ArrayList<Fish> fishes;
  private static BufferedImage fishImageR = null;
  private static BufferedImage fishImageL = null;
  private static BufferedImage background = null;

  Visuals() {

    try {

      fishImageR = ImageIO.read(Visuals.class.getResource("/fish_right.png"));
      fishImageL = ImageIO.read(Visuals.class.getResource("/fish_left.png"));
      background = ImageIO.read(Visuals.class.getResource("/underwater_bg.jpg"));

    } catch (IOException e) {
      e.printStackTrace();
    }

    createWindow();
    generateFish();

  }

  public void paint(Graphics g) {

    g.drawImage(background, 0, 0, null);

    for (Fish fish : fishes) {
      if (fish.getXDir() == RIGHT) {
        g.drawImage(fishImageR, fish.getX(), fish.getY(), null);
        continue;
      }
      g.drawImage(fishImageL, fish.getX(), fish.getY(), null);
    }
  }

  public void generateFish() {

    fishes = new ArrayList<>();

    for (int i = 0; i <= 10; i++) {
      Rectangle rectangle = new Rectangle(genX(), genY(), this.fishWidth, this.fishHeight);
      fishes.add(new Fish(rectangle));
    }
  }

  public static void updateFishPosition(Fish fish) {

    int currX = fish.getX();
    int currY = fish.getY();
    int fishSpeed = fish.getSpeed();

    if (fish.getXDir() == RIGHT) {
      if (currX + fishSpeed > frameWidth - fish.getWidth()) {
        fish.setXDir(LEFT);
        fish.setX(currX - fishSpeed);
      }
      else {
        fish.setX(currX + fishSpeed);
      }
    }
    else if (currX - fishSpeed < 0) {
      fish.setXDir(RIGHT);
      fish.setX(currX + fishSpeed);
    }
    else {
      fish.setX(currX - fishSpeed);
    }

    if (fish.getYDir() == DOWN) {
      if (currY + fishSpeed > frameHeight - fish.getHeight() * 2) {
        fish.setYDir(UP);
        fish.setY(currY - fishSpeed);
      }
      else {
        fish.setY(currY + fishSpeed);
      }

    }
    else {
      if (currY - fishSpeed < 0) {
        fish.setYDir(DOWN);
        fish.setY(currY + fishSpeed);
      }
      fish.setY(currY - fishSpeed);
    }
  }

  public static void moveFish() {

    for (Fish fish : fishes) {
      updateFishPosition(fish);
    }
    mainFrame.getContentPane().repaint();
  }

  public void paintComponent(Graphics g) {

    super.paintComponent(g); }

  public int genX() {

    int num = (new Random()).nextInt(frameWidth - this.fishWidth);
    return num;

  }

  public int genY() {

    int num = (new Random()).nextInt(frameHeight + this.fishHeight);
    return num;

  }

  public static void main(String[] args) {

    Visuals visuals = new Visuals();
    mainFrame.add(visuals);

    while (true) {
      try {
        TimeUnit.MILLISECONDS.sleep(50L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      moveFish();
    }
  }

  public void createWindow() {

    mainFrame = new JFrame("Aquarium");
    mainFrame.setSize(frameWidth, frameHeight);
    mainFrame.setResizable(false);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(3);
    mainFrame.getContentPane().setBackground(Color.decode("#6699ff"));
    mainFrame.setVisible(true);
    
  }
}
