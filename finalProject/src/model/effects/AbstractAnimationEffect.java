package model.effects;

import model.shapes.IShape;

/**
 * abstract class of animation effects.
 * 
 * @author hyojinkwak
 *
 */
public abstract class AbstractAnimationEffect implements IAnimationEffect {
  protected int startTime;
  protected int finishTime;
  protected IShape shape;
  protected AnimationEffectTypes effectType;

  /**
   * constructor of animation effects.sets shared fields among animation effects.
   * 
   * @param startTime  start time of this effect
   * @param endTime    end time of this effect
   * @param shape      shape received for this effect
   */
  public AbstractAnimationEffect(int startTime, int endTime, IShape shape)
      throws IllegalArgumentException {
    if (startTime > endTime) {
      throw new IllegalArgumentException("animation start time can't be bigger than finish time");
    }
    if (startTime < 0) {
      throw new IllegalArgumentException("start time should be at least 1");
    }
    
    this.startTime = startTime;
    this.finishTime = endTime;
    this.shape = shape;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }
  
  @Override
  public int getEndTime() {
    return this.finishTime;
  }
  
  @Override
  public IShape getShape() {
    return this.shape;
  }

  @Override
  public AnimationEffectTypes getEffectType() {
    return this.effectType;
  }

  /**
   * this is a tweening formula given from instruction. this formula can be used
   * for animation effects on shape, by chaning shape's attribute(s) during
   * animation. tweening is possible when start effect time <= curtime <= end
   * effect time
   * 
   * @param startval   start value of shape's attribute
   * @param finishVal  final value of shape's attribute
   * @param startTime  start time of animation effect
   * @param finishTime finish time of animation effect
   * @param curTime    cur time of animation effect
   * @return updatedAttribute values of shape with specific animation effect type.
   */
  protected final int tweening(int startval, int finishVal, int startTime,
      int finishTime, int curTime) {
    double t1 = (double)(finishTime - curTime) / (double)(finishTime - startTime);
    double t2 = (double)(curTime - startTime) / (double)(finishTime - startTime);
    double updated = (startval * t1) + (finishVal * t2);
    return (int) updated;
  }
  
  // these getter methods here are updated later for better access with (defualt values)
  
  @Override
  public int getInitialRed() {
    return 0;
  }
  
  @Override
  public int getInitialGreen() {
    return 0;
  }
  
  @Override
  public int getInitialBlue() {
    return 0;
  }
  
  @Override
  public int getUpdatingRed() {
    return 0;
  }
  
  @Override
  public int getUpdatingGreen() {
    return 0;
  }
  
  @Override
  public int getUpdatingBlue() {
    return 0;
  }
  
  @Override
  public int getInitialXPosition() {
    return 0;
  }
  
  @Override
  public int getInitialYPosition() {
    return 0;
  }
  
  @Override
  public int getFinalXPosition() {
    return 0;
  }
  
  @Override
  public int getFinalYPosition() {
    return 0;
  }
  
  @Override
  public int getXInitialSize() {
    return 0;
  }
  
  @Override
  public int getYInitialSize() {
    return 0;
  }
  
  @Override
  public int getFinalXSize() {
    return 0;
  }
  
  @Override
  public int getFinalYSize() {
    return 0;
  }

}
