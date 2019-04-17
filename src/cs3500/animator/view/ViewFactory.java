package cs3500.animator.view;

import cs3500.animator.model.motion.AnimatorModel;


/**
 * The class ViewFactory, which utilizes the different types of views created under IView to return
 * a object of that type.
 */
public class ViewFactory {

  /**
   * The method getView parses a string and utilizes the different types of view created in IView to
   * return an object of that type.
   */
  public static IView getView(String v, AnimatorModel a) {
    if (v == null) {
      throw new IllegalArgumentException("Ask for a view!");
    }
    if (a == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    if (v.equalsIgnoreCase("SVG")) {
      return new SVGView(a);

    } else if (v.equalsIgnoreCase("TEXT")) {
      return new TView(a);
    } else if (v.equalsIgnoreCase("VISUAL") || v.equalsIgnoreCase("EDIT")) {
      return new GraphicView(a);
    } else if (v.equalsIgnoreCase("PROVIDER") || v.equalsIgnoreCase("EDIT")) {
      return new GraphicView(a);
    } else {
      throw new IllegalArgumentException("You must have made a spelling error-- invalid input");
    }
  }
}
