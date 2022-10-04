import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import model.effects.AnimationEffectTypes;
import model.effects.ColorEffect;
import model.effects.IAnimationEffect;
import model.effects.MovingEffect;
import model.effects.ScalingEffect;
import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;
import model.shapes.ShapeTypes;
import org.junit.Test;

/**
 * testing functions in interface of animation effect on shapes.
 * 
 * @author hyojinkwak
 *
 */
public class AnimationEffectTest {

  /**
   * testing constructor of animation effect.
   */
  @Test
  public void testConstructorAndMutations() {
    IShape o = new Oval("O", ShapeTypes.Ellipse, 200, 200, 50, 50, new Color(1, 0, 0), 1,
        100);
    assertEquals(1, o.getColor().getRed());
    assertEquals(0, o.getColor().getGreen());
    assertEquals(0, o.getColor().getBlue());

    IAnimationEffect c = new ColorEffect(50, 80, o, new Color(1, 0, 0), 
        new Color(200, 1, 1));
    
    assertEquals(50, c.getStartTime());
    assertEquals(80, c.getEndTime());
    assertEquals(o, c.getShape());
    assertEquals(AnimationEffectTypes.COLOR, c.getEffectType());
    
    assertEquals(1, c.getInitialRed());
    assertEquals(0, c.getInitialGreen());
    assertEquals(0, c.getInitialBlue());
    
    assertEquals(200, c.getUpdatingRed());
    assertEquals(1, c.getUpdatingBlue());
    assertEquals(1, c.getUpdatingBlue());
    
    c.updatingShapeAttribute(o, 55);
    assertEquals(34, o.getColor().getRed());
    assertEquals(0, o.getColor().getGreen());
    assertEquals(0, o.getColor().getBlue());

    IShape r = new Rectangle("R", ShapeTypes.RECTANGLE, 500, 200, 60, 10, new Color(0, 1, 1), 1,
        80);
    IAnimationEffect m = new MovingEffect(50, 80, r, 500, 200, 300, 100);
    
    m.updatingShapeAttribute(r, 50);
    assertEquals(500, r.getXPosition());
    assertEquals(200, r.getYPosition());
    
    m.updatingShapeAttribute(r, 75);
    assertEquals(333, r.getXPosition());
    assertEquals(116, r.getYPosition());
    
    m.updatingShapeAttribute(r, 80);
    assertEquals(300, r.getXPosition());
    assertEquals(100, r.getYPosition());
    
    r = new Rectangle("R", ShapeTypes.RECTANGLE, 500, 200, 60, 10, new Color(0, 1, 1), 1,
        100);
    IAnimationEffect s = new ScalingEffect(30, 70, r, 60, 10, 90, 30);
    s.updatingShapeAttribute(r, 30);
    assertEquals(60, r.getXSize());
    assertEquals(10, r.getYSize());
    
    s.updatingShapeAttribute(r, 50);
    assertEquals(75, r.getXSize());
    assertEquals(20, r.getYSize());
    
    s.updatingShapeAttribute(r, 70);
    assertEquals(90, r.getXSize());
    assertEquals(30, r.getYSize());
    
  }
  
  /**
   * testing exceptions.
   */
  @Test
  public void testExceptions() {
    IShape r = new Rectangle("R", ShapeTypes.RECTANGLE, 500, 200, 60, 10, new Color(0, 1, 1), 1,
        100);
    try {
      new ScalingEffect(-1, 70, r, 60, 10, 90, 30);
      new MovingEffect(80, 50, r, 500, 200, 300, 100);
      fail("exception was not thrown");
      
    } catch (IllegalArgumentException e) {
      // not needed
    }
    
    try {
      new MovingEffect(50, 50, r, 500, 200, 300, 100);
    }
    catch (IllegalArgumentException e) {
      fail("exception should not be thrown");
    }
  }
  
  /**
   * toString method testing.
   */
  @Test
  public void testToString() {
    IShape r = new Rectangle("R", ShapeTypes.RECTANGLE, 500, 200, 60, 10, new Color(0, 1, 1), 1,
        100);
    IAnimationEffect s = new ScalingEffect(30, 70, r, 60, 10, 90, 30);
    assertEquals("Shape R scales from Width: 60, Height: 10 to Width: 90, Height:"
        + " 30", s.toString());
    
    IAnimationEffect m = new MovingEffect(50, 80, r, 500, 200, 300, 100);
    assertEquals("Shape R moves from (500,200) to (300,100)", m.toString());
    
    IAnimationEffect c = new ColorEffect(50, 80, r, new Color(0, 1, 1), 
        new Color(200, 1, 1));
    assertEquals("Shape R changes color from (0,1,1) to (200,1,1)", c.toString());
  }

}
