package model.shapes;

/**
 * rectangular shape class.
 * 
 * @author hyojinkwak
 *
 */
public class Rectangle extends AbstractShape {

  /**
   * calling super constructor of abstract class, sets rectangular shape's object
   * value.
   * 
   * @param name          name of rectangle
   * @param type          type of shape is rectangle
   * @param xPosition     xPosition of lower corner.
   * @param yPosition     y position of lower corner.
   * @param width         width of rectangle
   * @param height        height of rectangle
   * @param color         color of rectangle
   * @param appearTime    appeartime of rectangle
   * @param disappearTime disappear time of rectangle
   */
  public Rectangle(String name, ShapeTypes type, int xPosition, int yPosition, int width,
      int height, Color color, int appearTime, int disappearTime)
      throws IllegalArgumentException {
    super(name, type, xPosition, yPosition, width, height, color, appearTime, disappearTime);
  }

  @Override
  public String toString() {
    String rectangle = "Name: " + this.name + '\n' + "type: " + this.type + '\n' + "Min corner: ("
        + this.xPosition + "," + this.yPosition + "), Width: " + this.xSize + ", Height: "
        + this.ySize + ", Color: (" + this.color.getRed() + "," + this.color.getGreen() + ","
        + this.color.getBlue() + ")\nAppears at t=" + this.appearTime + "\nDisappears at t="
        + this.disappearTime + '\n';

    return rectangle;
  }

}
