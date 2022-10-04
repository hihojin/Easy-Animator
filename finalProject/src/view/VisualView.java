package view;

import model.shapes.IShape;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * visual view class.
 * @author hyojinkwak
 *
 */
@SuppressWarnings("serial")
public class VisualView extends JFrame implements IView {
  private int speed;
  private String viewName;
  ArrayList<IShape> shapes;
  private PanelView panel;
  
  /**
   * visual view constructor.
   */
  public VisualView(ArrayList<IShape> shapes, int speed, int width, int height) {
    super("Visual View");
    this.setSize(1000, 1000); // jframe
    this.setLocation(0, 0);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.speed = speed;
    this.viewName = "visual";
    this.panel = new PanelView(shapes, width, height);
    this.add(panel);
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setPreferredSize(new Dimension(1000, 1000));
    this.shapes = shapes;
    
    this.setVisible(true);
    
  }
  
  @Override
  public void display(ArrayList<IShape> shapes) {
    this.panel.setShapes(shapes);
    this.panel.repaint();
  }

  @Override
  public String getViewName() {
    return this.viewName;
  }
  
  /**
   * getting speed. speed is needed in controller.
   * @return speed.
   */
  public int getSpeed() {
    return this.speed;
  }

}
