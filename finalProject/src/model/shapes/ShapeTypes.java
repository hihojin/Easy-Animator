package model.shapes;

/**
 * type safety for shape objects.
 *  currently has rectangle and oval.
 * @author hyojinkwak
 *
 */
public enum ShapeTypes {
  RECTANGLE("rectangle"), Ellipse("ellipse");
  
  private String shape;
  
  ShapeTypes(String shape) {
    this.shape = shape;
  }
  
  @Override
  public String toString() {
    return this.shape;
  }
}
