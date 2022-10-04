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
 * SVG view. if output is null, send the output to system.out or save to .svg
 * file
 * 
 * @author hyojinkwak
 *
 */
public class SvgView implements IView {
  private int speed;
  private Appendable output;
  private String viewName;
  private ArrayList<IAnimationEffect> effects;
  private ArrayList<IShape> shapes;
  private int width;
  private int height;
  private int endTime;

  /**
   * svg view constructor.
   * @param speed speed converts to ms from tick
   * @param output output file source
   * @param effects animation effects list
   * @param shapes shapes list 
   * @param width width of canvas
   * @param height height of canvas
   * @param endTime animation end time
   */
  public SvgView(int speed, Appendable output, ArrayList<IShape> shapes, 
      ArrayList<IAnimationEffect> effects,
      int width, int height, int endTime) {
    this.speed = speed;
    this.output = output;
    this.effects = effects;
    this.shapes = shapes;
    this.viewName = "svg";
    this.width = width;
    this.height = height;
    this.endTime = endTime;
  }

  @Override
  public void display(ArrayList<IShape> shapes) {
    // to make sure all shapes are visible, adding some arbitrary value 200 to the
    // canvas size.
    String out = "<svg width=\"" + (this.width + 200) + "\" height=\"" + (this.height + 200) + "\" "
        + "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" >\n\n";
    
    // invisible rect
    out += "<rect>\n" + "<animate id=\"base\" begin=\"0;base.end\" dur=\""
        + ((endTime * 1000) / speed) + "ms\" attributeName=\"visibility\""
        + " from=\"hide\" to=\"hide\"/>\n" + "</rect>\n";

    for (IShape s : this.shapes) {
      if (s.getType().toString() == ShapeTypes.RECTANGLE.toString()) {

        out += "<rect id=\"" + s.getName() + "\" x=\"" + s.getXPosition() + "\" y=\""
            + s.getYPosition() + "\" width=\"" + s.getXSize() + "\" height=\"" + s.getYSize()
            + "\" fill=\"rgb(" + s.getColor().getRed() + "," + s.getColor().getGreen() + ","
            + s.getColor().getBlue() + ")\" visibility=\"hidden\" >\n\n";
      }

      else if (s.getType().toString() == ShapeTypes.Ellipse.toString()) {
        out += "<ellipse id=\"" + s.getName() + "\" cx=\"" + s.getXPosition() + "\" cy=\""
            + s.getYPosition() + "\" rx=\"" + (s.getXSize() / 2)
            + "\" ry=\"" + (s.getYSize() / 2) + "\" fill=\"rgb(" + s.getColor().getRed() + ","
            + s.getColor().getGreen() + "," + s.getColor().getBlue()
            + ")\" visibility=\"hidden\" >\n\n";

      }

      out += "<animate attributeType=\"xml\" begin=\"base.begin+"
          + ((s.getAppearTime() * 1000) / speed) + "ms\" dur=\""
          + "1.0ms\" attributeName=\"visibility\" "
          + "from=\"hidden\" to=\"visible\" fill=\"freeze\" />";
      out += "\n";

      out += this.animation(s.getName());
      
      out += "<animate attributeType=\"xml\" begin=\"base.begin+"
          + ((s.getDisappearTime() * 1000) / speed) + "ms\" dur=\""
          + "1.0ms\" attributeName=\"visibility\" "
          + "from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n";
      if (s.getType().toString() == ShapeTypes.RECTANGLE.toString()) {
        out += "</rect>";
        out += "\n\n";
      } else if (s.getType().toString() == ShapeTypes.Ellipse.toString()) {
        out += "</ellipse>";
        out += "\n\n";
      }
    }

    out += "</svg>\n";

    // append to appendable
    try {
      this.output.append(out);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JFrame(), "there was a problem writing to the svg file");
    }
  }

  /**
   * helper method for animation.
   * 
   * @param effects effects sorted list by start time
   * @return string output-svg
   */
  private String animation(String name) {
    String out = "";
    for (int i = 0; i < effects.size(); i++) {
      if (effects.get(i).getShape().getName().equals(name)) {
        if (effects.get(i).getShape().getType().toString() == ShapeTypes.RECTANGLE.toString()) {

          if (effects.get(i).getEffectType() == AnimationEffectTypes.MOVE) {
            out += "<animate attributeType=\"xml\" begin=\"base.begin+"
                + ((effects.get(i).getStartTime() * 1000) / speed) + "ms\" " + "dur=\""
                + ((effects.get(i).getEndTime() - effects.get(i).getStartTime()) * 1000) / speed
                + "ms\" attributeName=\"x\" from=\"" + effects.get(i).getInitialXPosition()
                + "\" to=\"" + effects.get(i).getFinalXPosition() + "\" fill=\"freeze\" />";
            out += "\n";

            out += "<animate attributeType=\"xml\" begin=\"base.begin+"
                + ((effects.get(i).getStartTime() * 1000) / speed) + "ms\" " + "dur=\""
                + ((effects.get(i).getEndTime() - effects.get(i).getStartTime()) * 1000) / speed
                + "ms\" attributeName=\"y\" from=\"" + effects.get(i).getInitialYPosition()
                + "\" to=\"" + effects.get(i).getFinalYPosition() + "\" fill=\"freeze\" />\n";
            
            //loop back
            out += "<animate attributeType=\"xml\" begin=\"base.end-"
                + effects.get(i).getStartTime() + "ms\""
                + " dur=\"1ms\" attributeName=\"x\""
                + " to=\"" + effects.get(i).getInitialXPosition() + "\" fill=\"freeze\" />\n";
            
            out += "<animate attributeType=\"xml\" begin=\"base.end-"
                + effects.get(i).getStartTime() + "ms\""
                + " dur=\"1ms\" attributeName=\"y\""
                + " to=\"" + effects.get(i).getInitialYPosition() + "\" fill=\"freeze\" />";
            
            out += "\n\n";
          }

          else if (effects.get(i).getEffectType() == AnimationEffectTypes.SCALE) {
            out += "<animate attributeType=\"xml\" begin=\"base.begin+"
                + ((effects.get(i).getStartTime() * 1000) / speed) + "ms\" " + "dur=\""
                + ((effects.get(i).getEndTime() - effects.get(i).getStartTime()) * 1000) / speed
                + "ms\" attributeName=\"width\" from=\"" + effects.get(i).getXInitialSize()
                + "\" to=\"" + effects.get(i).getFinalXSize() + "\" fill=\"freeze\" />";
            out += "\n";

            out += "<animate attributeType=\"xml\" begin=\"base.begin+"
                + ((effects.get(i).getStartTime() * 1000) / speed) + "ms\" " + "dur=\""
                + ((effects.get(i).getEndTime() - effects.get(i).getStartTime()) * 1000) / speed
                + "ms\" attributeName=\"height\" from=\"" + effects.get(i).getYInitialSize()
                + "\" to=\"" + effects.get(i).getFinalYSize() + "\" fill=\"freeze\" />\n";
            
            //loop back
            out += "<animate attributeType=\"xml\" begin=\"base.end-"
                + effects.get(i).getStartTime() + "ms\""
                + " dur=\"1ms\" attributeName=\"width\""
                + " to=\"" + effects.get(i).getXInitialSize() + "\" fill=\"freeze\" />\n";
            
            out += "<animate attributeType=\"xml\" begin=\"base.end-"
                + effects.get(i).getStartTime() + "ms\""
                + " dur=\"1ms\" attributeName=\"height\""
                + " to=\"" + effects.get(i).getYInitialSize() + "\" fill=\"freeze\" />";
            
            out += "\n\n";

          }

          else if (effects.get(i).getEffectType() == AnimationEffectTypes.COLOR) {
            out += "<animate attributeType=\"xml\" begin=\"base.begin+"
                + ((effects.get(i).getStartTime() * 1000) / speed) + "ms\" " + "dur=\""
                + ((effects.get(i).getEndTime() - effects.get(i).getStartTime()) * 1000) / speed
                + "ms\" attributeName=\"fill\" from=\"rgb(" + effects.get(i).getInitialRed() + ","
                + effects.get(i).getInitialGreen() + "," + effects.get(i).getInitialBlue()
                + ")\" to=\"rgb(" + effects.get(i).getUpdatingRed() + ","
                + effects.get(i).getUpdatingGreen() + "," + effects.get(i).getUpdatingBlue()
                + ")\" fill=\"freeze\" />\n";
            
            //loopback
            out += "<animate attributeType=\"xml\" begin=\"base.end-"
                + effects.get(i).getStartTime() + "ms\""
                + " dur=\"1ms\" attributeName=\"fill\""
                + " to=\"rgb(" + effects.get(i).getInitialRed() + "," 
                + effects.get(i).getInitialGreen() + "," + effects.get(i).getInitialBlue() 
                + ")\" fill=\"freeze\" />";
            
            out += "\n";
          }

        }

        if (effects.get(i).getShape().getType().toString() == ShapeTypes.Ellipse.toString()) {
          if (effects.get(i).getEffectType() == AnimationEffectTypes.MOVE) {
            out += "<animate attributeType=\"xml\" begin=\"base.begin+"
                + ((effects.get(i).getStartTime() * 1000) / speed) + "ms\" " + "dur=\""
                + ((effects.get(i).getEndTime() - effects.get(i).getStartTime()) * 1000) / speed
                + "ms\" attributeName=\"cx\" from=\"" + effects.get(i).getInitialXPosition()
                + "\" to=\"" + effects.get(i).getFinalXPosition() + "\" fill=\"freeze\" />";
            out += "\n";

            out += "<animate attributeType=\"xml\" begin=\"base.begin+"
                + ((effects.get(i).getStartTime() * 1000) / speed) + "ms\" " + "dur=\""
                + ((effects.get(i).getEndTime() - effects.get(i).getStartTime()) * 1000) / speed
                + "ms\" attributeName=\"cy\" from=\"" + effects.get(i).getInitialYPosition()
                + "\" to=\"" + effects.get(i).getFinalYPosition() + "\" fill=\"freeze\" />\n";

            // loop back
            out += "<animate attributeType=\"xml\" begin=\"base.end-"
                + effects.get(i).getStartTime() + "ms\""
                + " dur=\"1ms\" attributeName=\"cx\""
                + " to=\"" + effects.get(i).getInitialXPosition() + "\" fill=\"freeze\" />\n";
            
            out += "<animate attributeType=\"xml\" begin=\"base.end-"
                + effects.get(i).getStartTime() + "ms\""
                + " dur=\"1ms\" attributeName=\"cy\""
                + " to=\"" + effects.get(i).getInitialYPosition() + "\" fill=\"freeze\" />";

            out += "\n\n";
          }

          else if (effects.get(i).getEffectType() == AnimationEffectTypes.SCALE) {
            out += "<animate attributeType=\"xml\" begin=\"base.begin+"
                + ((effects.get(i).getStartTime() * 1000) / speed) + "ms\" " + "dur=\""
                + ((effects.get(i).getEndTime() - effects.get(i).getStartTime()) * 1000) / speed
                + "ms\" attributeName=\"rx\" from=\"" + (effects.get(i).getXInitialSize() / 2)
                + "\" to=\"" + (effects.get(i).getFinalXSize() / 2) + "\" fill=\"freeze\" />";
            out += "\n";

            out += "<animate attributeType=\"xml\" begin=\"base.begin+"
                + (((effects.get(i).getStartTime() * 1000) / speed) + "ms\" " + "dur=\""
                    + ((effects.get(i).getEndTime() - effects.get(i).getStartTime()) * 1000)
                        / speed)
                + "ms\" attributeName=\"ry\" from=\"" + (effects.get(i).getYInitialSize() / 2)
                + "\" to=\"" + (effects.get(i).getFinalYSize() / 2) + "\" fill=\"freeze\" />\n";
            
            // loop
            out += "<animate attributeType=\"xml\" begin=\"base.end-"
                + effects.get(i).getStartTime() + "ms\""
              + " dur=\"1ms\" attributeName=\"rx\""
              + " to=\"" + effects.get(i).getXInitialSize() + "\" fill=\"freeze\" />\n";
            
            out += "<animate attributeType=\"xml\" begin=\"base.end-"
                + effects.get(i).getStartTime() + "ms\""
                + " dur=\"1ms\" attributeName=\"ry\""
                + " to=\"" + effects.get(i).getYInitialSize() + "\" fill=\"freeze\" />";
            
            out += "\n\n";

          } else if (effects.get(i).getEffectType() == AnimationEffectTypes.COLOR) {
            out += "<animate attributeType=\"xml\" begin=\"base.begin+"
                + (((effects.get(i).getStartTime() * 1000) / speed) + "ms\" " + "dur=\""
                    + ((effects.get(i).getEndTime() - effects.get(i).getStartTime()) * 1000)
                        / speed)
                + "ms\" attributeName=\"fill\" from=\"rgb(" + effects.get(i).getInitialRed() + ","
                + effects.get(i).getInitialGreen() + "," + effects.get(i).getInitialBlue()
                + ")\" to=\"rgb(" + effects.get(i).getUpdatingRed() + ","
                + effects.get(i).getUpdatingGreen() + "," + effects.get(i).getUpdatingBlue()
                + ")\" fill=\"freeze\" />\n";
            
            //loop
            out += "<animate attributeType=\"xml\" begin=\"base.end-"
                + effects.get(i).getStartTime() + "ms\""
                + " dur=\"1ms\" attributeName=\"fill\""
                + " to=\"rgb(" + effects.get(i).getInitialRed() + "," 
                + effects.get(i).getInitialGreen() + "," + effects.get(i).getInitialBlue() 
                + ")\" fill=\"freeze\" />";
            
            out += "\n";
          }
        }

      }
    }

    return out;
  }

  @Override
  public String getViewName() {
    this.viewName = "svg";
    return this.viewName;
  }

}
