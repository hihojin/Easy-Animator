package view;

import model.shapes.IShape;

import java.util.ArrayList;

/**
 * interface of textview has functions such as display.
 * @author hyojinkwak
 *
 */
public interface IView {
  /**
   * display animation in plain text.
   * when output file is given, should display the result in the output text file.
   * If not, show it to System.out.
   */
  void display(ArrayList<IShape> shapes);
  
  /**
   * tells which view this is.
   * @return string name of the view.
   */
  String getViewName();

}
