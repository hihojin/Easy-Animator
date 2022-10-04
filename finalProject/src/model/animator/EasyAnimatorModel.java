package model.animator;

import model.effects.IAnimationEffect;
import model.shapes.IShape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**
 * this is the model of easy animator. Model will add shapes, animation effects,
 * get shapes by time, shows description of model and calculates animation's end
 * time.
 * 
 * @author hyojinkwak
 *
 */
public class EasyAnimatorModel implements IEasyAnimator {
  private ArrayList<IShape> shapes;
  private ArrayList<IAnimationEffect> animationEffects;
  
  // these are the attributes for the canvas(animation window)
  private int x;
  private int y;
  private int height;
  private int width;

  /**
   * constructor of animator model. constructor doesn't take arguments but
   * prepares two data structures: arraylists to store shapes and animations
   */
  public EasyAnimatorModel() {
    this.shapes = new ArrayList<>();
    this.animationEffects = new ArrayList<>();
  }

  @Override
  public void addShapes(String name, IShape shape) {
    if (this.shapes.isEmpty()) {
      this.shapes.add(shape);
    }
    
    HashSet<String> names = new HashSet<>();
    for (int i = 0; i < this.shapes.size(); i ++) {
      names.add(this.shapes.get(i).getName());
    }

    if (!names.contains(name)) {
      this.shapes.add(shape);
    }
  }

  @Override
  public ArrayList<IShape> getShapes() {
    ArrayList<IShape> shape = new ArrayList<>(this.shapes);
    return shape;
  }

  /**
   * private helpmer method checking whether animation effect can happen or not.
   * 
   * @param name   unique name of shape
   * @param effect animation effect
   * @return true if there is overlap
   */
  private boolean overlap(String name, IAnimationEffect effect) {

    // checking overlap (same name, same type, and overlap: invalid to add effects)
    for (IAnimationEffect animation : this.animationEffects) {
      if (animation.getShape().getName().equals(name)) {
        if (animation.getEffectType() == effect.getEffectType()) {
          // overlapping condition; excluding i.e. 1 ~20 and 20 ~ endtime
          if (animation.getStartTime() <  effect.getEndTime() 
              && animation.getEndTime() > effect.getStartTime()) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public void addAnimationEffect(String name, IAnimationEffect effect) {
    
    if (this.animationEffects.size() == 0) {
      this.animationEffects.add(effect);
      return;
    }
    
    if (!(this.overlap(name, effect))) {
      this.animationEffects.add(effect);
    }
    Collections.sort(this.animationEffects, startTime);
  }
  
  /**
   * proivate helper method to sort animation list above in order of 
   * animation start time in ascending order. idea from: (geeksforgeeks comparator explanation)
   */
  private static Comparator<IAnimationEffect> startTime = new Comparator<IAnimationEffect>() {
    public int compare(IAnimationEffect a1, IAnimationEffect a2) {
      Integer start1 = a1.getStartTime();
      Integer start2 = a2.getStartTime();
      
      return start1.compareTo(start2); // generic Interger type required.
    }
  };

  @Override
  public ArrayList<IShape> getAnimatedShapesByTime(int curTime) {
    ArrayList<IShape> animatedShapesWithinTime = new ArrayList<>();
    
    for (IAnimationEffect curEffect : this.animationEffects) {
      // animations at this time? if so, tween, and save in a variable so can be rendered.
      if (curEffect.getStartTime() <= curTime && curTime <= curEffect.getEndTime()) {
        // call tweening on shape stored in shapelist with the same name as cur_effect's
        // shape info.
        // because, effects list has animation effects such as move and this move object
        // or other
        // animationEffect object already has shape info (with a unique name) to be
        // applied for.
        // so here, getting same shape from the shapelist as curEffect's shape and
        // apply animation will be able to mutate that shape with the time provided.
        IShape shapeToBeUpdated = null;
        for (IShape s : this.shapes) {
          if (s.getName().equals(curEffect.getShape().getName())) {
            shapeToBeUpdated = s;
          }
        }
        if (shapeToBeUpdated != null) { // mutation
          curEffect.updatingShapeAttribute(shapeToBeUpdated, curTime);
        }
      }
    }

    // get shapes within the time frame for animation.
    for (IShape shape : this.shapes) {
      if (shape.getAppearTime() <= curTime && curTime <= shape.getDisappearTime()) {
        animatedShapesWithinTime.add(shape);
      }
    }

    return animatedShapesWithinTime;
  }

  @Override
  public int getAnimationEndTime() {
    int maxEndTime = -10; // some number that can never be max end time of animation.
    for (IShape shape: this.shapes) {
      if (shape.getDisappearTime() > maxEndTime) {
        maxEndTime = shape.getDisappearTime(); // gets repeatedly updated
      }
    }
    return maxEndTime;
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("canvas width and height should be positive values");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    
  }

  @Override
  public int getCanvasX() {
    return this.x;
  }

  @Override
  public int getCanvasY() {
    return this.y;
  }

  @Override
  public int getCanvasWidth() {   
    return this.width;
  }

  @Override
  public int getCanvasHeight() {
    return this.height;
  }

  @Override
  public ArrayList<IAnimationEffect> getAnimations() {
    ArrayList<IAnimationEffect> animations = new ArrayList<>();
    for (int i = 0; i < this.animationEffects.size(); i++) {
      animations.add(this.animationEffects.get(i));
    }
    return animations;
  }

}
