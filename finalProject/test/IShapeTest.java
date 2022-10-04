import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;
import model.shapes.ShapeTypes;
import org.junit.Test;


/**
 * testing functions in IShape interface.
 * 
 * @author hyojinkwak
 *
 */
public class IShapeTest {

  /**
   * constructor valid test.
   */
  @Test
  public void constructorTest() {
    IShape ellipse = new Oval("O", ShapeTypes.Ellipse, 200, 200, 50, 50, new Color(1, 0, 0), 1,
        100);

    assertEquals("Name: O\n"
        + "type: ellipse\nCenter: (200,200), X radius: 50, Y radius: 50,"
        + " Color: (1,0,0)\n"
        + "Appears at t=1\nDisappears at t=100\n", ellipse.toString());
    
    IShape rect = new Rectangle("R", ShapeTypes.RECTANGLE, 500, 200, 60, 10, new Color(0, 1, 1), 1,
        80);
    assertEquals("Name: R\n"
        + "type: rectangle\nMin corner: (500,200), Width: 60, Height: 10,"
        + " Color: (0,1,1)\n"
        + "Appears at t=1\nDisappears at t=80\n", rect.toString());
   
  }
  
  /**
   * exceptions cases.
   */
  @Test
  public void constructorExceptions() {
    try {
      new Oval("", ShapeTypes.Ellipse, 200, 200, 50, 50, new Color(1, 0, 0), 1,
        100);
      fail("exception was not thrown");
      new Oval("r", ShapeTypes.Ellipse, 200, 200, -50, -50, new Color(1, 0, 0), 1,
          100);
      fail("exception was not thrown");
    }
    catch (IllegalArgumentException e) {
      assertTrue(ShapeTypes.Ellipse != ShapeTypes.RECTANGLE);
    }
    
    try {
      new Oval("r", ShapeTypes.Ellipse, 200, 200, 50, 50, new Color(1, 0, 0), 100,
          1);
      fail("exception was not thrown");
    }
    catch (IllegalArgumentException e) {
      assertTrue(ShapeTypes.Ellipse != ShapeTypes.RECTANGLE);
    }

  }
  
  /**
   * test get name.
   */
  @Test
  public void testName() {
    IShape rect = new Rectangle("R", ShapeTypes.RECTANGLE, 500, 200, 60, 10, new Color(0, 1, 1), 1,
        80);
    assertEquals("R", rect.getName());
  }
  
  /**
   * get type.
   */
  @Test
  public void testType() {
    IShape rect = new Rectangle("R", ShapeTypes.RECTANGLE, 500, 200, 60, 10, new Color(0, 1, 1), 1,
        80);
    assertEquals("rectangle", rect.getType().toString());
  }
  
  /**
   * get/set x position.
   */
  @Test
  public void testXposition() {
    IShape rect = new Rectangle("R", ShapeTypes.RECTANGLE, 500, 200, 60, 10, new Color(0, 1, 1), 1,
        80);
    assertEquals(500, rect.getXPosition());
    rect.setXPosition(-100);
    assertEquals(-100, rect.getXPosition());
  }

  /**
   * get/set y position.
   */
  @Test
  public void testYposition() {
    IShape rect = new Rectangle("R", ShapeTypes.RECTANGLE, 500, 200, 60, 10, new Color(0, 1, 1), 1,
        80);
    assertEquals(200, rect.getYPosition());
    rect.setYPosition(500);
    assertEquals(500, rect.getYPosition());
  }
  
  /**
   * get/set width or x radius.
   */
  @Test
  public void xSize() {
    IShape rect = new Rectangle("R", ShapeTypes.RECTANGLE, 500, 200, 60, 10, new Color(0, 1, 1), 1,
        80);
    assertEquals(60, rect.getXSize());
    rect.setXSize(10);
    assertEquals(10, rect.getXSize());
  }
  
  /**
   * get/set height or y radius.
   */
  @Test
  public void ySize() {
    IShape o = new Oval("r", ShapeTypes.Ellipse, 200, 200, 50, 50, new Color(1, 0, 0), 1,
        100);
    assertEquals(50, o.getYSize());
    o.setYSize(10);
    assertEquals(10, o.getYSize());
  }

  /**
   * get color of shape./ set shape
   */
  @Test
  public void color() {
    IShape o = new Oval("r", ShapeTypes.Ellipse, 200, 200, 50, 50, new Color(1, 0, 0), 1,
        100);
    
    assertEquals(1, o.getColor().getRed());
    assertEquals(0, o.getColor().getGreen());
    assertEquals(0, o.getColor().getBlue());
    
    o.setColor(new Color(255, 255, 255));
    assertEquals(255, o.getColor().getRed());
    assertEquals(255, o.getColor().getGreen());
    assertEquals(255, o.getColor().getBlue());
  }

  /**
   * get/set appear time.
   */
  @Test
  public void appearTime() {
    IShape o = new Oval("r", ShapeTypes.Ellipse, 200, 200, 50, 50, new Color(1, 0, 0), 1,
        100);
    assertEquals(1, o.getAppearTime());
    o.setAppearTime(10);
    assertEquals(10, o.getAppearTime());
  }
  
  /**
   * get/set disappear time.
   */
  @Test
  public void disappearTime() {
    IShape o = new Oval("r", ShapeTypes.Ellipse, 200, 200, 50, 50, new Color(1, 0, 0), 1,
        100);
    assertEquals(100, o.getDisappearTime());
    o.setDisappearTime(120);
    assertEquals(120, o.getDisappearTime());
  }


}
