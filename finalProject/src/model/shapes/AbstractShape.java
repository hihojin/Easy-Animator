package model.shapes;

/**
 * abstract class of shape.
 * 
 * @author hyojinkwak
 *
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected ShapeTypes type;
  protected int xPosition;
  protected int yPosition;
  protected int xSize;
  protected int ySize;
  protected Color color;
  protected int appearTime;
  protected int disappearTime;

  /**
   * constructor of abstract shape class initializes properties of shpae.
   */
  public AbstractShape(String name, ShapeTypes type, int xPosition, int yPosition,
      int xSize, int ySize, Color color, int appearTime, int disappearTime)
      throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("shape name can't be none");
    }
    if (xSize < 0 || ySize < 0) {
      throw new IllegalArgumentException(xSize + " or " + ySize + "can't be negative values");
    }
    if (appearTime > disappearTime) {
      throw new IllegalArgumentException(
          "shape can't disappear first before if it hasn't appeared");
    }
    this.name = name;
    this.type = type;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.xSize = xSize;
    this.ySize = ySize;
    this.color = color;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public ShapeTypes getType() {

    return this.type;
  }

  @Override
  public int getXPosition() {

    return this.xPosition;
  }

  @Override
  public void setXPosition(int x) {
    this.xPosition = x;
  }

  @Override
  public int getYPosition() {

    return this.yPosition;
  }

  @Override
  public void setYPosition(int y) {
    this.yPosition = y;
  }

  @Override
  public int getXSize() {

    return this.xSize;
  }

  @Override
  public void setXSize(int xSize) {
    this.xSize = xSize;
  }

  @Override
  public int getYSize() {

    return this.ySize;
  }

  @Override
  public void setYSize(int ySize) {
    this.ySize = ySize;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setColor(Color newColor) {
    this.color = newColor;
  }

  @Override
  public int getAppearTime() {

    return this.appearTime;
  }
  
  @Override
  public void setAppearTime(int appear) {
    this.appearTime = appear;
  }

  @Override
  public int getDisappearTime() {

    return this.disappearTime;
  }
  
  @Override
  public void setDisappearTime(int disappear) {
    this.disappearTime = disappear;
  }

}
