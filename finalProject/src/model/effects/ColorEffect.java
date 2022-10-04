package model.effects;

import model.shapes.Color;
import model.shapes.IShape;

/**
 * color change animation effect class. chaninging color of shapes effect.
 * @author hyojinkwak
 *
 */
public class ColorEffect extends AbstractAnimationEffect {
  private int initialRed;
  private int updatingRed;
  private int initialGreen;
  private int updatingGreen;
  private int initialBlue;
  private int updatingBlue;

  /**
   * chaninging color of shapes animation effect.
   * @param startTime start time of color change animation effect
   * @param endTime finish time of color change effect
   * @param shape shape that color change will be applied during some time in animation duration
   * @param newColor new color to be updated
   * @param oldColor prev color
   */
  public ColorEffect(int startTime, int endTime, IShape shape,
      Color oldColor, Color newColor) throws IllegalArgumentException {
    super(startTime, endTime, shape);
    
    this.initialRed = oldColor.getRed();
    this.updatingRed = newColor.getRed();
    
    this.initialGreen = oldColor.getGreen();
    this.updatingGreen = newColor.getGreen();
    
    this.initialBlue = oldColor.getBlue();
    this.updatingBlue = newColor.getBlue();
    this.effectType = AnimationEffectTypes.COLOR;
  }
  
  @Override
  public int getInitialRed() {
    return this.initialRed;
  }
  
  @Override
  public int getInitialGreen() {
    return this.initialGreen;
  }
  
  @Override
  public int getInitialBlue() {
    return this.initialBlue;
  }
  
  @Override
  public int getUpdatingRed() {
    return this.updatingRed;
  }
  
  @Override
  public int getUpdatingGreen() {
    return this.updatingGreen;
  }
  
  @Override
  public int getUpdatingBlue() {
    return this.updatingBlue;
  }
  
  @Override
  public void updatingShapeAttribute(IShape shape, int curTime) {
    int red = this.tweening(initialRed, updatingRed, startTime, finishTime, curTime);
    int green = this.tweening(initialGreen, updatingGreen, startTime, finishTime, curTime);
    int blue = this.tweening(initialBlue, updatingBlue, startTime, finishTime, curTime);
    shape.setColor(new Color(red, green, blue));   
  }
  
  @Override
  public String toString() {
    String color = "Shape " + this.shape.getName() + " changes color from ("
                   + this.initialRed + "," + this.initialGreen + "," + this.initialBlue
                   + ") to (" + this.updatingRed + "," + this.updatingGreen + ","
                   + this.updatingBlue + ")";
    return color;
  }

}
