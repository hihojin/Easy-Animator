package model.shapes;

/**
 * oval shape class.
 * 
 * @author hyojinkwak
 *
 */
public class Oval extends AbstractShape {

  /**
   * constructs an oval shape object, sets its values.
   * 
   * @param name          of oval
   * @param type          type is oval
   * @param xPosition     center position x of oval
   * @param yPosition     center position y of oval
   * @param xRadius       radius at x of oval
   * @param yRadius       y radius of oval
   * @param color         color of oval
   * @param appearTime    appear time of oval
   * @param disappearTime disappear time of oval
   */
  public Oval(String name, ShapeTypes type, int xPosition, int yPosition, int xRadius,
      int yRadius, Color color, int appearTime, int disappearTime)
      throws IllegalArgumentException {
    super(name, type, xPosition, yPosition, xRadius, yRadius, color, appearTime, disappearTime);
  }

  @Override
  public String toString() {
    String oval = "Name: " + this.name + '\n' + "type: " + this.type + '\n' + "Center: ("
        + this.xPosition + "," + this.yPosition + "), X radius: " + this.xSize + ", Y radius: "
        + this.ySize + ", Color: (" + this.color.getRed() + "," + this.color.getGreen() + ","
        + this.color.getBlue() + ")\nAppears at t=" + this.appearTime + "\nDisappears at t="
        + this.disappearTime + '\n';

    return oval;

  }

}
