import java.awt.Rectangle;
import java.util.Random;


public class Fish {


  private Rectangle fishShape;
  private Boolean fishXDir;
  private Boolean fishYDir;
  private int fishSpeed;

  Fish(Rectangle fish) {

    this.fishSpeed = 2;
    this.fishShape = fish;
    Random random = new Random();
    this.fishXDir = Boolean.valueOf(random.nextBoolean());
    this.fishYDir = Boolean.valueOf(random.nextBoolean());
  }

  public void setXDir(Boolean direction) {

    this.fishXDir = direction;
  }

  public Boolean getXDir() {

    return this.fishXDir;
  }

  public void setYDir(Boolean direction) {

    this.fishYDir = direction;
  }

  public Boolean getYDir() {

    return this.fishYDir;
  }

  public void setX(int xCoord) {

    this.fishShape.x = xCoord;
  }

  public void setY(int yCoord) {

    this.fishShape.y = yCoord;
  }

  public int getX() {

    return (int)this.fishShape.getX();
  }

  public int getY() {

    return (int)this.fishShape.getY();
  }

  public int getHeight() {

    return (int)this.fishShape.getHeight();
  }

  public int getWidth() {

    return (int)this.fishShape.getWidth();
  }

  public int getSpeed() {

    return this.fishSpeed;
  }

}
