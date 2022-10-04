package model.shapes;

/**
 * color of shape class.
 * @author hyojinkwak
 *
 */
public class Color {
  private int red;
  private int green;
  private int blue;
  
  /**
   * constructor color constructor initalizes color values. Creates color object.
   * @param red red value
   * @param green green value
   * @param blue blue value
   */
  public Color(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  
  /**
   * gets red color value.
   * @return red color.
   */
  public int getRed() {
    
    return this.red;
  }

  /**
   * green color of shape.
   * @return green color of shape
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * blue color of shape.
   * @return blue color of shape
   */
  public int getBlue() {
    
    return this.blue;
  }


}
