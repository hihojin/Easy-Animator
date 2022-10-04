package model.shapes;

/**
 * shape interface represents 2D shapes.
 * @author hyojinkwak
 *
 */
public interface IShape {
  
  /**
   * get name of the shape. R: rectangle, O: oval.
   * @return name of shape.
   */
  String getName();
  
  /**
   * get type of shapes. type is defined in enum.
   * @return shape type.
   */
  ShapeTypes getType();
  
  /**
   * get the x position of shape.
   * @return x coordinate.
   */
  int getXPosition();
  
  /**
   * sets the new x position of shape.
   */
  void setXPosition(int x);
  
  /**
   * get the y position of shape.
   * @return y coordinate.
   */
  int getYPosition();
  
  /**
   * sets the new y position of shape.
   */
  void setYPosition(int y);
  
  /**
   * this x changes name in its concrete classes. width or xradius.
   * @return width of xradius depends on concrete shape.
   */
  int getXSize();
  
  /**
   * sets new x size.(width or x radius)
   */
  void setXSize(int xSize);
  
  /**
   * sets new y size (height or y radius).
   */
  void setYSize(int ySize);
  
  /**
   * y changes name in its concrete classes. height or yradius.
   * @return height or y radius depends on concrete shape.
   */
  int getYSize();

  /**
   * gets the color object of this shape.
   * @return color object of this shape.
   */
  Color getColor();
  
  /**
   * sets the color object of this shape.
   */
  void setColor(Color newColor);

  /**
   * appear time of shape.
   * @return appear time of shape.
   */
  int getAppearTime();
  
  /**
   * sets shape's appear time.
   * @param appear new appear time of shape
   */
  void setAppearTime(int appear);
  
  /**
   * gets disappear time of shape.
   * @return disappear time of shape.
   */
  int getDisappearTime();
  
  /**
   * sets shape's disappear time.
   * @param disappear new disappear time of shape
   */
  void setDisappearTime(int disappear);
  
}
