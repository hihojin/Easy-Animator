package model.effects;

import model.shapes.IShape;
import model.shapes.ShapeTypes;

/**
 * scale is one of animation effects on shapes.
 * 
 * @author hyojinkwak
 *
 */
public class ScalingEffect extends AbstractAnimationEffect {
  private int initialXSize;
  private int initialYSize;
  private int updatingXSize;
  private int updatingYSize;

  /**
   * scaling means resizing shapes. Should be able to set shape's xSize (width or
   * x-radius) and ySize (height or y-radius) by scaling.
   * 
   * @param startTime  start time of scaling effect on shape
   * @param endTime    end time of scaling effect on shape
   * @param shape      shape received for scaling during animation at certain time
   * @param initialXSize initial x value// width or radius
   * @param initialYSize initial y size
   * @param updatedXSize updated x value
   * @param updatedYSize updated y value
   */
  public ScalingEffect(int startTime, int endTime, IShape shape, int initialXSize,
      int initialYSize, int updatedXSize, int updatedYSize) throws IllegalArgumentException {
    
    super(startTime, endTime, shape);
    
    this.initialXSize = initialXSize;
    this.initialYSize = initialYSize;
    this.updatingXSize = updatedXSize;
    this.updatingYSize = updatedYSize;
    this.effectType = AnimationEffectTypes.SCALE;
  }
  
  @Override
  public int getXInitialSize() {
    return this.initialXSize;
  }
  
  @Override
  public int getYInitialSize() {
    return this.initialYSize;
  }
  
  @Override
  public int getFinalXSize() {
    return this.updatingXSize;
  }
  
  @Override
  public int getFinalYSize() {
    return this.updatingYSize;
  }
  
  @Override
  public void updatingShapeAttribute(IShape shape, int curTime) {
    int xSize = this.tweening(initialXSize, updatingXSize, startTime, finishTime, curTime);
    int ySize = this.tweening(initialYSize, updatingYSize, startTime, finishTime, curTime);
    shape.setXSize(xSize);
    shape.setYSize(ySize);
  }

  @Override
  public String toString() {
    String move = "";
    if (this.shape.getType() == ShapeTypes.RECTANGLE) {
      move = "Shape " + this.getShape().getName() + " scales from Width: "
          + this.initialXSize + ", Height: " + this.initialYSize + " to Width: " 
          + this.updatingXSize + ", Height: " + this.updatingYSize;
    }
    else if (this.shape.getType() == ShapeTypes.Ellipse) {
      move = "Shape " + this.getShape().getName() + " scales from X radius: "
          + this.initialXSize + ", Y radius: " + this.initialYSize + " to X radius: " 
          + this.updatingXSize + ", Y radius: " + this.updatingYSize;
    }

    return move;
  }

}
