package view;

import model.effects.AnimationEffectTypes;
import model.effects.IAnimationEffect;
import model.shapes.IShape;
import model.shapes.ShapeTypes;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * text view class takes output file name in string and takes speed.
 * 
 * @author hyojinkwak
 *
 */
public class TextView implements IView {
  private int speed;
  private Appendable outputFileName; // different output source (appendable) can come in
  private String viewName;
  private ArrayList<IAnimationEffect> effects;
  private ArrayList<IShape> shapes;

  /**
   * text view constructor.
   * 
   * @param speed          speed converts to seconds. defualt is 1. but can be
   *                       changed if there is specified speed as argument from
   *                       the main.
   * @param outputFileName file name which output will be stored. If it is not
   *                       specified, goes to system.out.
   * @param shapes shape list
   * @parm effects animation effect list
   */
  public TextView(int speed, Appendable outputFileName, 
       ArrayList<IShape> shapes, ArrayList<IAnimationEffect> effects) {
    this.speed = speed;
    this.outputFileName = outputFileName;
    this.viewName = "text";
    this.effects = effects;
    this.shapes = shapes;
  }

  @Override
  public void display(ArrayList<IShape> shapes) {
    
    String output = "";
    
    for (IShape shape : this.shapes) {
      
      if (shape.getType().toString().equals("rectangle")) {
        
        output += "Create (" + shape.getColor().getRed() + ","
            + shape.getColor().getGreen() + " " + shape.getColor().getBlue()
            + ") " + shape.getType() + " " + shape.getName()
            + " with corner at (" + shape.getXPosition() + ","
            + shape.getYPosition() + "), width " + shape.getXSize() + " and "
            + "height " + shape.getYSize() + "\n";
        
      } else if (shape.getType().toString().equals("ellipse")) {
        output += "Create (" + shape.getColor().getRed() + ","
            + shape.getColor().getGreen() + " " + shape.getColor().getBlue()
            + ") " + shape.getType() + " " + shape.getName()
            + " with center at (" + shape.getXPosition() + ","
            + shape.getYPosition() + "), radius " + (shape.getXSize() / 2)
            + " and " + (shape.getYSize() / 2) + "\n\n";
      }
    }
    
    // converting time to seconds with speed
    for (IShape shape : this.shapes) {
      output += shape.getName() + " appears at time t=" 
          + (shape.getAppearTime() / speed)
          + " and disappears at time t="
          + (shape.getDisappearTime() / speed) + "\n";
    }

    output += "\n";
    
    for (int i = 0; i < effects.size(); i++) {
      if (effects.get(i).getEffectType() == AnimationEffectTypes.COLOR) {
        output += effects.get(i).getShape().getName() + " changes from rgb(" 
               + effects.get(i).getInitialRed() + "," + effects.get(i).getInitialGreen() + ","
               + effects.get(i).getInitialBlue() + ") to rgb(" + effects.get(i).getUpdatingRed()
               + "," + effects.get(i).getUpdatingGreen() + ","
               + effects.get(i).getUpdatingBlue() + ")";
      }
      else if (effects.get(i).getEffectType() == AnimationEffectTypes.MOVE) {
        output += effects.get(i).getShape().getName() + " moves from ("
               + effects.get(i).getInitialXPosition() + "," + effects.get(i).getInitialYPosition()
               + ") to (" + effects.get(i).getFinalXPosition() + ","
               + effects.get(i).getFinalYPosition() + ")";
      }
      
      else if (effects.get(i).getEffectType() == AnimationEffectTypes.SCALE) { // by shape
        if (effects.get(i).getShape().getType().toString() == ShapeTypes.RECTANGLE.toString()) {
          output += effects.get(i).getShape().getName() + " changes(width, height) from ("
              + effects.get(i).getXInitialSize() + "," 
              + effects.get(i).getYInitialSize() + ") to (" + effects.get(i).getFinalXSize() + ","
              + effects.get(i).getFinalYSize() + ")";
        }
        else if (effects.get(i).getShape().getType().toString() == ShapeTypes.Ellipse.toString()) {
          output += effects.get(i).getShape().getName() + " changes (rx, ry) from ("
              + (effects.get(i).getXInitialSize() / 2) 
              + "," + (effects.get(i).getYInitialSize() / 2)
              + ") to (" + (effects.get(i).getFinalXSize() / 2) + ","
              + (effects.get(i).getFinalYSize() / 2) + ")";
        }
        
      }
      
      output += " from t=" + (effects.get(i).getStartTime() / speed) 
             + " to t=" + (effects.get(i).getEndTime() / speed) + "\n";
    }
    
    try {
      this.outputFileName.append(output);
      
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JFrame(), "couldn't write to the file");
    }

  }

  @Override
  public String getViewName() {
    return this.viewName;
  }

}
