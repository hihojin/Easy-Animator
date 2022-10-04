package controller;

import model.animator.EasyAnimatorModel;
import model.animator.IEasyAnimator;
import util.AnimationBuilderImpl;
import util.AnimationReader;
import view.Factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * this is a main class setting model, calls appropriate view and delegates to
 * controller to run the animation. final class here as we don't want this class
 * to have any subclasses.
 * 
 * @author hyojinkwak
 *
 */
public final class EasyAnimator {

  /**
   * takes command line that involves input filename (required), view
   * name(required), outputfile name (optional), speed (optional) and calls
   * controller with a set model for the view. view type is determined by the view
   * input and factory view class in main. outputfilename is empty string(defualt)
   * speed if 1 by defualt
   * 
   * @param args String args command line.
   */
  public static void main(String[] args) {
    String input = "";
    String output = "";
    String view = "";
    int speed = 1;

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        try {
          if (args[i + 1].endsWith(".txt")) {
            input = args[i + 1];
          } else {
            input = args[i + 1] + ".txt";
          } 
        } catch (Exception e) {
          JOptionPane.showMessageDialog(new JFrame(), "please specify input file");
          System.exit(0);
        }
        
      } else if (args[i].equals("-view")) {
        try {
          if (args[i + 1].equals("text") || args[i + 1].equals("svg")
              || args[i + 1].equals("visual") || args[i + 1].equals("playback")) {
            view = args[i + 1];
          }
        }
        catch (Exception e) {
          JOptionPane.showMessageDialog(new JFrame(), "please specify view name");
          System.exit(0);
        }
      } else if (args[i].equals("-out")) {
        try {
          if (args[i + 1].endsWith(".txt") || args[i + 1].endsWith(".svg")) {
            output = args[i + 1];
          } 
        } catch (Exception e) {
          JOptionPane.showMessageDialog(new JFrame(), "please specify output filename");
          System.exit(0);
        } 

      } else if (args[i].equals("-speed")) {
        try {
          speed = Integer.parseInt(args[i + 1]);
        }
        catch (Exception e) {
          JOptionPane.showMessageDialog(new JFrame(), "speed should be there if you wanted");
          System.exit(0);
        }
        
      }
    }
    // handling invalid arguments
    if (input.equals("")) {
      JOptionPane.showMessageDialog(new JFrame(), "input can't be empty");
      System.exit(0);
    }
    if (view.equals("")) {
      JOptionPane.showMessageDialog(new JFrame(), "view can't be empty");
      System.exit(0);
    }
    if (speed < 1) {
      JOptionPane.showMessageDialog(new JFrame(), "speed starts from 1");
      System.exit(0);
    }

    // initialize builder
    AnimationBuilderImpl builder = new AnimationBuilderImpl(new EasyAnimatorModel());

    // create model
    try {
      IEasyAnimator model = AnimationReader.parseFile(new FileReader(input), builder);

      // handling output either system.out (in factory view) or to file
      // if ouput is "": 2 cases: for text&svg view -> outputting to console
      // for visual views -> output is not needed so go to factory view and call the
      // class there
      try {
        if (!output.equals("")) {
          FileWriter file = new FileWriter(output);
          new Controller(Factory.viewFactory(view, speed, file, model.getCanvasWidth(),
              model.getCanvasHeight(), model.getAnimations(), 
              model.getShapes(), model.getAnimationEndTime()), model).run();
          file.close();
        } else {
          new Controller(Factory.viewFactory(view, speed, null, model.getCanvasWidth(),
              model.getCanvasHeight(), model.getAnimations(), 
              model.getShapes(), model.getAnimationEndTime()), model).run();
        }

      } catch (IOException e) {
        JOptionPane.showMessageDialog(new JFrame(), "there was a problem writing to the file");
      }

    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(new JFrame(), "such file doesn't exist");
      System.exit(0); // need to exit the program when exception is thrown. or, program doesn't end
    }

  }

}
