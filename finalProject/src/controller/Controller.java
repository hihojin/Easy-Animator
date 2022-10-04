package controller;

import model.animator.IEasyAnimator;
import view.IView;

/**
 * textview controller class.
 * @author hyojinkwak
 *
 */
public class Controller implements IController {
  private IView view;
  private IEasyAnimator model;
  
  /**
   * controller views and the model as argument and sets the view accordingly.
   * @param view the determined view from user to run
   */
  public Controller(IView view, IEasyAnimator model) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void run() {
    if (this.view.getViewName().equals("text")) {
      this.view.display(model.getShapes());
    }
    
    if (this.view.getViewName().equals("svg")) {
      this.view.display(model.getShapes());
    }
    
    if (this.view.getViewName().equals("visual")) {
      // need to keep working on this part.
      this.view.display(model.getAnimatedShapesByTime(0));
    }
    // playback view here
  }
}
