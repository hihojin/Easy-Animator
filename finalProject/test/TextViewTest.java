import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import model.animator.EasyAnimatorModel;
import model.animator.IEasyAnimator;
import org.junit.Test;
import util.AnimationBuilderImpl;
import util.AnimationReader;
import view.TextView;

import java.io.FileNotFoundException;
import java.io.FileReader;


/**
 * text view class test.
 * @author hyojinkwak
 *
 */
public class TextViewTest {

  /**
   * constructing text view and testing small demo output.
   */
  @Test
  public void testSmallDemo() {
    new EasyAnimatorModel();
    AnimationBuilderImpl builder = new AnimationBuilderImpl(new EasyAnimatorModel());
    
    StringBuilder s = new StringBuilder();
    try {
      IEasyAnimator model = AnimationReader.parseFile(new FileReader("smalldemo.txt"), builder);
      TextView text = new TextView(1, s, model.getShapes(), model.getAnimations());
      text.display(model.getShapes());
      
      assertEquals("Create (255,0 0) rectangle R with corner at (200,200),"
          + " width 50 and height 100\n"
          + "Create (0,0 255) ellipse C with center at (440,70), radius 60 and 30\n"
          + "\n"
          + "R appears at time t=1 and disappears at time t=100\n"
          + "C appears at time t=6 and disappears at time t=100\n"
          + "\n"
          + "R moves from (200,200) to (300,300) from t=10 to t=50\n"
          + "C moves from (440,70) to (440,250) from t=20 to t=50\n"
          + "C moves from (440,250) to (440,370) from t=50 to t=70\n"
          + "C changes from rgb(0,0,255) to rgb(0,170,85) from t=50 to t=70\n"
          + "R changes(width, height) from (50,100) to (25,100) from t=51 to t=70\n"
          + "R moves from (300,300) to (200,200) from t=70 to t=100\n"
          + "C changes from rgb(0,170,85) to rgb(0,255,0) from t=70 to t=80\n", s.toString());
    } catch (Exception e) {
      fail("failed");
    }
    
    // speed change
    new EasyAnimatorModel();
    builder = new AnimationBuilderImpl(new EasyAnimatorModel());
    
    s = new StringBuilder();
    try {
      IEasyAnimator model = AnimationReader.parseFile(new FileReader("smalldemo.txt"), builder);
      TextView text = new TextView(10, s, model.getShapes(), model.getAnimations());
      text.display(model.getShapes());
      
      assertEquals("Create (255,0 0) rectangle R with corner at (200,200),"
          + " width 50 and height 100\n"
          + "Create (0,0 255) ellipse C with center at (440,70), radius 60 and 30\n"
          + "\n"
          + "R appears at time t=0 and disappears at time t=10\n"
          + "C appears at time t=0 and disappears at time t=10\n"
          + "\n"
          + "R moves from (200,200) to (300,300) from t=1 to t=5\n"
          + "C moves from (440,70) to (440,250) from t=2 to t=5\n"
          + "C moves from (440,250) to (440,370) from t=5 to t=7\n"
          + "C changes from rgb(0,0,255) to rgb(0,170,85) from t=5 to t=7\n"
          + "R changes(width, height) from (50,100) to (25,100) from t=5 to t=7\n"
          + "R moves from (300,300) to (200,200) from t=7 to t=10\n"
          + "C changes from rgb(0,170,85) to rgb(0,255,0) from t=7 to t=8\n"
          + "", s.toString());
    } catch (Exception e) {
      fail("failed");
    }
  }
  
  /**
   * testing for view name.
   */
  @Test
  public void getViewName() {
    new EasyAnimatorModel();
    AnimationBuilderImpl builder = new AnimationBuilderImpl(new EasyAnimatorModel());
    
    IEasyAnimator model;
    try {
      model = AnimationReader.parseFile(new FileReader("smalldemo.txt"), builder);
      TextView text = new TextView(1, System.out, model.getShapes(), model.getAnimations());
      
      assertEquals("text", text.getViewName());
    } catch (FileNotFoundException e) {
      fail("failed");
    }
    
  }

}
