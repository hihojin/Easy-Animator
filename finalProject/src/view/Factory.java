package view;

import model.effects.IAnimationEffect;
import model.shapes.IShape;

import java.util.ArrayList;


/**
 * factory view creates an instance of each view class depends on the view input
 * name from main.
 * 
 * @author hyojinkwak
 *
 */
public class Factory {

  /**
   * this method creates instances of each view class. will be called in
   * main/EasyAnimator.java
   * 
   * @param viewName name of view given from user from the main.
   * @param speed speed that converts from arbitray tick to ms or seconds
   * @param outputFileName appendable object output source
   * @param width width of canvas
   * @param height height of canvas
   * @param shapes shape list
   * @param effects effects list
   * @param endTime animation endTime
   * @throws IOException file writer exception
   */
  public static IView viewFactory(String viewName, int speed, Appendable outputFileName,
      int width, int height, ArrayList<IAnimationEffect> effects,
      ArrayList<IShape> shapes, int endTime) {
    if (viewName.equals("text")) {
      if (outputFileName == null) {
        return new TextView(speed, System.out, shapes, effects);
      }
      return new TextView(speed, outputFileName, shapes, effects);
    }

    else if (viewName.equals("svg")) {
      if (outputFileName == null) {
        return new SvgView(speed, System.out, shapes, effects, width, height, endTime);
      }
      return new SvgView(speed, outputFileName, shapes, effects, width, height, endTime);
    }
    else if (viewName.equals("visual")) {
      return new VisualView(shapes, speed, width, height);
    }
    //    else if (viewName.equals("playback")) {
    //      
    //    }
    return null;
  }
}
