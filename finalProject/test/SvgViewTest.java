import static org.junit.Assert.assertEquals;

import model.animator.EasyAnimatorModel;
import model.animator.IEasyAnimator;
import org.junit.Test;
import util.AnimationBuilderImpl;
import util.AnimationReader;
import view.SvgView;

import java.io.FileReader;

/**
 * testing svg view's public methods.
 * 
 * @author hyojinkwak
 *
 */
public class SvgViewTest {

  /**
   * testing svg view small demo's svg output.
   */
  @Test
  public void testSvgOutput() {
    new EasyAnimatorModel();
    AnimationBuilderImpl builder = new AnimationBuilderImpl(new EasyAnimatorModel());

    StringBuilder s = new StringBuilder();
    try {
      IEasyAnimator model = AnimationReader.parseFile(new FileReader("smalldemo.txt"), builder);
      SvgView svg = new SvgView(1, s, model.getShapes(), model.getAnimations(),
          model.getCanvasWidth(), model.getCanvasHeight(), model.getAnimationEndTime());
      assertEquals(360, model.getCanvasWidth());
      svg.display(model.getShapes());
      
      assertEquals(
          "<svg width=\"560\" height=\"560\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" >\n"
          + "\n"
          + "<rect>\n"
          + "<animate id=\"base\" begin=\"0;base.end\""
          + " dur=\"100000ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
          + "</rect>\n"
          + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\""
          + " height=\"100\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
          + "\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+1000ms\""
          + " dur=\"1.0ms\" attributeName=\"visibility\" from=\"hidden\""
          + " to=\"visible\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+10000ms\""
          + " dur=\"40000ms\" attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+10000ms\""
          + " dur=\"40000ms\" attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-10ms\" dur=\"1ms\""
          + " attributeName=\"x\" to=\"200\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-10ms\" dur=\"1ms\""
          + " attributeName=\"y\" to=\"200\" fill=\"freeze\" />\n"
          + "\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+51000ms\" dur=\"19000ms\""
          + " attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+51000ms\" dur=\"19000ms\""
          + " attributeName=\"height\" from=\"100\" to=\"100\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-51ms\" dur=\"1ms\""
          + " attributeName=\"width\" to=\"50\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-51ms\" dur=\"1ms\""
          + " attributeName=\"height\" to=\"100\" fill=\"freeze\" />\n"
          + "\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+70000ms\" dur=\"30000ms\""
          + " attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+70000ms\" dur=\"30000ms\""
          + " attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-70ms\" dur=\"1ms\""
          + " attributeName=\"x\" to=\"300\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-70ms\" dur=\"1ms\""
          + " attributeName=\"y\" to=\"300\" fill=\"freeze\" />\n"
          + "\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+100000ms\" dur=\"1.0ms\""
          + " attributeName=\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
          + "</rect>\n"
          + "\n"
          + "<ellipse id=\"C\" cx=\"440\" cy=\"70\" rx=\"60\" ry=\"30\""
          + " fill=\"rgb(0,0,255)\" visibility=\"hidden\" >\n"
          + "\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+6000ms\""
          + " dur=\"1.0ms\" attributeName=\"visibility\" from=\"hidden\""
          + " to=\"visible\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+20000ms\""
          + " dur=\"30000ms\" attributeName=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+20000ms\""
          + " dur=\"30000ms\" attributeName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-20ms\" dur=\"1ms\""
          + " attributeName=\"cx\" to=\"440\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-20ms\" dur=\"1ms\""
          + " attributeName=\"cy\" to=\"70\" fill=\"freeze\" />\n"
          + "\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+50000ms\" dur=\"20000ms\""
          + " attributeName=\"cx\" from=\"440\" to=\"440\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+50000ms\" dur=\"20000ms\""
          + " attributeName=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-50ms\" dur=\"1ms\""
          + " attributeName=\"cx\" to=\"440\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-50ms\" dur=\"1ms\""
          + " attributeName=\"cy\" to=\"250\" fill=\"freeze\" />\n"
          + "\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+50000ms\" dur=\"20000ms\""
          + " attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\""
          + " fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-50ms\" dur=\"1ms\""
          + " attributeName=\"fill\""
          + " to=\"rgb(0,0,255)\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+70000ms\" dur=\"10000ms\""
          + " attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\""
          + " fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end-70ms\" dur=\"1ms\""
          + " attributeName=\"fill\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+100000ms\" dur=\"1.0ms\""
          + " attributeName=\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n"
          + "</ellipse>\n"
          + "\n"
          + "</svg>\n", s.toString());
    } catch (Exception e) {
      // nothing
    }

  }
  
  /**
   * svg view name test.
   */
  @Test
  public void testViewName() {
    new EasyAnimatorModel();
    AnimationBuilderImpl builder = new AnimationBuilderImpl(new EasyAnimatorModel());

    StringBuilder s = new StringBuilder();
    try {
      IEasyAnimator model = AnimationReader.parseFile(new FileReader("smalldemo.txt"), builder);
      SvgView svg = new SvgView(1, s, model.getShapes(), model.getAnimations(),
          model.getAnimationEndTime(), model.getCanvasWidth(), model.getCanvasHeight());
      assertEquals("svg", svg.getViewName());
    } catch (Exception e) {
      // nothing
    }
  }

}
