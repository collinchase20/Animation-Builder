package cs3500.animator;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.model.motion.IAnimator;
import cs3500.animator.model.motion.IAnimatorImpl;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.EditView;
import cs3500.animator.view.GraphicView;
import cs3500.animator.view.IEditor;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;

/**
 * Main class Excellence. Acts as a de facto controller in order to parse command line inputs and
 * build the view and model.
 */
public final class Excellence {

  /**
   * Parses the command line inputs, and builds the model based on the input file specified. We then
   * pass this model to the view specified by the user, and output the correct output.
   *
   * @param args the command line inputs
   */
  public static void main(String[] args) {

    String fileName = null;
    String viewType = null;
    String outputFile = null;
    String speed = null;

    for (int i = 0; i < args.length; i += 2) {
      switch (args[i]) {
        case "-in":
          fileName = args[i + 1];
          break;
        case "-view":
          viewType = args[i + 1];
          break;
        case "-out":
          outputFile = args[i + 1];
          break;
        case "-speed":
          speed = args[i + 1];
          break;
        default:
          break;
      }
    }

    FileReader f = null;

    try {
      f = new FileReader(fileName); //will take fileName
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    IAnimator a = AnimationReader.parseFile(f, new IAnimatorImpl.Builder());
    IView visual = ViewFactory.getView(viewType, a);

    StringBuilder output = new StringBuilder();
    if (viewType.equalsIgnoreCase("SVG") || viewType.equalsIgnoreCase("TEXT")) {
      output.append(visual.displayView());
    }

    if (outputFile == null) {
      System.out.print(output);
    } else {
      try {
        FileWriter fw = new FileWriter(outputFile);
        fw.write(output.toString());
        fw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    if (speed != null) {
      visual.setSpeed(Integer.parseInt(speed));
    }

    switch (viewType) {
      case "text":
        visual.displayView();
        break;
      case "svg":
        visual.displayView();
        break;
      case "visual":
        visual.startVisualView();
        break;
      case "edit":
        IEditor editor;
        if (visual instanceof GraphicView) {
          editor = new EditView((GraphicView) visual);
        }
        else {
          throw new IllegalArgumentException("Can't edit a non-visual view");
        }
        IController controller = new Controller(editor,a);
        controller.beginController();
        break;
      default:
        throw new IllegalArgumentException("Invalid view type");
    }
  }
}
