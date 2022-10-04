package model.effects;

import model.shapes.IShape;

/**
 * move animation effect class on shape.
 * 
 * @author hyojinkwak
 *
 */
public class MovingEffect extends AbstractAnimationEffect {
  private int initialXPosition;
  private int initialYPosition;
  private int finalXPosition;
  private int finalYPosition;

  /**
   * move constructor. should be able to update a shape's x and y position.
   * 
   * @param startTime      start time of move effect
   * @param endTime        end time of move effect
   * @param shape          shape applying move effect.
   * @param initialXPosition initial x position
   * @param initialYPosition initial y position
   * @param finalXPosition updated x position
   * @param finalYPosition updated y position
   */
  public MovingEffect(int startTime, int endTime, IShape shape, int initialXPosition,
      int initialYPosition, int finalXPosition, int finalYPosition)
          throws IllegalArgumentException {
    
    super(startTime, endTime, shape);
    
    this.initialXPosition = initialXPosition;
    this.initialYPosition = initialYPosition;
    this.finalXPosition = finalXPosition;
    this.finalYPosition = finalYPosition;
    this.effectType = AnimationEffectTypes.MOVE;
  }
  
  @Override
  public int getInitialXPosition() {
    return this.initialXPosition;
  }
  
  @Override
  public int getInitialYPosition() {
    return this.initialYPosition;
  }
  
  @Override
  public int getFinalXPosition() {
    return this.finalXPosition;
  }
  
  @Override
  public int getFinalYPosition() {
    return this.finalYPosition;
  }
  
  @Override
  public void updatingShapeAttribute(IShape shape, int curTime) {
    int xPosition = this.tweening(initialXPosition, finalXPosition, startTime, finishTime,
        curTime);
    int yPosition = this.tweening(initialYPosition, finalYPosition, startTime, finishTime,
        curTime);
    shape.setXPosition(xPosition);
    shape.setYPosition(yPosition);
  }
  
  @Override
  public String toString() {
    String move = "Shape " + this.getShape().getName() + " moves from (" + this.initialXPosition
        + "," + this.initialYPosition + ") to (" + this.finalXPosition + ","
        + this.finalYPosition + ")";
    
    return move;
  }

}
