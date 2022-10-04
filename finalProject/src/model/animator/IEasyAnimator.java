package model.animator;

import model.effects.IAnimationEffect;
import model.shapes.IShape;

import java.util.ArrayList;

/**
 * this is the interface of easy animator model. this animator model is going to
 * store shapes and animation effects, sort them by start time so they are
 * ordered for the animation.
 * 
 * @author hyojinkwak
 *
 */
public interface IEasyAnimator {

  /**
   * adding shapes in inserted order.
   * adding shapes only when the incoming name is unique;
   * every name of shape should be unique.
   * @param name shape name. name is unique.
   * @param shape shape to be added.
   */
  void addShapes(String name, IShape shape);
  
  /**
   * getting all shapes in the list.
   * @return shape list
   */
  ArrayList<IShape> getShapes();

  /**
   * add animation effects in a list.
   * animation should be ordered by start time of animation.
   * same type of animation can't happen in overlapping time.
   * i.e. one shape with the given name can't move to left and right in same time frame
   * @param effect animation effect
   * @param name shape name which animation effect will be applied.
   */
  void addAnimationEffect(String name, IAnimationEffect effect);
  
  /**
   * gets animations in the list, that is sorted by start time.
   * @return a copy of the animation list
   */
  ArrayList<IAnimationEffect> getAnimations();

  /**
   * getting list of animated shapes to show for animation in the time frame.
   * display these shapes in animation if this curTime is in valid range of start and end time.
   * @param curTime curtime frame
   * @return return the list of animated shapes respecting the curTime
   */
  ArrayList<IShape> getAnimatedShapesByTime(int curTime);
  
  /**
   * get the end time of animation.
   * animation ends time can be caluclated by knowing each shape's disappear time.
   * sets the duration of animation as the latest disappear time.
   * @return end time of animation
   */
  int getAnimationEndTime();
  
  /**
   * added method in the model. will be used to set canvas size.
   * @param x canvas x
   * @param y canvas y
   * @param width width of canvas
   * @param height height of canvas
   * @throws IllegalArgumentException when height or width are non positive values
   */
  void setCanvas(int x, int y, int width, int height) throws IllegalArgumentException;
  
  /**
   * get the x coordinate of canvas.
   * @return x coordinate value
   */
  int getCanvasX();
  
  /**
   * get the y coordinate of canvas.
   * @return y coordinate value
   */
  int getCanvasY();
  
  /**
   * get width of canvas.
   * @return width of canvas
   */
  int getCanvasWidth();
  
  /**
   * get height of canvas.
   * @return height of canvas
   */
  int getCanvasHeight();

}
