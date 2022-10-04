package util;

import model.animator.IEasyAnimator;
import model.effects.ColorEffect;
import model.effects.MovingEffect;
import model.effects.ScalingEffect;
import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;
import model.shapes.ShapeTypes;

/**
 * animation builder class calls methods of EasyAnimator Model class.
 * 
 * @author hyojinkwak
 *
 */
public class AnimationBuilderImpl implements AnimationBuilder<IEasyAnimator> {
  private IEasyAnimator animatorModel;

  /**
   * animation builder class gets model as argument.
   */
  public AnimationBuilderImpl(IEasyAnimator animatorModel) {
    this.animatorModel = animatorModel;
  }

  @Override
  public IEasyAnimator build() {
    return this.animatorModel;
  }

  @Override
  public AnimationBuilder<IEasyAnimator> setBounds(int x, int y, int width, int height) {
    this.animatorModel.setCanvas(x, y, width, height);
    return this;
  }

  @Override
  public AnimationBuilder<IEasyAnimator> declareShape(String name, String type) {
    // initializing all other attributes to 0; default. as this add method only
    // takes 2 arguments
    if (type.equalsIgnoreCase(ShapeTypes.Ellipse.toString())) {
      IShape o = new Oval(name, ShapeTypes.Ellipse, 0, 0, 0, 0, new Color(0, 0, 0), 0, 0);
      this.animatorModel.addShapes(name, o);
    } else if (type.equalsIgnoreCase(ShapeTypes.RECTANGLE.toString())) {
      IShape r = new Rectangle(name, ShapeTypes.RECTANGLE, 0, 0, 0, 0, new Color(0, 0, 0), 0, 0);
      this.animatorModel.addShapes(name, r);
    }

    return this;
  }

  @Override
  public AnimationBuilder<IEasyAnimator> addMotion(String name, int t1, int x1, int y1, int w1,
      int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
      int b2) {
    /*
     * assume that shapes (with unique name) were added successfuly by the declare
     * shape method above: 1. find the shape with the name 2. update its initial
     * values with above arguments 3. call animation effect on that shape.
     */

    for (IShape shape : this.animatorModel.getShapes()) {
      if (shape.getName().equals(name)) { // name is unique

        // update (initialize) values for shape name found
        if (shape.getAppearTime() == 0 && shape.getDisappearTime() == 0) {
          shape.setAppearTime(t1);
          shape.setDisappearTime(t2);
          shape.setColor(new Color(r1, g1, b1));
          shape.setXPosition(x1);
          shape.setYPosition(y1);
          if (shape.getType() == ShapeTypes.Ellipse) {
            shape.setXSize(w1);
            shape.setYSize(h1);
          } else {
            shape.setXSize(w1);
            shape.setYSize(h1);
          }
        }
        // updating disappearance time here. (moved from animation model to reflect the end time
        // where shape doesn't change motion but only time goes till i.e t= 100)
        if (shape.getDisappearTime() < t2) {
          shape.setDisappearTime(t2);
        }

        // before adding animation first check if any intial values and final values are
        // different
        // else if -> if for all conditions as we want to check every changes
        if (x1 != x2 || y1 != y2) {
          this.animatorModel.addAnimationEffect(name,
              new MovingEffect(t1, t2, shape, x1, y1, x2, y2));

        } if (w1 != w2 || h1 != h2) {
          this.animatorModel.addAnimationEffect(name,
              new ScalingEffect(t1, t2, shape, w1, h1, w2, h2));

        } if (r1 != r2 || g1 != g2 || b1 != b2) {
          this.animatorModel.addAnimationEffect(name, new ColorEffect(t1, t2, shape,
              new Color(r1, g1, b1), new Color(r2, g2, b2)));
        }

      }

    }
    return this;
  }

}
