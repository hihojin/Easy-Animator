package model.effects;

import model.shapes.IShape;

/**
 * interface of animation effect such as scale, move, change color on shapes: oval, rectangle.
 * @author hyojinkwak
 *
 */
public interface IAnimationEffect {
  
  /**
   * get the start time of this animation effect.
   * @return start time of animation effect
   */
  int getStartTime();
  
  /**
   * get the end time of this animation effect.
   * @return end time of this animation effect.
   */
  int getEndTime();
  
  /**
   * get the shape which this animation effect will be done.
   * having this method in animation effect is useful as i can access to shape's attributes
   * @return the shape of which animation effect will be applied.
   */
  IShape getShape();
  
  /**
   * get the animation effect type.
   * @return animation effect types: scale or move or change color effect on shape
   */
  AnimationEffectTypes getEffectType(); 
  
  /**
   * this method is utilizing tweening formula for updating attribute of shape with time.
   * @param curTime curtime given.
   */
  void updatingShapeAttribute(IShape shape, int curTime);
  
  /**
   * get initial red color.
   * @return initial red
   */
  int getInitialRed();
  
  /**
   * get initial green color.
   * @return initial green
   */
  int getInitialGreen();
  
  /**
   * get initial blue color.
   * @return initial blue
   */
  int getInitialBlue();
  
  /**
   * get updating red.
   * @return updating red
   */
  int getUpdatingRed();
  
  /**
   * get updating green.
   * @return updating green
   */
  int getUpdatingGreen();
  
  /**
   * get updating blue.
   * @return updating blue
   */
  int getUpdatingBlue();
  
  /**
   * get initial x position.
   * @return initial x position 
   */
  int getInitialXPosition();
  
  /**
   * get initial y position.
   * @return initial y position
   */
  int getInitialYPosition();
  
  /**
   * get updating x position.
   * @return x position
   */
  int getFinalXPosition();
  
  /**
   * get updating y position.
   * @return y position
   */
  int getFinalYPosition();
  
  /**
   * get initial width or radius.
   * @return initial x size
   */
  int getXInitialSize();
  
  /**
   * get initial height or y radius.
   * @return initial y size
   */
  int getYInitialSize();
  
  /**
   * get final x size.
   * @return final x size
   */
  int getFinalXSize();
  
  /**
   * get final y size.
   * @return y size.
   */
  int getFinalYSize();

}
