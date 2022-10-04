import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import model.animator.EasyAnimatorModel;
import model.animator.IEasyAnimator;
import model.effects.ColorEffect;
import model.effects.MovingEffect;
import model.effects.ScalingEffect;
import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;
import model.shapes.ShapeTypes;
import org.junit.Test;


/**
 * animator model methods testing.
 * @author hyojinkwak
 *
 */
public class IEasyAnimatorTest {

  /**
   * testing constructor. Initializing two lists for shape and effects.
   */
  @Test
  public void test() {
    IEasyAnimator animation = new EasyAnimatorModel();
    assertEquals(0, animation.getShapes().size());
    assertEquals(0, animation.getAnimations().size());
  }
  
  /**
   * adding shapes to shape hashmap.
   */
  @Test
  public void addShapeAndGetShapes() {
    IEasyAnimator animation = new EasyAnimatorModel();
    IShape o = new Oval("c", ShapeTypes.Ellipse, 0, 0, 0, 0, new Color(0, 0, 0), 0,
        0);
    animation.addShapes("c", o);
    assertEquals("Name: c\ntype: ellipse\nCenter: (0,0),"
        + " X radius: 0, Y radius: 0, Color: (0,0,0)\n"
        + "Appears at t=0\nDisappears at t=0\n", animation.getShapes().get(0).toString());
    
    
    o = new Oval("c", ShapeTypes.Ellipse, 0, 0, 0, 0, new Color(0, 0, 0), 0,
        0);
    // should not add.
    animation.addShapes("c", o);
    assertEquals("Name: c\ntype: ellipse\nCenter: (0,0),"
        + " X radius: 0, Y radius: 0, Color: (0,0,0)\n"
        + "Appears at t=0\nDisappears at t=0\n", animation.getShapes().get(0).toString());
    
    //should not add
    IShape r = new Rectangle("c", ShapeTypes.RECTANGLE, 1, 10, 20, 100, new Color(255, 0, 0), 1,
        100);
    animation.addShapes("c", r);
    assertEquals("Name: c\ntype: ellipse\nCenter: (0,0),"
        + " X radius: 0, Y radius: 0, Color: (0,0,0)\n"
        + "Appears at t=0\nDisappears at t=0\n", animation.getShapes().get(0).toString());
    
    r = new Rectangle("r", ShapeTypes.RECTANGLE, 1, 10, 20, 100, new Color(255, 0, 0), 1,
        100);
    animation.addShapes("r", r);
    
    assertEquals("Name: c\ntype: ellipse\nCenter: (0,0),"
        + " X radius: 0, Y radius: 0, Color: (0,0,0)\n"
        + "Appears at t=0\nDisappears at t=0\n", animation.getShapes().get(0).toString());
    assertEquals("Name: r\n"
        + "type: rectangle\n"
        + "Min corner: (1,10), Width: 20, Height: 100, Color: (255,0,0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n", animation.getShapes().get(1).toString());
    
    IShape d = new Oval("c1", ShapeTypes.Ellipse, 1, 2, 10, 4, new Color(1, 0, 0), 2,
        10);
    animation.addShapes("c1", d);
    assertEquals("Name: c1\ntype: ellipse\nCenter: (1,2),"
        + " X radius: 10, Y radius: 4, Color: (1,0,0)\n"
        + "Appears at t=2\nDisappears at t=10\n", animation.getShapes().get(2).toString());
    
  }
  
