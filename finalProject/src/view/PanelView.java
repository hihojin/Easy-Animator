package view;

import model.shapes.IShape;
import model.shapes.ShapeTypes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;


/**
 * panel has paintcomponent, and sets the shape list.
 * draws shapes.
 * @author hyojinkwak
 */
@SuppressWarnings("serial")
public class PanelView extends JPanel {
  private ArrayList<IShape> shapes;

  /**
   * constructor of panel sets values.
   */
  public PanelView(ArrayList<IShape> shapes, int width, int height) {
    super(true);
    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(width,height));
    this.setLocation(0, 50);
    this.shapes = shapes;
  }
  
  @Override
  public void paintComponent(Graphics g) {
    
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    
    for (int i = 0; i < this.shapes.size(); i++) {
      
      g2.setColor(new Color(this.shapes.get(i).getColor().getRed(),
          this.shapes.get(i).getColor().getGreen(),
          this.shapes.get(i).getColor().getBlue()));
      
      if (this.shapes.get(i).getType().toString().equals(ShapeTypes.Ellipse.toString())) {
        g2.fillOval(this.shapes.get(i).getXPosition(), this.shapes.get(i).getYPosition(),
            this.shapes.get(i).getXSize(), this.shapes.get(i).getYSize());
      }
      else if (this.shapes.get(i).getType().toString().equals(ShapeTypes.RECTANGLE.toString())) {
        g2.fillRect(this.shapes.get(i).getXPosition(), this.shapes.get(i).getYPosition(),
            this.shapes.get(i).getXSize(), this.shapes.get(i).getYSize());
      }
    }
  }
  
  /**
   * sets the shape list for drawing shapes.
   * @param shapes shapes list
   */
  public void setShapes(ArrayList<IShape> shapes) {
    this.shapes = shapes;
  }

}