  /**
   * testing adding animation effect the the shape name requested.
   * updating
   */
  @Test
  public void testaddAnimationEffect() {
    IEasyAnimator animation = new EasyAnimatorModel();
    
    IShape r1 = new Rectangle("r", ShapeTypes.RECTANGLE, 1, 10, 20, 100, new Color(255, 0, 0), 1,
        100);
    animation.addShapes("r", r1);
    
    animation.addAnimationEffect("r", new MovingEffect(1, 1, r1, 1, 20, 2, 50));
    
    animation.addAnimationEffect("r", new MovingEffect(1, 50, r1, 2, 50, 50, 200));
    
    animation.addAnimationEffect("r", new ScalingEffect(25, 101, r1, 50, 200, 90, 30));
    
    animation.addAnimationEffect("r", new ColorEffect(10, 30, r1, new Color(255, 0, 1), 
        new Color(200, 1, 1)));
    
    animation.addAnimationEffect("r", new MovingEffect(50, 51, r1, 50, 200, 100, 100));
    
    animation.addAnimationEffect("r", new ScalingEffect(1, 25, r1, 90, 30, 5, 10));
    
    animation.addAnimationEffect("r", new ColorEffect(1, 5, r1, new Color(200, 1, 1), 
        new Color(255, 100, 1)));
    
    String actual = "";
    for (IShape shape: animation.getShapes()) {
      actual += shape.toString();
    }
    for (int i = 0; i < animation.getAnimations().size(); i++) {
      actual += animation.getAnimations().get(i).toString();
    }
    
    assertEquals("Name: r\n"
        + "type: rectangle\n"
        + "Min corner: (1,10), Width: 20, Height: 100, Color: (255,0,0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n" // not updated yet
        + "Shape r moves from (1,20) to (2,50)"
        + "Shape r moves from (2,50) to (50,200)"
        + "Shape r scales from Width: 90, Height: 30 to Width: 5, Height: 10"
        + "Shape r changes color from (200,1,1) to (255,100,1)"
        + "Shape r changes color from (255,0,1) to (200,1,1)"
        + "Shape r scales from Width: 50, Height: 200 to Width: 90, Height: 30"
        + "Shape r moves from (50,200) to (100,100)", actual);
    
  }
  
  /**
   * testing for getting shapes within the time given.
   */
  @Test
  public void testgetAnimatedShapesByTime() {
    IEasyAnimator animation = new EasyAnimatorModel();
    
    IShape r1 = new Rectangle("r", ShapeTypes.RECTANGLE, 1, 10, 20, 100, new Color(255, 0, 0), 1,
        50);
    animation.addShapes("r", r1);
    
    IShape o = new Oval("c", ShapeTypes.Ellipse, 10, 30, 4, 50, new Color(1, 255, 200), 1,
        80);
    animation.addShapes("c", o);
    
    IShape o2 = new Oval("f", ShapeTypes.Ellipse, 4, 1, 4, 50, new Color(1, 255, 200), 10,
        80);
    animation.addShapes("f", o2);
    
    animation.addAnimationEffect("r", new MovingEffect(1, 100, r1, 1, 10, 50, 200));
    
    animation.addAnimationEffect("c", new MovingEffect(1, 50, o, 10, 30, 50, 200));
    animation.addAnimationEffect("c", new ColorEffect(50, 55, o, new Color(1, 255, 200), 
        new Color(10, 255, 200)));
    animation.addAnimationEffect("f", new ScalingEffect(10, 55, o2, 4, 50, 100, 2));
    
    assertEquals(3, animation.getAnimatedShapesByTime(10).size());
    String actual = "";
    for (int i = 0; i < animation.getAnimatedShapesByTime(10).size(); i++) {
      actual += animation.getAnimatedShapesByTime(10).get(i).toString();
    }
    
    assertEquals("Name: r\n"
        + "type: rectangle\n"
        + "Min corner: (5,27), Width: 20, Height: 100, Color: (255,0,0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=50\n" 
        + "Name: c\n"
        + "type: ellipse\n"
        + "Center: (17,61), X radius: 4, Y radius: 50, Color: (1,255,200)\n"
        + "Appears at t=1\n"
        + "Disappears at t=80\n"
        + "Name: f\n"
        + "type: ellipse\n"
        + "Center: (4,1), X radius: 4, Y radius: 50, Color: (1,255,200)\n"
        + "Appears at t=10\n"
        + "Disappears at t=80\n", actual);
    
    // tested animation end time here. animation end time will be updated by builderImpl class
    // using addmotion method.
    assertEquals(80, animation.getAnimationEndTime());
  }
  
  /**
   * testing canvas/ window size.
   */
  @Test
  public void canvas() {
    IEasyAnimator animation = new EasyAnimatorModel();
    animation.setCanvas(100, 80, 70, 50);
    assertEquals(50, animation.getCanvasHeight());
    assertEquals(70, animation.getCanvasWidth());
    assertEquals(80, animation.getCanvasY());
    assertEquals(100, animation.getCanvasX());
    
    try {
      animation.setCanvas(0, 0, 0, 0);
      animation.setCanvas(100, 800, -5, 1);
      animation.setCanvas(100, 800, -5, -1);
      fail("exception was not thrown");
    }
    catch (IllegalArgumentException e) {
      // do nothing
    }
    
    try {
      animation.setCanvas(10, 0, 100, 43);
    }
    catch (IllegalArgumentException e) {
      fail("exception was thrown when it should not");
    }
  }
  
  

}
